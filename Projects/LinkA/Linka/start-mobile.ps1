# LinkA Mobile Development Setup Script
# This script sets up the development environment for mobile testing

Write-Host "=========================================" -ForegroundColor Cyan
Write-Host "  LinkA Mobile Development Environment" -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "[1/4] Starting Backend Server (Port 8081)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-File", "Linka/start-dev.bat" -WindowStyle Normal

Start-Sleep -Seconds 3

Write-Host "[2/4] Starting Frontend Server (Port 5173)..." -ForegroundColor Yellow
Set-Location "Linka-Frontend"
Write-Host "Installing dependencies and starting Vite dev server..."
Start-Process powershell -ArgumentList "-Command", "npm install" -WindowStyle Minimized

Start-Sleep -Seconds 5

Write-Host "[3/4] Opening application in default browser..." -ForegroundColor Yellow
Start-Sleep -Seconds 10
Start-Process "http://localhost:5173"

Write-Host "[4/4] Development environment ready!" -ForegroundColor Green
Write-Host ""
Write-Host "Backend API: http://localhost:8081/api/health" -ForegroundColor White
Write-Host "Frontend App: http://localhost:5173" -ForegroundColor White
Write-Host "Mobile Testing: http://[YOUR_IP]:5173" -ForegroundColor Yellow
Write-Host ""
Write-Host "To find your local IP for mobile testing, run: ipconfig" -ForegroundColor Gray
Write-Host ""
Write-Host "Press any key to continue..." -ForegroundColor Gray
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")