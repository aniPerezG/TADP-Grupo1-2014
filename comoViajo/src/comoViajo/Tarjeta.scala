package comoViajo

import scala.collection.mutable.MutableList

trait Tarjeta {

  val condicion: (Recorrido => Boolean)

  def precioDeTarjeta(unRecorrido: Recorrido): Double = if (this.condicion(unRecorrido)) this.precioConDescuento(unRecorrido) else unRecorrido.costoBase

  def precioConDescuento(unRecorrido: Recorrido): Double

}