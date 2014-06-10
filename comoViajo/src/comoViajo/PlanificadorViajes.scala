package comoViajo

import transportes.InformacionTransportes

class PlanificadorViajes {
  
  var precioBase : Double
  var viajesPosibles : List[Viaje] = Nil
  var moduloTransporte : InformacionTransportes
  
  def obtenerViajesPosibles (criterio: CriterioSeleccion ){
    /*buscar todas las opciones posibles*/
    criterio.seleccionar(viajesPosibles)
  }
  
  
  

}