package comoViajo

class ViajeCompuesto(var unRecorrido: Recorrido, var otroRecorrido: Recorrido) extends Viaje {

  val informador = new InformadorPosta

  def costoDelViaje(): Double = {
    return costoCombinar(unRecorrido, otroRecorrido)
  }

  def costoDelViaje(unaTarjeta: Tarjeta): Double = {
    return costoCombinar(unRecorrido, otroRecorrido, unaTarjeta)
  }

  def costoCombinar(primerRecorrido: Recorrido, segundoRecorrido: Recorrido) =
    primerRecorrido.transporte match {
      case SUBTE(_,_) => segundoRecorrido.transporte match {
        case SUBTE(_,_) => primerRecorrido.costoBase
      }
      case _ => primerRecorrido.costoBase + segundoRecorrido.costoBase
    }

  def costoCombinar(primerRecorrido: Recorrido, segundoRecorrido: Recorrido, tarjeta: Tarjeta) =
    primerRecorrido.transporte match {
      case SUBTE(_,_) => segundoRecorrido.transporte match {
        case SUBTE(_,_) => tarjeta.precioDeTarjeta(primerRecorrido)
      }
      case _ => tarjeta.precioDeTarjeta(primerRecorrido) + tarjeta.precioDeTarjeta(segundoRecorrido)
    }

  def tiempoDelViaje: Double = (unRecorrido.transporte, otroRecorrido.transporte ) match {
    case (SUBTE(_,_), SUBTE(_,_)) => 4
    case (TREN(_,_,_), SUBTE(_,_)) | (SUBTE(_,_), TREN(_,_,_)) => 5
    case (TREN(_,_,_), TREN(_,_,_))  => 6
    case _ => {
      var distanciaEntreParadas = informador.distanciaAPie(unRecorrido.paradaDeBajada, otroRecorrido.paradaDeSubida)
      return (distanciaEntreParadas / 100) * 2.5
    }
  }

}