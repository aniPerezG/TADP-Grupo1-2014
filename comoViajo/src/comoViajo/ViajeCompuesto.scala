package comoViajo

class ViajeCompuesto(var unRecorrido: Recorrido, var otroRecorrido: Recorrido) extends Viaje {

  def costoDelViaje(): Double = {
    return costoCombinar(unRecorrido, otroRecorrido)
  }

  def costoDelViaje(unaTarjeta: Tarjeta): Double = {
    return costoCombinar(unRecorrido, otroRecorrido, unaTarjeta)
  }

  def costoCombinar(primerRecorrido: Recorrido, segundoRecorrido: Recorrido) =
    primerRecorrido.transporte match {
      case SUBTE(_, _) => segundoRecorrido.transporte match {
        case SUBTE(_, _) => primerRecorrido.costoBase
      }
      case _ => primerRecorrido.costoBase + segundoRecorrido.costoBase
    }

  def costoCombinar(primerRecorrido: Recorrido, segundoRecorrido: Recorrido, tarjeta: Tarjeta) =
    primerRecorrido.transporte match {
      case SUBTE(_, _) => segundoRecorrido.transporte match {
        case SUBTE(_, _) => tarjeta.precioDeTarjeta(primerRecorrido)
      }
      case _ => tarjeta.precioDeTarjeta(primerRecorrido) + tarjeta.precioDeTarjeta(segundoRecorrido)
    }

  def tiempoDelViaje: Double = {
    unRecorrido.tiempoBase + otroRecorrido.tiempoBase + this.tiempoDeCombinacion
  }

  def tiempoDeCombinacion: Double = (unRecorrido.transporte, otroRecorrido.transporte) match {
    case (SUBTE(_, _), SUBTE(_, _)) => 4
    case (TREN(_, _, _), SUBTE(_, _)) | (SUBTE(_, _), TREN(_, _, _)) => 5
    case (TREN(_, _, _), TREN(_, _, _)) => 6
    case (COLECTIVO(paradas, informador), _) => tiempoDelColectivo(COLECTIVO(paradas, informador))
    case (_, COLECTIVO(paradas, informador)) => tiempoDelColectivo(COLECTIVO(paradas, informador))
  }

  def tiempoDelColectivo(colectivo: COLECTIVO): Double = {
    var distanciaEntreParadas = colectivo.informador.distanciaAPie(unRecorrido.paradaDeBajada, otroRecorrido.paradaDeSubida)
    return (distanciaEntreParadas / 100) * 2.5
  }

}