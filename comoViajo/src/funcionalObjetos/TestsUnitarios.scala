package funcionalObjetos

import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import comoViajo.SUBTE
import comoViajo.Recorrido
import comoViajo.Recorrido
import comoViajo.Direccion
import comoViajo.InformacionTransportes
import comoViajo.InformacionTransportes
import comoViajo.InformadorPosta

class TestsUnitarios {

  var informador: InformacionTransportes = new InformadorPosta

  @Test
  def testPrecioBaseDeUnRecorrido = {
    var paradas : Array[Direccion] = Array(new Direccion("Corrientes", 41000), new Direccion ("Corrientes", 4500))
    var primeraDire: Direccion = new Direccion("avellaneda", 923)
    var segundaDire: Direccion = new Direccion("scalabrini", 1600)
    var unRecorrido: Recorrido = new Recorrido(primeraDire, segundaDire, SUBTE(paradas, informador))
    
    assertEquals(4.5, unRecorrido.costoBase, 0.1)
    
  }
}