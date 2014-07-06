package comoViajo

class TarjetaDiscapacitado extends Tarjeta {

  val condicion: (Recorrido => Boolean) = { unRecorrido: Recorrido => true }

  def precioConDescuento(unRecorrido: Recorrido): Double = 0.0

}