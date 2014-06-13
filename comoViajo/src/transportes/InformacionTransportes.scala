package transportes

import module.Lugar

trait InformacionTransportes {

  def transportesCerca(lugar: Lugar): List[Transporte]
  def dondeMeBajoCombinando (linea : Colectivo ): Lugar
  def distanciaColectivo ( origen : Lugar, destino : Lugar): Int
  def distanciaAPie (origen : Lugar, destino : Lugar): Int


}