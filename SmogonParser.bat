echo off
cls
:START
echo.
set /p Input=Enter move name: 
java -jar SmogonParser_DATA.jar %Input%
goto START