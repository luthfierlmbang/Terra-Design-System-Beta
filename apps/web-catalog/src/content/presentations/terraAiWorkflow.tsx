import type { ReactNode } from 'react';

export type PresentationSlideKind =
  | 'title'
  | 'bullet-list'
  | 'architecture'
  | 'process'
  | 'comparison'
  | 'role-split'
  | 'mapping'
  | 'stack'
  | 'benefits'
  | 'closing';

export type PresentationSlide = {
  id: string;
  eyebrow?: string;
  title: string;
  subtitle?: string;
  summary?: string;
  kind: PresentationSlideKind;
  notes?: string;
  bullets?: string[];
  leftTitle?: string;
  leftBullets?: string[];
  rightTitle?: string;
  rightBullets?: string[];
  steps?: string[];
  nodes?: string[];
  mapping?: Array<{
    label: string;
    value: string;
  }>;
  closingLine?: string;
  accent?: string;
  visual?: ReactNode;
};

export type PresentationDeckEntry = {
  slug: string;
  title: string;
  description: string;
  audience?: string;
  slides: PresentationSlide[];
};

function flowVisual(items: string[]) {
  return (
    <div className="presentationSlide__flow" aria-hidden="true">
      {items.map((item, index) => (
        <div key={item} className="presentationSlide__flow-item">
          <span>{item}</span>
          {index < items.length - 1 ? <div className="presentationSlide__flow-arrow">→</div> : null}
        </div>
      ))}
    </div>
  );
}

