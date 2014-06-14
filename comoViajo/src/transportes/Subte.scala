package transportes

import module.Viaje

class Subte extends Transporte {

  def costoViaje(viaje : Viaje) =  4.50 
  
  def tiempoPorEstaciones (viaje : Viaje) : Int =
  {
    2 * cantidadDeEstaciones(viaje)
  }
  def tiempoPorCombinaciones (viaje : Viaje) : Int
}