course := "progfun1"
assignment := "example"
scalaVersion := "2.13.0"
scalacOptions ++= Seq("-language:implicitConversions", "-deprecation")
libraryDependencies += Seq("com.novocode" % "junit-interface" % "0.11" % Test,
"org.scalatest" %% "scalatest" % "3.0.8" % "test")

testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v", "-s")
