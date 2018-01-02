package travis.Config

import travis.Matrix._

/**
  * A single Job being run by travis
  *
  * @param description A short, human-readable, description of the job
  * @param script A script to executed when running the job
  * @param keySet a set of keys that will be expanded in the build matrix
  */
case class TravisJob(description: String, script: String, keySet: MatrixSet = MatrixSet()) {
  lazy val defaults : MatrixSet = MatrixSet(
    ScriptKey(script),
    DescriptionKey(description)
  )

  /** expands this job into a concrete list [[]] assignments */
  def expand(stage: TravisStage, config: TravisConfig): List[MatrixAssignment] =
    (keySet << (config.globals && defaults)).expand
}