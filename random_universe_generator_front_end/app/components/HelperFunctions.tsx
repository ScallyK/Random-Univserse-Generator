import { useEffect } from "react";
import { type ComponentType } from "react";
import { AcidPlanetModel, DesertPlanetModel, EarthLikePlanetModel, IcePlanetModel, LavaPlanetModel, MarsLikePlanetModel, MoonLikePlanetModel, RingedGasGiantModel, VolcanicPlanetModel } from "../models/MapModels";

// Helper function called by maps or objects, and other stuff like interfaces for props and data structures

// Generates a random scale for different types of objects
export function generateRandomScale(objectType: string): number {

    switch (objectType) {

        case "star":
            return Math.random() * 0.01 + 0.05;

        case "planet":
            return Math.random() * 0.35 + 0.25;

        case "moon":
            return Math.random() * 0.0005 + 0.001;

        case "galaxy":
            return Math.random() * 20 + 5;

        default:
            return 1; // Default scale
    }
}

// Generates a random rotation speed for different types of objects
export function generateRandomRotationSpeed(objectType: string): number {

    switch (objectType) {

        case "star":
            return Math.random() * 0.015 + 0.005;

        case "planet":
            return Math.random() * 0.02 + 0.01;

        case "moon":
            return Math.random() * 0.03 + 0.01;

        case "galaxy":
            return Math.random() * 0.004 + 0.001;

        default:
            return 0.01;
    }
}

// Randomly positions galaxies in 3D space using the golden angle method
export function computeGalaxyPosition(index: number, _total: number): [number, number, number] {

    const goldenAngle = Math.PI * (3 - Math.sqrt(5)); // ~2.399963
    const radius = 5 + 30 * 5.0;
    const angle = index * goldenAngle;
    
    const x = Math.cos(angle) * radius;
    const z = Math.sin(angle) * radius;

    // small vertical jitter so everything isn't flat
    const y = ((index % 5) - 2) * 100;
    return [x, y, z];
}

// Randomly positions stars in the star system map
export function computeStarPosition(index: number, _total: number): [number, number, number] {

    // Position stars in a single horizontal line
    const spacing = 3;
    const xOffset = (index - (_total - 1) / 2) * spacing;

    return [xOffset, 0, 0];
}

// Randomly positions planets in the star system map
export function computePlanetPosition(starPosition: [number, number, number], planetIndex: number): [number, number, number] {

    // Position planets vertically below the star, aligned on X axis
    const verticalSpacing = 1.2;
    const verticalOffset = -1.5 - planetIndex * verticalSpacing;

    return [starPosition[0], verticalOffset, starPosition[2]];
}

// Randomly positions moons around their parent planet in the planet map
export function computeMoonPosition(planetPosition: [number, number, number], moonIndex: number): [number, number, number] {

    const angle = (moonIndex / 5) * Math.PI * 2;
    const radius = 0.75;

    const x = planetPosition[0] + Math.cos(angle) * radius;
    const y = planetPosition[1] + Math.sin(angle) * radius * 0.5;
    const z = planetPosition[2] + Math.sin(angle) * radius;

    return [x, y, z];
}

// Creates two lookup tables for scales and rotation speeds for stars and planets in a star system keyed by their IDs
export function memorizeScaleAndRotation(objectType: "star" | "planet" | "moon", celestialBody: StarSystemData | PlanetData) {

    if (objectType === "planet") {

        celestialBody as StarSystemData;

        const scales: { [id: number]: number } = {};
        const rotationSpeeds: { [id: number]: number } = {};

        (celestialBody as StarSystemData).planets.forEach(planet => {
            scales[planet.id] = generateRandomScale("planet");
            rotationSpeeds[planet.id] = generateRandomRotationSpeed("planet");
        });

        return { scales, rotationSpeeds };
    }

    if (objectType === "moon") {

        celestialBody as PlanetData;

        const scales: { [id: number]: number } = {};
        const rotationSpeeds: { [id: number]: number } = {};

        (celestialBody as PlanetData).moons.forEach((moon: any) => {
            scales[moon.id] = generateRandomScale("moon");
            rotationSpeeds[moon.id] = generateRandomRotationSpeed("moon");
        });

        return { scales, rotationSpeeds };
    }

    // Must be star
    else {

        celestialBody as StarSystemData;
        const scales: { [id: number]: number } = {};
        const rotationSpeeds: { [id: number]: number } = {};

        (celestialBody as StarSystemData).stars.forEach(star => {
            scales[star.id] = generateRandomScale("star");
            rotationSpeeds[star.id] = generateRandomRotationSpeed("star");
        });

        return { scales, rotationSpeeds };
    }
}

