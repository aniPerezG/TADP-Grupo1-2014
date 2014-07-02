package comoViajo

import scala.collection.mutable.MutableList

class PlanificadorViaje {
  
  val informador : InformacionTransportes
  
  def armarPosiblesViajes(unUsuario : Usuario ,origen : Direccion, destino : Direccion) : List[Viaje] = {
      var transportesCercaDelOrigen = informador.transportesCerca(origen)
      var transportesCercaDelDestino = informador.transportesCerca(destino)
      var listaDeCombinaciones :List[Map[Transporte,Transporte]] = List();
      
     var transportesQuePuedenLlegarADestino = transportesCercaDelOrigen.filter{unTransporte: Transporte => transportesCercaDelDestino.contains(unTransporte)|| unTransporte.transportesVecinos.anySatisfy{transporteVecino: Transporte =>transportesCercaDelDestino.contains(transporteVecino)}}
     var listaDeViajes: MutableList[Viaje] = MutableList()
     transportesQuePuedenLlegarADestino.foreach{unTransporte: Transporte=> listaDeViajes  +(unTransporte.dameElViajeA(destino))}//TODO
     
  }
  
}