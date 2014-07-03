package comoViajo

import scala.collection.mutable.MutableList

class MenorCosto extends CriterioDeSeleccion {
  
  def seleccionar (posiblesViajes : MutableList[Viaje], unaTarjeta: Tarjeta) : Viaje =
  {
    posiblesViajes.reduceLeft((unViaje,otroViaje) => 	
      		if (unViaje.costoDelViaje(unaTarjeta) < otroViaje.costoDelViaje(unaTarjeta)) unViaje else otroViaje)
  }
  
  def seleccionar (posiblesViajes : MutableList[Viaje]) : Viaje = {
    posiblesViajes.reduceLeft((unViaje,otroViaje) => 	
      		if (unViaje.costoDelViaje() < otroViaje.costoDelViaje()) unViaje else otroViaje)
  }


}