export function persistintlyAssignPlanetsToStars(starSystem: StarSystemData, setPlanetsByStarId: (map: { [starId: number]: any[] }) => void) {

    // Will run after render and on dependency changes (like ids, planets, stars)
    useEffect(() => {

        // Builds cache key based on star system id and stores it in local storage
        const cacheKey = `starSystemPlanets_${starSystem.id}`;
        const cached = localStorage.getItem(cacheKey);

        // If cached data exists, use it
        if (cached) {

            // Attempt to set each planet to it's star by using the data that is in the cache
            try {
                setPlanetsByStarId(JSON.parse(cached));
                return;
            } 

            catch {
                // If parse fails, time to regenerate
            }
        }

        // Generate a new mapping of planets to stars
        const map: { [starId: number]: any[] } = {};

        // For each planet, randomly assign it to one of the stars in the star system
        starSystem.planets.forEach((planet) => {

            // Randomly pick a star index
            const randomStarIdx = Math.floor(Math.random() * starSystem.stars.length);
            const starId = starSystem.stars[randomStarIdx].id;

            // Star ID becomes bucket for planets
            if (!map[starId]) map[starId] = [];

            // Push planet to that star's bucket if it isn't already there
            map[starId].push(planet);
        });

        // Set the mapping state and cache it in local storage
        setPlanetsByStarId(map);
        localStorage.setItem(cacheKey, JSON.stringify(map));

    }, [starSystem.id, starSystem.planets, starSystem.stars]); // Dependency array (re-run if star system changes based on these vars)
}

// Interfaces

export interface GalaxyData {
    id: number;
    name: string;
    position: [number, number, number];
    age: number;
    type: string;
    starSystems: any[];
}

export interface StarSystemData {
    id: number;
    name: string;
    position?: [number, number, number];
    age: number;
    stars: any[];
    planets: any[];
    hasComets: boolean;
    hasAsteroids: boolean;
    hasNebulae: boolean;
    hasBlackHoles: boolean;
}

export interface StarData {
    name: string;
    starType: string;
    mass: number;
    diameter: number;
    temperature: number;
    luminosity: number;
    radius: number;
    age: number;
    metallacity: number;
}

export interface PlanetData {
    id: number;
    name: string;
    planetType: string;
    diameter: number;
    mass: number;
    moons: any[];
    planetHasLife: boolean | null;
    lifeforms: any[];
    gravity: number;
    orbitalPeriod: number;
    rotationalPeriod: number;
    surfaceTemperature: number;
    hasAtmosphere: boolean;
    hasWater: boolean;
    color: string;
    averagePressure: number;
    axialTilt: number;
    distanceFromStar: number;
    hasRings: boolean;
    escapeVelocity: number;
}

export interface MoonData {
    id: number;
    name: string;
    diameter: number;
    mass: number;
    gravity: number;
    orbitalPeriod: number;
    rotationalPeriod: number;
    surfaceTemperature: number;
    isTidallyLocked: boolean;
    hasWater: boolean;
    color: string;
}

export interface LifeformData {
    name: string;
    lifespan: number;
    intelligent: boolean;
    averageHeight: number;
    averageWeight: number;
    kardashevScale: string;
    habitat: string;
    diet: string;
    reproductionMethod: string;
}

