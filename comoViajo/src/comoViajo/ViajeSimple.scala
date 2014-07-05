package comoViajo

class ViajeSimple (var recorrido : Recorrido) extends Viaje{ 
  
    def costoDelViaje (unaTarjeta : Tarjeta) : Double = {
      return unaTarjeta.precioDeTarjeta(recorrido)
    }
    
    def costoDelViaje () : Double = {
      return recorrido.costoBase
    }
    
//    def tiempoDelViaje : Double = {
//      return recorrido.tiempoBase
//    }

}