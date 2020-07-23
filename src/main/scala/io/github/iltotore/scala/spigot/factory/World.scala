package io.github.iltotore.scala.spigot.factory

import java.util.UUID

import org.bukkit.{Bukkit, World}

/**
 * Wrapper for Bukkit.getWorld
 */
object World {

  def apply(name: String): World = Bukkit.getWorld(name)
  def apply(id: UUID): World = Bukkit.getWorld(id)
}
