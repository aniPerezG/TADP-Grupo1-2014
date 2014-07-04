package comoViajo

trait Transporte {
  
  val informadorTransportes: InformacionTransportes
  var paradas: Array[Direccion] //Usamos un array porque tiene que estar ordenado
  
  def costoRecorrido (unRecorrido : Recorrido): Double 
  def tiempoRecorrido (unRecorrido : Recorrido) : Double
  def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, otroTransporte : Transporte) : Double

  
  def paradasDe(unRecorrido : Recorrido) : Int = {
    
    val indiceOrigen = paradas.indexOf(unRecorrido.paradaDeSubida)
    val indiceLlegada = paradas.indexOf(unRecorrido.paradaDeBajada , indiceOrigen)
    return indiceLlegada - indiceOrigen
    
  }

  def interseccionCon(otroTransporte: Transporte) : Direccion = {
    return this.paradas.intersect(otroTransporte.paradas).head
  }
  
  def transportesVecinosEntre(): Array[Transporte] = {
       
      return paradas.flatMap{unaParada => informadorTransportes.transportesCerca(unaParada)}
  }

  def costoDeCombinar(combinacion: Recorrido, unTransporte: Transporte) : Double = {
    return combinacion.costoBase
  }
  
  def costoDeCombinar(combinacion : Recorrido, unTransporte : Transporte, unaTarjeta : Tarjeta) : Double = {
    return unaTarjeta.precioDeTarjeta(combinacion)
  }

}