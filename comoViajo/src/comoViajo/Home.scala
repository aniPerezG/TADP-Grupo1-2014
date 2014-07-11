package comoViajo

trait Home[T] {
  
  var allInstances: List[T] = Nil
  
  def add(elem: T) = allInstances = elem :: allInstances
  
  def clean() = allInstances = Nil

}

object ViajesSimples extends Home[ViajeSimple]


