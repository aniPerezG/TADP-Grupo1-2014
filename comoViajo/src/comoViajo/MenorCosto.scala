package comoViajo

import scala.collection.mutable.MutableList

class MenorCosto extends CriterioDeSeleccion {
  
  def seleccionar (posiblesViajes : MutableList[Viaje], unaTarjeta: Tarjeta) : Viaje = {
    posiblesViajes.minBy(_.costoDelViaje(unaTarjeta))
  }
  
  def seleccionar (posiblesViajes : MutableList[Viaje]) : Viaje = {
    posiblesViajes.minBy(_.costoDelViaje())
  }


}