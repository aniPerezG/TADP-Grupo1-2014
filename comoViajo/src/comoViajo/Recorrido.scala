package comoViajo

class Recorrido(var paradaDeSubida: Direccion, var paradaDeBajada: Direccion, var transporte: CaseTransporte) {

  //Fijarnos de en el recorrido hacer lo mismo que en listas que no sabemos e que tipo son hasta declararlas y hacer Recorrido[Transporte]

  def costoBase: Double = transporte match {
    case SUBTE(_,_) => 4.5
    case TREN(paradas,informador,tabla) => TREN(paradas,informador,tabla).costo(this)
    case COLECTIVO(_,informador) =>
      var distancia: Int = informador.distanciaColectivo(paradaDeSubida, paradaDeBajada)
      if (distancia < 3000) 2.5 else {
        if (distancia < 6000) 2.75 else 2.85
      }
  }

  def tiempoBase: Double = transporte match {
    case SUBTE(paradas,informador) => SUBTE(paradas,informador).paradasDe(this) * 2
    case TREN(paradas,informador,tabla) => TREN(paradas,informador,tabla).paradasDe(this) * 3
    case COLECTIVO(_,informador)=>
      var distancia = informador.distanciaColectivo(paradaDeSubida, paradaDeBajada)
      return distancia / 15000.00 * 60.00
      
  }


}