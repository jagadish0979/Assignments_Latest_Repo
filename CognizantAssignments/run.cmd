@REM ----------------------------------------------------------------------------
@REM CognizantAssignments Angular JS Integrated With Spring Boot Project Start Up Batch script
@REM ----------------------------------------------------------------------------

@echo off

echo.
echo "[run]Build Started........................................................."
echo.

call mvnw clean install

echo.
echo "[run]Build Completed Successfully *****************************************!"
echo "============================================================================"
echo.

echo.
echo "[run]Running Spring Boot Application......................................."
echo.
java -jar target\CognizantAssignments-0.0.1-SNAPSHOT.jar
