# OpenCart Selenium TestNG Framework

Hybrid framework skeleton for OpenCart demo automation.

## Folder Structure
- `src/test/java/pages` → Page Objects
- `src/test/java/testcases` → TestNG test classes
- `src/test/java/utilities` → Driver, config, screenshot, Excel, and reports utilities
- `src/test/java/base` → Base test setup and teardown
- `src/test/resources/config` → Config file
- `src/test/resources/testdata` → Test data files
- `screenshots` → Captured screenshots
- `reports` → Extent report output

## Run
```bash
mvn test
```

## Notes
This is a starter skeleton. You can expand page locators and assertions based on the exact OpenCart flows used in your class assignment.
