package comoViajo

trait InformacionTransportes {
  
  
  def transportesCerca(unaDireccion: Direccion): List[CaseTransporte] //TODO Agregar un mapa con el transporte y la parada
  //def dondeMeBajoCombinando (unaLinea : Colectivo, otraLinea: Colectivo ): Direccion
  def distanciaColectivo ( origen : Direccion, destino : Direccion): Int
  def distanciaAPie (origen : Direccion, destino : Direccion): Int

}