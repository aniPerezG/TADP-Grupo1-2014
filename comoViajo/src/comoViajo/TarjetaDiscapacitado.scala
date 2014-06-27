package comoViajo

class TarjetaDiscapacitado extends Tarjeta {
  
     condicion = {unRecorrido : Recorrido => true}
	  
	   def precioConDescuento (unRecorrido : Recorrido) : Double = {
	     
       return 0.0
     }  

}