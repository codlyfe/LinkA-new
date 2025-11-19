Write-Host "========================================" -ForegroundColor Cyan
Write-Host "       Linka Application Launcher" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if Java is installed
try {
    java -version | Out-Null
} catch {
    Write-Host "ERROR: Java is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Java 21 or later" -ForegroundColor Yellow
    exit 1
}

# Check if Maven is installed
try {
    mvn -version | Out-Null
} catch {
    Write-Host "ERROR: Maven is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Maven 3.6 or later" -ForegroundColor Yellow
    exit 1
}

# Check if Node.js is installed
try {
    node -v | Out-Null
} catch {
    Write-Host "ERROR: Node.js is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Node.js 18 or later" -ForegroundColor Yellow
    exit 1
}

Write-Host "Starting Backend Server..." -ForegroundColor Green
Write-Host "Backend will run on http://localhost:8081" -ForegroundColor Yellow
Write-Host "To stop the backend: Ctrl+C in the terminal window" -ForegroundColor Yellow
Write-Host ""

# Start backend in background
Start-Process -FilePath "cmd.exe" -ArgumentList "/k", "cd Linka-Backend && mvn spring-boot:run" -WindowStyle Normal -WorkingDirectory $PWD

Start-Sleep -Seconds 2

Write-Host "Starting Frontend Server..." -ForegroundColor Green
Write-Host "Frontend will run on http://localhost:8080" -ForegroundColor Yellow
Write-Host "To stop the frontend: Ctrl+C in the terminal window" -ForegroundColor Yellow
Write-Host ""

# Start frontend in background
Start-Process -FilePath "cmd.exe" -ArgumentList "/k", "cd Linka-Frontend && npm run dev" -WindowStyle Normal -WorkingDirectory $PWD

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Both servers are starting..." -ForegroundColor Green
Write-Host "Backend: http://localhost:8081" -ForegroundColor Yellow
Write-Host "Frontend: http://localhost:8080" -ForegroundColor Yellow
Write-Host "API Health Check: http://localhost:8081/api/health" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Wait a moment and then open browser
Start-Sleep -Seconds 10
Start-Process "http://localhost:8080"

Write-Host "Applications are running!" -ForegroundColor Green
Write-Host "Close the terminal windows to stop the servers." -ForegroundColor Yellow