package module

import transportes.Transporte

class Viaje(tipoTarjeta : Tarjeta, unOrigen: Lugar, unDestino:Lugar) {
  
  var origen = unOrigen
  var destino = unDestino
  
  var transportes: List[Transporte]
  var precioBase: Double
  
  
  def obtenerCostoViaje {
    precioBase = transportes.foldLeft(0.0)((num, trans) => num + trans.costoViaje(this)) 
    tipoTarjeta.precioAPagar(this)
  }
  
}