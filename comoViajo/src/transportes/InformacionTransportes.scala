package transportes

import comoViajo.Lugar

trait InformacionTransportes {

  def transportesCerca(lugar: Lugar): List[Transporte]
  def combinar (linea : Colectivo ): List[Lugar]
  def distanciaColectivo ( lugar : Lugar): Int
  def distanciaAPie (lugar : Lugar ): Int


}