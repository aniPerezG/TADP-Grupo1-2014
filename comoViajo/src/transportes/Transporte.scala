package transportes

import module.Viaje

trait Transporte {

  def costoViaje (viaje : Viaje): Double
  
}