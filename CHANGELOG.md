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
