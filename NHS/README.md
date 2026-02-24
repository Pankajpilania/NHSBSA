# NHSBSA Technical Exercise – NHS Jobs Search

This repository contains a BDD-focused Functional Acceptance automation suite for the NHS Jobs Search ticket using **Java 21**, **Selenium 4**, and **Cucumber**.

## Scope Covered
- Search for jobs using user preferences.
- Validate results are returned.
- Sort results by **Date posted (newest)**.
- Run against **Chrome** and **Firefox** using Selenium Manager (no locally downloaded drivers required).

## Project Structure
- `src/test/resources/features/job_search.feature` – user-centric BDD scenario.
- `src/test/java/uk/nhsbsa/jobs/steps` – Cucumber step definitions.
- `src/test/java/uk/nhsbsa/jobs/pages` – page objects and UI interactions.
- `src/test/java/uk/nhsbsa/jobs/config` – browser/driver configuration.
- `src/test/java/uk/nhsbsa/jobs/hooks` – Cucumber setup/teardown hooks.
- `docs/presentation.md` – presentation content for accessibility, compatibility, performance, and migration testing approach.

## Prerequisites
- Java 21+
- Maven 3.9+
- Chrome and/or Firefox installed

## Command-line execution
Run on Chrome:
```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Run on Firefox:
```bash
mvn clean test -Dbrowser=firefox -Dheadless=true
```

## Notes
- Selenium Manager is used via Selenium 4 to resolve browser drivers automatically.
- Test selectors are written to be resilient to minor UI markup differences.
