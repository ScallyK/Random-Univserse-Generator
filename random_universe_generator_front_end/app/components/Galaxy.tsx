import React, { useRef } from "react";
import { useFrame } from "@react-three/fiber";
import * as THREE from "three";
import { GalaxyModel } from "../models/MapModels";
import StarSystem from "./StarSystem";
import styles from "../stylesheets/GalaxyMap.module.css";

interface StarSystemData {
  id: number;
  name: string;
  position?: [number, number, number];
  age: number;
  hasComets: boolean;
  hasAsteroids: boolean;
  hasNebulae: boolean;
  hasBlackHoles: boolean;
}

interface GalaxyProps {
  position: [number, number, number];
  name: string;
  onClick: () => void;
  starSystems?: StarSystemData[];
}

// compute relative positions for star systems inside the galaxy
function computeLocalStarPosition(index: number, total: number): [number, number, number] {

  const radius = 2 + Math.min(8, total / 2);

  // stable, seeded PRNG
  function mulberry32(seed: number) {

    return function() {

      let t = (seed += 0x6D2B79F5);
      t = Math.imul(t ^ (t >>> 15), t | 1);
      t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
      return ((t ^ (t >>> 14)) >>> 0) / 4294967296;

    };
  }

  const seed = index * 374761393 + total * 668265263;
  const rand = mulberry32(seed);

  // uniform dist.
  const r = radius * Math.sqrt(rand());
  const angle = rand() * Math.PI * 2;

  const x = Math.cos(angle) * r;
  const z = Math.sin(angle) * r;
  const y = (rand() - 0.5) * 0.8; // some small variation

  return [x, y, z];
}

export default function Galaxy({ position, name, onClick, starSystems = [] }: GalaxyProps) {

  const [selectedStarSystem, setSelectedStarSystem] = React.useState<StarSystemData | null>(null);

  const mesh = useRef<THREE.Mesh>(null);

  useFrame(() => {
    if (mesh.current) {
      mesh.current.rotation.y += 0.004; // slow-ish rotation
    }
  });

  return (
    <group position={position} ref={mesh} onClick={onClick}>
      <GalaxyModel scale={10} />

      {starSystems.map((ss, idx) => {
        const localPos: [number, number, number] = ss.position
          ? ss.position
          : computeLocalStarPosition(idx, starSystems.length);

        return (
          <StarSystem
            key={ss.id}
            position={localPos}
            name={ss.name}
            onClick={() => {console.log("Star system clicked:", ss.id, ss.name);
            }}
          />
        );
      })}

      {/* {selectedStarSystem && (
        <div className={styles.entity_information_container}>
          <h2>Selected Galaxy</h2>
          <p>Name: {selectedStarSystem.name}</p>
          <p>Age: {Number(selectedStarSystem.age).toPrecision(3)}mil Years</p>
          <p>Has any Comets?: {selectedStarSystem.hasComets ? "Yes" : "No"}</p>
          <p>Has any Asteroids?: {selectedStarSystem.hasAsteroids ? "Yes" : "No"}</p>
          <p>Has any Nebulae?: {selectedStarSystem.hasNebulae ? "Yes" : "No"}</p>
          <p>Has any Black Holes?: {selectedStarSystem.hasBlackHoles ? "Yes" : "No"}</p>
          <div className={styles.bottom_buttons}>
            <button onClick={() => setSelectedStarSystem(null)}>Close</button>
          </div>
        </div>
      )} */}

    </group>
  );
}
