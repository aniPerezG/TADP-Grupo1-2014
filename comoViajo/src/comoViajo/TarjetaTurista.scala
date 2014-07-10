package comoViajo

class TarjetaTurista extends Tarjeta {

  var zona: List[Direccion] = List()

  val condicion: (Recorrido => Boolean) = { unRecorrido: Recorrido => true }

  def precioConDescuento(unRecorrido: Recorrido): Double = (unRecorrido.costoBase) * 0.9
  
}