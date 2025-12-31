# Random Universe Generator

[WIP]

Randomly generates a universe containing galaxies, star systems, stars, planets, moons, and lifeforms. Universes are generated via a REST API, can be saved in-memory and exported as JSON, and visualized in an interactive 3D map.

Built with Java, Spring Boot, NEXT.js, and more.

## Features

- **Random Generation**

    - Procedural universe generation with randomized data  

- **Hierarchical Entities**

    - Galaxies, star systems, stars, planets, moons, and lifeforms organized hierarchically  

- **REST API**

    - Endpoints for generation and querying of universe data  

- **Persistence & Export**

    - Persistence via Spring Data JPA (in-memory) and JSON snapshot export  

- **Interactive Frontend**

   -  Next.js frontend using React Three Fiber, Three.js, and zustand for an interactive 3D map  

- **CI/CD**

    - GitHub Actions pipeline for build, test, containerization, and optional deployment

## Getting Started

### Prerequisites

- Java 17+ (recommended 21)  
- Maven (or Gradle)  
- Node.js 18+ and npm/yarn  
- Docker (optional)  
- REST client (like Postman) or browser

### Build & Run Backend

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### Run Frontend (inside `random_universe_generator_front_end`)

```bash
cd frontend
npm install
npm run dev
# or
yarn install
yarn dev
```

Frontend default: http://localhost:3000

Backend default: http://localhost:8080

## API Endpoints

- `GET /health/` — Service healthcheck. Returns a string.

- `GET /universe/` — Primary service endpoint. (Not currently implemented)
- `GET /universe/random?saveToDatabase={true|false}&saveToFile={true|false}` — Generate a new universe snapshot  
- `GET /universe/fetch?id=` — Fetch a generated universe by ID.

- `GET /galaxy/random` — Generate a random galaxy
- `GET /galaxy/fetch?id=` — Fetch a generated galaxy by ID.

- `GET /star_system/random` — Generate a random star system
- `GET /star_system/fetch?id=` — Fetch a star system galaxy by ID.

- `GET /star/random` — Generate a random star
- `GET /star/fetch?id=` — Fetch a generated star by ID.

- `GET /planet/random` — Generate a random planet
- `GET /planet/fetch?id=` — Fetch a generated planet by ID.

- `GET /moon/random` — Generate a random moon
- `GET /moon/fetch?id=` — Fetch a generated moon by ID.

- `GET /lifeform/random` — Generate a random lifeform
- `GET /lifeform/fetch?id=` — Fetch a generated lifeform by ID.

## Entities

- **Universe**
    - Contains 1-10 galaxies for simplicity.

- **Galaxy**
    - Contains 1-1000 star systems
    - Properties:
        - Name
        - Age
        - Type

- **Star System**
    - Contains 1-7 stars and 0-12 planets
    - Properties:
        - Name
        - Age
        - Comets
        - Asteroids
        - Nebulae
        - Black Holes

- **Star**
    - Properties:
        - Name
        - Type
        - Mass
        - Diameter
        - Radius
        - Temperature
        - Luminosity
        - Age
        - Metallacity

- **Planet**
    - Contains 0-5 moons
    - Contains 0-10 lifeforms
    - Properties:
        - Name
        - Type
        - Mass
        - Life
        - Gravity
        - Orbital Period
        - Rotational Period
        - Surface Temperature
        - Atmosphere
        - Water
        - Color
        - Avg. Pressure
        - Axial Tilt
        - Distance from star
        - Rings
        - Escape Velocity

- **Moon**
    - Properties:
        - Name
        - Diameter
        - Mass
        - Gravity
        - Orbital Period
        - Rotational Period
        - Surface Temperature
        - Atmosphere
        - Tidal lock
        - Water
        - Color

- **Lifeform**
    - Properties:
        - Name
        - Lifespan
        - Intelligence
        - Avg. height
        - Avg. weight
        - Kardashev Scale
        - Habitat
        - Diet
        - Reproduction Method

## Persistence & Export

- Database: Spring Data JPA repositories for querying and persistence  
- JSON Files: serialized snapshots stored on disk for archival or external analysis

## Continuous Integration & Deployment

GitHub Actions workflow includes:

- Checkout and JDK (21) setup  
- Maven dependency caching, tests, and packaging  
- Docker image build and push  
- Optional deployment (Render, EC2) via API

Secrets required (examples):

- `DOCKERHUB_USERNAME`, `DOCKERHUB_TOKEN`  
- `RENDER_SERVICE_ID`, `RENDER_API_KEY` (if deploying to Render)

## Frontend Highlights

- Next.js 13+ with React Three Fiber  
- 3D universe map with clickable entities 
- 3D models, smooth orbit controls, info panels  

## License & Credits

## License & Credits

### 3D Models

- **Galaxy**: ["Galaxy" model](https://skfb.ly/6xEsD) by 991519166 — licensed under **Creative Commons Attribution 4.0 (CC BY 4.0)**.
- **Planets**: [Planet models](https://sketchfab.com/3d-models/various-planets-73c6c24e7c40409cafc6adacd5d71ff5) by Feivelyn.
- **Sun**: [Sun model](https://sketchfab.com/3d-models/sun-9ef1c68fbb944147bcfcc891d3912645) by SebastianSosnowski.
- **Moon**: [Ganymede Moon model](https://sketchfab.com/3d-models/ganymede-moon-7021e376ecb04b89b11fcbe017e48e8c) by SebastianSosnowski.
- **Ringed Gas Giant**: [Ringed gas giant planet model](https://sketchfab.com/3d-models/ringed-gas-giant-planet-b4e21775e84e4cf3890f7796cfab1aca) by berzerkey.
- **Ice Planet**: [Ice planet (procedural textures) model](https://sketchfab.com/3d-models/ice-planet-with-procedural-textures-68965317ff6f4338a79f6d373e2ae9fe) by imperioame.
- **Lava Planet**: [Lava planet model](https://sketchfab.com/3d-models/lava-planet-9792612f76184ee3b48530c7515ce747) by FPSunreal.

### Favicon

- Favicon emoji graphic is from **Twemoji** (open source). Graphics copyright 2020 Twitter, Inc and other contributors.

## Contact

Open an issue or reach out via the repository for questions or support.
