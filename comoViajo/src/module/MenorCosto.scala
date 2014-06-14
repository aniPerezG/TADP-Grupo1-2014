package module

class MenorCosto extends CriterioSeleccion {
  
  def seleccionar (posiblesViajes : List[Viaje]) : Viaje =
  {
    posiblesViajes.reduceLeft((unViaje,otroViaje) => 	
      		if (unViaje.obtenerCostoViaje < otroViaje.obtenerCostoViaje) unViaje else otroViaje)
  }


}