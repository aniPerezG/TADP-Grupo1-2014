package comoViajo

class Recorrido(var paradaDeSubida : Direccion, var paradaDeBajada: Direccion, var transporte : CaseTransporte, var informador : InformacionTransportes) {

    //Fijarnos de en el recorrido hacer lo mismo que en listas que no sabemos e que tipo son hasta declararlas y hacer Recorrido[Transporte]
   
    def costoBase : Double = transporte match { 
      case SUBTE => 4.5 
      case TREN(tabla) => tabla.precio(this)
      case COLECTIVO => 
        var distancia : Int = informador.distanciaColectivo(paradaDeSubida, paradaDeBajada )
        if (distancia < 3) 2.5 else {
          if (distancia < 6) 2.75 else 2.85
        }
    }
/*
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
    */

}