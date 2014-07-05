package comoViajo

trait InformacionTransportes {
  
  def transportesCerca(unaDireccion: Direccion): List[Transporte] //TODO Agregar un mapa con el transporte y la parada
  def dondeMeBajoCombinando (unaLinea : Colectivo, otraLinea: Colectivo ): Direccion
  def distanciaColectivo ( origen : Direccion, destino : Direccion): Int
  def distanciaAPie (origen : Direccion, destino : Direccion): Int

}

class StubInformacionTransportes extends InformacionTransportes {

  def transportesCerca(unaDireccion: Direccion): List[Transporte] = List() 
  def dondeMeBajoCombinando (unaLinea : Colectivo, otraLinea: Colectivo ): Direccion = new Direccion("Medrano", 900)
  def distanciaColectivo ( origen : Direccion, destino : Direccion): Int = 2000
  def distanciaAPie (origen : Direccion, destino : Direccion): Int = 300

}