package comoViajo

abstract class Viaje() {

  def costoDelViaje(unaTarjeta: Tarjeta): Double

  def costoDelViaje(): Double

  def tiempoDelViaje: Double
  
  def zonasPorLaQuePasa: List[Zona]

}