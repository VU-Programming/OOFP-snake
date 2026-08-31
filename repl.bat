@echo off
for /f "delims=" %%i in ('gradlew.bat -q printRuntimeClasspath') do set "CP=%%i"
java -cp "%CP%" dotty.tools.MainGenericRunner -usejavacp