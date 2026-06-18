const COLLECTIONS = {
  primitive: "TERRA Primitive",
  semantic: "TERRA Semantic Color",
  component: "TERRA Component Color",
};

const NODE_IDS = {
  buttonSet: "7:1081",
  fabSticky: "120:1170",
  overlays: ["97:3080", "982:14"],
  pageIdentifier: "505:35",
};

const primitiveTokens = [
  ["orange/50", "#FEF5ED"],
  ["orange/100", "#FDE8D6"],
  ["orange/200", "#FBCFA8"],
  ["orange/300", "#F8B67B"],
  ["orange/400", "#F69C4D"],
  ["orange/500", "#F4831F"],
  ["orange/600", "#D2711B"],
  ["orange/700", "#B15F16"],
  ["orange/800", "#8F4D12"],
  ["orange/900", "#6E3B0E"],
  ["teal/50", "#EBF4F4"],
  ["teal/100", "#D9EAEB"],
  ["teal/200", "#B5D6D8"],
  ["teal/300", "#91C3C5"],
  ["teal/400", "#6DAFB2"],
  ["teal/500", "#4A9B9F"],
  ["teal/600", "#26888C"],
  ["teal/700", "#027479"],
  ["teal/800", "#015458"],
  ["teal/900", "#013436"],
  ["yellow/50", "#FEF9ED"],
  ["yellow/100", "#FEF0D5"],
  ["yellow/200", "#FCE0A6"],
  ["yellow/300", "#FACF77"],
  ["yellow/400", "#F9BF48"],
  ["yellow/500", "#F7AE19"],
  ["yellow/600", "#D59616"],
  ["yellow/700", "#B37E12"],
  ["yellow/800", "#91660F"],
  ["yellow/900", "#6F4E0B"],
  ["red/50", "#FEEFEF"],
  ["red/100", "#FCDADA"],
  ["red/200", "#F9B1B1"],
  ["red/300", "#F68888"],
  ["red/400", "#F25F5F"],
  ["red/500", "#EF3636"],
  ["red/600", "#CE2F2F"],
  ["red/700", "#AD2727"],
  ["red/800", "#8C2020"],
  ["red/900", "#6C1818"],
  ["neutral/10", "#FFFFFF"],
  ["neutral/20", "#EBF0F4"],
  ["neutral/30", "#EDEDED"],
  ["neutral/40", "#DADADA"],
  ["neutral/50", "#CCCCCC"],
  ["neutral/60", "#999999"],
  ["neutral/70", "#7A7D7F"],
  ["neutral/80", "#555555"],
  ["neutral/90", "#333333"],
  ["neutral/100", "#000000"],
  ["blue/50", "#F5FAFE"],
  ["blue/100", "#EBF5FD"],
  ["blue/200", "#D8EBFA"],
  ["blue/300", "#BFE0F6"],
  ["pink/50", "#FFF8F8"],
  ["pink/100", "#FDEDED"],
  ["pink/200", "#FADADA"],
  ["black/alpha-50", "rgba(0,0,0,0.5)"],
  ["black/alpha-12", "rgba(0,0,0,0.12)"],
];

const legacyPrimitiveNames = {
  "primitive/neutral/10": "neutral/10",
  "primitive/neutral/20": "neutral/20",
  "primitive/neutral/30": "neutral/30",
  "primitive/neutral/40": "neutral/40",
  "primitive/neutral/50": "neutral/50",
  "primitive/neutral/60": "neutral/60",
  "primitive/neutral/70": "neutral/70",
  "primitive/neutral/80": "neutral/80",
  "primitive/neutral/90": "neutral/90",
  "primitive/neutral/100": "neutral/100",
  "primitive/surface/blue-50": "blue/50",
  "primitive/surface/pink-50": "pink/50",
  "primitive/overlay/black-50": "black/alpha-50",
  "primitive/shadow/black-12": "black/alpha-12",
};

