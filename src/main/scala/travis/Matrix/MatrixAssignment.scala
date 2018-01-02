package travis.Matrix

import travis.yaml._

/**
  * A concrete assignment of [[MatrixKey]]s
  * @param nameMap
  */
case class MatrixAssignment(nameMap: Map[String, MatrixKey[YAML]]){
  implicit def toMap: Map[String, YAML] = nameMap.toList.map(_._2.toPair).toMap

  def ++(ass: MatrixAssignment) = MatrixAssignment(nameMap ++ ass.nameMap)
  def ++(key: MatrixKey[YAML]) = MatrixAssignment(nameMap ++ Map((key.name, key)))
  lazy val toStructure: YAMLStructure = toMap
}

object MatrixAssignment {
  def apply(nameList: List[MatrixKey[YAML]]) = new MatrixAssignment(
    nameList.groupBy(_.name).mapValues({
      case List(h) => h
      case _ => throw new Exception("nameList should contain exactly one MatrixKey per _.name")
    })
  )
}