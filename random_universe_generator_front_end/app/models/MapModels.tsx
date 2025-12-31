import { useGLTF } from '@react-three/drei'
import { useMemo } from 'react'

export function GalaxyModel(props: any) {
  const { scene } = useGLTF('/models/galaxy.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    const HIDE_RADIUS = 98

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
        const r = node.geometry.boundingSphere?.radius ?? 0
        if (r > HIDE_RADIUS) {
          node.visible = false
        }
      }
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function StarModel(props: any) {
  const { scene } = useGLTF('/models/sun.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
       
      }
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function AcidPlanetModel(props: any) {
  const { scene } = useGLTF('/models/acidlike.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
       
      }
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function DesertPlanetModel(props: any) {
  const { scene } = useGLTF('/models/desertlike.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
          node.geometry.center()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
      }
      // Reset all transformations to prevent offset orbiting
      node.position.set(0, 0, 0)
      node.rotation.set(0, 0, 0)
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function EarthLikePlanetModel(props: any) {
  const { scene } = useGLTF('/models/earthlike.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
          node.geometry.center()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
      }
      // Reset all transformations to prevent offset orbiting
      node.position.set(0, 0, 0)
      node.rotation.set(0, 0, 0)
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function IcePlanetModel(props: any) {
  const { scene } = useGLTF('/models/icelike.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
          node.geometry.center()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
      }
      // Reset all transformations to prevent offset orbiting
      node.position.set(0, 0, 0)
      node.rotation.set(0, 0, 0)
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function LavaPlanetModel(props: any) {
  const { scene } = useGLTF('/models/lavalike.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
          node.geometry.center()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
      }
      // Reset all transformations to prevent offset orbiting
      node.position.set(0, 0, 0)
      node.rotation.set(0, 0, 0)
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function MarsLikePlanetModel(props: any) {
  const { scene } = useGLTF('/models/marslike.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
          node.geometry.center()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
      }
      // Reset all transformations to prevent offset orbiting
      node.position.set(0, 0, 0)
      node.rotation.set(0, 0, 0)
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function MoonLikePlanetModel(props: any) {
  const { scene } = useGLTF('/models/moonlike.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
          node.geometry.center()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
      }
      // Reset all transformations to prevent offset orbiting
      node.position.set(0, 0, 0)
      node.rotation.set(0, 0, 0)
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function MoonModel(props: any) {
  const { scene } = useGLTF('/models/moon.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
       
      }
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function RingedGasGiantModel(props: any) {
  const { scene } = useGLTF('/models/ringedgasgiant.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
          node.geometry.center()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
      }
      // Reset all transformations to prevent offset orbiting
      node.position.set(0, 0, 0)
      // Don't reset rotation for ringed gas giant - keep original orientation
    })

    // Rotate the entire scene to orient rings horizontally
    c.rotation.x = Math.PI / 5

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}

export function VolcanicPlanetModel(props: any) {
  const { scene } = useGLTF('/models/volcaniclike.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    c.traverse((node: any) => {
      if (node.isMesh && node.geometry) {
        try {
          node.geometry.computeBoundingSphere()
          node.geometry.center()
        } catch (e) {
          // geometry might be non-standard so skip
          return
        }
      }
      // Reset all transformations to prevent offset orbiting
      node.position.set(0, 0, 0)
      node.rotation.set(0, 0, 0)
    })

    return c
  }, [scene])

  return <primitive object={cloned} {...props} />
}