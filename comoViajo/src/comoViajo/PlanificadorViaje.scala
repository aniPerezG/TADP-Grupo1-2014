package comoViajo

import scala.collection.mutable.MutableList

class PlanificadorViaje (val informador : InformacionTransportes) {
  

  
  def armarPosiblesViajes(unUsuario : Usuario ,origen : Direccion, destino : Direccion) : MutableList[Viaje] = {
      var transportesCercaDelOrigen = informador.transportesCerca(origen)
      var transportesCercaDelDestino = informador.transportesCerca(destino)
      var listaDeCombinaciones :List[Map[Transporte,Transporte]] = List();
      
     var transportesQuePuedenLlegarADestino = transportesCercaDelOrigen.filter{unTransporte: Transporte => transportesCercaDelDestino.contains(unTransporte)|| unTransporte.transportesVecinos.exists{transporteVecino: Transporte =>transportesCercaDelDestino.contains(transporteVecino)}}
     var listaDeViajes: MutableList[Viaje] = MutableList()
     transportesQuePuedenLlegarADestino.foreach{ unTransporte => listaDeViajes += (unTransporte.dameElViajeA(destino))}//TODO
     return listaDeViajes
  }
  
}