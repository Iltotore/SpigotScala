package io.github.iltotore.scala.spigot

import java.util

import org.bukkit.block.data.BlockData
import org.bukkit.block.{Block, BlockState}
import org.bukkit.command.CommandExecutor
import org.bukkit.entity.{Entity, Item}
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.{EntityEquipment, EquipmentSlot, Inventory, ItemStack}
import org.bukkit.material.MaterialData
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.{Objective, Score}
import org.bukkit.util.Vector
import org.bukkit.{ChatColor, Location, World}

import scala.reflect.ClassTag

object Implicits {

  implicit class StringDecorator(base: String) {

    def color(char: Char = '&'): String = ChatColor.translateAlternateColorCodes(char, base)
  }

  implicit class InventoryDecorator(base: Inventory) {

    def +=(item: ItemStack): util.HashMap[Integer, ItemStack] = base.addItem(item)

    def update(index: Int, item: ItemStack): Unit = base.setItem(index, item)

    def apply(index: Int): ItemStack = base.getItem(index)
  }

  implicit class WorldDecorator(base: World) {

    def apply(loc: Location): Block = base.getBlockAt(loc)

    def apply(x: Int, y: Int, z: Int): Block = base.getBlockAt(x, y, z)

    def spawnGeneric[T <: Entity: ClassTag](loc: Location)(implicit tag: ClassTag[T]): T =
      base.spawn(loc, tag.runtimeClass.asInstanceOf[Class[T]])

    def getEntitiesByGeneric[T <: Entity: ClassTag](loc: Location)(implicit tag: ClassTag[T]): util.Collection[T] =
      base.getEntitiesByClass(tag.runtimeClass.asInstanceOf[Class[T]])
  }

  implicit class CommandAssignment(name: String)(implicit plugin: JavaPlugin) {
    def ~>(executor: CommandExecutor): Unit = plugin.getCommand(name).setExecutor(executor)
  }

  implicit class EquipmentDecorator(base: EntityEquipment) {

    def apply(slot: EquipmentSlot): ItemStack = base.getItem(slot)

    def update(slot: EquipmentSlot, item: ItemStack): Unit = base.setItem(slot, item)
  }

  implicit class VectorArithmetic(base: Vector) {

    def +=(vector: Vector): Vector = base.add(vector)

    def -=(vector: Vector): Vector = base.subtract(vector)

    def *=(vector: Vector): Vector = base.multiply(vector)

    def *=(number: Number): Vector = base.multiply(number.doubleValue())

    def /=(vector: Vector): Vector = base.add(vector)
  }

  implicit class LocationDecorator(base: Location) {

    def +=(location: Location): Location = base.add(location)

    def -=(location: Location): Location = base.subtract(location)

    def *=(number: Number): Location = base.multiply(number.doubleValue())

    def /=(location: Location): Location = base.add(location)

    def toBlock: Location = base.getBlock.getLocation
  }

  implicit class BlockDecorator(base: Block) {

    def getStateAs[T <: BlockState]: T = base.getState.asInstanceOf[T]
  }

  implicit class ItemDecorator(base: ItemStack) {

    def getItemMetaAs[T <: ItemMeta]: T = base.getItemMeta.asInstanceOf[T]

  }

  implicit class ObjectiveDecorator(base: Objective) {

    def apply(entry: String): Int = base.getScore(entry).getScore
    def update(entry: String, score: Int): Unit = base.getScore(entry).setScore(score)
  }
}
