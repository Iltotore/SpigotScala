package io.github.iltotore.scala.spigot.factory

import java.util.UUID

import org.bukkit.{Bukkit, OfflinePlayer}

object OfflinePlayer {
  def apply(id: UUID): OfflinePlayer = Bukkit.getOfflinePlayer(id)
}
