import { useRef } from "react";
import { useFrame } from "@react-three/fiber";
import * as THREE from "three";
import { GalaxyModel } from "../../models/MapModels";
import { GalaxyProps } from "../HelperFunctions";

// Galaxy component for rendering galaxies in the 3D universe map
export default function Galaxy({ position, onClick, isSelected = false }: GalaxyProps) {

  const ref = useRef<THREE.Mesh>(null);

  useFrame(() => {
    if (ref.current) {
      ref.current.rotation.y += 0.004; // slow-ish rotation
    }
  });

  return (
    <group
      position={position}
      ref={ref}
      onClick={onClick}
    >
      {/* 20 seems to be the best scale for visibility without being too huge */}
      <GalaxyModel scale={20} />

      {isSelected && (
        
        // This puts a little sphere in the middle of the model to indicate selection
        <mesh>
          <sphereGeometry args={[6, 24, 24]} />
          <meshStandardMaterial
            color="red"
            opacity={0.8}
          />
        </mesh>
      )}
    </group>
  );
}