package comoViajo

class TarjetaTrabajador extends Tarjeta {

  var laBoca: List[Direccion] = List()
  var liniers: List[Direccion] = List()
  var centro: List[Direccion] = List()
//
//  val condicion = { unRecorrido: Recorrido =>
//    (liniers.contains(unRecorrido.paradaDeSubida) || laBoca.contains(unRecorrido.paradaDeSubida)) &&
//      centro.contains(unRecorrido.paradaDeBajada)
//  }
  val condicion = {unRecorrido : Recorrido =>
    (unRecorrido.paradaDeSubida.zona match{
      case PALERMO | LINIERS=> true
      case _ => false
    }) && 
    (unRecorrido.paradaDeBajada.zona match{
      case CENTRO => true
      case _ => false
    })
   }//FIXME para mi esto es un mal uso de guardas

  def precioConDescuento(unRecorrido: Recorrido): Double = unRecorrido.costoBase - 1.50
}