package module

import transportes.Transporte

class Viaje(tipoTarjeta : Tarjeta) {

  var transportes: List[Transporte]
  var precioBase: Double

  def obtenerCostoViaje {
    precioBase = transportes.foldLeft(0.0)((num, trans) => num + trans.costoViaje(this))
    tipoTarjeta.actuar(this)
  }
}