@echo off
echo ========================================
echo       Linka Application Launcher
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 21 or later
    pause
    exit /b 1
)

REM Check if Maven is installed
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven 3.6 or later
    pause
    exit /b 1
)

echo Starting Backend Server...
echo Backend will run on http://localhost:8081
start "Backend" cmd /c "cd Linka-Backend && mvn spring-boot:run"

echo.
echo Waiting for backend to start...
timeout /t 15 /nobreak >nul

echo.
echo Starting Frontend Server...
echo Frontend will run on http://localhost:8080
start "Frontend" cmd /c "cd Linka-Frontend && npm run dev"

echo.
echo ========================================
echo Both servers are starting...
echo Backend: http://localhost:8081
echo Frontend: http://localhost:8080
echo API Health Check: http://localhost:8081/api/health
echo.
echo Press any key to open the application in your browser...
pause >nul

start http://localhost:8080

echo.
echo Applications are running! Close the terminal windows to stop the servers.
pause