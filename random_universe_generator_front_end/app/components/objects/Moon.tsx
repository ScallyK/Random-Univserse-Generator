import { useRef } from "react";
import * as THREE from "three";
import { useFrame } from "@react-three/fiber";
import { MoonModel } from "../../models/MapModels";
import { MoonProps } from "../HelperFunctions";

// Moon component for rendering moons in the 3D universe map
export default function Moon({position = [0, 0, 0], onClick, isSelected = false, scale = 0.001, rotationSpeed = 0.01 }: MoonProps) {

  const ref = useRef<THREE.Mesh>(null);

  useFrame(() => {
    if (ref.current) {
      ref.current.rotation.y += rotationSpeed;
    }
  });

  return (
    <group
      position={position}
      ref={ref}
      onClick={(e) => {
        e.stopPropagation(); // this prevents bubble-up clicking
        onClick?.();
      }}
    >
      <MoonModel scale={scale} />

      {isSelected && (
        
        // Just large enough to cover most of the moon models regardless of scale
        <mesh>
          <sphereGeometry args={[0.085, 24, 24]} />
          <meshStandardMaterial
            color="grey"
            emissive="grey"
            emissiveIntensity={2}
            wireframe
            opacity={0.2}
          />
        </mesh>
      )}
    </group>
  );
}