package comoViajo

class Colectivo extends Transporte{
  
    val informadorTransportes: InformacionTransportes

  
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
    
    def tiempoRecorrido (unRecorrido : Recorrido) : Int = {
      
      return this.distanciaARecorrer(unRecorrido) / 15000 * 60
    }
    
     
     def tiempoDeCombinacion(combinacion : Recorrido) : Int = {
       return 0 //TODO
     }

}