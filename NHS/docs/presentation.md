# Presentation: NHS Jobs Search Testing Approach

## 1) Testing approach to the ticket
- **Goal:** Prove the search journey works from a jobseeker perspective.
- **BDD style:** One readable user-centric scenario that mirrors the acceptance criteria.
- **Design:**
  - Feature file defines behaviour.
  - Step definitions express business actions.
  - Page objects hold locators and browser operations.
- **Coverage:**
  - Enter search preferences (keyword, location, distance).
  - Submit search and verify results are returned.
  - Sort by "Date posted (newest)" and validate newest ordering when dates are available.
## 2) Choice of tech and tools
- **Java 21** for modern language support/LTS.
- **Selenium 4** for browser automation + Selenium Manager (no local drivers).
- **Cucumber** for BDD and stakeholder-readable scenarios.
- **JUnit Platform + Maven Surefire** for CLI execution and CI integration.

## 3) Analysis of user story and acceptance criteria
- **User story intent:** jobseekers want relevant and recent results quickly.
- **Acceptance criteria translated into checks:**
  - Preferences can be entered and submitted.
  - Jobs list is produced (non-empty result set).
  - Results can be sorted to newest by date posted.
  - Sort confirmation and date descending assertion (where parseable dates are shown).

## 4) Reusability and methods implemented
- Reusable base page methods (`type`, `click`, `visible`).
- Browser-agnostic `WebDriverFactory` using runtime properties:
  - `-Dbrowser=chrome|firefox`
  - `-Dheadless=true|false`
- Hooks manage clean setup/teardown per scenario.

## 5) Element locators used
- Strategy preference: semantic attributes and form controls (`name`, `id`, `type`, `select`).
- Fallback locators included for markup variations to reduce brittleness.
- Sorting control accessed via `Select` abstraction.

## 6) Issues found / testing risks
- Public website markup can change; locator stability risk.
- Date labels may appear in mixed formats (e.g., relative dates), requiring conditional parsing.
- Live environment data changes frequently, so deterministic expected counts are unsuitable.

## 7) Improvements to be made
- Add more scenarios for negative/edge searches (no results, invalid location).
- Add tags and profiles for smoke vs regression packs.
- Add richer result relevance assertions (keyword match in title/summary filters).
- Add screenshots/recording on failure and CI artifacts.

---

## Part 2 – Accessibility testing approach

### Standards and scope
- Validate against **WCAG 2.2 AA** for the search page and result listing interactions.

### Test layers
1. **Automated accessibility checks (fast feedback):**
   - Integrate axe-core scans into Selenium tests for search and results pages.
   - Fail build on serious/critical issues.
2. **Manual keyboard checks:**
   - Full journey with keyboard only (tab order, focus indicator, skip links, controls).
3. **Screen reader checks:**
   - NVDA + Firefox and VoiceOver + Safari (if available) for labels, announcements, landmarks.
4. **Visual accessibility:**
   - Colour contrast, zoom/reflow (200–400%), text spacing.

### Accessibility evidence in reporting
- Accessibility defect log by WCAG criterion, user impact, severity, and suggested fix.

## Part 2 – Compatibility testing approach

### Browser/device matrix
- **Desktop:** Chrome, Firefox, Edge (latest stable + one previous if required).
- **Mobile:** iOS Safari, Android Chrome responsive behaviour.
- **Viewport coverage:** common breakpoints (mobile, tablet, laptop, large desktop).

### Compatibility checks
- Search form interactions and validation.
- Sorting and pagination behaviour.
- Rendering/overlap issues at different zoom levels and DPI settings.
- Locale/date format display consistency.

### Execution model
- Primary automation on Chrome/Firefox.
- Supplementary cross-browser runs in CI grid/cloud when available.

## Part 2 – Performance testing considerations
- **Objectives:** acceptable search response time and stable behaviour under load.
- **Key metrics:** p95 response time, error rate, throughput, Apdex, server resource use.
- **Test types:**
  - Baseline single-user test.
  - Load test at expected peak.
  - Stress test to identify breaking point.
  - Soak test for endurance and memory/resource leaks.
- **Scenarios:** common keywords, broad keywords (large result sets), with sorting.
- **Tooling suggestion:** JMeter/Gatling + observability dashboards.

## Part 2 – Data migration testing considerations
For migrating vacancies and applications from in-house systems to NHS Jobs:

1. **Data mapping validation**
   - Source-to-target field mapping (mandatory fields, types, enums, statuses).
2. **Data quality checks**
   - Completeness, accuracy, duplicates, truncation, encoding/special characters.
3. **Migration execution testing**
   - Dry runs in lower environments with production-like datasets.
4. **Reconciliation**
   - Record counts and control totals by entity/state before and after migration.
5. **Business integrity checks**
   - End-to-end checks that migrated vacancies are searchable and application states are correct.
6. **Non-functional checks**
   - Migration window duration, rollback strategy, auditability, security/privacy controls.
7. **Cutover and hypercare plan**
   - Parallel run criteria, go/no-go checks, and post-migration monitoring.
