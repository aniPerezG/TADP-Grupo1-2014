package comoViajo

import scala.collection.immutable.List

class InformadorPosta extends InformacionTransportes {
  def transportesCerca(unaDireccion: Direccion): List[CaseTransporte]  = {
    ???
  }
  def distanciaColectivo(origen: Direccion, destino: Direccion): Int = {
    ???
  }
  def distanciaAPie(origen: Direccion, destino: Direccion): Int = {
    ???
  }
}