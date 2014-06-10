package transportes

import comoViajo.Viaje

trait Transporte {

  def costoViaje (viaje : Viaje): Double
  
}