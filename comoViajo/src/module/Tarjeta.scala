package module

abstract class Tarjeta {
  var condicion: (Viaje => Boolean) //esto no se si esta bien 

  def precioAPagar(viaje: Viaje):Double = {
    if(this.condicion(viaje)){
      return this.totalAPagar(viaje)
    } else {
      return viaje.precioBase
    }
  }
  
  def totalAPagar(viaje :Viaje) : Double 
}