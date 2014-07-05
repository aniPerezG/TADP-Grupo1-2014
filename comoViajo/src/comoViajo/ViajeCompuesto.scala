package comoViajo

class ViajeCompuesto(var unRecorrido: Recorrido, var otroRecorrido: Recorrido) extends Viaje {

  var informador = new InformadorPosta

  def costoDelViaje(): Double = {
    return costoCombinar(unRecorrido, otroRecorrido)
  }

  def costoDelViaje(unaTarjeta: Tarjeta): Double = {
    return costoCombinar(unRecorrido, otroRecorrido, unaTarjeta)
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

  def tiempoDelViaje: Double = (unRecorrido.transporte, otroRecorrido.transporte ) match {
    case (SUBTE, SUBTE) => 4
    case (TREN(_), SUBTE) | (SUBTE, TREN(_)) => 5
    case (TREN(_), TREN(_))  => 6
    case _ => {
      var distanciaEntreParadas = informador.distanciaAPie(unRecorrido.paradaDeBajada, otroRecorrido.paradaDeSubida)
      return (distanciaEntreParadas / 100) * 2.5
    }
  }

}