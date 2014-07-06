package comoViajo

class TarjetaTrabajador extends Tarjeta {

  var laBoca: List[Direccion] = List()
  var liniers: List[Direccion] = List()
  var centro: List[Direccion] = List()

  val condicion = { unRecorrido: Recorrido =>
    (liniers.contains(unRecorrido.paradaDeSubida) || laBoca.contains(unRecorrido.paradaDeSubida)) &&
      centro.contains(unRecorrido.paradaDeBajada)
  }

  def precioConDescuento(unRecorrido: Recorrido): Double = unRecorrido.costoBase - 1.50
}