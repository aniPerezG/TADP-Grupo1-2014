package comoViajo

class ViajeSimple(var recorrido: Recorrido) extends Viaje {

  def costoDelViaje(unaTarjeta: Tarjeta): Double = unaTarjeta.precioDeTarjeta(recorrido)

  def costoDelViaje(): Double = recorrido.costoBase

  def tiempoDelViaje: Double = recorrido.tiempoBase
  
  def zonasPorLaQuePasa: Set[Zona] = recorrido.zonasDelRecorrido 

}