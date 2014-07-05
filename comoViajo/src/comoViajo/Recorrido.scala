package comoViajo

 class Recorrido(var paradaDeSubida : Direccion, var paradaDeBajada: Direccion, var transporte : Transporte)  {

   
    type T <: Transporte
    
    
    //Fijarnos de en el recorrido hacer lo mismo que en listas que no sabemos e que tipo son hasta declararlas y hacer Recorrido[Transporte]
    
    def costoBase : Double = { 
      return transporte.costoRecorrido(this)
    } 

    def costoCombinado(combinacion : Recorrido) : Double = {
    	return this.costoBase + transporte.costoDeCombinar(combinacion, combinacion.transporte)
    }
    
    def costoCombinado(combinacion : Recorrido , unaTarjeta : Tarjeta) : Double = {
    	return unaTarjeta.precioDeTarjeta(this) + transporte.costoDeCombinar(combinacion,combinacion.transporte, unaTarjeta)
    }
    
    def tiempoBase : Double = {
      return transporte.tiempoRecorrido(this)
    }

    def tiempoDeCombinacionCon(recorrido: Recorrido) : Double = {
   
       return transporte.tiempoDeCombinacionEntre(this, recorrido, recorrido.transporte)
        
    }
    

}