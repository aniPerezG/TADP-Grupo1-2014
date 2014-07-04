package comoViajo

class Colectivo (val informadorTransportes: InformacionTransportes, var paradas: Array[Direccion]) extends Transporte{
  
    def costoRecorrido(unRecorrido : Recorrido) : Double = {
     		if(this.distanciaARecorrer(unRecorrido) < 3000) 
     		{
     		  return 2.5
     		}
     		if (this.distanciaARecorrer(unRecorrido) < 6000)
     		{
     		  return 2.75
     		}
     		else
     		{
     		  return 2.85
     		}
    } 
    
    def distanciaARecorrer(unRecorrido : Recorrido) : Int = {
    
      return informadorTransportes.distanciaColectivo(unRecorrido.paradaDeSubida , unRecorrido.paradaDeBajada )
    
    }
    
    def tiempoRecorrido (unRecorrido : Recorrido) : Double = {
      
      return this.distanciaARecorrer(unRecorrido) / 15000 * 60
    }
    
     
    def tiempoDeCombinacionEntre(unRecorrido : Recorrido ,combinacion : Recorrido, unTransporte : Transporte) : Double = {
       
      val distanciaEntreParadas = informadorTransportes.distanciaAPie( unRecorrido.paradaDeBajada,combinacion.paradaDeSubida)
      return (distanciaEntreParadas / 100) * 2.5

    }
}