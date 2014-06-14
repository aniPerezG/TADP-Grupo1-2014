package transportes

import module.Viaje

class Colectivo extends Transporte{
  
  var informadorTransportes: InformacionTransportes

  
  def costoViaje(viaje : Viaje) : Double = {
   		if(this.distanciaARecorrer(viaje) < 3) 
   		{
   		  return 2.5
   		}
   		if (this.distanciaARecorrer(viaje) < 6)
   		{
   		  return 2.75
   		}
   		else
   		{
   		  return 2.85
   		}
  }
  
  override
  def tiempoDeViaje(viaje : Viaje) : Int = 
  {
	distanciaARecorrer(viaje) / 15000 * 60
	
  }
  
  def distanciaARecorrer(viaje : Viaje) : Int =
  {
    informadorTransportes.distanciaColectivo(viaje.origen , viaje.destino ) + tiempoPorCombinaciones(viaje)
  }

  def tiempoPorCombinaciones (viaje : Viaje) : Int 
}