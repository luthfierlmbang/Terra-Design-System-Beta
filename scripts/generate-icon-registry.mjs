import { readdir, readFile, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const repoRoot = path.resolve(__dirname, '..');
const sourceDir = path.join(repoRoot, 'packages/web-ui/src/icons/figma-source');
const outputFile = path.join(repoRoot, 'packages/web-ui/src/components/Icon/iconRegistry.ts');

const categoryOrder = ['action', 'navigation', 'status', 'state', 'info', 'domain', 'shapes', 'system', 'priority', 'menu'];
const aliasNameMap = {
  'kebab-horizontal-2': 'kebab-vertical',
};

// Categories whose original colors are meaningful and must be preserved
// (i.e. not flattened to currentColor).
const coloredCategories = new Set(['priority', 'menu']);

function fileNameToEntry(fileName) {
  const match = fileName.match(/^([a-z-]+)__([a-z0-9-]+)\.(svg|png)$/i);

  if (!match) {
    return null;
  }

  const rawName = match[2];

  return {
    category: match[1],
    name: aliasNameMap[rawName] ?? rawName,
    extension: match[3].toLowerCase(),
  };
}

function stripWrapper(svg) {
  const viewBoxMatch = svg.match(/viewBox\s*=\s*['\"]([^'\"]+)['\"]/i);
  const inner = svg
    .replace(/^[\s\S]*?<svg[^>]*>/i, '')
    .replace(/<\/svg>[\s\S]*$/i, '')
    .trim();

  return {
    inner,
    viewBox: viewBoxMatch?.[1] ?? '0 0 24 24',
  };
}

function normalizeSvg(svg, { preserveColors = false } = {}) {
  const compact = svg
    .replace(/\r\n/g, '\n')
    .replace(/<!--([\s\S]*?)-->/g, '')
    .replace(/>\s+</g, '><')
    .trim();
  const { inner, viewBox } = stripWrapper(compact);

  let normalized = `<svg viewBox="${viewBox}" fill="none" xmlns="http://www.w3.org/2000/svg">${inner}</svg>`
    .replace(/\s(width|height)=['\"][^'\"]*['\"]/gi, '')
    .replace(/\s(xmlns:xlink|xmlns:serif)=['\"][^'\"]*['\"]/gi, '');

  if (preserveColors) {
    // Figma exports literal colors inside CSS variable fallbacks, e.g.
    // fill="var(--fill-0, #EF3636)". Unwrap to the plain literal color so the
    // icon keeps its semantic colors regardless of the host `color`.
    return normalized.replace(/var\(--[a-z0-9-]+,\s*([^)]+)\)/gi, '$1');
  }

  return normalized
    .replace(/\s(fill|stroke)=['\"](?!none|currentColor)([^'\"]+)['\"]/gi, ' $1="currentColor"')
    .replace(/\s(stop-color)=['\"]([^'\"]+)['\"]/gi, ' stop-color="currentColor"')
    .replace(/\s(style)=['\"]([^'\"]*)['\"]/gi, (_match, attr, value) => {
      const normalizedStyle = value
        .replace(/fill\s*:\s*(?!none|currentColor)[^;]+/gi, 'fill:currentColor')
        .replace(/stroke\s*:\s*(?!none|currentColor)[^;]+/gi, 'stroke:currentColor');
      return ` ${attr}="${normalizedStyle}"`;
    });
}

// Wrap a raster image (PNG) as an inline SVG with an embedded data URI so it
// flows through the same string-based registry + rendering path as vectors.
function rasterToSvg(buffer) {
  const base64 = buffer.toString('base64');
  return `<svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"><image href="data:image/png;base64,${base64}" width="48" height="48"/></svg>`;
}

function compareEntries(a, b) {
  const categoryDiff = categoryOrder.indexOf(a.category) - categoryOrder.indexOf(b.category);

  if (categoryDiff !== 0) {
    return categoryDiff;
  }

  return a.name.localeCompare(b.name);
}

function createAliasEntries(entries) {
  const aliasEntries = [];

  for (const [aliasName, targetName] of Object.entries(aliasNameMap)) {
    const targetEntry = entries.find((entry) => entry.name === targetName);

    if (!targetEntry) {
      continue;
    }

    aliasEntries.push({
      ...targetEntry,
      name: aliasName,
    });
  }

  return aliasEntries;
}

function renderRegistry(entries) {
  const renderedEntries = entries
    .map(
      (entry) => `  {\n    category: '${entry.category}',\n    name: '${entry.name}',\n    svg: ${JSON.stringify(entry.svg)},\n  },`,
    )
    .join('\n');

  return `import type { TerraIconDefinition } from './Icon.types';\n\nexport const terraIconRegistry = [\n${renderedEntries}\n] as const satisfies readonly TerraIconDefinition[];\n`;
}

async function main() {
  const files = (await readdir(sourceDir)).filter(
    (file) => file.endsWith('.svg') || file.endsWith('.png'),
  );
  const entries = [];

  for (const file of files) {
    const parsed = fileNameToEntry(file);

    if (!parsed) {
      console.warn(`Skipping ${file}: expected <category>__<name>.(svg|png)`);
      continue;
    }

    const { category, name, extension } = parsed;
    let svg;

    if (extension === 'png') {
      const buffer = await readFile(path.join(sourceDir, file));
      svg = rasterToSvg(buffer);
    } else {
      const source = await readFile(path.join(sourceDir, file), 'utf8');
      svg = normalizeSvg(source, { preserveColors: coloredCategories.has(category) });
    }

    entries.push({ category, name, svg });
  }

  entries.push(...createAliasEntries(entries));
  entries.sort(compareEntries);
  await writeFile(outputFile, renderRegistry(entries));
  console.log(`Generated ${entries.length} icon entries in ${path.relative(repoRoot, outputFile)}`);
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
