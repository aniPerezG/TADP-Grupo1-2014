package comoViajo

trait CriterioSeleccion {

  def seleccionar (posiblesViajes : List[Viaje]) : List[Viaje]
  
}