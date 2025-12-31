"use client"
import React, { useState, useEffect, useMemo } from "react";
import { Canvas } from "@react-three/fiber";
import { Stars, Line, MapControls } from "@react-three/drei";
import Star from "../objects/Star";
import Planet from "../objects/Planet";
import styles from "../../stylesheets/StarSystemMap.module.css";
import PlanetMap from "./PlanetMap";
import { computeStarPosition, computePlanetPosition, PlanetTypeLabel, StarTypeLabel, StarData, PlanetData, StarSystemData, memorizeScaleAndRotation, persistintlyAssignPlanetsToStars } from "../HelperFunctions";

// Renders a map of a star system with its stars and planets in a 3D canvas
export default function StarSystemMap({ starSystem, onBack }: { starSystem: StarSystemData; onBack: () => void }) {

    const [selectedStar, setSelectedStar] = useState<StarData | null>(null);                    // Holds currently selected star
    const [selectedPlanet, setSelectedPlanet] = useState<PlanetData | null>(null);              // Holds currently selected planet for details view
    const [navigateToPlanet, setNavigateToPlanet] = useState<PlanetData | null>(null);          // Holds currently selected planet to navigate to its detailed map
    const [planetsByStarId, setPlanetsByStarId] = useState<{ [starId: number]: any[] }>({});    // Helps map planets to their respective stars
    const [isMobile, setIsMobile] = useState(false);                                            // Tracks if viewport is mobile-sized

    useEffect(() => {
        const checkIsMobile = () => setIsMobile(window.innerWidth <= 768);

        checkIsMobile();
        window.addEventListener("resize", checkIsMobile);

        return () => window.removeEventListener("resize", checkIsMobile);
    }, []);

    // Memoize scales and rotation speeds to prevent regeneration on re-render
    const starScales = useMemo(() => memorizeScaleAndRotation("star", starSystem).scales, [starSystem.id]);
    const starRotationSpeeds = useMemo(() => memorizeScaleAndRotation("star", starSystem).rotationSpeeds, [starSystem.id]);
    const planetScales = useMemo(() => memorizeScaleAndRotation("planet", starSystem).scales, [starSystem.id]);
    const planetRotationSpeeds = useMemo(() => memorizeScaleAndRotation("planet", starSystem).rotationSpeeds, [starSystem.id]);

    // Initialize planet-to-star assignment once per star system, persisted to localStorage
    persistintlyAssignPlanetsToStars(starSystem, setPlanetsByStarId);

    // When set true, renders the planet map and hides the star system map
    if (navigateToPlanet) {
        return (
            <PlanetMap
                planet={navigateToPlanet}
                onBack={() => setNavigateToPlanet(null)}
            />
        );
    }

    // was going to use these for camera controls but decided against it for now
    const cameraZ = 15;
    const cameraX = 0;
    const cameraY = 0;

    return (
        <>

            <div className={styles.header}>
                <button className={styles.back_button} onClick={onBack}>Back</button>
                <h2 className={styles.title}>{starSystem.name}</h2>
                <div className={styles.star_system_details}>
                    <p>Age: <span className={styles.star_system_details_subtext}>{starSystem.age.toPrecision(3)}M Years</span></p>
                    <p>Comets: <span className={styles.star_system_details_subtext}>{starSystem.hasComets ? "Yes" : "No"}</span></p>
                    <p>Asteroids: <span className={styles.star_system_details_subtext}>{starSystem.hasAsteroids ? "Yes" : "No"}</span></p>
                    <p>Nebulae: <span className={styles.star_system_details_subtext}>{starSystem.hasNebulae ? "Yes" : "No"}</span></p>
                    <p>Black Holes: <span className={styles.star_system_details_subtext}>{starSystem.hasBlackHoles ? "Yes" : "No"}</span></p>
                    <p>Stars: <span className={styles.star_system_details_subtext}>{starSystem.stars.length}</span></p>
                    <p>Planets: <span className={styles.star_system_details_subtext}>{starSystem.planets.length}</span></p>
                </div>
            </div>

            <Canvas
                camera={{ position: [cameraX, cameraY, cameraZ], fov: 60 }}
                onPointerMissed={(e) => {

                    // Clear selection when clicking on empty space
                    if (e.button !== 0) return;
                    setSelectedStar(null);
                    setSelectedPlanet(null);
                }}
            >

                <ambientLight intensity={1.2} />

                <pointLight position={[6, 6, 6]} />
                
                <Stars />

                {starSystem.stars.map((star: any, idx: number) => {

                    // compute a fallback position if the star doesn't provide one
                    const position: [number, number, number] = star.position ? star.position : computeStarPosition(idx, starSystem.stars.length);

                    // Get planets for this star
                    const planetsForThisStar = planetsByStarId[star.id] || [];

                    // Create line points from star through all planets for this star by accumulating positions
                    const linePoints: [number, number, number][] = [position];

                    // Add planet positions to line points
                    planetsForThisStar.forEach((_: any, pIdx: number) => {
                        linePoints.push(computePlanetPosition(position, pIdx));
                    });

                    return (
                        <React.Fragment key={star.id}>
                            <Star
                                name={star.name}
                                position={position}
                                isSelected={selectedStar?.name === star.name}
                                onClick={() => { setSelectedStar(star); setSelectedPlanet(null); }}
                                scale={starScales[star.id]}
                                rotationSpeed={starRotationSpeeds[star.id]}
                            />

                            {planetsForThisStar.map((planet: any, pIdx: number) => {

                                const planetPosition = computePlanetPosition(position, pIdx);

                                return (
                                    <Planet
                                        key={planet.id}
                                        name={planet.name}
                                        position={planetPosition}
                                        planetType={planet.planetType}
                                        isSelected={!!(selectedPlanet && (selectedPlanet as PlanetData).name === planet.name)}
                                        onClick={() => { setSelectedPlanet(planet); setSelectedStar(null); }}
                                        onDoubleClick={() => setNavigateToPlanet(planet)}
                                        scale={planetScales[planet.id]}
                                        rotationSpeed={planetRotationSpeeds[planet.id]}
                                    />
                                );
                            })}

                            {linePoints.length > 1 && (
                                <Line points={linePoints} color="white" lineWidth={1} />
                            )}

                        </React.Fragment>
                    );
                })}


                <MapControls
                    enableZoom={false}
                    enableRotate={false}

                    // These dont seem to lock the camera properly, but leaving them in for now
                    minDistance={15}
                    maxDistance={20}
                    minPolarAngle={Math.PI / 2}
                    maxPolarAngle={Math.PI / 2}
                />
            </Canvas>


            {(!isMobile || selectedStar) && (
                <div className={styles.star_information_container}>
                    {selectedStar && (
                        <>
                            <h2>Star: <strong>{selectedStar.name || "Star"}</strong></h2>
                            <hr className={styles.navigation_divider}/>

                            <p>Type: <span className={styles.star_information_container_subtext}>{StarTypeLabel[selectedStar.starType as keyof typeof StarTypeLabel]}</span></p>
                            <p>Mass: <span className={styles.star_information_container_subtext}>{selectedStar.mass.toPrecision(3)}M</span></p>
                            <p>Diameter: <span className={styles.star_information_container_subtext}>{selectedStar.diameter.toPrecision(3)}km</span></p>
                            <p>Temperature: <span className={styles.star_information_container_subtext}>{selectedStar.temperature.toPrecision(3)}K</span></p>
                            <p>Luminosity: <span className={styles.star_information_container_subtext}>{selectedStar.luminosity.toPrecision(3)}L</span></p>
                            <p>Radius: <span className={styles.star_information_container_subtext}>{selectedStar.radius.toPrecision(3)}R</span></p>
                            <p>Age: <span className={styles.star_information_container_subtext}>{selectedStar.age.toPrecision(3)}M years</span></p>
                            <p>Metallicity: <span className={styles.star_information_container_subtext}>{selectedStar.metallacity.toPrecision(3)}</span></p>
                        </>
                    )}
                    {!selectedStar && <p>No star selected</p>}
                </div>
            )}

            {(!isMobile || selectedPlanet) && (
                <div className={styles.planet_information_container}>
                    {selectedPlanet && (
                        <>
                            <h2>Planet: <strong>{selectedPlanet.name || "Planet"}</strong></h2>
                            <hr className={styles.navigation_divider}/>

                            <p>Type: <span className={styles.planet_information_container_subtext}>{PlanetTypeLabel[(selectedPlanet as PlanetData).planetType as keyof typeof PlanetTypeLabel]}</span></p>
                            <p>Diameter: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).diameter.toPrecision(3)}km</span></p>
                            <p>Mass: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).mass.toPrecision(3)} EM</span></p>
                            <p>Surface Temp: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).surfaceTemperature.toPrecision(3)}K</span></p>
                            <p>Gravity: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).gravity.toPrecision(3)}m/s²</span></p>
                            <p>Has Water: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).hasWater ? "Yes" : "No"}</span></p>
                            <p>Has Atmosphere: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).hasAtmosphere ? "Yes" : "No"}</span></p>
                            <p>Has Life: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).planetHasLife === null ? "Unknown" : (selectedPlanet as PlanetData).planetHasLife ? "Yes" : "No"}</span></p>
                            <p>Orbital Period: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).orbitalPeriod.toPrecision(3)}</span></p>
                            <p>Rotation Period: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).rotationalPeriod.toPrecision(3)}</span></p>
                            <p>Number of Moons: <span className={styles.planet_information_container_subtext}>{(selectedPlanet as PlanetData).moons.length}</span></p>
                        </>
                    )}
                    {!selectedPlanet && <p>No planet selected</p>}
                </div>
            )}

            <div className={styles.navigation_instructions}>
                <h2>Map Controls</h2>
                <hr className={styles.navigation_divider} />

                <ul>
                    <li>Left Click + Drag Left/Right:<p>Pan L/R</p></li>
                    <li>Left Click + Drag Up/Down<p>Zoom</p></li>
                    <li>Double Click on Planet<p>Planet View</p></li>
                </ul>
                <p className={styles.bottom_flavor}>Click on a Star or Planet to View More Details</p>
            </div>
        </>
    );
}