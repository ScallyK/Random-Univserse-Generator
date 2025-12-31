"use client"
import { useState, useMemo } from "react";
import { Canvas } from "@react-three/fiber";
import { Stars, MapControls } from "@react-three/drei";
import Planet from "../objects/Planet";
import Moon from "../objects/Moon";
import styles from "../../stylesheets/PlanetMap.module.css";
import { PlanetTypes, type PlanetTypeName, PlanetData, MoonData, LifeformData, computeMoonPosition, PlanetTypeLabel, memorizeScaleAndRotation } from "../HelperFunctions";

export default function PlanetMap({ planet, onBack }: { planet: PlanetData; onBack: () => void }) {

    const [selectedMoon, setSelectedMoon] = useState<MoonData | null>(null);                // Holds currently selected moon to view its details
    const [selectedLifeform, setSelectedLifeform] = useState<LifeformData | null>(null);    // Holds currently selected lifeform to view its details

    // Memoize moon scales and rotation speeds to prevent regeneration on re-render
    const moonScales = useMemo(() => memorizeScaleAndRotation("moon", planet).scales, [planet.id]);
    const moonRotationSpeeds = useMemo(() => memorizeScaleAndRotation("moon", planet).rotationSpeeds, [planet.id]);

    // Resolve planet type, defaulting to Earth Analog if unknown
    const resolvedPlanetType: PlanetTypeName = (planet.planetType in PlanetTypes ? planet.planetType : "EARTH_ANALOG") as PlanetTypeName;

    // was going to use these for camera controls but decided against it for now
    const cameraZ = 2;
    const cameraX = 0;
    const cameraY = 0;

    return (
        <>

            <div className={styles.header}>
                <button className={styles.back_button} onClick={onBack}>Back</button>
                <h2 className={styles.title}>{planet.name}</h2>
                <div className={styles.planet_details}>
                    <p>Type: <span className={styles.planet_details_subtext}>{PlanetTypeLabel[(planet as PlanetData).planetType as keyof typeof PlanetTypeLabel]}</span></p>
                    <p>Diameter: <span className={styles.planet_details_subtext}>{planet.diameter.toPrecision(3)} km</span></p>
                    <p>Mass: <span className={styles.planet_details_subtext}>{planet.mass.toPrecision(3)} EM</span></p>
                    <p>Moons: <span className={styles.planet_details_subtext}>{planet.moons?.length ?? 0}</span></p>
                    <p>Has Life: <span className={styles.planet_details_subtext}>{planet.planetHasLife === null ? "Unknown" : planet.planetHasLife ? "Yes" : "No"}</span></p>
                    <p>Lifeforms: <span className={styles.planet_details_subtext}>{planet.lifeforms?.length ?? 0}</span></p>
                    <p>Gravity: <span className={styles.planet_details_subtext}>{planet.gravity.toPrecision(3)} m/s²</span></p>
                    <p>Orbital Period: <span className={styles.planet_details_subtext}>{planet.orbitalPeriod.toPrecision(3)}</span></p>
                    <p>Rotational Period: <span className={styles.planet_details_subtext}>{planet.rotationalPeriod.toPrecision(3)}</span></p>
                    <p>Surface Temp: <span className={styles.planet_details_subtext}>{planet.surfaceTemperature.toPrecision(3)} K</span></p>
                    <p>Has Atmosphere: <span className={styles.planet_details_subtext}>{planet.hasAtmosphere ? "Yes" : "No"}</span></p>
                    <p>Has Water: <span className={styles.planet_details_subtext}>{planet.hasWater ? "Yes" : "No"}</span></p>
                    <p>Avg Pressure: <span className={styles.planet_details_subtext}>{planet.averagePressure.toPrecision(3)}</span></p>
                    <p>Axial Tilt: <span className={styles.planet_details_subtext}>{planet.axialTilt.toPrecision(3)}°</span></p>
                    <p>Distance From Star: <span className={styles.planet_details_subtext}>{planet.distanceFromStar.toPrecision(3)}</span></p>
                    <p>Has Rings: <span className={styles.planet_details_subtext}>{planet.hasRings ? "Yes" : "No"}</span></p>
                    <p>Escape Velocity: <span className={styles.planet_details_subtext}>{planet.escapeVelocity.toPrecision(3)}</span></p>
                </div>
            </div>

            {(planet.lifeforms?.length ?? 0) > 0 && (
                <div className={styles.lifeforms_dropdown}>
                    <label htmlFor="lifeforms_select"><strong>Lifeforms:</strong></label>
                    <select
                        className={styles.lifeforms_select}
                        id="lifeforms_select"
                        onChange={(e) => {
                            const selectedLifeformName = e.target.value;
                            const selectedLifeform = planet.lifeforms.find((lf: LifeformData) => lf.name === selectedLifeformName) || null;
                            setSelectedLifeform(selectedLifeform);
                        }}
                    >
                        <option value="">-- Select a lifeform --</option>
                        {planet.lifeforms.map((lifeform: LifeformData, idx: number) => (
                            <option key={idx} value={lifeform.name}>
                                {lifeform.name}
                            </option>
                        ))}
                    </select>

                    <div className={styles.lifeform_data}>
                        {selectedLifeform ? (
                            <>
                                <h3>Species: <strong>{selectedLifeform.name || "Lifeform"}</strong></h3>
                                <hr className={styles.navigation_divider}/>

                                <p>Lifespan: <span className={styles.lifeform_data_subtext}>{selectedLifeform.lifespan} years</span></p>
                                <p>Intelligent: <span className={styles.lifeform_data_subtext}>{selectedLifeform.intelligent ? "Yes" : "No"}</span></p>
                                <p>Average Height: <span className={styles.lifeform_data_subtext}>{selectedLifeform.averageHeight.toPrecision(3)} m</span></p>
                                <p>Average Weight: <span className={styles.lifeform_data_subtext}>{selectedLifeform.averageWeight.toPrecision(3)} kg</span></p>
                                <p>Kardashev Scale: <span className={styles.lifeform_data_subtext}>{selectedLifeform.kardashevScale}</span></p>
                                <p>Habitat: <span className={styles.lifeform_data_subtext}>{selectedLifeform.habitat}</span></p>
                                <p>Diet: <span className={styles.lifeform_data_subtext}>{selectedLifeform.diet}</span></p>
                                <p>Reproduction Method: <span className={styles.lifeform_data_subtext}>{selectedLifeform.reproductionMethod}</span></p>
                            </>
                        ) : (
                            <p>No lifeform selected</p>
                        )}
                    </div>
                </div>
            )}

            <Canvas 
            camera={{ position: [cameraX, cameraY, cameraZ], fov: 60 }}

            onPointerMissed={(e) => {

                    // Clear selection when clicking on empty space
                    if (e.button !== 0) return;
                    setSelectedMoon(null);
                }}
            >

                <ambientLight intensity={1.2} />

                <pointLight position={[6, 6, 6]} />

                <Stars />

                <Planet
                    key={planet.id}
                    name={planet.name}
                    position={[0, 0, 0]}
                    planetType={resolvedPlanetType}
                />

                {planet.moons.map((moon: MoonData, idx: number) => {
                    const moonPosition = computeMoonPosition([0, 0, 0], idx);
                    return (
                        <Moon
                            key={moon.name}
                            name={moon.name}
                            position={moonPosition}
                            isSelected={selectedMoon?.name === moon.name}
                            onClick={() => setSelectedMoon(moon)}
                            scale={moonScales[moon.id]}
                            rotationSpeed={moonRotationSpeeds[moon.id]}
                        />
                    );
                })}

                <MapControls
                    enableZoom={false}
                    enablePan={false}
                    enableRotate={true}
                    mouseButtons={{
                        LEFT: 0,
                        MIDDLE: 1,
                        RIGHT: 1,
                    }}
                    touches={{
                        ONE: 0,
                        TWO: 1
                    }}
                />
            </Canvas>

            <div className={styles.moon_information_container}>
                {selectedMoon && (
                    <>
                        <h2><strong>Moon: {selectedMoon.name || "Moon"}</strong></h2>
                        <hr className={styles.navigation_divider}/>

                        <p>Diameter: <span className={styles.moon_information_container_subtext}>{selectedMoon.diameter.toPrecision(3)} km</span></p>
                        <p>Mass: <span className={styles.moon_information_container_subtext}>{selectedMoon.mass.toPrecision(3)} EM</span></p>
                        <p>Gravity: <span className={styles.moon_information_container_subtext}>{selectedMoon.gravity.toPrecision(3)} m/s²</span></p>
                        <p>Orbital Period: <span className={styles.moon_information_container_subtext}>{selectedMoon.orbitalPeriod.toPrecision(3)}</span></p>
                        <p>Rotational Period: <span className={styles.moon_information_container_subtext}>{selectedMoon.rotationalPeriod.toPrecision(3)}</span></p>
                        <p>Surface Temp: <span className={styles.moon_information_container_subtext}>{selectedMoon.surfaceTemperature.toPrecision(3)} K</span></p>
                        <p>Tidally Locked: <span className={styles.moon_information_container_subtext}>{selectedMoon.isTidallyLocked ? "Yes" : "No"}</span></p>
                        <p>Has Water: <span className={styles.moon_information_container_subtext}>{selectedMoon.hasWater ? "Yes" : "No"}</span></p>
                    </>
                )}
                {!selectedMoon && <p>No moon selected</p>}
            </div>

            <div className={styles.navigation_instructions}>
                <h2>Getting Started</h2>
                <hr className={styles.navigation_divider} />

                <ul>
                    <li>Left Click + Drag:<p>Rotate View</p></li>
                </ul>
                <p className={styles.bottom_flavor}>Click on a Moon to View More Details</p>
            </div>
        </>
    );
}