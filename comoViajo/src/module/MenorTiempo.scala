package module

class MenorTiempo extends CriterioSeleccion {
  
  def seleccionar (posiblesViajes : List[Viaje]) : Viaje =
  {
    posiblesViajes.reduceLeft((unViaje,otroViaje) => 	
      		if (unViaje.obtenerTiempoViaje < otroViaje.obtenerTiempoViaje) unViaje else otroViaje)
  }


}