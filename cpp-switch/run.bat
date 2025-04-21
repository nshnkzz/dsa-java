@echo off

rem Compile the C++ code
make

rem Run the compiled executable
dsa.exe

rem Delete the compiled object and executable files
del *.o main.exe

pause