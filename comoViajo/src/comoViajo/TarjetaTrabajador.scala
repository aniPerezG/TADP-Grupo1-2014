package comoViajo

class TarjetaTrabajador  extends Tarjeta {

      
     var laBoca : Array[Direccion] = Array()
     var liniers : Array[Direccion] = Array()
     var centro : Array[Direccion] = Array()
     
     val condicion = {unRecorrido : Recorrido => (liniers.contains(unRecorrido.paradaDeSubida) || laBoca.contains(unRecorrido.paradaDeSubida)) &&
     						   centro.contains(unRecorrido.paradaDeBajada)}
     
     def precioConDescuento(unRecorrido: Recorrido) : Double = {
       
       return unRecorrido.costoBase - 1.50
     }
}