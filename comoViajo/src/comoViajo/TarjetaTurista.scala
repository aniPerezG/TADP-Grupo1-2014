package comoViajo

class TarjetaTurista extends Tarjeta {
  
  var zona : List[Direccion] = List()
  
  def precioConDescuento (unRecorrido : Recorrido) : Double = 
  {
    
    return (unRecorrido.costoBase) * 0.9
  }
  
   val condicion = {unRecorrido: Recorrido => zona.contains(unRecorrido.paradaDeSubida) && zona.contains(unRecorrido.paradaDeBajada) }


}