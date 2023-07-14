@echo off
REM OneDriveBackup for WorldHistory data files and reports

if not exist "%userprofile%\OneDrive\Documents\App_Data_and_Reporting_Backups\WorldHistory\" mkdir %userprofile%\OneDrive\Documents\App_Data_and_Reporting_Backups\WorldHistory

xcopy *.xml %userprofile%\OneDrive\Documents\App_Data_and_Reporting_Backups\WorldHistory /Y

xcopy out\*.* %userprofile%\OneDrive\Documents\App_Data_and_Reporting_Backups\WorldHistory\out /I /Y
