package comoViajo

class Recorrido(var paradaDeSubida: Direccion, var paradaDeBajada: Direccion, var transporte: CaseTransporte, var informador: InformacionTransportes) {

  //Fijarnos de en el recorrido hacer lo mismo que en listas que no sabemos e que tipo son hasta declararlas y hacer Recorrido[Transporte]

  def costoBase: Double = transporte match {
    case SUBTE => 4.5
    case TREN(tabla) => tabla.precio(this)
    case COLECTIVO =>
      var distancia: Int = informador.distanciaColectivo(paradaDeSubida, paradaDeBajada)
      if (distancia < 3) 2.5 else {
        if (distancia < 6) 2.75 else 2.85
      }
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

  def tiempoBase: Double = transporte match {
    case SUBTE => SUBTE.paradasDe(this) * 2
    case TREN(tabla) => TREN(tabla).paradasDe(this) * 3
    case COLECTIVO =>
      var distancia = informador.distanciaColectivo(paradaDeSubida, paradaDeBajada)
      distancia / 15000 * 60
  }

  /*
    def tiempoDeCombinacionCon(recorrido: Recorrido) : Double = {
   
       return transporte.tiempoDeCombinacionEntre(this, recorrido, recorrido.transporte)
        
    }
    */

}