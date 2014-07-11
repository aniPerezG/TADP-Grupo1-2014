package comoViajo


class ViajeCompuesto(var unRecorrido: Recorrido, var otroRecorrido: Recorrido) extends Viaje {

  def costoDelViaje(): Double = costoCombinar(unRecorrido, otroRecorrido)

  def costoDelViaje(unaTarjeta: Tarjeta): Double = costoCombinar(unRecorrido, otroRecorrido, unaTarjeta)

  def costoCombinar(primerRecorrido: Recorrido, segundoRecorrido: Recorrido) =
    (primerRecorrido.transporte, segundoRecorrido.transporte) match {
      case (SUBTE(_, _,_), SUBTE(_, _,_)) => primerRecorrido.costoBase
      case _ => primerRecorrido.costoBase + segundoRecorrido.costoBase
    }

  def costoCombinar(primerRecorrido: Recorrido, segundoRecorrido: Recorrido, tarjeta: Tarjeta) =
    (primerRecorrido.transporte, segundoRecorrido.transporte) match {
      case (SUBTE(_, _,_), SUBTE(_, _,_)) => tarjeta.precioDeTarjeta(primerRecorrido)
      case _ => tarjeta.precioDeTarjeta(primerRecorrido) + tarjeta.precioDeTarjeta(segundoRecorrido)
    }

  def tiempoDelViaje: Double =
    unRecorrido.tiempoBase + otroRecorrido.tiempoBase + this.tiempoDeCombinacion

  def tiempoDeCombinacion: Double = (unRecorrido.transporte, otroRecorrido.transporte) match {
    case (SUBTE(_, _,_), SUBTE(_, _,_)) => 4
    case (TREN(_, _, _,_), SUBTE(_, _,_)) | (SUBTE(_, _,_), TREN(_, _, _,_)) => 5
    case (TREN(_, _, _,_), TREN(_, _, _,_)) => 6
    case (COLECTIVO(paradas, informador,compania), _) =>
      COLECTIVO(paradas, informador,compania).tiempoCombinacion(unRecorrido.paradaDeBajada, otroRecorrido.paradaDeSubida)
    case (_, COLECTIVO(paradas, informador,compania)) =>
      COLECTIVO(paradas, informador,compania).tiempoCombinacion(unRecorrido.paradaDeBajada, otroRecorrido.paradaDeSubida)
  } 
  
  def zonasPorLaQuePasa: Set[Zona] = 
    unRecorrido.zonasDelRecorrido ++ otroRecorrido.zonasDelRecorrido

}