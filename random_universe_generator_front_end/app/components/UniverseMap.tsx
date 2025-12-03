import React, { useState } from "react";
import { Canvas } from "@react-three/fiber";
import { OrbitControls, Stars } from "@react-three/drei";
import Galaxy from "./Galaxy";
import fetchUniverse from "../hooks/FetchUniverse";

interface GalaxyData {
  id: number;
  name: string;
  position: [number, number, number];
}

// spreads galaxies across the map
function computeGalaxyPosition(index: number, _total: number): [number, number, number] {
  const goldenAngle = Math.PI * (3 - Math.sqrt(5)); // ~2.399963
  const radius = 5 + 30 * 5.0;
  const angle = index * goldenAngle;
  const x = Math.cos(angle) * radius;
  const z = Math.sin(angle) * radius;
  
  // small vertical jitter so everything isn't flat
  const y = ((index % 5) - 2) * 100;
  return [x, y, z];
}

export default function UniverseMap() {

  const [selectedGalaxy, setSelectedGalaxy] = useState<GalaxyData | null>(null);
  const universe = fetchUniverse();
  if (!universe) return <div>Loading universe...</div>;

  return (
    <>
      <Canvas camera={{ position: [0, 5, 15], fov: 60 }}>

        {/* Lighting */}
        <ambientLight intensity={4} />
        <pointLight position={[10, 10, 10]} />

        <Stars /> {/* Fills background with stars */}

        {universe.galaxies.map((galaxy: any, idx: number) => {

          // compute a fallback position if the galaxy doesn't provide one
          const position: [number, number, number] = galaxy.position
            ? galaxy.position
            : computeGalaxyPosition(idx, universe.galaxies.length);

          return (
            <Galaxy
              key={galaxy.id}
              position={position}
              name={galaxy.name}
              onClick={() => setSelectedGalaxy(galaxy)}
            />
          );
        })}
        <OrbitControls />
      </Canvas>

      {selectedGalaxy && (
        <div
          style={{
            position: "absolute",
            top: 20,
            left: 20,
            padding: "1rem",
            backgroundColor: "rgba(0,0,0,0.7)",
            color: "white",
            borderRadius: "8px",
          }}
        >
          <h2>Selected Galaxy</h2>
          <p>Name: {selectedGalaxy.name}</p>
          <button onClick={() => setSelectedGalaxy(null)}>Close</button>
        </div>
      )}
    </>
  );
}
