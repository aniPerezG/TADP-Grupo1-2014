package comoViajo

trait Transporte {
  
  var paradas: Array[Direccion] //Usamos un array porque tiene que estar ordenado
  
  def costoRecorrido (unRecorrido : Recorrido): Double //Ver si lo del usuario lo calculamos ac�
  def tiempoRecorrido (unRecorrido : Recorrido) : Double
  def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido) : Double//Cuando lo hagamos en funcional podr�amos usar pattern Matching aca
  def combinacionCon(unTren : Tren) : Double
  def combinacionCon(unSubte : Subte) : Double
  
  def paradasDe(unRecorrido : Recorrido) : Int = {
    
    val indiceOrigen = paradas.indexOf(unRecorrido.paradaDeSubida)
    val indiceLlegada = paradas.indexOf(unRecorrido.paradaDeBajada , indiceOrigen)
    return indiceLlegada - indiceOrigen
    
  }

  def transportesVecinos : List[Transporte]= {
    
    ???
  }

  def dameElViajeA(destino: Direccion) : Viaje= {
    ???
  }

}