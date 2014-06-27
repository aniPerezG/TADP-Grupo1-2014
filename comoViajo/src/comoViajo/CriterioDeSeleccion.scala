package comoViajo

trait CriterioDeSeleccion {

   def seleccionar (posiblesViajes : List[Viaje]) : Viaje
}