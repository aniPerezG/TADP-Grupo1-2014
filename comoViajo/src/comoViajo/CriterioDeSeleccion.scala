package comoViajo

import scala.collection.mutable.MutableList

trait CriterioDeSeleccion {

   def seleccionar (posiblesViajes : MutableList[Viaje], unaTarjeta: Tarjeta) : Viaje 
   
   def seleccionar (posiblesViajes : MutableList[Viaje]) : Viaje
   
   
}