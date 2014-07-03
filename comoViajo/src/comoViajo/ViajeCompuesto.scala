package comoViajo

class ViajeCompuesto (var unRecorrido : Recorrido, var combinacion : Recorrido)extends Viaje{
  
    def costoDelViaje() : Double = {
        return unRecorrido.costoCombinado(combinacion.transporte )
    }
    
    def costoDelViaje(unaTarjeta : Tarjeta) : Double {
      
    }

  def tiempoDelViaje: Double = {
      ???
    }

  def obtenerTiempoDeCombinaciones: Double = {
      ???
    }
}