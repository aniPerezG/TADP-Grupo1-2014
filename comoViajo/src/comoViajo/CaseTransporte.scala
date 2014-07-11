package comoViajo

import scala.collection.mutable.Buffer


abstract class CaseTransporte {
  val paradas : Array[Direccion]
  val informador : InformacionTransportes
  val compania : Compania

  def paradasDe(unRecorrido: Recorrido): Int = {
    val indiceOrigen = paradas.indexOf(unRecorrido.paradaDeSubida)
    val indiceLlegada = paradas.indexOf(unRecorrido.paradaDeBajada, indiceOrigen)
    indiceLlegada - indiceOrigen
  }
  
  def zonasDe(unRecorrido: Recorrido): Set[Zona] = {
    var paradasDeUnRecorrido : Buffer[Direccion] = Buffer[Direccion]()
    val indiceOrigen = paradas.indexOf(unRecorrido.paradaDeSubida)
<<<<<<< HEAD
    val indiceLlegada = paradas.indexOf(unRecorrido.paradaDeBajada, indiceOrigen)

    paradas.slice(indiceOrigen,indiceLlegada).copyToBuffer(paradasDeUnRecorrido)
    paradasDeUnRecorrido.map(_.zona).toSet

=======
    paradas.copyToArray(paradasDeUnRecorrido, indiceOrigen, this.paradasDe(unRecorrido))
    val zonas = paradasDeUnRecorrido.map(_.zona).toSet
    zonas
>>>>>>> origin/solucionFuncional
  }

  def interseccionCon(otroTransporte: CaseTransporte): Direccion = this.paradas.intersect(otroTransporte.paradas).head

  def transportesVecinosEntre(): Array[CaseTransporte] = paradas.flatMap{unaParada => informador.transportesCerca(unaParada) }


}

case class SUBTE(paradas: Array[Direccion], informador: InformacionTransportes, compania : Compania) extends CaseTransporte

case class COLECTIVO(paradas: Array[Direccion], informador: InformacionTransportes, compania : Compania) extends CaseTransporte {
  def tiempoCombinacion(paradaBajada: Direccion, paradaSubida: Direccion): Double =
    (this.informador.distanciaAPie(paradaBajada, paradaSubida) / 100) * 2.5
}

case class TREN(paradas: Array[Direccion], informador: InformacionTransportes, tablaDePrecios: Map[Int, Double] = Map(), compania : Compania) extends CaseTransporte {
  def costo(recorrido: Recorrido): Double = {
    var cantParadas = this.paradasDe(recorrido)
    var tuplaConElPosiblePrecioAPagar = tablaDePrecios.find { case (cantidadMaxima, precioAPagar) => cantidadMaxima >= cantParadas }
    if (tuplaConElPosiblePrecioAPagar.isDefined) {
      tuplaConElPosiblePrecioAPagar.get._2 //En caso de que el option devuelto por el find sea Some obtengo el elemento del diccionario(el segundo componente de la tupla)
    } else {
      tablaDePrecios.last._2 //En caso de que el option devuelto por el find sea none devuelvo el mayor precio
    }
  }
}
