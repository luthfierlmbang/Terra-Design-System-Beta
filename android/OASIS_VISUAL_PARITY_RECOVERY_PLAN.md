# Oasis Android Visual Parity Recovery Plan

_Date:_ 2026-06-22
_Status:_ Draft recovery plan after first-pass Android port failed visual parity review
_Owner:_ ZCode session follow-up

## 1. Problem Statement

The current Android implementation of the Oasis design system is **not visually equivalent** to the Oasis web source.

Although the Android catalog builds and runs, the implementation currently only satisfies a **technical smoke test**:
- Gradle builds successfully
- Catalog app launches
- Component preview screens render

It does **not** yet satisfy the actual product goal:
- **1:1 visual match with Oasis web components**
- matching spacing, sizing, typography, border radii, color usage, icon placement, state styling, and overall composition

This means the current Android output should be treated as a **rough first-pass port**, not a finished native translation.

## 2. What Went Wrong

The first implementation pass appears to have optimized too early for breadth (shipping all components) instead of fidelity (matching the web source exactly).

### Main failure modes

1. **Component-by-component visual auditing did not happen before implementation was considered complete**
   - Components were rendered in Android and considered present, but not verified against the web baseline.

2. **Design tokens were only partially sufficient for true parity**
   - Colors, text styles, and dimensions were introduced, but the implementation likely missed exact web values for:
     - vertical rhythm
     - internal padding
     - corner radii
     - line heights
     - icon sizing and placement
     - border thickness/opacities
     - state-specific surface/text combinations

3. **Catalog previews were used as proof of completion**
   - The catalog is useful for smoke testing, but it is not a parity harness by itself.
   - A component being visible in the catalog does not mean it matches the web implementation.

4. **Native Android defaults likely leaked into the implementation**
   - Material defaults, text rendering differences, widget baselines, and intrinsic view padding can easily distort parity if not overridden carefully.

5. **Visual regression was attempted too late**
   - It should have been a gate during implementation, not a final afterthought.

## 3. Recovery Goal

Rework the Oasis Android implementation so that each component is rebuilt and verified against the Oasis web source until the Android output is visually trustworthy.

### Recovery definition of done

A component is only considered complete when all of the following are true:
- Android implementation matches the web source visually at component level
- all supported variants are present
- all supported sizes are present
- all supported states are present
- screenshot comparison against the web reference passes manual review
- catalog preview for that component is updated and accurate

## 4. Recovery Strategy

The fix should **not** be another broad rewrite of everything at once.

Instead, use a strict **baseline → audit → patch → verify** loop.

### Phase A — Establish authoritative source of truth

For every component, collect the exact web reference from Oasis:
- React component source
- CSS / Tailwind / style definition
- tokens used by the component
- screenshots from the web version
- all states, sizes, and variants

Deliverable:
- per-component parity notes
- web screenshots grouped by component

### Phase B — Audit current Android output

For each Android component, compare current implementation against web on:
- typography
- spacing
- dimensions
- border radius
- stroke width
- color usage
- icon placement
- alignment
- elevation/shadow if any
- interaction states
- disabled styling
- error/focus/selected/checked/active styling

Deliverable:
- a gap list per component with concrete fixes

### Phase C — Repair foundations first

Before fixing all components individually, lock the shared foundations:
- color tokens
- spacing tokens
- radius tokens
- typography tokens
- stroke/border tokens
- common container/background tokens
- icon sizing tokens

This prevents repeating the same visual mismatch in every component.

Deliverable:
- corrected token layer in `oasis-designsystem-tokens`
- updated shared attrs/dimens/styles where needed

### Phase D — Rebuild high-impact components first

Start with components that define the system look most strongly:
1. Button
2. TextField
3. SearchBar
4. Tag
5. Checkbox
6. Radio
7. Toggle
8. Dropdown
9. Chips
10. Header / BottomNav / Tabs
11. Remaining display/feedback components

Reason:
- these components expose the most obvious visual mismatch
- they also validate the shared token layer quickly

### Phase E — Re-capture deterministic screenshots

Do not rely on tap-by-coordinate screenshots for the final regression pack.

Instead:
- launch component previews deterministically
- capture stable screenshots with fixed device/emulator settings
- store screenshots inside the repo, not only in `/tmp`
- name them correctly by component and variant

Suggested repo location:
- `android/oasis-catalog/screenshots/`

### Phase F — Final visual review

For each component, review Android vs web side-by-side and classify:
- pass
- minor drift
- fail

Only after all critical components pass should the overall port be considered complete.

## 5. Proposed Work Breakdown

## 5.1 Foundation repair
- audit token coverage vs web source
- add missing spacing/radius/typography/state tokens
- remove accidental Material/default styling leakage

## 5.2 Form + action components
- Button
- TextField
- SearchBar
- NumericInput
- TextArea
- Dropdown
- QtyInput
- Chips
- Checkbox
- Radio
- Toggle

## 5.3 Navigation + feedback components
- Tabs
- Header
- BottomNav
- Toast
- Alert
- ProgressBar
- IndicatorGroup
- Modal
- FileUpload

## 5.4 Display components
- Icon
- Illustration
- Avatar
- Tag
- Table

## 6. Recommended Execution Rules

To avoid repeating the same failure, use these rules during rework:

1. **No component is marked done from code inspection alone**
   - Completion requires visual comparison.

2. **No component is marked done from catalog rendering alone**
   - Rendering is not parity.

3. **Fix shared tokens before patching repeated local values**
   - If multiple components share the same mismatch, solve it at the foundation level.

4. **Use web source as the authority, not approximation**
   - No “close enough” substitutions unless explicitly approved.

5. **Capture proof artifacts in-repo**
   - screenshots, audit notes, and comparison results should live in the repository.

## 7. Deliverables To Produce Next

### Required docs
1. `android/OASIS_COMPONENT_PARITY_AUDIT.md`
   - component-by-component gap list

2. `android/OASIS_SCREENSHOT_BASELINE_PLAN.md`
   - how screenshots will be generated deterministically

3. `android/oasis-catalog/screenshots/`
   - stable Android screenshots for regression review

### Required implementation output
- corrected token layer
- corrected component implementations
- corrected catalog previews
- deterministic screenshot generation workflow

## 8. Suggested Next Practical Step

The best next step is:

1. audit the Oasis web `Button`
2. audit the Android `Button`
3. patch token/foundation issues exposed by `Button`
4. verify `Button` visually
5. repeat with `TextField`, `SearchBar`, and `Tag`

This gives a reliable baseline before touching the rest.

## 9. Current Conclusion

The current Oasis Android library is **structurally present but visually not yet acceptable**.

The right move now is not to keep adding more previews or more screenshots from the current build, but to:
- treat the current version as a first-pass scaffold
- run a component parity recovery pass
- verify each component visually against the Oasis web source before calling the port complete
