package io.github.iltotore.scala.spigot.example

import io.github.iltotore.scala.spigot.Implicits._
import io.github.iltotore.scala.spigot.factory
import io.github.iltotore.scala.spigot.factory.Player
import org.bukkit.entity.Slime
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.plugin.java.JavaPlugin

object Main extends JavaPlugin {

  implicit val implicitReference: JavaPlugin = this

  override def onEnable(): Unit = {
    "sec" ~> null
    factory.World("").spawnGeneric[Slime](null)
  }
}
