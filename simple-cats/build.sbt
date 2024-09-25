val scala3Version = "3.5.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "simple-cats",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    // https://mvnrepository.com/artifact/org.typelevel/cats-core
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0",

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
