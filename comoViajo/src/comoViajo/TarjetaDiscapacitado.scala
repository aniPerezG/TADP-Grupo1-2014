package comoViajo

class TarjetaDiscapacitado extends Tarjeta {
 
	  val condicion : (Recorrido => Boolean) = {unRecorrido : Recorrido => true}
	  
	   def precioConDescuento (unRecorrido : Recorrido) : Double = {
	     
       return 0.0
     }  

}