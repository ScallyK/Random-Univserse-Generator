"use client";
import dynamic from "next/dynamic";

// Perform the dynamic import from a Client Component so ssr:false is allowed
const UniverseMap = dynamic(() => import("./UniverseMap"), { ssr: false });

export default function UniverseMapClient() {
  return <UniverseMap />;
}
