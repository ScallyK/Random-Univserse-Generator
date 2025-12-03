import React, { useRef } from "react";
import { useFrame } from "@react-three/fiber";
import * as THREE from "three";
import { GalaxyModel } from "../MapModels";

interface GalaxyProps {
  position: [number, number, number];
  name: string;
  onClick: () => void;
}

export default function Galaxy({ position, name, onClick }: GalaxyProps) {
  const mesh = useRef<THREE.Mesh>(null);

  useFrame(() => {
    if (mesh.current) {
      mesh.current.rotation.y += 0.004; // slow rotation, may change
    }
  });

  return (
    <group position={position} ref={mesh} onClick={onClick}>
      <GalaxyModel scale={10} />
    </group>
  );
}
