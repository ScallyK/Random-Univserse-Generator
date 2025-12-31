"use client"
import { useState } from "react";
import { Canvas } from "@react-three/fiber";
import { OrbitControls, Stars } from "@react-three/drei";
import Galaxy from "../objects/Galaxy";
import fetchUniverse from "../../hooks/FetchUniverse";
import styles from "../../stylesheets/UniverseMap.module.css";
import StarSystemMap from "../maps/StarSystemMap";
import { computeGalaxyPosition, GalaxyData } from "../HelperFunctions";

// Renders a map of star systems and galaxies in a 3D canvas
export default function UniverseMap() {

  const [selectedGalaxy, setSelectedGalaxy] = useState<GalaxyData | null>(null);                        // Holds currently selceted galaxy on the map
  const [starSystemListOpen, setStarSystemListOpen] = useState<boolean>(false);                         // Toggles star system dropdown list
  const [starSystemListButtonText, setStarSystemListButtonText] = useState<string>("Click to Expand");  // Toggles star system dropdown list button text
  const [selectedStarSystem, setSelectedStarSystem] = useState<any | null>(null);                       // Holds currently selected star system to view its map  

  // Toggles star system list dropdown open/close
  function cycleStarSystemListButton() {
    setStarSystemListOpen(!starSystemListOpen);
    setStarSystemListButtonText(starSystemListOpen ? "Click to Expand" : "Click to Collapse");
  }

  // Fetches universe data from the Java backend
  const universe = fetchUniverse();

  // Displays a loading screen while fetching universe data
  if (!universe) return (
    <div className={styles.loadingScreenContainer}>
      <h1>Loading universe...</h1>
    </div>
  );

  // Displays the star system map when a star system is selected, unrenders the universe map
  if (selectedStarSystem) {
    return (
      <StarSystemMap
        starSystem={selectedStarSystem}
        onBack={() => setSelectedStarSystem(null)}
      />
    );
  }

  return (
    <>

      <div className={styles.application_title}>
        <h1>RUG</h1>
        <h3>Random Universe Generator</h3>
        <p>Number of Galaxies: <span>{universe.galaxies.length ? universe.galaxies.length : 0}</span></p>
      </div>

      <Canvas
        camera={{ position: [0, 300, 350], fov: 60 }}

        // Clear selection when clicking on empty space
        onPointerMissed={(e) => {
          if (e.button !== 0) return;
          setSelectedGalaxy(null);
        }}>

        <ambientLight intensity={4} />

        <pointLight position={[10, 10, 10]} />

        <Stars />

        {universe.galaxies.map((galaxy: any, idx: number) => {

          // Compute a fallback position if the galaxy doesn't provide one
          const position: [number, number, number] = galaxy.position ? galaxy.position: computeGalaxyPosition(idx, universe.galaxies.length);

          return (
            <Galaxy
              key={galaxy.id}
              position={position}
              name={galaxy.name}
              onClick={() => setSelectedGalaxy(galaxy)}
              starSystems={galaxy.starSystems}
              isSelected={selectedGalaxy?.id === galaxy.id}
            />
          );
        })}
        <OrbitControls />
      </Canvas>

      {selectedGalaxy && (

        <div className={styles.galaxy_information_container}>
          <h2>Selected Galaxy</h2>

          <hr className={styles.navigation_divider}/>

          <p>Name: <span className={styles.galaxy_information_container_subtext}>{selectedGalaxy.name}</span></p>
          <p>Age: <span className={styles.galaxy_information_container_subtext}>{Number(selectedGalaxy.age).toPrecision(3)}M Years</span></p>
          <p>Galaxy Type: <span className={styles.galaxy_information_container_subtext}>{selectedGalaxy.type}</span></p>
          <p>Number of Star Systems: <span className={styles.galaxy_information_container_subtext}>{selectedGalaxy.starSystems ? selectedGalaxy.starSystems.length : 0}</span></p>

          <hr className={styles.navigation_divider}/>
          
          <button className={styles.star_systems_list_button} onClick={() => cycleStarSystemListButton()}> {starSystemListButtonText} </button>

          {/* Renders star system drop down only when a galaxy has been clicked and has star systems */}
          {selectedGalaxy.starSystems && selectedGalaxy.starSystems.length > 0 && (
            <div className={styles.star_systems_list}>

              <hr className={styles.navigation_divider}/>

              {starSystemListOpen && (
                <ul>
                  {selectedGalaxy.starSystems.map((system: any) => (
                    <li key={system.id}>
                      <button
                        className={styles.star_systems_list_item}
                        onClick={() => setSelectedStarSystem(system)}
                      >
                        {system.name}
                      </button>
                    </li>
                  ))}
                </ul>
              )}
            </div>
          )}

        </div>
      )}

      <div className={styles.navigation_instructions}>
        <h2>Getting Started</h2>
        <hr className={styles.navigation_divider} />

        <ul>
          <li>Left Click + Drag:<p>Rotate View</p></li>
          <li>Scroll Wheel:<p>Zoom In & Out</p></li>
          <li>Right Click + Drag:<p>Pan View</p></li>
        </ul>
        <p className={styles.bottom_flavor}>Click on a Galaxy to View More Details</p>
      </div>
    </>
  );
}
