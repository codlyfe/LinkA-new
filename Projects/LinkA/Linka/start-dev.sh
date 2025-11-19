#!/bin/bash

echo "========================================"
echo "       Linka Application Launcher"
echo "========================================"
echo

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java 21 or later"
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    echo "Please install Maven 3.6 or later"
    exit 1
fi

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "ERROR: Node.js is not installed or not in PATH"
    echo "Please install Node.js 18 or later"
    exit 1
fi

# Check if npm is installed
if ! command -v npm &> /dev/null; then
    echo "ERROR: npm is not installed or not in PATH"
    echo "Please install npm"
    exit 1
fi

echo "Starting Backend Server..."
echo "Backend will run on http://localhost:8081"
echo "To stop the backend: Ctrl+C in this terminal or close the terminal window"
echo

# Start backend in background
cd Linka-Backend
mvn spring-boot:run &
BACKEND_PID=$!
cd ..

echo
echo "Waiting for backend to start..."
sleep 15

echo
echo "Starting Frontend Server..."
echo "Frontend will run on http://localhost:8080"
echo "To stop the frontend: Ctrl+C in this terminal or close the terminal window"
echo

# Start frontend in background
cd Linka-Frontend
npm run dev &
FRONTEND_PID=$!
cd ..

echo
echo "========================================"
echo "Both servers are starting..."
echo "Backend: http://localhost:8081"
echo "Frontend: http://localhost:8080"
echo "API Health Check: http://localhost:8081/api/health"
echo "========================================"
echo
echo "Press Ctrl+C to stop both servers"

# Function to cleanup background processes
cleanup() {
    echo
    echo "Shutting down servers..."
    kill $BACKEND_PID 2>/dev/null
    kill $FRONTEND_PID 2>/dev/null
    echo "Servers stopped."
    exit 0
}

# Trap Ctrl+C
trap cleanup INT

# Wait for both processes
wait