package travis.Config

import travis.Matrix._
import travis.yaml._

/**
  * A single Travis CI build configuration
  * @param globals
  * @param stages
  */
case class TravisConfig(globals: MatrixSet, stages: TravisStage*) {
  def toYAML: YAMLStructure = {
    val stagesDesc : YAMLSequence = stages.map(_.toStageDesc).map(YAMLSequence.from(_)).foldLeft(YAMLSequence.empty)( _ ++ _)
    Map(
      "stages" -> stagesDesc,
      "jobs" -> YAMLStructure(Map(
        "include" -> stages.map(_.toJobList(this)).foldLeft(YAMLSequence.empty)(_ ++ _)
      ), None)
    )
  }
  def serialize : String = toYAML.serialize
}