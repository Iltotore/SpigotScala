package io.github.iltotore.scala.spigot.scheduler

import org.bukkit.scheduler.BukkitTask

object SpigotTimer {

  def consumer(time: Long)(consumer: BukkitTask => Unit):  SpigotRunnable = {
    var t = time
    SpigotRunnable.consumer( task => {
      t-=1
      consumer(task)
      if(t <= 0) task.cancel()
    })
  }

  def apply(time: Long)(runnable: => Unit): SpigotRunnable = consumer(time)(_ => runnable)
}
