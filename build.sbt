enablePlugins(GitVersioning)

val projectName = "aws-lambda-scala-example"

lazy val createVersionFile = taskKey[Unit]("Create version file")
createVersionFile := {
  import java.nio.file.{Paths, Files}
  import java.nio.charset.StandardCharsets
  Files.write(Paths.get("version.txt"), version.value.getBytes(StandardCharsets.UTF_8))
}

val awsSdkVersion = "1.12.49"
val awsLambdaVersion = "1.2.1"
val awsLambdaEventsVersion = "3.9.0"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.automatrope",
      git.useGitDescribe := true,
      scalaVersion := "2.13.6"
    )),
    name := projectName,
    libraryDependencies ++= Seq(
      // AWS
      "com.amazonaws" % "aws-java-sdk-core"      % awsSdkVersion,
      "com.amazonaws" % "aws-java-sdk-s3"        % awsSdkVersion,
      "com.amazonaws" % "aws-lambda-java-core"   % awsLambdaVersion,
      "com.amazonaws" % "aws-lambda-java-events" % awsLambdaEventsVersion,

      // Gson
      "com.google.code.gson" % "gson" % "2.8.8",

      // Logging
      "com.amazonaws" % "aws-lambda-java-log4j2" % "1.2.0",
      "org.apache.logging.log4j" % "log4j-api" % "2.14.1",
      "org.apache.logging.log4j" % "log4j-core" % "2.14.1",
      "org.apache.logging.log4j" % "log4j-slf4j18-impl" % "2.13.0"
    ),

    assembly / assemblyJarName := s"$projectName.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
      case "reference.conf" => MergeStrategy.concat
      case _ => MergeStrategy.first
    }

  )

scalacOptions ++= Seq("-deprecation", "-feature")
