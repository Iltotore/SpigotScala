package io.github.iltotore.scala.spigot.scheduler

import java.util.function.Consumer

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

class SpigotRunnable(consumer: BukkitTask => Unit) {
  var delay = 0L
  var interval = Option.empty[Long] //Option = Java's optionals. If defined, the produced Runnable will be run using runTaskTimer.

  def after(delay: Long): SpigotRunnable = {
    this.delay = delay
    this //We return this instance for chaining.
  }

  def each(interval: Long): SpigotRunnable = {
    this.interval = Option(interval)
    this //The return keyword is not required at the last method line.
  }

  //Launch the runnable and return the generated task.
  def now(plugin: Plugin): Unit = interval match { //This is called pattern matching. Check A Tour of Scala's dedicated article ;)
    case Some(ticks) => Bukkit.getScheduler.runTaskTimer(plugin, consumer.asInstanceOf[Consumer[BukkitTask]], delay, ticks) //An Option can be an instance of Some or None.

    case None => Bukkit.getScheduler.runTaskLater(plugin, consumer.asInstanceOf[Consumer[BukkitTask]], delay)
  }
}

object SpigotRunnable {
  def apply(consumer: BukkitTask => Unit): SpigotRunnable = new SpigotRunnable(consumer)
  def apply(runnable: => Unit):  SpigotRunnable = apply(_ => runnable)
}