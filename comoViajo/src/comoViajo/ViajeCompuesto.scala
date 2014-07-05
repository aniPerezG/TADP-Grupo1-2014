package comoViajo

class ViajeCompuesto (var unRecorrido : Recorrido, var otroRecorrido : Recorrido)extends Viaje{
  
    def costoDelViaje() : Double = {
        return unRecorrido.costoCombinado(otroRecorrido)
    }
    
    def costoDelViaje(unaTarjeta : Tarjeta) : Double = {
        return unRecorrido.costoCombinado(otroRecorrido,unaTarjeta)
    }

  def tiempoDelViaje: Double = {
      return unRecorrido.tiempoBase + unRecorrido.tiempoDeCombinacionCon(otroRecorrido) + otroRecorrido.tiempoBase
    }

}