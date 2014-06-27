package comoViajo

class MenorCosto extends CriterioDeSeleccion {
  
  def seleccionar (posiblesViajes : List[Viaje], unUsuario : Usuario) : Viaje =
  {
    posiblesViajes.reduceLeft((unViaje,otroViaje) => 	
      		if (unViaje.costoDelViaje(unUsuario) < otroViaje.costoDelViaje(unUsuario)) unViaje else otroViaje)
  }


}