const legacyStops = ["50", "100", "200", "300", "400", "500", "600", "700", "800", "900"];
for (const stop of legacyStops) {
  legacyPrimitiveNames[`primitive/orange/orange-${stop}`] = `orange/${stop}`;
  legacyPrimitiveNames[`primitive/brand/primary-orange-${stop}`] = `orange/${stop}`;
  legacyPrimitiveNames[`primitive/teal/teal-${stop}`] = `teal/${stop}`;
  legacyPrimitiveNames[`primitive/brand/secondary-teal-${stop}`] = `teal/${stop}`;
  legacyPrimitiveNames[`primitive/yellow/yellow-${stop}`] = `yellow/${stop}`;
  legacyPrimitiveNames[`primitive/status/warning-yellow-${stop}`] = `yellow/${stop}`;
  legacyPrimitiveNames[`primitive/red/red-${stop}`] = `red/${stop}`;
  legacyPrimitiveNames[`primitive/status/danger-red-${stop}`] = `red/${stop}`;
}
for (const stop of ["10", "20", "30", "40", "50", "60", "70", "80", "90", "100"]) {
  legacyPrimitiveNames[`primitive/neutral/neutral-${stop}`] = `neutral/${stop}`;
}
for (const stop of ["50", "100", "200", "300"]) {
  legacyPrimitiveNames[`primitive/blue/blue-${stop}`] = `blue/${stop}`;
  legacyPrimitiveNames[`primitive/surface/surface-blue-${stop}`] = `blue/${stop}`;
}
for (const stop of ["50", "100", "200"]) {
  legacyPrimitiveNames[`primitive/pink/pink-${stop}`] = `pink/${stop}`;
  legacyPrimitiveNames[`primitive/surface/surface-pink-${stop}`] = `pink/${stop}`;
}
legacyPrimitiveNames["primitive/black/black-alpha-50"] = "black/alpha-50";
legacyPrimitiveNames["primitive/black/black-alpha-12"] = "black/alpha-12";
legacyPrimitiveNames["primitive/overlay/overlay-black-50"] = "black/alpha-50";
legacyPrimitiveNames["primitive/shadow/shadow-black-12"] = "black/alpha-12";

