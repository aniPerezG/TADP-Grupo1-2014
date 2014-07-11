package comoViajo


class ViajeSimple(var recorrido: Recorrido) extends Viaje {
  
  def transporte = recorrido.transporte //Para los tests

  def costoDelViaje(unaTarjeta: Tarjeta): Double = unaTarjeta.precioDeTarjeta(recorrido)

  def costoDelViaje(): Double = recorrido.costoBase

  def tiempoDelViaje: Double = recorrido.tiempoBase
  
  def zonasPorLaQuePasa: Set[Zona] = recorrido.zonasDelRecorrido 

}