package comoViajo

abstract class CaseTransporte  {

  //var informador = new InformadorPosta
  val paradas: Array[Direccion] //Usamos un array porque tiene que estar ordenado
  val informador: InformacionTransportes

  def paradasDe(unRecorrido: Recorrido): Int = {
    val indiceOrigen = paradas.indexOf(unRecorrido.paradaDeSubida)
    val indiceLlegada = paradas.indexOf(unRecorrido.paradaDeBajada, indiceOrigen)
    return indiceLlegada - indiceOrigen
  }

  def interseccionCon(otroTransporte: CaseTransporte): Direccion = {
    return this.paradas.intersect(otroTransporte.paradas).head
  }

  def transportesVecinosEntre(): Array[CaseTransporte] = {

    return paradas.flatMap { unaParada => informador.transportesCerca(unaParada) }
  }
}

case class SUBTE(paradas: Array[Direccion], informador: InformacionTransportes) extends CaseTransporte
case class COLECTIVO(paradas: Array[Direccion], informador: InformacionTransportes) extends CaseTransporte
case class TREN(paradas: Array[Direccion], informador: InformacionTransportes, tablaDePrecios: Map[Int,Double] = Map()) extends CaseTransporte {
  def costo (recorrido : Recorrido) : Double = {
       var cantParadas = this.paradasDe(recorrido)
      var tuplaConElPosiblePrecioAPagar = tablaDePrecios.find{case (cantidadMaxima, precioAPagar) => cantidadMaxima >= cantParadas}
      if(tuplaConElPosiblePrecioAPagar.isDefined) {
        tuplaConElPosiblePrecioAPagar.get._2 //En caso de que el option devuelto por el find sea Some obtengo el elemento del diccionario(el segundo componente de la tupla)
      }
      else {
        tablaDePrecios.last._2  //En caso de que el option devuelto por el find sea none devuelvo el mayor precio
      }
    }
}
