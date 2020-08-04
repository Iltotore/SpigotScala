package io.github.iltotore.scala.spigot

import java.util.Locale

import org.bukkit.NamespacedKey
import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin

object Schemas {

  /**
   * An alias schema for Plugin#getCommand#setExecutor: `"cmd" ~> executor`.
   * @param name the name of the target command.
   * @param plugin the command's owner.
   */
  implicit class CommandAssignment(name: String)(implicit plugin: JavaPlugin) {

    def ~>(executor: CommandExecutor): Unit = plugin.getCommand(name).setExecutor(executor)
  }

  /**
   * An alias schema for NamespacedKey creation: `plugin::"key"` or `"namespace"::"key"`.
   * @param namespace the NamespacedKey prefix before `:`.
   */
  implicit class NamespacedKeyCreator(namespace: String) {

    def this(plugin: JavaPlugin) = this(plugin.getName.toLowerCase(Locale.ENGLISH))

    @SuppressWarnings("deprecation")
    def ::(key: String): NamespacedKey = new NamespacedKey(namespace, key)
  }
}
