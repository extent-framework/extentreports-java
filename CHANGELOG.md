## [5.0.0]

#### New Features
- [#47](../issues/47) Anchors for each test
- [#150](../issues/150) Unused status from charts will not be displayed
- [#152](../issues/152) Spark: Author view
- [#153](../issues/153) Spark: Device view
- [#157](../issues/157) Spark: Add TestRunnerLogs view
- [#168](../issues/168) Spark: Navigation from attributes pages (tags, exception) to Tests View
- [#173](../issues/173) Spark: Allow configuration to select view order, Dashboard view as primary
- [#176](../issues/176) Apply Status filters to report
- [#177](../issues/177) Minified templates (> 10% reduction in file size)
- [#184](../issues/184) Reporter::loadExternalConfiguration with json file
- [#188](../issues/188) Display upto 4 `pre` blocks on a single row
- [#191](../issues/191) Navigation from Tags/Exception pages to nested steps
- [#213](../issues/213) oggle theme from URI component '#theme='

#### Issues Resolved
- [#131](../issues/131) Merging two html extent Reports works but Category view gets overwritten
- [#161](../issues/161) Merging ExtentSpark 4.1.5 Reports causing the existing base64 image removed
- [#169](../issues/169) Fix SparkReporter shortcuts
- [#197](../issues/197) MarkupHelper::codeBlock has extra indentation on the first line
- [#208](../issues/208) Show title text for screencaptures

## [4.1.7](https://github.com/extent-framework/extentreports-java/compare/v4.1.6...v4.1.7)
#### Fixes
* [#193] Unable to format the JSON code String in report

## [4.1.6](https://github.com/extent-framework/extentreports-java/compare/v4.1.5...v4.1.6)
#### Fixes
* [#149] Skipped count is not displaying in authors tag and category tag
* [#155] Fixes Cmd+C key listener 
* [#159] Fixes ConcurrentModificationException 
* [#167] Clicking Extent Logo on top-left navigating to 404

## [4.1.5](https://github.com/extent-framework/extentreports-java/compare/v4.1.4...v4.1.5)
#### Fixes
* [#139] BDD: Feature must not be eligible to increment counts for TestAttributeContext, part of fix of [cucumber4-adapter#50](https://github.com/extent-framework/extentreports-cucumber4-adapter/issues/50)
* [#143] Fix issue with a long node name breaking view (Spark)
* [#144] Fix Skipped status colors in charts
* [#145] ExtentKlovReporter adds BasicConfiguration 

## [4.1.4](https://github.com/extent-framework/extentreports-java/compare/v4.1.3...v4.1.4)
#### Improvements
* [#114] ExtentSparkReporter to display hierarchical name in tags view 
* [#119] ExtentSparkReporter to display thumbnail screenshots 
* [#125] ExtentSparkReporter displays clip icon for tests with leaf containing screenshots 
* [#132] ExtentSparkReporter to accept file-name instead of Index.html default  

#### Fixes
* [#135] ExtentSparkReporter issue with not parsing the `scripts` tag in config.xml  

## [4.1.3](https://github.com/extent-framework/extentreports-java/compare/v4.1.2...v4.1.3)
#### Improvements
* [#119] `ExtentSparkReporter` now displays thumbnails as default 

#### Fixes
* [#123] Prevent class cast exception in `ExtentHtmlReporterConfiguration`  
* [#124] Fixes issue with `ExtentHtmlReporter` throwing freemarker errors due to image path being empty
 
#### Deprecation
* ExtentHtmlReporter has been deprecated. ExtentSparkReporter is the default reporter starting version 4 and it now supports SPA-style view by default. 

## [4.1.2](https://github.com/extent-framework/extentreports-java/compare/v4.1.1...v4.1.2)
#### Improvements
* [#114] Tags tab lost a display of a full name of test
* ExtentKlovReporter can not be initialized with the `projectName`

#### Fixes
* [#115] Fixes issue with Base64 strings not displaying
* [#121] Fixes BDD SessionStats bug causing feature aggregates to be miscalculated

## [4.1.1](https://github.com/extent-framework/extentreports-java/compare/v4.1.0...v4.1.1)

## [4.1.0](https://github.com/extent-framework/extentreports-java/compare/v4.0.9...v4.1.0)
#### Improvements
* [#18] Steps count in dashboard displays 0 even though there are logged steps
* [#94] Cleanup tag view naming
* [#103] AppendExisting functionality similar to version 3 API, via `JsonFormatter` and `ExtentReports::createDomainFromJsonArchive`:
```java
JsonFormatter json = new JsonFormatter("target/test.json");
extent.attachReporter(json);
extent.createDomainFromJsonArchive("target/test.json");
```
* [#107] ExtentSparkReporter's default view is SPA, similar to V3HtmlReporter. This can be changed by instantiating as `new ExtentSparkReporter("dir", ViewStyle.DEFAULT);`
* [#108] All resources migrated to jsDelivr

#### Fixes
* [#57] ExtentKlovReporter: Screenshot cannot be saved in base64 format
* [#101] Fixes Freemarker templating error

## [4.0.9](https://github.com/extent-framework/extentreports-java/compare/v4.0.8...v4.0.9)
#### Improvements
* [#35] HtmlReporter, BDD: description to appear as tooltip instead of newline
* {#36] SparkReporter: display ScenarioOutline children as toggles
* [#37] SparkReporter: auto-size textarea on click
* [#42] CategoryView status toggles
#### Fixes
* [#39] Test case name overlap test case timestamp Spark Reporter
* [#40] Author & device name don't have a background label
* [#43] SparkReporter: issue with `setCSS` not working

## [4.0.8](https://github.com/extent-framework/extentreports-java/compare/v4.0.7...v4.0.8)
#### Fixes
* [#31] ExtentHtmlReporter does not display devices
* [#32] ExtentSparkReporter freemarker issue when injecting custom js
* [#33] ExtentSparkReporter dark theme fix

## [4.0.7](https://github.com/extent-framework/extentreports-java/compare/v4.0.6...v4.0.7)
#### New
* ExtentSparkReporter
#### Fixes
* {#6] Invalid testEndTime and timeTaken
* [#21] Adds ResourceCDN to use ExtentReports instead of cdn.rawgit
* [#24] Timeline is broken because commas appear in duration
* [#29] Embedded screenshot is missing in html report
* [#30] Skipped tests not being displayed in HtmlReporter's tags view
* Issue with KlovReporter not uploaded base64 strings to the server

## [4.0.6](https://github.com/extent-framework/extentreports-java/compare/v4.0.5...v4.0.6)
#### Fixes
* [#testng-adapter-1] Fixes issue where test start and end times were created as per the IReporter time of execution
* [#12] Chart increasing in size when resizing browser window

## [4.0.5](https://github.com/extent-framework/extentreports-java/compare/v4.0.4...v4.0.5)
#### Improvements
* Several layout improvements for BDD view
#### Fixes
* Adds missing ScreenCapture for steps

## [4.0.4](https://github.com/extent-framework/extentreports-java/compare/v4.0.3...v4.0.4)
#### Fixes
* Issue with BDD view showing charts in 2 rows

## [4.0.3](https://github.com/extent-framework/extentreports-java/compare/v4.0.2...v4.0.3)
#### Fixes
* Fixes build configuration

## [4.0.2](https://github.com/extent-framework/extentreports-java/compare/v4.0.1...v4.0.2)
#### New
* Add support for LoggerReporter to navigate from Category/Bug view to Test view
#### Fixes
* [#6] Fix issue where total time taken was 0
* [#7] Fix issue where markup was not parsed correctly in code blocks

## [4.0.1](https://github.com/extent-framework/extentreports-java/compare/v4.0.0...v4.0.1)
#### New
* [#4] Add support for Asterisk (*), BDD/Gherkin
* [#5] Auto-size pre/textarea/code size upon click
#### Fixes
* Disable checking for media existence as its causing issue with several Cucumber1 users
