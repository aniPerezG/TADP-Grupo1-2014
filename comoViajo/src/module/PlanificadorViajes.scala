package module

import transportes.InformacionTransportes

class PlanificadorViajes {
  
  var viajesPosibles : List[Viaje] = Nil
  var moduloTransporte : InformacionTransportes
  var tipoTarjeta : Tarjeta
  var criterio: CriterioSeleccion
  
  def obtenerViajePosible (criterio: CriterioSeleccion, origen: Lugar , destino: Lugar ){
    /*buscar todas las opciones posibles*/
    criterio.seleccionar(viajesPosibles)
  }
  

  
  

}