package comoViajo

abstract class CaseTransporte {

  var paradas: Array[Direccion] //Usamos un array porque tiene que estar ordenado

  def paradasDe(unRecorrido: Recorrido): Int = {

    val indiceOrigen = paradas.indexOf(unRecorrido.paradaDeSubida)
    val indiceLlegada = paradas.indexOf(unRecorrido.paradaDeBajada, indiceOrigen)
    return indiceLlegada - indiceOrigen

  }
}

case object SUBTE extends CaseTransporte
case object COLECTIVO extends CaseTransporte
case class TREN(tabla: TablaPrecios) extends CaseTransporte
