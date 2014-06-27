package comoViajo

class MenorTiempo extends CriterioDeSeleccion {

  def seleccionar (posiblesViajes : List[Viaje]) : Viaje =
  {
    posiblesViajes.reduceLeft((unViaje,otroViaje) => 	
      		if (unViaje.tiempoDelViaje < otroViaje.tiempoDelViaje) unViaje else otroViaje)
  }
  
}