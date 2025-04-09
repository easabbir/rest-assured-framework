@echo off
REM Generate Allure report and open it in the default browser

echo Checking Allure installation...
where allure >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo Error: Allure commandline is not installed or not in PATH
    echo Please install Allure from https://docs.qameta.io/allure/
    pause
    exit /b
)

echo Generating Allure Report...
allure generate allure-results --clean -o allure-report

if %ERRORLEVEL% neq 0 (
    echo Error: Failed to generate Allure report
    echo Check if 'allure-results' directory exists with test results
    pause
    exit /b
)

echo Opening Allure Report...
start "" "allure-report\index.html"

REM Alternative opening methods if the above doesn't work:
REM allure open allure-report
REM OR
REM start chrome "allure-report\index.html"

pause