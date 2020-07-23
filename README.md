# SpigotScala
Extends the Spigot API using Scala.

# Description
SpigotScala is a Scala-specific rework of the Spigot API. It includes syntactic sugar and scala-specific features.

# Features
SpigotScala includes:
- Scala plugin wrapper as a Spigot plugin
- [Factory methods sugaring using the `apply` method](https://github.com/Iltotore/SpigotScala/wiki/Factory-methods)
- [Method aliases (like operators for arithmetic methods)](https://github.com/Iltotore/SpigotScala/wiki/Decorators)
- [Better generic support and removal of class parameters](https://github.com/Iltotore/SpigotScala/wiki/Decorators#better-generics)
- [Usage of Scala implicits to extend existing classes](https://github.com/Iltotore/SpigotScala/wiki/Decorators)
- [Custom DSLs](https://github.com/Iltotore/SpigotScala/wiki/DSL)

# Installation
## Developers
You can add the [release jar](https://github.com/Iltotore/SpigotScala/releases) to your project libraries
or use a build tool like Gradle or SBT:

<details>
<summary>Using Gradle</summary>

```gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation 'io.github.iltotore:spigot-scala_2.13:version'
}
```
</details>

<details>
<summary>Using SBT</summary>

```sbt
libraryDependencies += "io.github.iltotore" %% "spigot-scala" % "version"
```
</details>

## Server owners
You must add the [release jar](https://github.com/Iltotore/SpigotScala/releases) to your `plugins` directory, then restart/reload the server.