package comoViajo

class ViajeCompuesto(var unRecorrido: Recorrido, var otroRecorrido: Recorrido) extends Viaje {

  def costoDelViaje(): Double = {
    return costoCombinar (unRecorrido , otroRecorrido)
  }

  def costoDelViaje(unaTarjeta: Tarjeta): Double = {
    return costoCombinar(unRecorrido , otroRecorrido , unaTarjeta)
  } 

  
  def costoCombinar(primerRecorrido: Recorrido, segundoRecorrido: Recorrido) = 
    primerRecorrido.transporte match {
    case SUBTE => segundoRecorrido.transporte match {
      case SUBTE => primerRecorrido.costoBase
    }
    case _ => primerRecorrido.costoBase + segundoRecorrido.costoBase
  }

  def costoCombinar(primerRecorrido: Recorrido, segundoRecorrido: Recorrido, tarjeta: Tarjeta) =
    primerRecorrido.transporte match {
      case SUBTE => segundoRecorrido.transporte match {
        case SUBTE => tarjeta.precioDeTarjeta(primerRecorrido)
      }
      case _ => tarjeta.precioDeTarjeta(primerRecorrido) + tarjeta.precioDeTarjeta(segundoRecorrido)
    }

  //  def tiempoDelViaje: Double = {
  //      return unRecorrido.tiempoBase + unRecorrido.tiempoDeCombinacionCon(otroRecorrido)
  //    }

}