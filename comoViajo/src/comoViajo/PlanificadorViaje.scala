package comoViajo

import scala.collection.mutable.MutableList

class PlanificadorViaje(val informador: InformacionTransportes, var listaDeViajes: MutableList[Viaje] = MutableList()) {

  def armarViaje(origen: Direccion, destino: Direccion): MutableList[Viaje] = {

    listaDeViajes = MutableList()
    var transportesCercaDelOrigen = informador.transportesCerca(origen)
    var transportesCercaDelDestino = informador.transportesCerca(destino)

    transportesCercaDelOrigen.foreach { unTransporte =>
      if (transportesCercaDelDestino.contains(unTransporte)) {
        this.armarViajeSimple(unTransporte, origen, destino)
      }
      var transportesVecinosQueLlegan = unTransporte.transportesVecinosEntre().filter { transporteVecino => transportesCercaDelDestino.contains(transporteVecino) }
      transportesVecinosQueLlegan.foreach { otroTransporte => this.armarViajeCompuesto(unTransporte, otroTransporte, origen, destino) }
    }
    if (listaDeViajes.isEmpty) {
      throw new RuntimeException("No hay viajes posibles")
    }
    return listaDeViajes

  }

  def armarViajeSimple(unTransporte: CaseTransporte, origen: Direccion, destino: Direccion) = {

    var unRecorrido: Recorrido = new Recorrido(origen, destino, unTransporte, informador)
    listaDeViajes += new ViajeSimple(unRecorrido)

  }

  def armarViajeCompuesto(unTransporte: CaseTransporte, otroTransporte: CaseTransporte, origen: Direccion, destino: Direccion) = {

    var paradaIntermedia: Direccion = unTransporte.interseccionCon(otroTransporte)
    var unRecorrido: Recorrido = new Recorrido(origen, paradaIntermedia, unTransporte, informador)
    var otroRecorrido: Recorrido = new Recorrido(paradaIntermedia, destino, otroTransporte, informador)

    listaDeViajes += new ViajeCompuesto(unRecorrido, otroRecorrido)
  }

  def viajeMasConveniente(unCriterio: CriterioDeSeleccion, listaDeViajes: MutableList[Viaje]): Viaje = {
    return unCriterio.seleccionar(listaDeViajes)
  }

  def viajeMasConveniente(unCriterio: CriterioDeSeleccion, listaDeViajes: MutableList[Viaje], unaTarjeta: Tarjeta): Viaje = {
    return unCriterio.seleccionar(listaDeViajes, unaTarjeta)
  }

}
