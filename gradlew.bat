@echo off
setlocal
set DIR=%~dp0
if exist "%DIR%gradle-8.10.2\bin\gradle.bat" (
  "%DIR%gradle-8.10.2\bin\gradle.bat" %*
) else (
  echo Gradle distribution not found. Run the setup step again.
  exit /b 1
)
