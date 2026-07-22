# India Data Center Water Risk Tracker

> AI data centers across India are consuming millions of liters of groundwater daily to cool their servers. Almost none of them are required to report how much. This project puts what's known — and what's being hidden — in one place.

## The Problem

- Yotta's data center park in Greater Noida is projected to consume **over 9 million liters per day** once fully operational
- Groundwater near Amazon's data center in Hyderabad **dropped almost a meter in four months**
- An RTI filed to get real consumption numbers returned a response from the government agency that wrote the data center policy: **"no data available"**

The gaps in the data aren't a bug in this project. They're the story this project tells.

## What This Project Does

A live, publicly accessible dashboard that tracks:
- Real data center locations across India (city, district, state, coordinates)
- Groundwater stress levels for each location
- Water usage records where available — and flags where data is missing
- A **risk score** that penalizes both high water usage AND lack of transparency

## Live Demo
🔗 *Deployment in progress — live link will be added once deployed*

## Tech Stack

**Backend**
- Java 21
- Spring Boot 4.1.0
- Spring Data JPA + Hibernate
- MySQL 8
- Maven

**Frontend**
- HTML5, CSS3, Vanilla JavaScript
- Native `fetch()` API — no framework

**Deployment**
- Backend: Railway / Render
- Frontend: GitHub Pages
- Database: Managed MySQL (cloud)

## Project Status

- [x] Backend setup — Spring Boot + MySQL connected
- [x] All 4 entities created and mapped to MySQL tables
- [x] All 4 repositories created
- [x] DataCenter CRUD API — GET, POST, PUT, DELETE all working
- [x] 10 real data centers seeded with verified, source-backed data
- [x] Risk scores calculated — 1 CRITICAL, 5 HIGH, 4 MEDIUM
- [x] AI summary endpoint — `/api/waterriskscores/summary/{id}`
- [x] Multi-turn AI chat endpoint — `POST /api/chat` with facility context
- [x] Frontend v2 — dark-themed scroll-driven storytelling dashboard
- [x] Real India SVG map with pulsing markers and company logos
- [x] GSAP ScrollTrigger animations
- [ ] Deployment — live URL coming soon

## Data Centers Tracked
| Data Center | Company | Location | Risk Level |
|-------------|---------|----------|------------|
| Yotta D1 | Yotta | Greater Noida, UP | CRITICAL |
| AWS Hyderabad | Amazon | Rangareddy, Telangana | HIGH |
| Google Cloud | Google | Vizag, Andhra Pradesh | HIGH |
| Microsoft Azure | Microsoft | Pune, Maharashtra | HIGH |
| NTT Data Center | NTT | Mumbai, Maharashtra | HIGH |
| AdaniConneX | Adani | Chennai, Tamil Nadu | HIGH |
| STT GDC | STT | Bangalore, Karnataka | MEDIUM |
| Meta-Sify | Meta | Vizag, Andhra Pradesh | MEDIUM |
| TCS Data Center | TCS | Mumbai, Maharashtra | MEDIUM |
| Reliance Jio | Reliance | Nagpur, Maharashtra | MEDIUM |

## Why I Built This

I'm a 3rd-year BCA student from Greater Noida — the same district where one of India's
largest data center parks is being built. When I found out there was no public tool
tracking the water impact of this industry, I built one.

## Running Locally

```bash
# Clone the repository
git clone https://github.com/Rajat-Builds/india-water-risk-tracker.git

# Set up MySQL
mysql -u root -p
CREATE DATABASE water_risk_tracker;

# Configure credentials
# Edit src/main/resources/application.properties
# Set your MySQL username and password

# Run the application
./mvnw spring-boot:run
```

## Data Sources
- Central Ground Water Board (CGWB) reports
- RTI responses and investigative journalism
- Company sustainability reports (where available)
- Manual research and public records
