package io.github.iltotore.scala.spigot.scheduler

import java.util.function.Consumer

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

/**
 * A custom control structure wrapping org.bukkit.BukkitRunnable.
 * @param consumer the executed method.
 */
class SpigotRunnable(consumer: BukkitTask => Unit) {
  private var delay = 0L
  private var interval = Option.empty[Long] //Option = Java's optionals. If defined, the produced Runnable will be run using runTaskTimer.

  /**
   * Set the "later" delay.
   * @param delay the time in ticks to wait before running the task.
   * @return this SpigotRunnable for chaining
   */
  def after(delay: Long): SpigotRunnable = {
    this.delay = delay
    this //We return this instance for chaining.
  }

  /**
   * Set the time between each execution.
   * @param interval the period in ticks between each execution.
   * @return this SpigotRunnable for chaining
   */
  def each(interval: Long): SpigotRunnable = {
    this.interval = Option(interval)
    this //The return keyword is not required at the last method line.
  }

  /**
   * Launch this SpigotRunnable.
   * @param plugin the plugin used to schedule this SpigorRunnable
   */
  def now(plugin: Plugin): Unit = interval match { //This is called pattern matching. Check A Tour of Scala's dedicated article ;)
    case Some(ticks) => Bukkit.getScheduler.runTaskTimer(plugin, consumer.asInstanceOf[Consumer[BukkitTask]], delay, ticks) //An Option can be an instance of Some or None.

    case None => Bukkit.getScheduler.runTaskLater(plugin, consumer.asInstanceOf[Consumer[BukkitTask]], delay)
  }
}

/**
 * SpigotRunnable DSL's starting point
 */
object SpigotRunnable {
  def consumer(consumer: BukkitTask => Unit): SpigotRunnable = new SpigotRunnable(consumer)
  def apply(runnable: => Unit):  SpigotRunnable = consumer(_ => runnable)
}