package io.github.iltotore.scala.spigot.factory

import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.{Inventory, InventoryHolder}

/**
 * Wrapper for Bukkit.createInventory
 */
object Inventory {

  def apply(owner: InventoryHolder, size: Int, name: String): Inventory = Bukkit.createInventory(owner, size, name)
  def apply(owner: InventoryHolder, invType: InventoryType, name: String): Inventory = Bukkit.createInventory(owner, invType, name)
}
