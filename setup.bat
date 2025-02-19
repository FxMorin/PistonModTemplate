@echo off
setlocal enabledelayedexpansion

:: Load config values
set CONFIG_FILE=setup.config
if not exist "%CONFIG_FILE%" (
    echo %CONFIG_FILE% file not found
    exit /b 1
)

:: Read values from config file
set "MOD_ID="
set "MOD_NAME="
set "MAVEN_GROUP="
for /f "tokens=1,2 delims==" %%A in (%CONFIG_FILE%) do (
    set "key=%%A"
    set "value=%%B"
    set "key=!key: =!"
    set "value=!value: =!"
    if "!key!"=="mod_id" set "MOD_ID=!value!"
    if "!key!"=="mod_name" set "MOD_NAME=!value!"
    if "!key!"=="maven_group" set "MAVEN_GROUP=!value!"
)

if "%MOD_ID%"=="" (
    echo mod_id is not set in %CONFIG_FILE%.
    exit /b 1
)

if "%MOD_NAME%"=="" (
    echo mod_name is not set in %CONFIG_FILE%.
    exit /b 1
)

if "%MAVEN_GROUP%"=="" (
    echo maven_group is not set in %CONFIG_FILE%.
    exit /b 1
)

:: Rename directories
for /d /r %%D in (*pistonmodtemplate*) do (
    set "newdir=%%D"
    set "newdir=!newdir:pistonmodtemplate=%MOD_ID%!"
    if not "%%D"=="!newdir!" ren "%%D" "!newdir!"
)

:: Rename files
for /r %%F in (*pistonmodtemplate*) do (
    set "newfile=%%F"
    set "newfile=!newfile:PistonModTemplate=%MOD_NAME%!"
    if not "%%F"=="!newfile!" ren "%%F" "!newfile!"
)

:: Replace mod_id occurrences in files
for /r %%F in (*) do (
    powershell -Command "(Get-Content '%%F') -replace 'pistonmodtemplate', '%MOD_ID%' | Set-Content '%%F'"
)
:: Replace mod_name occurrences in files
for /r %%F in (*) do (
    powershell -Command "(Get-Content '%%F') -replace 'PistonModTemplate', '%MOD_NAME%' | Set-Content '%%F'"
)

:: Change Maven Group
if not "%MAVEN_GROUP%"=="ca.fxco" (
    set "OLD_PACKAGE_PATH=ca.fxco"
    set "NEW_PACKAGE_PATH=%MAVEN_GROUP:.=\%"
    mkdir "src\main\java\%NEW_PACKAGE_PATH%"
    move "src\main\java\%OLD_PACKAGE_PATH%\*" "src\main\java\%NEW_PACKAGE_PATH%"
    rmdir /s /q "src\main\java\%OLD_PACKAGE_PATH%"
    for /r %%F in (src\main\java\*) do (
        powershell -Command "(Get-Content '%%F') | Where-Object { $_ -notmatch 'ca\.fxco\.pistonlib' } | ForEach-Object { $_ -replace 'ca\.fxco', '%MAVEN_GROUP%' } | Set-Content '%%F'"
    )
    :: Replace maven_group in gradle.properties
    powershell -Command "(Get-Content 'gradle.properties') -replace 'ca\.fxco', '%MAVEN_GROUP%' | Set-Content 'gradle.properties'"
)

:: Remove setup files
del /f /q "setup.bat" "setup.sh" "setup.config"

echo Setup completed successfully.