export interface GalaxyProps {
    position: [number, number, number];
    name: string;
    onClick: () => void;
    starSystems?: StarSystemData[];
    isSelected?: boolean;
}

export interface StarProps {
    name: string;
    position?: [number, number, number];
    onClick?: () => void;
    isSelected?: boolean;
    scale?: number;
    rotationSpeed?: number;
}

export interface PlanetProps {
    name: string;
    position?: [number, number, number];
    onClick?: () => void;
    onDoubleClick?: () => void;
    isSelected?: boolean;
    planetType: PlanetTypeName;
    scale?: number;
    rotationSpeed?: number;
}

export interface MoonProps {
    name: string;
    position?: [number, number, number];
    onClick?: () => void;
    isSelected?: boolean;
    scale?: number;
    rotationSpeed?: number;
}

export const PlanetTypes = {
    CHTHONIAN: VolcanicPlanetModel,
    CARBON: AcidPlanetModel,
    CORELESS: RingedGasGiantModel,
    DESERT: DesertPlanetModel,
    GAS_DWARF: RingedGasGiantModel,
    GAS_GIANT: RingedGasGiantModel,
    HELIUM: RingedGasGiantModel,
    HYCEAN: IcePlanetModel,
    ICE_GIANT: IcePlanetModel,
    ICE: IcePlanetModel,
    IRON: MoonLikePlanetModel,
    LAVA: LavaPlanetModel,
    OCEAN: EarthLikePlanetModel,
    PROTOPLANET: MoonLikePlanetModel,
    PUFFY: RingedGasGiantModel,
    SOOT: MarsLikePlanetModel,
    STEAM_WORLD: RingedGasGiantModel,
    SUPER_PUFF: RingedGasGiantModel,
    SILICATE: MarsLikePlanetModel,
    TERRESTRIAL: EarthLikePlanetModel,
    CATASTROPHICALLY_EVAPORATING: VolcanicPlanetModel,
    EARTH_ANALOG: EarthLikePlanetModel,
    HYPOTHETICAL_PLANET: AcidPlanetModel,
} as const satisfies Record<string, ComponentType<any>>;

export type PlanetTypeName = keyof typeof PlanetTypes;

export enum PlanetTypeLabel {
    CHTHONIAN = "Chthonian Planet",
    CARBON = "Carbon Planet",
    CORELESS = "Coreless Planet",
    DESERT = "Desert Planet",
    GAS_DWARF = "Gas Dwarf",
    GAS_GIANT = "Gas Giant",
    HELIUM = "Helium Planet",
    HYCEAN = "Hycean Planet",
    ICE_GIANT = "Ice Giant",
    ICE = "Ice Planet",
    IRON = "Iron Planet",
    LAVA = "Lava Planet",
    OCEAN = "Ocean Planet",
    PROTOPLANET = "Protoplanet",
    PUFFY = "Puffy Planet",
    SOOT = "Soot Planet",
    STEAM_WORLD = "Steam World",
    SUPER_PUFF = "Super-puff",
    SILICATE = "Silicate Planet",
    TERRESTRIAL = "Terrestrial Planet",
    CATASTROPHICALLY_EVAPORATING = "Catastrophically Evaporating Planet",
    EARTH_ANALOG = "Earth Analog",
    HYPOTHETICAL_PLANET = "Hypothetical Planet",
}

export enum StarTypeLabel {
    MAIN_SEQUENCE = "Main Sequence Star",
    GIANT = "Giant Star",
    WHITE_DWARF = "White Dwarf Star",
    NEUTRON_STAR = "Neutron Star",
    RED_DWARF = "Red Dwarf Star",
    BROWN_DWARF = "Brown Dwarf Star",
    SUPERGIANT = "Supergiant Star",
    BLUE_GIANT = "Blue Giant Star",
    PROTOSTAR = "Protostar"
}