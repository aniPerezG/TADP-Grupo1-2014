package comoViajo

import scala.collection.mutable.MutableList

trait Tarjeta {

  
    val condicion: (Recorrido => Boolean)
    
    def precioDeTarjeta (unRecorrido : Recorrido) : Double  = {
      
       if(this.condicion(unRecorrido))
       {
         return this.precioConDescuento(unRecorrido) 
       } 
       else 
       {
         return unRecorrido.costoBase
       }
     }
    
    def precioConDescuento(unRecorrido : Recorrido) : Double 

}