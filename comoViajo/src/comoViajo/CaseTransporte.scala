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
case class TREN(paradas: Array[Direccion], informador: InformacionTransportes, tabla: TablaPrecios) extends CaseTransporte
