package comoViajo

class Estadistica[A] {

  var conditions: List[A => Boolean] = List()

  var transformation: Option[A => _] = None

  var group: Option[A => _] = None

  var _from: List[A] = List()

  def from(value: List[A]) = {
    _from = value
    this
  }

  def from = _from

  def select[B](s: A => B) = {
    transformation = Some(s)
    this
  }

  def where(s: A => Boolean) = {
    conditions = { s :: conditions }
    this
  }

  def execute = group match {
    case None => simpleExecute(from)
    case _ => from.groupBy(group.get).map { case (k, values) => (k, simpleExecute(values)) }
  }

  def simpleExecute(values: List[A]) = {
    transformation match {
      case None => applyConditions(values)
      case _ => applyConditions(values).map(transformation.get)
    }
  }

  def applyConditions(values: List[A]) =
    values.filter(value => conditions.forall(_.apply(value)))

  def groupBy[C](s: A => C)= {
    group = Some(s)
    this
  }

}


