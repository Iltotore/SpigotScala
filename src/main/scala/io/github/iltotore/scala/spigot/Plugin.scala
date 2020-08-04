package io.github.iltotore.scala.spigot

import org.bukkit.plugin.java.JavaPlugin

class Plugin extends JavaPlugin {

  override def onEnable(): Unit = getLogger.info("Enabling Scala...")
}
