package comoViajo

trait InformacionTransportes {
  
  
  def transportesCerca(unaDireccion: Direccion): List[CaseTransporte] 
  def distanciaColectivo ( origen : Direccion, destino : Direccion): Int
  def distanciaAPie (origen : Direccion, destino : Direccion): Int

}


class StubInformacionTransportes extends InformacionTransportes {

  def transportesCerca(unaDireccion: Direccion): List[CaseTransporte] = List() 
  def distanciaColectivo ( origen : Direccion, destino : Direccion): Int = 15000
  def distanciaAPie (origen : Direccion, destino : Direccion): Int = 300

}