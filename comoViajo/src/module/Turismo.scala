package module

class Turismo extends Tarjeta {
	
  val zona : Zona
  
  val coeficienteDescuento = 0.9
  condicion = {viaje: Viaje => zona.lugares.contains(viaje.origen) && zona.lugares.contains(viaje.destino) }

  def totalAPagar (viaje : Viaje)= {
    (viaje.precioBase) * (this.coeficienteDescuento)
  }
}