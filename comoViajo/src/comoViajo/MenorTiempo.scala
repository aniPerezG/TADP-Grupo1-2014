package comoViajo

import scala.collection.mutable.MutableList

class MenorTiempo extends CriterioDeSeleccion {

 def seleccionar (posiblesViajes : MutableList[Viaje]) : Viaje =
    posiblesViajes.reduceLeft((unViaje,otroViaje) => 	
      		if (unViaje.tiempoDelViaje < otroViaje.tiempoDelViaje) unViaje else otroViaje)
  
  def seleccionar (posiblesViajes : MutableList[Viaje], unaTarjeta: Tarjeta) : Viaje = 
     seleccionar(posiblesViajes)
  
}