package comoViajo

import scala.collection.mutable.MutableList

trait Tarjeta {

  
    val condicion: (Recorrido => Boolean) //esto no se si esta bien 
    
    def precioDeTarjeta (unRecorrido : Recorrido) : Double  = {
      
       if(this.condicion(unRecorrido))
       {
         return this.precioConDescuento(unRecorrido) 
       } 
       else 
       {
         return unRecorrido.precioBase
       }
     }
    
    def precioConDescuento(unRecorrido : Recorrido) : Double 

 //      var recorridos : MutableList[Recorrido] = MutableList(viaje)
//      var recorridoActual : Recorrido = viaje
//      while(viaje.siguienteRecorrido != null)
//      {
//        recorridoActual = viaje.siguienteRecorrido
//        recorridos += recorridoActual
//      }
//      return recorridos.map(unRecorrido => this.precioConDescuento(unRecorrido)).sum
}