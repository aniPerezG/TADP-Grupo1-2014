package comoViajo

import scala.collection.mutable.MutableList

class PlanificadorViaje(val informador: InformacionTransportes, var listaDeViajes: MutableList[Viaje] = MutableList()) {

  
  
  def armarViaje(origen: Direccion, destino: Direccion): MutableList[Viaje] = {

    listaDeViajes = MutableList()
    var transportesCercaDelOrigen = informador.transportesCerca(origen)
    var transportesCercaDelDestino = informador.transportesCerca(destino)
    

    
    transportesCercaDelOrigen.foreach{unTransporte => if(transportesCercaDelDestino.contains(unTransporte)) {
                                                        this.armarViajeSimple(unTransporte,origen,destino)
                                                      }
                                                      var transportesVecinosQueLlegan = unTransporte.transportesVecinosEntre().filter{ transporteVecino =>transportesCercaDelDestino.contains(transporteVecino)}
                                                      transportesVecinosQueLlegan.foreach{ otroTransporte => this.armarViajeCompuesto(unTransporte,otroTransporte,origen,destino)}
                             }
    if (listaDeViajes.isEmpty){
      throw new RuntimeException ("No hay viajes posibles")
    }
    return listaDeViajes 

  }

  def armarViajeSimple(unTransporte: Transporte, origen: Direccion, destino: Direccion) = {
    
    var unRecorrido : Recorrido = new Recorrido (origen, destino, unTransporte)
    listaDeViajes += new ViajeSimple(unRecorrido)
    
  }

  def armarViajeCompuesto(unTransporte: Transporte, otroTransporte: Transporte, origen: Direccion, destino: Direccion) = {
    
    var paradaIntermedia : Direccion = unTransporte.interseccionCon(otroTransporte)
    var unRecorrido : Recorrido = new Recorrido (origen, paradaIntermedia, unTransporte)
    var otroRecorrido : Recorrido = new Recorrido (paradaIntermedia, destino, otroTransporte)
    
    listaDeViajes += new ViajeCompuesto(unRecorrido, otroRecorrido)
  }
  
  def viajeMasConveniente (unCriterio : CriterioDeSeleccion, listaDeViajes : MutableList[Viaje] ) : Viaje = {
    return unCriterio.seleccionar(listaDeViajes) 
  }
  
  def viajeMasConveniente(unCriterio : CriterioDeSeleccion, listaDeViajes : MutableList[Viaje], unaTarjeta : Tarjeta ) : Viaje = {
    return unCriterio.seleccionar(listaDeViajes, unaTarjeta)
  }
  

}

  


//El viejo armarPosiblesViajes
    //     var transportesCercaDelOrigen = informador.transportesCerca(origen)
    //     var transportesCercaDelDestino = informador.transportesCerca(destino)
    //     var listaDeCombinaciones :List[Map[Transporte,Transporte]] = List();
    //      
    //     var transportesQuePuedenLlegarADestino = transportesCercaDelOrigen.filter{unTransporte: Transporte => transportesCercaDelDestino.contains(unTransporte)|| unTransporte.transportesVecinos.exists{transporteVecino: Transporte =>transportesCercaDelDestino.contains(transporteVecino)}}
    //     var listaDeViajes: MutableList[Viaje] = MutableList()
    //     transportesQuePuedenLlegarADestino.foreach{ unTransporte => listaDeViajes += (unTransporte.dameElViajeA(destino))}//TODO
    //     return listaDeViajes