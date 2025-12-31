import { useRef } from "react";
import * as THREE from "three";
import { useFrame } from "@react-three/fiber";
import { StarModel } from "../../models/MapModels";
import { StarProps } from "../HelperFunctions";

// Star component for rendering stars in the 3D universe map
export default function Star({position = [0, 0, 0], onClick, isSelected = false, scale = 0.06, rotationSpeed = 0.01 }: StarProps) {

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
      <StarModel scale={scale} />

      {isSelected && (

        // Just large enough to cover most of the star models regardless of scale
        <mesh>
          <sphereGeometry args={[0.65, 24, 24]} />
          <meshStandardMaterial
            color="yellow"
            emissive="yellow"
            emissiveIntensity={1.5}
            wireframe
            opacity={0.35}
          />
        </mesh>
      )}
    </group>
  );
}

