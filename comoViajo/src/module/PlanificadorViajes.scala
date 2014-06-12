package module

import transportes.InformacionTransportes

class PlanificadorViajes {
  
  var viajesPosibles : List[Viaje] = Nil
  var moduloTransporte : InformacionTransportes
  var tipoTarjeta : Tarjeta
  
  def obtenerViajesPosibles (criterio: CriterioSeleccion, origen: Lugar , destino: Lugar ){
    /*buscar todas las opciones posibles*/
    criterio.seleccionar(viajesPosibles)
  }
  

  
  

}