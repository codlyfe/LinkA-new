# LinkA Frontend Development Prompt

## Project Context
**LinkA** is Uganda's trusted marketplace application designed to connect verified buyers and sellers across Uganda. The platform emphasizes security, verification, and mobile-first experience to serve the Ugandan market effectively.

## Frontend Architecture Requirements

### Core Technology Stack
**Primary Framework**: React 18+
**Mobile Platform**: React Native with Expo
**Web Platform**: Next.js 14+ (App Router)
**State Management**: 
- React Context API / Zustand for global state
- TanStack Query (React Query) for server-state management
**Navigation**:
- Mobile: React Navigation
- Web: Next.js App Router
**UI Framework**:
- Mobile: Tamagui or NativeWind (Tailwind CSS for React Native)
- Web: Shadcn/ui (Tailwind CSS-based)

### Key Features to Implement

#### 1. Authentication & User Management
- JWT-based authentication integration with Spring Boot backend
- User registration/login with phone number verification (OTP via Twilio/Africa's Talking)
- User profile management with verification status
- Support for both Individual and Business account types
- NIN (National Identification Number) verification integration

#### 2. Listing Management
- Create, edit, and delete product/service listings
- Image upload with cloud storage integration (AWS S3/Google Cloud)
- Category management for Ugandan market
- Price specification with Uganda Shillings (UGX)
- Location-based listing with Google Maps integration
- Search and filter functionality

#### 3. User Verification System
- ID verification workflow
- Business license verification for business accounts
- Verification badge display
- Trust score calculation and display

#### 4. Communication System
- In-app messaging between buyers and sellers
- Push notifications via Expo Notifications
- Message history and management
- Media sharing in messages

#### 5. Location & Maps
- Google Maps integration for location picker
- Address geocoding
- Proximity-based listing search
- Map view for listings

#### 6. Payment Integration
- MTN Mobile Money integration
- Airtel Money integration
- Payment status tracking
- Transaction history

#### 7. Mobile-First Design
- Responsive design optimized for mobile devices
- Native-like performance and user experience
- Offline capability for basic browsing
- Fast loading with optimized images and caching

### Technical Implementation Guidelines

#### Code Organization
- Shared components between web and mobile where possible
- Custom hooks for API integration
- Error boundary implementation
- Loading states and skeleton screens

#### Performance Optimization
- Code splitting for web application
- Image optimization and lazy loading
- Efficient caching strategies with TanStack Query
- Bundle size optimization

#### Security Considerations
- Secure token storage
- Input validation and sanitization
- HTTPS enforcement
- XSS and CSRF protection

#### Ugandan Market Specific Requirements
- Support for Luganda and other local languages (future)
- Uganda-specific address formats
- Mobile money integration
- Low-bandwidth optimization
- Offline functionality for core features

### Integration Requirements

#### Backend API Integration
- RESTful API consumption from Spring Boot backend
- JWT token management and refresh
- File upload with signed URLs
- Real-time updates where applicable

#### Third-Party Services
- Google Maps Platform integration
- Twilio/Africa's Talking for OTP
- Push notification services
- Analytics tracking (Google Analytics/Firebase)

#### Development Standards
- TypeScript for type safety
- ESLint and Prettier for code quality
- Component testing with Jest/React Testing Library
- Storybook for component development

### UI/UX Guidelines
- Clean, modern design with accessibility compliance
- Consistent design system across platforms
- Intuitive navigation and user flows
- Fast loading and smooth animations
- Support for both light and dark themes

### Deployment Targets
- **Mobile**: iOS and Android via Expo Application Services (EAS)
- **Web**: Vercel hosting with CI/CD integration
- Progressive Web App (PWA) capabilities for web

## Success Metrics
- App performance score > 90 (Lighthouse)
- Core Web Vitals optimization
- User engagement metrics
- Conversion rate optimization
- Cross-platform consistency

## Development Priorities
1. Core authentication and user management
2. Listing creation and browsing
3. User verification system
4. Communication features
5. Payment integration
6. Advanced features and optimizations

---

**Note**: Focus on creating a robust, scalable, and user-friendly frontend that leverages the full potential of the modern React ecosystem while maintaining excellent performance and user experience across all platforms.