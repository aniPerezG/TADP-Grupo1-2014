package transportes

import module.Viaje
import module.Lugar

trait Transporte {

  var paradas: List[Lugar]
 
  def costoViaje (viaje : Viaje): Double
  def tiempoPorEstaciones (viaje : Viaje) : Int
  def tiempoPorCombinaciones (viaje : Viaje) : Int
  
  
  def tiempoDeViaje(viaje : Viaje) : Int = {
    this.tiempoPorEstaciones(viaje) + this.tiempoPorCombinaciones(viaje)
  }
  
  def cantidadDeEstaciones(viaje:Viaje) : Int = {
    viaje.recorrido.intersect(this.paradas).length
  }/*Busco la cantidad de coincidencias entre mi recorrido y las paradas (o estaciones) del transporte*/
 
 
  
}