## [5.0.9](https://github.com/extent-framework/extentreports-java/compare/v5.0.9...v5.0.9)

#### Issues Resolved
- [#305](/issues/305) Fix HttpClient version-4.5.2 vulnerability
- [#310](/issues/310) Remove RawGit hosted templates
- [377354](/commit/99fa74ba6c745262ba17f66c11e39d0569377354) Update test status using generatedLog

## [5.0.8](https://github.com/extent-framework/extentreports-java/compare/v5.0.7...v5.0.8)

#### Issues Resolved
- [#292](/issues/292) SparkReporter - Exceptions - Overall count of failed tests is not correct
- [#293](/issues/293) SparkReporter - Timestamps - Datetime formats are not unified
- [#297](/issues/297) Nodes: improve node meta-info

#### New Features
- [#295](/issues/295) KlovReporter: Expose MongoClient

## [5.0.7](https://github.com/extent-framework/extentreports-java/compare/v5.0.6...v5.0.7)

#### Issues Resolved
- [#250](/issues/250) ConcurrentModificationException in ReportStats
- [#261](/issues/261) Fix issue with table creation from POJOs
- [#275](/issues/275) setEncoding not working on ExtentSparkReporter on 5.0.6
- [#282](/issues/282) Category tags are being duplicated if multiple tests have same tag

## [5.0.6](https://github.com/extent-framework/extentreports-java/compare/v5.0.5...v5.0.6)

#### Issues Resolved
- [#265](/issues/265) Alignment issue in authors table in the dashboard view
- [#273](/issues/273) ExtentKlovReporter: incorrect order for default @AllArgsConstructor of KlovMedia
- [#274](/issues/274) ExtentTest does not propagate generated-log to listener

#### New Features
- [#271](/issues/271) Update gherkin-languages.json

## [5.0.5](https://github.com/extent-framework/extentreports-java/compare/v5.0.4...v5.0.5)

#### Issues Resolved
- [#247](/issues/247) Spark Report - Wrong test durations displayed in tests tab
- [#259](/issues/259) ExtentSparkReporter constructor throws NullPointerException if path argument don`t contains parrent fodler
- [#267](/issues/267) Spark-css.css uses a googleapis.com import causing offline reports to attempt external connection

#### New Features
- [#269](/issues/269) KlovReporter: provide ability to specify a database name

## [5.0.4](https://github.com/extent-framework/extentreports-java/compare/v5.0.3...v5.0.4)

#### Issues Resolved
- [#240](/issues/240) Change static URI anchor behavior
- [#246](/issues/246) Collapse all nodes other than failure
- [#252](/issues/252) Nodes were using status color from the parent node
- [#235](/issues/235) [#236](/issues/236) Style updates

#### New Features
- [#233](/issues/233) ExtentSparkReporter does not show timeline in dashboard view

## [5.0.3](https://github.com/extent-framework/extentreports-java/compare/v5.0.2...v5.0.3)

#### Issues Resolved
- [#232](/issues/232) Display duration for child tests
- [#235](/issues/235) Critical: KlovReporter fails to upload ScreenCapture for events

## [5.0.2](https://github.com/extent-framework/extentreports-java/compare/v5.0.1...v5.0.2)

#### Issues Resolved
- [#103](/issues/103) Add missing lightbox for base64 screenshots
- [#220](/issues/220) Add missing tags for child tests
- [#222](/issues/222) Removed TestNG from main scope and added under `test`
- [#226](/issues/226) Perform null check for SystemEnvInfo elements
- [#227](/issues/227) Change SKIP priority to be higher than WARN

#### New Features
- [#223](/issues/223) Added attribute (tag) details for Klov
- [#224](/issues/224) All tags will be propagated down the test tree. An attribute assigned to a parent test
    would automatically be assigned to a child test also.
- [#225](/issues/225) All attribute views (Author, Device, Tag) now use the same template
- [#231](/issues/231) Exceptions from log will be assigned to its owning test

## [5.0.1](https://github.com/extent-framework/extentreports-java/compare/v5.0.0...v5.0.1)

#### Issues Resolved
- [#103](/issues/103) Clicking base64 images does not fire lightbox
- KlovReporter uses the ExtentReports 5.0.0-SNAPSHOT dependency, revert to RELEASE
- [#191](/issues/191) Navigation from Attributes/Exceptions views to test for BDD

## [5.0.0](https://github.com/extent-framework/extentreports-java/compare/v4.1.6...v5.0.0)

#### New Features
- [#47](/issues/47) Anchors for each test
- [#150](/issues/150) Unused status from charts will not be displayed
- [#152](/issues/152) Spark: Author view
- [#153](/issues/153) Spark: Device view
- [#157](/issues/157) Spark: Add TestRunnerLogs view
- [#168](/issues/168) Spark: Navigation from attributes pages (tags, exception) to Tests View
- [#173](/issues/173) Spark: Allow configuration to select view order, Dashboard view as primary
- [#176](/issues/176) Apply Status filters to report
- [#177](/issues/177) Minified templates (> 10% reduction in file size)
- [#184](/issues/184) Reporter::loadExternalConfiguration with json file
- [#188](/issues/188) Display upto 4 `pre` blocks on a single row
- [#191](/issues/191) Navigation from Tags/Exception pages to nested steps
- [#213](/issues/213) Toggle theme from URI component using '#theme='
- [#215](/issues/215) A test removed from the report will also be removed from MongoDB (Klov)

#### Issues Resolved
- [#131](/issues/131) Merging two html extent Reports works but Category view gets overwritten
- [#161](/issues/161) Merging ExtentSpark 4.1.5 Reports causing the existing base64 image removed
- [#169](/issues/169) Fix SparkReporter shortcuts
- [#197](/issues/197) MarkupHelper::codeBlock has extra indentation on the first line
- [#208](/issues/208) Show title text for screencaptures

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
