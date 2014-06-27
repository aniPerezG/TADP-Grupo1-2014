package comoViajo

trait Transporte {
  
  var paradas: Array[Direccion] //Usamos un array porque tiene que estar ordenado
  
  def costoRecorrido (unRecorrido : Recorrido): Double //Ver si lo del usuario lo calculamos acá
  def tiempoRecorrido (unRecorrido : Recorrido) : Int
  def tiempoDeCombinacion(combinacion : Recorrido) : Int//Cuando lo hagamos en funcional podríamos usar pattern Matching aca

  def paradasDe(unRecorrido : Recorrido) : Int = {
    
    //TODO ver como obtener la cantidad de elementos entre uno y otro
    return 1
  }

}