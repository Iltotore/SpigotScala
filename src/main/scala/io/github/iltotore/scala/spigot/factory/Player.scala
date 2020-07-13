package io.github.iltotore.scala.spigot.factory

import java.util.UUID

import org.bukkit.Bukkit
import org.bukkit.entity.Player

object Player {
  def apply(name: String): Player = Bukkit.getPlayer(name)
  def apply(id: UUID): Player = Bukkit.getPlayer(id)
}
