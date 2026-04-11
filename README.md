# Selenium TestNG Java Automation Framework

A Maven-based UI automation framework for end-to-end web testing using **Selenium WebDriver 4** and **TestNG**. The codebase targets **[Sauce Demo](https://www.saucedemo.com/)** as the primary application under test and implements a **Page Object Model** with environment-specific configuration, centralized driver management, and HTML reporting suitable for regression-style checks.

---

## Description

This project automates critical user journeys against a demo e-commerce flow: authentication, catalog interaction, cart validation, and checkout steps through the overview page. Locators live in dedicated classes; page interactions are encapsulated in page objects; tests orchestrate flows and assertions. Execution is driven by **TestNG suites** (`testng.xml`) invoked through **Maven Surefire**, with **environment** and **browser** selectable at runtime without code changes.

**Chrome / Edge:** The driver layer applies **Chromium preferences and launch flags** so Google’s native **“Change your password” / data-breach** password-manager UI is less likely to block automation (that dialog is not part of the page DOM and cannot be clicked with normal Selenium locators).

---

## Tech Stack

| Area | Technology |
|------|------------|
| Language | Java 17 |
| UI automation | Selenium WebDriver **4.29.x** |
| Test runner | TestNG **7.11.x** |
| Build | Maven |
| Driver binaries | WebDriverManager (**Bonigarcia**) |
| Reporting | ExtentReports (**ExtentSpark**) |
| Logging | **Log4j 2** |
| Utilities | Apache Commons IO / Lang3, Apache POI (test data), org.json |

---

## Project Structure

```
<project-root>/
├── pom.xml                          # Dependencies, compiler (Java 17), Surefire → testng.xml
├── testng.xml                       # TestNG suite (explicit test method includes)
├── extent-config.xml                # Extent Spark reporter theme / document settings
├── extent.properties                # Extent reporter toggles (Spark output)
├── log4j.properties                 # Root Log4j config (if used from project root)
│
├── src/main/java/com/automationframeworks/selenium_testng_java/
│   ├── locators/                    # XPath/string constants per page (single source for @FindBy)
│   ├── pageObjects/                 # Page Factory page objects (Login, inventory, cart, checkout, overview)
│   └── utilities/
│       ├── Base.java                # Driver lifecycle, TestNG hooks, Log4j, Extent listener wiring
│       ├── WebDriverProvider.java   # Browser factory; Chrome/Edge prefs + flags (notifications, password UI)
│       ├── BrowserPopupHelper.java  # Optional helpers: dismiss JS alert, send Escape (use from tests if needed)
│       ├── PropertiesReader.java    # Loads dev / uat / prod properties from src/main/resources
│       ├── Listener.java            # ExtentReports + failure screenshots under test-output/
│       ├── CommonMethods.java       # Shared helpers (waits, random data, Excel hooks, etc.)
│       └── TestUserProvider.java    # JSON-backed user resolution by app + role (extensible)
│
├── src/main/resources/
│   ├── dev.properties               # Default environment config
│   ├── uat.properties
│   ├── prod.properties
│   ├── log4j2.properties
│   └── dev-users.json               # Sample user catalog for JSON-driven tests
│
└── src/test/java/com/automationframeworks/selenium_testng_java/
    └── SauceDemoTest.java           # Primary E2E scenario (Sauce Demo)
```

---

## Features

- **Page Object Model** with **Page Factory** (`@FindBy`) and locator classes decoupled from step logic.
- **Multi-environment config** via `dev` / `uat` / `prod` property files, selected with `-Denv=`.
- **WebDriverManager** for automatic driver setup; support for **headless** Chrome/Firefox.
- **Runtime browser override** via `-Dbrowser=` (overrides TestNG parameter default).
- **Chrome / Edge hardening:** preferences and `--disable-features=…` style flags to reduce **native** password-manager and notification UI that can block clicks (see `WebDriverProvider`).
- **ExtentReports** HTML reports under `test-output/`, with **failure screenshots** attached to the report.
- **Log4j 2** integration for structured test and framework logging.
- **ThreadLocal WebDriver** in `Base` for safe use with listeners and screenshots on failure.
- **TestNG `@Listeners`** on the base class for consistent reporting across tests.

---

## How to Run

### Prerequisites

- **JDK 17**
- **Maven 3.8+**
- **Google Chrome** (default) or another [supported browser](#browser-values) installed
- **Network access** (Maven Central, Sauce Demo, and WebDriverManager driver resolution)

Run all commands from the **project root** (the folder that contains `pom.xml`) so classpath and resource paths resolve as expected.

### Run the full suite (default `testng.xml`)

```bash
mvn clean test
```

Or without clean:

```bash
mvn test
```

### Run a single test method

```bash
mvn test -Dtest=SauceDemoTest#verifyProductPurchase
```

### Environment (`dev` | `uat` | `prod`)

Properties are loaded from `src/main/resources/<env>.properties`. Default is **`dev`** if omitted.

```bash
mvn clean test -Denv=uat
mvn clean test -Denv=prod
```

### Browser

The framework reads **`-Dbrowser=`** in `Base` (falls back to TestNG `@Optional("chrome")` when not set).

Examples:

```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=chrome-headless
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=firefox-headless
mvn clean test -Dbrowser=edge
```

Combine flags as needed:

```bash
mvn clean test -Denv=dev -Dbrowser=chrome-headless
```

### IDE — Eclipse

- Import as **Maven** project.
- **Right-click `testng.xml` → Run As → TestNG Suite** (runs the suite exactly as defined in XML).
- Or **right-click `SauceDemoTest` → Run As → TestNG Test**.
- For JVM properties (`env`, `browser`), set them in the run configuration (VM arguments / environment) equivalent to `-Denv=...` `-Dbrowser=...`.

### IDE — VS Code / Cursor

The **Java Testing** view often **does not** discover tests from **`testng.xml`** alone (XML is not Java). Prefer:

1. **`mvn test`** in the integrated terminal from the project root, or  
2. **Run / Debug** on the **`@Test`** method in `SauceDemoTest.java` after the **Extension Pack for Java** has finished loading the Maven project.

If CodeLens does not appear, run **Java: Clean Java Language Server Workspace** from the Command Palette, then reload the window.

#### Browser values

| Value | Behavior |
|-------|----------|
| `chrome` | Chrome (default) |
| `chrome-headless` | Chrome headless |
| `firefox` | Firefox |
| `firefox-headless` | Firefox headless |
| `edge` | Microsoft Edge |

---

## TestNG suite (`testng.xml`)

The suite declares **`SauceDemoTest`** and explicitly **includes** `verifyProductPurchase`. That makes the runnable test visible in the suite file and keeps execution aligned with Surefire.

To add another `@Test` in the same class, either:

- add another `<include name="yourNewMethod"/>`, or  
- remove the `<methods>` block to run **all** `@Test` methods in the class.

---

## Sample Test Scenario

**Class:** `com.automationframeworks.selenium_testng_java.SauceDemoTest`  
**Method:** `verifyProductPurchase`

**Flow (high level):**

1. Open the configured `baseURL` (Sauce Demo).
2. Validate login page title and fields; sign in with credentials from properties (aligned with on-page credential hints).
3. On the inventory page, assert products and “Add to cart” actions; add **Sauce Labs Backpack** and **Sauce Labs Bike Light**.
4. Assert cart badge count, open cart, verify line items and **subtotal** against summed prices.
5. Proceed to checkout step one; fill first name, last name, and postal code from properties.
6. Continue to **Checkout: Overview**; parse **Item total** and assert it matches the cart subtotal.

This scenario exercises **login → catalog → cart → checkout information → checkout overview** with URL, visibility, and numeric assertions suitable for smoke or regression.

---

## Configuration (Properties)

| File | Purpose |
|------|---------|
| `dev.properties` | Default environment: `baseURL`, optional `browser`, Sauce Demo credentials, checkout test data, product name references. |
| `uat.properties` | Same key layout for UAT-style runs (`-Denv=uat`). |
| `prod.properties` | Same key layout for production-style runs (`-Denv=prod`). |

**Important keys** (all environments should define them consistently):

| Key | Role |
|-----|------|
| `baseURL` | Application under test (e.g. Sauce Demo URL). |
| `browser` | Documented default browser hint (runtime still honors `-Dbrowser=`). |
| `username` / `password` | Login credentials used in tests. |
| `firstName` / `lastName` / `postalCode` | Checkout step-one data. |
| `FirstProductName` / `SecondProductName` | Reference labels for documentation / future data-driven use. |

**User JSON (optional extension):** `dev-users.json` (and matching files for other envs if added) supports `TestUserProvider` for role-based user lookup—useful when scaling to multiple personas or applications.

**Note:** `PropertiesReader` loads files via paths like `src/main/resources/...` relative to the **working directory**. Running **`mvn test`** from the project root matches that layout. Running tests with a different CWD may require a future classpath-only loader.

---

## Reporting & artifacts

- **ExtentReports (Spark):** Each run produces a timestamped HTML report under **`test-output/`**, named like `Test-Report-yyyy.MM.dd.h.mm.ss.html`.
- **Styling / metadata:** `extent-config.xml` controls theme, encoding, report title, and related Spark settings.
- **Failures:** On test failure, a screenshot is saved under `test-output/` (see `Listener`) and linked in the Extent report.
- **Logs:** Log4j 2 configuration in `src/main/resources/log4j2.properties` supports file/console logging for triage alongside the HTML report.

Open the latest `Test-Report-*.html` in a browser after `mvn test`.

---

## Cloning and running from Git

Anyone with **JDK 17**, **Maven**, **Chrome** (or chosen browser), and **internet** can clone and run:

```bash
git clone <your-repo-url>
cd <repo-folder>
mvn test
```

Sauce Demo credentials in the sample properties files are **public demo values**, not secrets. Do not commit real production passwords.

---

## Troubleshooting

| Issue | What to try |
|-------|-------------|
| Chrome **“Change your password”** / breach modal still appears | Confirm you are on a recent build; Chrome updates can rename features. Try **Firefox** (`-Dbrowser=firefox`) to compare, or adjust flags in `WebDriverProvider` for your Chrome major version. |
| **No tests found** in VS Code Testing panel on `testng.xml` | Expected for many setups; run **`mvn test`** or use **Run** on the `@Test` in `SauceDemoTest.java`. |
| Selenium **CDP version** warning in logs | Informational when browser version is ahead of bundled CDP; UI tests still run unless you rely on CDP APIs. Add a matching `selenium-devtools-v*` dependency if needed. |

---

## Future Enhancements

- Wire **CI/CD** (e.g. GitHub Actions) with headless Chrome and published HTML artifacts.
- Expand **TestNG suite**: groups, priorities, and optional **parallel** execution at method or class level where safe.
- Add **negative and boundary** cases (locked user, invalid login, empty checkout fields).
- Complete the purchase flow through **Finish** and assert the **order confirmation** page.
- Load properties from the **classpath** only (portable regardless of working directory).
- Introduce **explicit waits** as the primary synchronization strategy and tune implicit waits.
- Optional **Allure** or unified reporting pipeline alongside Extent for dashboard-friendly output.

---

## Author

**Viraj Sodaye**

---

## License

This repository is provided for portfolio and learning purposes. Sauce Demo is a third-party demo site; use responsibly and respect their terms of use.
