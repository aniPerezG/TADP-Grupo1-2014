package transportes

import module.Viaje
import module.Lugar

trait Transporte {

  var parada: Lugar
  def costoViaje (viaje : Viaje): Double
  
}