import React, { useRef } from "react";
import * as THREE from "three";
import { useFrame } from "@react-three/fiber";

interface StarSystemProps {
  position: [number, number, number];
  name: string;
  onClick?: () => void;
}

export default function StarSystem({ position, name, onClick }: StarSystemProps) {
  const ref = useRef<THREE.Mesh>(null);

  useFrame(() => {
    if (ref.current) {
      ref.current.rotation.y += 0.01;
    }
  });

  return (
    <mesh
      ref={ref}
      position={position}
      onClick={(e) => {

        // prevent clicks on a star system from bubbling up the galaxies
        e.stopPropagation();
        if (onClick) onClick();
      }}
    >
      <sphereGeometry args={[0.3, 16, 16]} />
      <meshStandardMaterial color="yellow" emissive="yellow" emissiveIntensity={0.7} />
    </mesh>
  );
}
