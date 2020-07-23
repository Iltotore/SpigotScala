package io.github.iltotore.scala.spigot.factory

import org.bukkit.{Location, World}

/**
 * Create a new Location, passing the world implicitly
 */
object WorldLocation {

  def apply(x: Double, y: Double, z: Double, yaw: Float = 0, pitch: Float = 0)(implicit world: World): Location =
    new Location(world, x, y, z, yaw, pitch)
}
