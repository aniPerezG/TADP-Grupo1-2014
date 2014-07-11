package comoViajo

class TarjetaTrabajador extends Tarjeta {

  var laBoca: List[Direccion] = List()
  var liniers: List[Direccion] = List()
  var centro: List[Direccion] = List()

  val condicion = {unRecorrido : Recorrido =>
    (unRecorrido.paradaDeSubida.zona match{
      case LABOCA | LINIERS=> true
      case _ => false
    }) && 
    (unRecorrido.paradaDeBajada.zona match{
      case CENTRO => true
      case _ => false
    })
   }

  def precioConDescuento(unRecorrido: Recorrido): Double = unRecorrido.costoBase - 1.50
}