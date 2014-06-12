package module

abstract class Tarjeta {
  var coeficienteDescuento: Double 
  var condicion : (Viaje => Boolean) //esto no se si esta bien 
  
  def precioAPagar(viaje: Viaje) {
    if(condicion(viaje)){
    (viaje.precioBase) * (this.coeficienteDescuento)
  }
  else{
    return viaje.precioBase
  }
}