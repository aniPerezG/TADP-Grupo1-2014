package comoViajo

trait Transporte {
  
  var paradas: Array[Direccion] //Usamos un array porque tiene que estar ordenado
  
  def costoRecorrido (unRecorrido : Recorrido): Double //Ver si lo del usuario lo calculamos acá
  def tiempoRecorrido (unRecorrido : Recorrido) : Double
  def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido) : Double//Cuando lo hagamos en funcional podríamos usar pattern Matching aca

  def paradasDe(unRecorrido : Recorrido) : Int = {
    
    val indiceOrigen = paradas.indexOf(unRecorrido.paradaDeSubida)
    val indiceLlegada = paradas.indexOf(unRecorrido.paradaDeBajada , indiceOrigen)
    return indiceLlegada - indiceOrigen
    
  }

}