import React, { useRef, useState } from "react";
import * as THREE from "three";
import { useFrame } from "@react-three/fiber";
import { EarthLikePlanetModel } from "../../models/MapModels";
import { PlanetProps, PlanetTypes } from "../HelperFunctions";

// Planet component for rendering planets in the 3D universe map
export default function Planet({position = [0, 0, 0], onClick, onDoubleClick, isSelected = false, planetType, scale = 0.4, rotationSpeed = 0.01 }: PlanetProps) {

  const ref = useRef<THREE.Group>(null);
  const [lastClickTime, setLastClickTime] = useState(0);

  const handleClick = (e: any) => {
    e.stopPropagation(); // this prevents bubble-up clicking

    // Used for logging click speed
    const now = Date.now();
    const timeSinceLastClick = now - lastClickTime;

    // Differentiates between double and single clicks
    if (timeSinceLastClick < 300 && onDoubleClick) {
      onDoubleClick();
    } 

    else if (onClick) {
      onClick();
    }

    setLastClickTime(now);
  };

  useFrame(() => {
    if (ref.current) {
      ref.current.rotation.y += rotationSpeed;
    }
  });

  return (
    <group position={position} onClick={handleClick}>
      <group ref={ref}>
        {React.createElement(PlanetTypes[planetType] || EarthLikePlanetModel, { scale: scale })}
      </group>

      {isSelected && (
        
        // Just large enough to cover most of the planet models regardless of scale. Thanksfully we can actualy use the scale prop here and it works.
        <mesh>
          <sphereGeometry args={[scale, 24, 24]} />
          <meshStandardMaterial
            color="red"
            emissive="red"
            emissiveIntensity={2}
            transparent
            opacity={0.7}
          />
        </mesh>
      )}
    </group>
  );
}