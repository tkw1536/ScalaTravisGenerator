lazy val root = (project in file("."))
  .settings(
    name := "ScalaTravisGenerator",
    crossScalaVersions := Seq("2.11.8", "2.12.2"),
    scalacOptions := Seq("-feature", "-language:implicitConversions", "-language:postfixOps", "-deprecation")
  )