package io.github.iltotore.scala.spigot.factory

import java.util.UUID

import org.bukkit.{Bukkit, OfflinePlayer}

/**
 * Wrapper for Bukkit.getOfflinePlayer
 */
object OfflinePlayer {
  def apply(id: UUID): OfflinePlayer = Bukkit.getOfflinePlayer(id)
}