export const terraAiWorkflowDeck: PresentationDeckEntry = {
  slug: 'terra-ai-workflow',
  title: 'TERRA Design System + AI-Assisted Workflow',
  description: 'Deck presentasi tentang bagaimana Figma, AI, dan local design system library mempercepat implementasi UI yang lebih konsisten.',
  audience: 'Developer, design system team, dan engineering stakeholders',
  slides: [
    {
      id: 'opening',
      eyebrow: 'TERRA Design System',
      title: 'Mempercepat implementasi UI dari Figma ke code dengan bantuan AI dan reusable library',
      summary: 'Session ini membahas kenapa design system dibuat, bagaimana perannya dalam workflow developer, dan bagaimana Figma, AI, serta library saling melengkapi.',
      kind: 'title',
      bullets: [
        'kenapa design system ini dibuat',
        'gimana perannya di workflow developer',
        'gimana Figma, AI, dan library saling bantu',
        'cara pakainya supaya implementasi UI lebih cepat dan konsisten',
      ],
      notes:
        'Tujuan sesi ini bukan cuma ngenalin design system sebagai kumpulan komponen, tapi sebagai bagian dari workflow modern developer. Jadi bukan sekadar reusable UI, tapi juga enabler buat AI-assisted implementation.',
      accent: 'accelerate',
    },
    {
      id: 'problem',
      eyebrow: 'Pain points hari ini',
      title: 'Kenapa implementasi UI masih sering makan waktu?',
      kind: 'bullet-list',
      bullets: [
        'Developer tetap harus translate design ke code secara manual',
        'Komponen yang mirip sering di-implement ulang',
        'Hasil implementasi bisa beda antar squad',
        'AI bisa bantu generate code, tapi tanpa standard hasilnya bisa random',
        'QA visual dan alignment design-engineering jadi lebih mahal',
      ],
      notes:
        'Masalah utamanya bukan karena developer nggak bisa implement. Masalahnya adalah proses translate design ke code itu repetitive, rawan beda interpretasi, dan makin berat kalau setiap squad jalan sendiri-sendiri.',
      accent: 'problem',
    },
    {
      id: 'solution',
      eyebrow: 'Konsep besar',
      title: 'Bukan cuma design system. Ini orkestrasi workflow.',
      summary: 'Kita membangun alur yang menyambungkan design context, AI assistance, dan komponen reusable dalam satu workflow yang lebih terarah.',
      kind: 'architecture',
      bullets: [
        'Figma sebagai referensi design',
        'AI Agent + MCP Figma sebagai helper pembaca context design',
        'Local Design System Library sebagai source komponen reusable',
        'Developer sebagai decision maker dan executor',
      ],
      nodes: ['Developer', 'Figma', 'AI Agent (MCP Figma)', 'Local Design System Library', 'Implementation'],
      visual: flowVisual(['Developer', 'Figma', 'AI Agent', 'Library', 'Implementation']),
      notes:
        'Yang penting di sini: AI bukan pengganti developer. AI cuma jadi jembatan yang bantu baca design dan mempercepat implementasi, sementara output-nya tetap diarahkan ke library yang sudah kita standardkan.',
      accent: 'accelerate',
    },
    {
      id: 'workflow',
      eyebrow: 'Developer-led flow',
      title: 'Workflow utamanya tetap: dev refer ke Figma',
      kind: 'process',
      steps: [
        'Developer lihat UI design di Figma',
        'Developer pakai AI Agent yang bisa akses Figma via MCP',
        'AI membaca struktur dan design intent dari Figma',
        'AI generate atau suggest implementation',
        'Implementation diarahkan untuk memakai local design system library',
        'Developer review, adjust, lalu pakai di feature',
      ],
      notes:
        'Jadi flow utamanya tetap developer-led. Developer tetap lihat Figma, tetap ngerti konteks screen-nya. Bedanya, sekarang ada akselerator di tengah: AI yang bisa bantu mapping ke component library yang benar.',
      accent: 'workflow',
    },
    {
      id: 'library-importance',
      eyebrow: 'Fondasi standard',
      title: 'Tanpa library, AI cuma generate code. Dengan library, AI generate standard.',
      kind: 'comparison',
      leftTitle: 'Tanpa library',
      leftBullets: [
        'AI bisa generate UI dari nol',
        'Pattern bisa beda-beda',
        'Hasil susah dijaga konsistensinya',
      ],
      rightTitle: 'Dengan library',
      rightBullets: [
        'AI punya referensi komponen yang jelas',
        'Hasil implementasi lebih konsisten',
        'Code lebih reusable',
        'Developer nggak mulai dari blank page',
      ],
      notes:
        'Ini poin kuncinya: library design system bikin AI nggak liar. AI jadi punya pagar. Dia tahu kalau button sebaiknya pakai TerraButtonView, kalau form pakai komponen yang sudah ada, bukan bikin interpretasi sendiri.',
      accent: 'library',
    },
    {
      id: 'figma-role',
      eyebrow: 'Source of truth',
      title: 'Figma tetap jadi source of truth untuk design',
      kind: 'bullet-list',
      summary: 'Kita tidak memindahkan sumber design. Kita hanya mempercepat proses translasinya ke implementation.',
      bullets: [
        'Figma tetap jadi referensi utama untuk layout',
        'Figma menjaga visual hierarchy, spacing, variant, dan state',
        'Developer tetap melihat design langsung dari Figma',
        'AI Agent via MCP membantu membaca context tersebut',
      ],
      notes:
        'Jadi jangan dibayangkan design system menggantikan Figma. Enggak. Figma tetap source of truth visual. Design system library itu source of truth implementation. Dan AI bantu menjembatani dua dunia itu.',
      accent: 'figma',
    },
    {
      id: 'ai-role',
      eyebrow: 'AI as copilot',
      title: 'AI membantu translate, bukan menggantikan judgment developer',
      kind: 'role-split',
      leftTitle: 'AI Agent membantu',
      leftBullets: [
        'Membaca context dari Figma',
        'Mengurangi effort translate manual',
        'Menyarankan komponen yang sesuai',
        'Mempercepat boilerplate implementation',
      ],
      rightTitle: 'Developer tetap',
      rightBullets: [
        'Review output',
        'Memastikan logic feature benar',
        'Memutuskan apakah hasilnya sesuai standard',
      ],
      notes: 'AI di sini kita posisikan sebagai copilot yang tahu konteks. Bukan mesin auto-build yang dilepas begitu aja. Developer tetap pegang kontrol.',
      accent: 'ai',
    },
    {
      id: 'library-role',
      eyebrow: 'Reusable target',
      title: 'Library adalah target implementasi yang reusable',
      kind: 'mapping',
      bullets: [
        'Library menyimpan komponen reusable yang sudah distandardkan',
        'Developer dan AI sama-sama punya target implementasi yang jelas',
        'Component, token, dan usage pattern lebih mudah dicari',
        'Hasil implementasi lebih dekat ke standard tim, bukan style personal masing-masing dev',
      ],
      mapping: [
        { label: 'button', value: 'TerraButtonView' },
        { label: 'text input', value: 'TerraTextFieldView' },
        { label: 'search', value: 'TerraSearchBarView' },
      ],
      notes:
        'Kalau AI tahu library yang tersedia secara lokal, dia nggak perlu nebak-nebak harus bangun komponen seperti apa. Dia tinggal pakai building block yang memang sudah kita siapkan.',
      accent: 'library',
    },
    {
      id: 'architecture',
      eyebrow: 'Android structure',
      title: 'Di Android, struktur TERRA dibuat supaya gampang dipakai dan dipelajari',
      kind: 'stack',
      nodes: ['terra-tokens', 'terra-ui', 'catalog'],
      bullets: [
        'terra-tokens → foundation resources',
        'terra-ui → reusable component library',
        'catalog → preview/demo app',
      ],
      summary: 'tokens = fondasi · ui = komponen reusable · catalog = tempat lihat contoh dan validasi visual',
      notes:
        'Struktur ini penting karena bikin developer, AI, dan dokumentasi punya tempat referensi yang rapi. Jadi nggak semua hal tercampur di satu layer.',
      accent: 'stack',
    },
    {
      id: 'benefits',
      eyebrow: 'Nilai praktis',
      title: 'What’s in it for developers?',
      kind: 'benefits',
      bullets: [
        'Lebih cepat implement UI dari design',
        'Mengurangi coding ulang komponen yang sama',
        'Hasil lebih konsisten antar squad',
        'Lebih gampang onboarding developer baru',
        'AI-assisted workflow jadi lebih efektif karena punya referensi library yang jelas',
        'Review dan QA visual lebih ringan',
      ],
      notes: 'Kalau diringkas: design system ini bikin developer kerja lebih cepat, AI lebih useful, dan hasil tim lebih konsisten.',
      accent: 'benefits',
    },
    {
      id: 'daily-workflow',
      eyebrow: 'Practical workflow',
      title: 'Praktiknya gimana?',
      kind: 'process',
      steps: [
        'Lihat design di Figma',
        'Gunakan AI Agent dengan MCP Figma untuk bantu baca design',
        'Pastikan AI refer ke local design system library',
        'Review hasil implementation',
        'Pakai component reusable yang sudah tersedia',
        'Kalau ada gap, eskalasi lewat flow design system, bukan bikin solusi liar sendiri',
      ],
      notes:
        'Jadi rule sederhananya: tetap refer ke Figma, gunakan AI untuk mempercepat, dan arahkan implementasi ke library yang sudah ada. Itu kombinasi paling sehat.',
      accent: 'workflow',
    },
    {
      id: 'closing',
      eyebrow: 'Closing',
      title: 'Tujuan akhirnya: faster, consistent, scalable',
      kind: 'closing',
      bullets: [
        'Figma tetap jadi referensi design utama',
        'AI membantu mempercepat translate design ke code',
        'Design system library memastikan output tetap reusable dan konsisten',
        'Developer tetap jadi pengambil keputusan utama',
        'Kalau workflow ini dipakai lintas squad, velocity naik tanpa mengorbankan standard',
      ],
      closingLine: 'Bukan sekadar bikin UI lebih cepat, tapi bikin cara kerja antara design, developer, dan AI jadi lebih sinkron.',
      notes:
        'Yang kita bangun di sini bukan cuma library component. Tapi workflow yang lebih matang antara design, engineering, dan AI assistance.',
      accent: 'closing',
    },
  ],
};
