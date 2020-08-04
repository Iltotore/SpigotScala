package io.github.iltotore.scala.spigot

import java.util

import org.bukkit.block.{Block, BlockState}
import org.bukkit.command.CommandExecutor
import org.bukkit.entity.Entity
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.{EntityEquipment, EquipmentSlot, Inventory, ItemStack}
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Objective
import org.bukkit.util.Vector
import org.bukkit.{ChatColor, Location, World}

import scala.jdk.CollectionConverters._
import scala.reflect.ClassTag
import scala.util.Try

/**
 * Contains implicit decorators classes.
 */
object Decorators {

  implicit class StringDecorator(base: String) {

    /**
     * Color the wrapped String using ChatColor.translateAlternatesColors.
     *
     * @param char the used char. Default: &.
     * @return a colored version of this String.
     */
    def color(char: Char = '&'): String = ChatColor.translateAlternateColorCodes(char, base)
  }

  implicit class InventoryDecorator(base: Inventory) extends IndexedSeq[ItemStack] {

    /**
     * Operator alias of Inventory#addItem
     *
     * @param item the ItemStack to add.
     * @return the wrapped Inventory content as HashMap[Integer, ItemStack]
     */
    def +=(item: ItemStack): util.HashMap[Integer, ItemStack] = base.addItem(item)

    /**
     * Operator alias of Inventory#setItem
     *
     * @param index the index to set.
     * @param item  the added ItemStack.
     */
    def update(index: Int, item: ItemStack): Unit = base.setItem(index, item)

    /**
     * Get the ItemStack contained by the given slot.
     *
     * @param index the slot index.
     * @return an Optional potentially containing the held ItemStack.
     */
    override def apply(index: Int): ItemStack = base.getItem(index)

    override def length: Int = base.getSize
  }

  implicit class WorldDecorator(base: World) {

    /**
     * Alias of World#getBlockAt.
     *
     * @param loc the Location of the wanted Block.
     * @return the Block at is Location.
     */
    def apply(loc: Location): Block = base.getBlockAt(loc)

    /**
     * Alias of World#getBlockAt.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param z the z coordinate.
     * @return the Block at the given coordinates.
     */
    def apply(x: Int, y: Int, z: Int): Block = base.getBlockAt(x, y, z)

    /**
     * A classless wrapper of World#spawn.
     *
     * @param loc the location where the entity will be spawned.
     * @param tag the implicit tag passed by the T generic type. Used to retrieve the entity class.
     * @tparam T the entity type to spawn.
     * @return the newly created entity.
     */
    def spawnGeneric[T <: Entity: ClassTag](loc: Location)(implicit tag: ClassTag[T]): T =
      base.spawn(loc, tag.runtimeClass.asInstanceOf[Class[T]])

    /**
     * A classless wrapper of World#getEntitiesByClass.
     *
     * @param tag the implicit tag passed by the T generic type. Used to retrieve the entity class.
     * @tparam T the wanted entity type.
     * @return a collection of entities matching with the given type.
     */
    def getEntitiesOfType[T <: Entity: ClassTag](implicit tag: ClassTag[T]): Iterable[T] =
      base.getEntitiesByClass(tag.runtimeClass.asInstanceOf[Class[T]]).asScala
  }

  implicit class EquipmentDecorator(base: EntityEquipment) {

    /**
     * Alias of EntityEquipment#getItem.
     *
     * @param slot the EquipmentSlot to get at.
     * @return an Option containing the ItemStack at the given slot if exists.
     */
    def apply(slot: EquipmentSlot): Option[ItemStack] = Option(base.getItem(slot))

    /**
     * Alias of EntityEquipment#setItem.
     *
     * @param slot the EquipmentSlot to set at.
     * @param item the ItemStack to set.
     */
    def update(slot: EquipmentSlot, item: ItemStack): Unit = base.setItem(slot, item)
  }

  /**
   * A Simple wrapper of org.bukkit.Vector adding arithmetic aliases.
   *
   * @param base the decorated Vector.
   */
  implicit class VectorArithmetic(base: Vector) {

    def +=(vector: Vector): Vector = base.add(vector)

    def -=(vector: Vector): Vector = base.subtract(vector)

    def *=(vector: Vector): Vector = base.multiply(vector)

    def *=(number: Number): Vector = base.multiply(number.doubleValue())

    def /=(vector: Vector): Vector = base.add(vector)
  }

  /**
   * A Simple wrapper of org.bukkit.Location adding arithmetic aliases.
   *
   * @param base the decorated Location.
   */
  implicit class LocationDecorator(base: Location) {

    def +=(location: Location): Location = base.add(location)

    def -=(location: Location): Location = base.subtract(location)

    def *=(number: Number): Location = base.multiply(number.doubleValue())

    def /=(location: Location): Location = base.add(location)

    def toBlock: Location = base.getBlock.getLocation
  }

  implicit class BlockDecorator(base: Block) {

    /**
     * Get the wrapped Block's state and try to cast it.
     *
     * @tparam T the type to cast to.
     * @return A Try[T] representing the attempt of casting the wanted BlockState.
     */
    def getStateAs[T <: BlockState]: Try[T] = Try(base.getState.asInstanceOf[T])
  }

  implicit class ItemDecorator(base: ItemStack) {

    /**
     * Get the wrapped item's metadata and try to cast it.
     *
     * @tparam T the type to cast to.
     * @return A Try[T] representing the attempt of casting the wanted ItemMeta.
     */
    def getItemMetaAs[T <: ItemMeta]: T = base.getItemMeta.asInstanceOf[T]
  }

  implicit class ObjectiveDecorator(base: Objective) {

    /**
     * Get the score of an entry for the wrapped Objective as Int.
     *
     * @param entry the entry to get score of.
     * @return the score of the given entry for the wrapped Objective.
     */
    def apply(entry: String): Int = base.getScore(entry).getScore

    /**
     * Set the score of an entry for the wrapped Objective.
     *
     * @param entry the entry to set score of.
     * @param score the new score.
     */
    def update(entry: String, score: Int): Unit = base.getScore(entry).setScore(score)
  }

}
