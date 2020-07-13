package io.github.iltotore.scala.spigot.scheduler

import org.bukkit.scheduler.BukkitTask

object SpigotTimer {

  def apply(time: Long)(consumer: BukkitTask => Unit):  SpigotRunnable = {
    var t = time;
    SpigotRunnable (task => {
      t-=1
      consumer(task)
      if(t <= 0) task.cancel()
    })
  }

  object Runnable {
    def apply(time: Long)(runnable: => Unit): SpigotRunnable = SpigotTimer(time)(_ => runnable)
  }
}