const semanticTokens = [
  ["color/bg/default", "neutral/10", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/canvas/default", "blue/50", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/thumbnail/default", "pink/50", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/surface/primary/default", "neutral/10", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/surface/secondary/default", "neutral/30", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/surface/secondary/hover", "neutral/20", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/fill/primary/default", "orange/500", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/fill/primary/active", "orange/500", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/fill/secondary/default", "teal/700", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/fill/danger/default", "red/500", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/fill/warning/default", "yellow/500", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/disabled/default", "neutral/50", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/bg/overlay/default", "black/alpha-50", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/text/primary/default", "neutral/90", ["TEXT_FILL"]],
  ["color/text/primary/disabled", "neutral/50", ["TEXT_FILL"]],
  ["color/text/secondary/default", "neutral/80", ["TEXT_FILL"]],
  ["color/text/inverse/default", "neutral/10", ["TEXT_FILL"]],
  ["color/text/action/primary/default", "teal/700", ["TEXT_FILL"]],
  ["color/text/action/secondary/default", "orange/500", ["TEXT_FILL"]],
  ["color/text/action/danger/default", "red/500", ["TEXT_FILL"]],
  ["color/border/default", "neutral/30", ["STROKE_COLOR"]],
  ["color/border/secondary/default", "neutral/50", ["STROKE_COLOR"]],
  ["color/border/tertiary/default", "neutral/40", ["STROKE_COLOR"]],
  ["color/border/action/primary/default", "teal/700", ["STROKE_COLOR"]],
  ["color/border/action/secondary/default", "orange/500", ["STROKE_COLOR"]],
  ["color/border/disabled/default", "neutral/50", ["STROKE_COLOR"]],
  ["color/icon/primary/default", "neutral/90", ["ALL_FILLS"]],
  ["color/icon/secondary/default", "neutral/80", ["ALL_FILLS"]],
  ["color/icon/secondary/hover", "teal/700", ["ALL_FILLS"]],
  ["color/icon/action/primary/default", "teal/700", ["ALL_FILLS"]],
  ["color/icon/action/secondary/default", "orange/500", ["ALL_FILLS"]],
  ["color/icon/action/danger/default", "red/500", ["ALL_FILLS"]],
  ["color/icon/inverse/default", "neutral/10", ["ALL_FILLS"]],
  ["color/icon/disabled/default", "neutral/50", ["ALL_FILLS"]],
];

const componentTokens = [
  ["color/button/primary/bg/default", "color/bg/fill/secondary/default", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/button/primary/text/default", "color/text/inverse/default", ["TEXT_FILL"]],
  ["color/button/secondary/bg/default", "color/bg/fill/primary/default", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/button/secondary/text/default", "color/text/inverse/default", ["TEXT_FILL"]],
  ["color/button/outlined-primary/border/default", "color/border/action/primary/default", ["STROKE_COLOR"]],
  ["color/button/outlined-primary/text/default", "color/text/action/primary/default", ["TEXT_FILL"]],
  ["color/button/outlined-secondary/border/default", "color/border/action/secondary/default", ["STROKE_COLOR"]],
  ["color/button/outlined-secondary/text/default", "color/text/action/secondary/default", ["TEXT_FILL"]],
  ["color/button/danger/bg/default", "color/bg/fill/danger/default", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/button/danger/text/default", "color/text/inverse/default", ["TEXT_FILL"]],
  ["color/button/disabled/bg/default", "color/bg/disabled/default", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/button/disabled/text/default", "color/text/inverse/default", ["TEXT_FILL"]],
  ["color/button/text-button/text/default", "color/text/action/primary/default", ["TEXT_FILL"]],
  ["color/button/text-button/text/disabled", "color/text/primary/disabled", ["TEXT_FILL"]],
  ["color/fab/sticky/bg/default", "color/bg/surface/primary/default", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/overlay/scrim/default", "color/bg/overlay/default", ["FRAME_FILL", "SHAPE_FILL"]],
  ["color/page-identifier/text/default", "color/text/inverse/default", ["TEXT_FILL"]],
];

function rgbaFromValue(value) {
  if (value.startsWith("#")) {
    const hex = value.slice(1);
    const r = parseInt(hex.slice(0, 2), 16) / 255;
    const g = parseInt(hex.slice(2, 4), 16) / 255;
    const b = parseInt(hex.slice(4, 6), 16) / 255;
    return { r, g, b, a: 1 };
  }
  const match = value.match(/^rgba\(([^,]+),([^,]+),([^,]+),([^)]+)\)$/);
  if (!match) throw new Error(`Unsupported color value: ${value}`);
  return {
    r: Number(match[1].trim()) / 255,
    g: Number(match[2].trim()) / 255,
    b: Number(match[3].trim()) / 255,
    a: Number(match[4].trim()),
  };
}

function cssName(variableName, prefix) {
  const body = variableName.replace(/\//g, "-");
  return prefix ? `--${prefix}-${body}` : `--${body}`;
}

function getOrCreateCollection(name) {
  const found = figma.variables.getLocalVariableCollections().find((collection) => collection.name === name);
  return found || figma.variables.createVariableCollection(name);
}

function getModeId(collection) {
  return collection.modes[0].modeId;
}

function getVariable(collection, name) {
  return figma.variables
    .getLocalVariables("COLOR")
    .find((variable) => variable.variableCollectionId === collection.id && variable.name === name);
}

function migrateLegacyVariableName(collection, name) {
  const existing = getVariable(collection, name);
  if (existing) return existing;
  for (const legacyName in legacyPrimitiveNames) {
    if (legacyPrimitiveNames[legacyName] !== name) continue;
    const legacyVariable = getVariable(collection, legacyName);
    if (legacyVariable) {
      legacyVariable.name = name;
      return legacyVariable;
    }
  }
  return null;
}

function getOrCreateColorVariable(collection, name, scopes, codePrefix) {
  const variable = migrateLegacyVariableName(collection, name) || figma.variables.createVariable(name, collection, "COLOR");
  variable.scopes = scopes || [];
  variable.setVariableCodeSyntax("WEB", cssName(name, codePrefix));
  return variable;
}

function setPrimitiveValue(variable, collection, value) {
  variable.setValueForMode(getModeId(collection), rgbaFromValue(value));
}

function setAliasValue(variable, collection, targetVariable) {
  variable.setValueForMode(getModeId(collection), figma.variables.createVariableAlias(targetVariable));
}

function bindPaints(paints, variable) {
  if (!Array.isArray(paints)) return paints;
  return paints.map((paint) => {
    if (paint.type !== "SOLID") return paint;
    return figma.variables.setBoundVariableForPaint(paint, "color", variable);
  });
}

function colorKeyFromRgba(rgba) {
  const r = Math.round(rgba.r * 255);
  const g = Math.round(rgba.g * 255);
  const b = Math.round(rgba.b * 255);
  const a = rgba.a === undefined ? 1 : rgba.a;
  return `${r},${g},${b},${Math.round(a * 1000) / 1000}`;
}

function colorKeyFromPaint(paint) {
  if (!paint || paint.type !== "SOLID") return "";
  return colorKeyFromRgba({
    r: paint.color.r,
    g: paint.color.g,
    b: paint.color.b,
    a: paint.opacity === undefined ? 1 : paint.opacity,
  });
}

function colorKeyFromTokenValue(value) {
  return colorKeyFromRgba(rgbaFromValue(value));
}

function isIconLike(node) {
  const name = (node.name || "").toLowerCase();
  return (
    name.indexOf("icon") >= 0 ||
    name.indexOf("placeholder") >= 0 ||
    node.type === "VECTOR" ||
    node.type === "BOOLEAN_OPERATION" ||
    node.type === "STAR" ||
    node.type === "REGULAR_POLYGON"
  );
}

function pickGlobalVariableForPaint(node, field, paint, colorLookup, vars) {
  const primitiveName = colorLookup[colorKeyFromPaint(paint)];
  if (!primitiveName) return null;
  if (field === "stroke") {
    if (primitiveName === "teal/700") return vars["color/border/action/primary/default"];
    if (primitiveName === "orange/500") return vars["color/border/action/secondary/default"];
    if (primitiveName === "neutral/50") return vars["color/border/secondary/default"];
    if (primitiveName === "neutral/40") return vars["color/border/tertiary/default"];
    if (primitiveName === "neutral/30") return vars["color/border/default"];
    return null;
  }
  if (node.type === "TEXT") {
    if (primitiveName === "neutral/10") return vars["color/text/inverse/default"];
    if (primitiveName === "neutral/90") return vars["color/text/primary/default"];
    if (primitiveName === "neutral/80") return vars["color/text/secondary/default"];
    if (primitiveName === "neutral/50") return vars["color/text/primary/disabled"];
    if (primitiveName === "teal/700") return vars["color/text/action/primary/default"];
    if (primitiveName === "orange/500") return vars["color/text/action/secondary/default"];
    if (primitiveName === "red/500") return vars["color/text/action/danger/default"];
    return null;
  }
  if (isIconLike(node)) {
    if (primitiveName === "neutral/10") return vars["color/icon/inverse/default"];
    if (primitiveName === "neutral/90") return vars["color/icon/primary/default"];
    if (primitiveName === "neutral/80") return vars["color/icon/secondary/default"];
    if (primitiveName === "neutral/50") return vars["color/icon/disabled/default"];
    if (primitiveName === "teal/700") return vars["color/icon/action/primary/default"];
    if (primitiveName === "orange/500") return vars["color/icon/action/secondary/default"];
    if (primitiveName === "red/500") return vars["color/icon/action/danger/default"];
    return null;
  }
  if (primitiveName === "neutral/10") return vars["color/bg/surface/primary/default"];
  if (primitiveName === "neutral/20") return vars["color/bg/surface/secondary/hover"];
  if (primitiveName === "neutral/30") return vars["color/bg/surface/secondary/default"];
  if (primitiveName === "neutral/50") return vars["color/bg/disabled/default"];
  if (primitiveName === "teal/700") return vars["color/bg/fill/secondary/default"];
  if (primitiveName === "orange/500") return vars["color/bg/fill/primary/default"];
  if (primitiveName === "red/500") return vars["color/bg/fill/danger/default"];
  if (primitiveName === "yellow/500") return vars["color/bg/fill/warning/default"];
  if (primitiveName === "blue/50") return vars["color/bg/canvas/default"];
  if (primitiveName === "pink/50") return vars["color/bg/thumbnail/default"];
  if (primitiveName === "black/alpha-50") return vars["color/bg/overlay/default"];
  return null;
}

function bindMatchingPaints(node, field, colorLookup, vars) {
  const key = field === "fill" ? "fills" : "strokes";
  if (!(key in node) || !Array.isArray(node[key])) return false;
  let changed = false;
  const updatedPaints = node[key].map((paint) => {
    if (paint.type !== "SOLID") return paint;
    const variable = pickGlobalVariableForPaint(node, field, paint, colorLookup, vars);
    if (!variable) return paint;
    changed = true;
    return figma.variables.setBoundVariableForPaint(paint, "color", variable);
  });
  if (changed) node[key] = updatedPaints;
  return changed;
}

function isInsideComponent(node) {
  let current = node;
  while (current && current.type !== "DOCUMENT") {
    if (current.type === "COMPONENT" || current.type === "COMPONENT_SET") return true;
    current = current.parent;
  }
  return false;
}

function applyGlobalComponentColorBindings(vars, colorLookup, report) {
  const allNodes = figma.root.findAll((node) => isInsideComponent(node));
  let boundCount = 0;
  for (const node of allNodes) {
    const fillChanged = bindMatchingPaints(node, "fill", colorLookup, vars);
    const strokeChanged = bindMatchingPaints(node, "stroke", colorLookup, vars);
    if (fillChanged || strokeChanged) {
      boundCount += 1;
      report.boundNodes.push(node.id);
    }
  }
  report.globalBoundNodeCount = boundCount;
}

function bindFills(node, variable) {
  if ("fills" in node && Array.isArray(node.fills)) {
    node.fills = bindPaints(node.fills, variable);
    return true;
  }
  return false;
}

function bindStrokes(node, variable) {
  if ("strokes" in node && Array.isArray(node.strokes)) {
    node.strokes = bindPaints(node.strokes, variable);
    return true;
  }
  return false;
}

function walk(node, callback) {
  callback(node);
  if ("children" in node) {
    for (const child of node.children) walk(child, callback);
  }
}

function includesAll(text, parts) {
  return parts.every((part) => text.includes(part));
}

function resolveButtonTokens(componentName, vars) {
  const name = componentName.toLowerCase();
  const disabled = name.includes("state=disabled");
  if (disabled) {
    return {
      bg: vars["color/button/disabled/bg/default"],
      text: vars["color/button/disabled/text/default"],
      border: vars["color/border/disabled/default"],
    };
  }
  if (includesAll(name, ["variant=primary"])) {
    return { bg: vars["color/button/primary/bg/default"], text: vars["color/button/primary/text/default"] };
  }
  if (includesAll(name, ["variant=secondary"])) {
    return { bg: vars["color/button/secondary/bg/default"], text: vars["color/button/secondary/text/default"] };
  }
  if (includesAll(name, ["variant=outlined primary"])) {
    return { border: vars["color/button/outlined-primary/border/default"], text: vars["color/button/outlined-primary/text/default"] };
  }
  if (includesAll(name, ["variant=outlined secondary"])) {
    return { border: vars["color/button/outlined-secondary/border/default"], text: vars["color/button/outlined-secondary/text/default"] };
  }
  if (includesAll(name, ["variant=danger"])) {
    return { bg: vars["color/button/danger/bg/default"], text: vars["color/button/danger/text/default"] };
  }
  if (includesAll(name, ["variant=text button"])) {
    return { text: vars["color/button/text-button/text/default"] };
  }
  return {};
}

function applyButtonBindings(vars, report) {
  const buttonSet = figma.getNodeById(NODE_IDS.buttonSet);
  if (!buttonSet || !("children" in buttonSet)) {
    report.warnings.push(`Button component set not found: ${NODE_IDS.buttonSet}`);
    return;
  }
  for (const component of buttonSet.children) {
    const tokens = resolveButtonTokens(component.name, vars);
    if (tokens.bg) bindFills(component, tokens.bg);
    if (tokens.border) bindStrokes(component, tokens.border);
    walk(component, (node) => {
      if (node.type === "TEXT" && tokens.text) bindFills(node, tokens.text);
      if (node.type !== "TEXT" && tokens.text && node.name.toLowerCase().includes("icon")) bindFills(node, tokens.text);
    });
    report.boundNodes.push(component.id);
  }
}

function applyFabBindings(vars, report) {
  const fab = figma.getNodeById(NODE_IDS.fabSticky);
  if (!fab) {
    report.warnings.push(`FAB component not found: ${NODE_IDS.fabSticky}`);
    return;
  }
  bindFills(fab, vars["color/fab/sticky/bg/default"]);
  report.boundNodes.push(fab.id);
}

function applyOverlayBindings(vars, report) {
  for (const id of NODE_IDS.overlays) {
    const overlay = figma.getNodeById(id);
    if (!overlay) {
      report.warnings.push(`Overlay component not found: ${id}`);
      continue;
    }
    walk(overlay, (node) => {
      if ("fills" in node) bindFills(node, vars["color/overlay/scrim/default"]);
    });
    report.boundNodes.push(overlay.id);
  }
}

function applyPageIdentifierBindings(vars, report) {
  const pageIdentifier = figma.getNodeById(NODE_IDS.pageIdentifier);
  if (!pageIdentifier) {
    report.warnings.push(`Page Identifier component not found: ${NODE_IDS.pageIdentifier}`);
    return;
  }
  walk(pageIdentifier, (node) => {
    if (node.type === "TEXT") bindFills(node, vars["color/page-identifier/text/default"]);
  });
  report.boundNodes.push(pageIdentifier.id);
}

const report = {
  collections: [],
  variablesCreatedOrUpdated: [],
  boundNodes: [],
  globalBoundNodeCount: 0,
  warnings: [],
};

try {
  const primitiveCollection = getOrCreateCollection(COLLECTIONS.primitive);
  const semanticCollection = getOrCreateCollection(COLLECTIONS.semantic);
  const componentCollection = getOrCreateCollection(COLLECTIONS.component);
  report.collections.push(primitiveCollection.name, semanticCollection.name, componentCollection.name);

  const primitiveVars = {};
  const colorLookup = {};
  for (const [name, value] of primitiveTokens) {
    const variable = getOrCreateColorVariable(primitiveCollection, name, []);
    setPrimitiveValue(variable, primitiveCollection, value);
    primitiveVars[name] = variable;
    colorLookup[colorKeyFromTokenValue(value)] = name;
    report.variablesCreatedOrUpdated.push(name);
  }

  const semanticVars = {};
  for (const [name, aliasTarget, scopes] of semanticTokens) {
    const target = primitiveVars[aliasTarget];
    if (!target) throw new Error(`Missing primitive alias target: ${aliasTarget}`);
    const variable = getOrCreateColorVariable(semanticCollection, name, scopes);
    setAliasValue(variable, semanticCollection, target);
    semanticVars[name] = variable;
    report.variablesCreatedOrUpdated.push(name);
  }

  const componentVars = {};
  for (const [name, aliasTarget, scopes] of componentTokens) {
    const target = semanticVars[aliasTarget];
    if (!target) throw new Error(`Missing semantic alias target: ${aliasTarget}`);
    const variable = getOrCreateColorVariable(componentCollection, name, scopes);
    setAliasValue(variable, componentCollection, target);
    componentVars[name] = variable;
    report.variablesCreatedOrUpdated.push(name);
  }

  const vars = Object.assign({}, semanticVars, componentVars);
  applyButtonBindings(vars, report);
  applyFabBindings(vars, report);
  applyOverlayBindings(vars, report);
  applyPageIdentifierBindings(vars, report);
  applyGlobalComponentColorBindings(vars, colorLookup, report);

  figma.closePlugin(`TERRA tokens applied. ${report.variablesCreatedOrUpdated.length} variables updated, ${report.globalBoundNodeCount} component nodes globally bound.`);
} catch (error) {
  figma.closePlugin(`TERRA token apply failed: ${error.message}`);
  throw error;
}
