package transportes

import module.Viaje

class Colectivo extends Transporte{
  
  var informadorTransportes: InformacionTransportes
  var distanciaARecorrer : Int
  
  def costoViaje(viaje : Viaje) : Double = {
   		distanciaARecorrer = informadorTransportes.distanciaColectivo(viaje.origen , viaje.destino )
   		if(distanciaARecorrer < 3) 
   		{
   		  return 2.5
   		}
   		if (distanciaARecorrer < 6)
   		{
   		  return 2.75
   		}
   		else
   		{
   		  return 2.85
   		}
  }

}