import { useGLTF } from '@react-three/drei'
import { useMemo } from 'react'

export function GalaxyModel(props: any) {
  const { scene } = useGLTF('/models/galaxy.glb')

  // cloning the loaded scene so multiple instances don't share the same obj3d state.
  const cloned = useMemo(() => {
    const c = scene.clone(true)

    // automatically hide very large meshes
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
