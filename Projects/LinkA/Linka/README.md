# LinkA - Full Stack Application

LinkA is a full-stack marketplace application for Uganda, built with Spring Boot (backend) and React with TypeScript (frontend).

## 🏗️ Architecture

- **Backend**: Spring Boot 3.5.7 with Java 21
- **Frontend**: React 18 with TypeScript, Vite, Tailwind CSS, and shadcn/ui
- **Database**: Configured for PostgreSQL (can be switched to other databases)
- **Development**: Hot reload for both frontend and backend

## 🚀 Quick Start

### Prerequisites

Before running the application, ensure you have the following installed:

- **Java 21** or later
- **Maven 3.6** or later  
- **Node.js 18** or later
- **npm** (comes with Node.js)

### Option 1: Automated Launch Scripts

#### Windows Users
```bash
# Double-click or run in Command Prompt/PowerShell
Linka\start-dev.bat

# Or for PowerShell users
powershell -ExecutionPolicy Bypass -File Linka\start-dev.ps1
```

#### Unix/Linux/macOS Users
```bash
# Make executable and run
chmod +x Linka/start-dev.sh
./Linka/start-dev.sh
```

### Option 2: Manual Launch

#### Start Backend
```bash
cd Linka-Backend
mvn spring-boot:run
```
Backend runs on: http://localhost:8081

#### Start Frontend (in a new terminal)
```bash
cd Linka-Frontend  
npm install
npm run dev
```
Frontend runs on: http://localhost:8080

## 🔧 Configuration

### Backend Configuration
Location: `Linka-Backend/src/main/resources/application.yaml`

Key settings:
- **Port**: 8081 (configurable)
- **CORS**: Configured to allow frontend at http://localhost:8080
- **Profiles**: Development profile active by default

### Frontend Configuration  
Location: `Linka-Frontend/vite.config.ts`

Key settings:
- **Port**: 8080 (configurable)
- **API Proxy**: Automatically proxies `/api` requests to backend

## 📡 API Endpoints

The backend provides the following endpoints:

- `GET /api/health` - Health check
- `GET /api/info` - Application information
- `GET /actuator/*` - Spring Boot Actuator endpoints

### Example API Usage

```javascript
// Check backend health
fetch('/api/health')
  .then(response => response.json())
  .then(data => console.log(data));

// Expected response:
{
  "status": "UP",
  "message": "Backend is running successfully", 
  "timestamp": "2025-11-19T16:21:39",
  "application": "Linka Backend"
}
```

## 🌟 Features Implemented

### Backend
- ✅ Spring Boot application with proper configuration
- ✅ CORS enabled for frontend integration  
- ✅ Health check and info endpoints
- ✅ Database auto-configuration excluded (ready for future use)
- ✅ Development logging configured

### Frontend  
- ✅ React 18 with TypeScript
- ✅ Vite for fast development
- ✅ Tailwind CSS for styling
- ✅ shadcn/ui component library
- ✅ React Router for navigation
- ✅ API service for backend communication
- ✅ Backend connection status indicator
- ✅ Responsive design

### Launch Scripts
- ✅ Windows batch script (.bat)
- ✅ PowerShell script (.ps1) 
- ✅ Unix/Linux shell script (.sh)
- ✅ Automatic dependency checking
- ✅ Sequential startup with proper delays
- ✅ Browser auto-opening

## 🔄 Development Workflow

1. **Run the launch script** or start both servers manually
2. **Frontend** automatically proxies API calls to backend
3. **Backend** serves on port 8081, **Frontend** on port 8080
4. **Hot reload** works for both frontend and backend
5. **CORS** is configured for seamless development

## 🛠️ Troubleshooting

### Backend won't start
- Check Java version: `java -version` (should be 21+)
- Check Maven installation: `mvn -version` (should be 3.6+)
- Verify port 8081 is available

### Frontend won't start  
- Check Node.js version: `node -version` (should be 18+)
- Check npm installation: `npm --version`
- Verify port 8080 is available
- Install dependencies: `npm install` in Linka-Frontend directory

### CORS Errors
- Ensure backend is running on port 8081
- Check CORS configuration in `WebConfig.java`
- Verify frontend is running on port 8080

### API Connection Failed
- Backend must be running before frontend can connect
- Check backend health: http://localhost:8081/api/health
- Verify Vite proxy configuration

## 📁 Project Structure

```
Linka/
├── Linka-Backend/
│   ├── src/main/java/com/Linka/backend/
│   │   ├── BackendApplication.java     # Main application
│   │   ├── config/WebConfig.java       # CORS configuration
│   │   └── controller/HealthController.java # API endpoints
│   └── src/main/resources/
│       └── application.yaml            # Backend configuration
├── Linka-Frontend/
│   ├── src/
│   │   ├── services/api.ts             # API service
│   │   ├── pages/Home.tsx              # Home page with backend status
│   │   └── ...                         # Other React components
│   └── vite.config.ts                  # Frontend configuration
├── start-dev.bat                       # Windows launch script
├── start-dev.ps1                       # PowerShell launch script
├── start-dev.sh                        # Unix/Linux launch script
└── README.md                           # This file
```

## 🚀 Next Steps

This setup provides a solid foundation for developing the full LinkA marketplace application. You can now:

1. **Add database integration** - Configure JPA and add entities
2. **Implement authentication** - Add JWT-based auth system  
3. **Create more API endpoints** - Build out marketplace functionality
4. **Add more frontend pages** - Build out the user interface
5. **Deploy to production** - Configure for production environment

## 🔗 Links

- Frontend: http://localhost:8080
- Backend API: http://localhost:8081  
- Backend Health: http://localhost:8081/api/health
- Backend Actuator: http://localhost:8081/actuator

---

Made with ❤️ for the Uganda marketplace