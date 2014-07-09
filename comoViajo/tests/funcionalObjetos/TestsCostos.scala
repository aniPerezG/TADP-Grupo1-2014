package funcionalObjetos

import org.junit.Assert._
import org.junit.Test
import comoViajo.SUBTE
import comoViajo.COLECTIVO
import comoViajo.TREN
import comoViajo.Recorrido
import comoViajo.Recorrido
import comoViajo.Direccion
import comoViajo.InformacionTransportes
import comoViajo.InformacionTransportes
import comoViajo.StubInformacionTransportes
import comoViajo.TarjetaDiscapacitado
import comoViajo.TarjetaTrabajador
import comoViajo.ViajeSimple
import comoViajo.TarjetaTurista
import comoViajo.ViajeCompuesto
import org.junit.Before
import comoViajo.PALERMO

class TestsCostos {

  var paradaColec1: Direccion = _
  var paradaColec2: Direccion = _
  var paradaColec3: Direccion = _

  var paradaSubteB1: Direccion = _
  var paradaSubteB2: Direccion = _
  var paradaSubteB3: Direccion = _
  var paradaSubteB4: Direccion = _
  var paradaSubteB5: Direccion = _

  var paradaSubteD1: Direccion = _
  var paradaSubteD2: Direccion = _
  var paradaSubteD3: Direccion = _
  var paradaSubteD4: Direccion = _
  var paradaSubteD5: Direccion = _

  var paradaTren1: Direccion = _
  var paradaTren2: Direccion = _
  var paradaTren3: Direccion = _
  var paradaTren4: Direccion = _
  var paradaTren5: Direccion = _
  var paradaTren6: Direccion = _
  var paradaTren7: Direccion = _

  var paradasDeColec: Array[Direccion] = _
  var paradasDeSubteB: Array[Direccion] = _
  var paradasDeSubteD: Array[Direccion] = _
  var paradasDeTren: Array[Direccion] = _

  var tablaDePreciosDelTren: Map[Int, Double] = _

  var informador: StubInformacionTransportes = _
  var linea17: COLECTIVO = _
  var lineaB: SUBTE = _
  var lineaD: SUBTE = _
  var sarmiento: TREN = _
  
  @Before
  def setUp = {
    paradaColec1 = new Direccion("Onsari", 600, PALERMO)
    paradaColec2 = new Direccion("Onsari", 2500, PALERMO)
    paradaColec3 = new Direccion("Onsari", 3500, PALERMO)

    paradaSubteB1 = new Direccion("Corrientes", 5000, PALERMO)
    paradaSubteB2 = new Direccion("Corrientes", 4500, PALERMO)
    paradaSubteB3 = new Direccion("Corrientes", 4000, PALERMO)
    paradaSubteB4 = new Direccion("Corrientes", 3500, PALERMO)
    paradaSubteB5 = new Direccion("Corrientes", 3000, PALERMO)

    paradaSubteD1 = new Direccion("Corrientes", 5000, PALERMO)
    paradaSubteD2 = new Direccion("Santa Fe", 4200, PALERMO)
    paradaSubteD3 = new Direccion("Santa Fe", 3400, PALERMO)
    paradaSubteD4 = new Direccion("Santa Fe", 2600, PALERMO)
    paradaSubteD5 = new Direccion("Santa Fe", 1800, PALERMO)

    paradaTren1 = new Direccion("La via", 0, PALERMO)
    paradaTren2 = new Direccion("La via", 1000, PALERMO)
    paradaTren3 = new Direccion("La via", 3000, PALERMO)
    paradaTren4 = new Direccion("La via", 5000, PALERMO)
    paradaTren5 = new Direccion("La via", 7000, PALERMO)
    paradaTren6 = new Direccion("La via", 9000, PALERMO)
    paradaTren7 = new Direccion("La via", 11000, PALERMO)

    paradasDeColec = new Array[Direccion](3)
    paradasDeTren = new Array[Direccion](7)
    paradasDeSubteB = new Array[Direccion](5)
    paradasDeSubteD = new Array[Direccion](5)

    paradasDeColec :+= paradaColec1
    paradasDeColec :+= paradaColec2
    paradasDeColec :+= paradaColec3

    paradasDeSubteB :+= paradaSubteB1
    paradasDeSubteB :+= paradaSubteB2
    paradasDeSubteB :+= paradaSubteB3
    paradasDeSubteB :+= paradaSubteB4
    paradasDeSubteB :+= paradaSubteB5

    paradasDeSubteD :+= paradaSubteD1
    paradasDeSubteD :+= paradaSubteD2
    paradasDeSubteD :+= paradaSubteD3
    paradasDeSubteD :+= paradaSubteD4
    paradasDeSubteD :+= paradaSubteD5

    paradasDeTren :+= paradaTren1
    paradasDeTren :+= paradaTren2
    paradasDeTren :+= paradaTren3
    paradasDeTren :+= paradaTren4
    paradasDeTren :+= paradaTren5
    paradasDeTren :+= paradaTren6
    paradasDeTren :+= paradaTren7

    tablaDePreciosDelTren = Map(3 -> 2.0, 5 -> 4.5, 7 -> 8.75)

    informador = new StubInformacionTransportes()
    linea17 = COLECTIVO(paradasDeColec, informador)
    lineaB = SUBTE(paradasDeSubteB, informador)
    lineaD = SUBTE(paradasDeSubteD, informador)
    sarmiento = TREN(paradasDeTren, informador, tablaDePreciosDelTren)

  }

  @Test
  def testCostoBaseDeUnRecorrido = {

    var paradas: Array[Direccion] = Array(new Direccion("Corrientes", 41000, PALERMO), new Direccion("Corrientes", 4500, PALERMO))
    var primeraDire: Direccion = new Direccion("avellaneda", 923, PALERMO)
    var segundaDire: Direccion = new Direccion("scalabrini", 1600, PALERMO)
    var unRecorrido: Recorrido = new Recorrido(primeraDire, segundaDire, SUBTE(paradas, informador))

    assertEquals(4.5, unRecorrido.costoBase, 0.1)

  }

  @Test
  def elCostoDeUnRecorridoConTarjetaDiscapacitadoDaCorrecto() {
    var tarjetaDiscapacitado: TarjetaDiscapacitado = new TarjetaDiscapacitado

    val seteo = setUp
    var recorridoEnColectivo = new Recorrido(paradaColec1, paradaColec3, linea17)
    var viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)

    assertEquals(0.0, viajeEnColectivo.costoDelViaje(tarjetaDiscapacitado), 0.1)

  }

  @Test
  def elCostoDeUnRecorridoConTarjetaYendoAlTrabajoDaCorrecto() {

    var tarjetaYendoAlTrabajo: TarjetaTrabajador = new TarjetaTrabajador

    val direccionDeLaBoca = new Direccion("Brandsen", 200, PALERMO)
    val direccionDelCentro = new Direccion("Riobamba", 700, PALERMO)

    val parada1Del24 = new Direccion("Av. Patricios", 150, PALERMO)
    val parada3Del24 = new Direccion("Lavalle", 1500, PALERMO)

    var paradasBondi = new Array[Direccion](4)
    paradasBondi :+= parada1Del24
    paradasBondi :+= direccionDeLaBoca
    paradasBondi :+= parada3Del24
    paradasBondi :+= direccionDelCentro

    tarjetaYendoAlTrabajo.laBoca :+= direccionDeLaBoca
    tarjetaYendoAlTrabajo.centro :+= direccionDelCentro

    val linea24 = COLECTIVO(paradasBondi, informador)
    val recorridoAlTrabajo = new Recorrido(direccionDeLaBoca, direccionDelCentro, linea24)
    val viajeAlLaburo = new ViajeSimple(recorridoAlTrabajo)

    assertEquals(1.0, viajeAlLaburo.costoDelViaje(tarjetaYendoAlTrabajo), 0.01)

  }

  @Test
  def elCostoDeUnRecorridoConTarjetaTurismoDaCorrecto() {
   
    var tarjetaTurista = new TarjetaTurista

    tarjetaTurista.zona :+= paradaSubteB1
    tarjetaTurista.zona :+= paradaSubteB5

    val recorridoTurista = new Recorrido(paradaSubteB1, paradaSubteB5, lineaB)
    val viajeTurista = new ViajeSimple(recorridoTurista)

    assertEquals(4.05, viajeTurista.costoDelViaje(tarjetaTurista), 0.01)
  }

  @Test
  def elCostoDeUnViajeSimpleEnColectivoMeDaCorrecto() {

    val recorridoEnColectivo = new Recorrido(paradaColec1, paradaColec2, linea17)
    val viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)

    assertEquals(2.5, viajeEnColectivo.costoDelViaje(), 0.1)

  }

  @Test
  def elCostoDeUnViajeSimpleEnSubteMeDaCorrecto() {

    val seteo = setUp

    val recorridoEnSubte = new Recorrido(paradaSubteB1, paradaSubteB5, lineaB)
    val viajeEnSubte = new ViajeSimple(recorridoEnSubte)

    assertEquals(4.5, viajeEnSubte.costoDelViaje(), 0.1)

  }

  @Test
  def elCostoDeUnViajeSimpleEnTrenMeDaCorrecto() {

    val recorridoEnTren = new Recorrido(paradaTren4, paradaTren5, sarmiento)
    val viajeEnTren = new ViajeSimple(recorridoEnTren)

    assertEquals(2.0, viajeEnTren.costoDelViaje(), 0.1)

  }
  /**
   * *****
   *
   */

  @Test
  def siTenemosUnViajeCombinadoConDosSubtesSaleComoSiFueraConUnoSolo {

    val recorridoEnSubteB = new Recorrido(paradaSubteB1, paradaSubteB5, lineaB)
    val recorridoEnSubteD = new Recorrido(paradaSubteD1, paradaSubteD3, lineaD)
    val viajeEnSubte = new ViajeCompuesto(recorridoEnSubteB, recorridoEnSubteD)

    assertEquals(4.5 , viajeEnSubte.costoDelViaje(), 0.1)
  }

  @Test
  def siTenemosUnViajeCombinadoEntreUnTrenYUnSubteDaElPrecioSumadoDeAmbosRecorridos {

    val recorridoEnSubteB = new Recorrido(paradaSubteB1, paradaSubteB5, lineaB)
    val recorridoEnTren = new Recorrido(paradaTren4, paradaTren5, sarmiento)
    val viajeCombinado = new ViajeCompuesto(recorridoEnSubteB, recorridoEnTren)

    assertEquals(6.5 ,  viajeCombinado.costoDelViaje(), 0.1)
  }

  @Test
  def siTenemosUnViajeCombinadoEntreTrenYUnColectivoDaLaSumaDeAmbosRecorridos {
    val recorridoEnColectivo = new Recorrido(paradaColec1, paradaColec2, linea17)
    val recorridoEnTren = new Recorrido(paradaTren4, paradaTren5, sarmiento)
    val viajeCombinado = new ViajeCompuesto(recorridoEnColectivo, recorridoEnTren)

    assertEquals(4.5 , viajeCombinado.costoDelViaje(), 0.1)

  }

  @Test
  def siTenemosUnViajeCombinadoEntreUnTrenYUnColectivoConLaTarjetaDiscapacitadoNosSaleGratisTodoElViaje {
    val tarjetaDiscapacitado: TarjetaDiscapacitado = new TarjetaDiscapacitado

    val recorridoEnColectivo = new Recorrido(paradaColec1, paradaColec3, linea17)
    val recorridoEnTren = new Recorrido(paradaTren4, paradaTren5, sarmiento)

    val viajeCombinado = new ViajeCompuesto(recorridoEnColectivo, recorridoEnTren)

    assertEquals(0 , viajeCombinado.costoDelViaje(tarjetaDiscapacitado), 0.1)

  }

  @Test
  def siTenemosUnViajeCombinandoUnColectivoAlTrabajoYUnSubte {
    var tarjetaYendoAlTrabajo: TarjetaTrabajador = new TarjetaTrabajador

    val direccionDeLaBoca = new Direccion("Brandsen", 200, PALERMO)
    val direccionDelCentro = new Direccion("Riobamba", 700, PALERMO)

    val parada1Del24 = new Direccion("Av. Patricios", 150, PALERMO)
    val parada3Del24 = new Direccion("Lavalle", 1500, PALERMO)

    var paradasBondi = new Array[Direccion](4)
    paradasBondi :+= parada1Del24
    paradasBondi :+= direccionDeLaBoca
    paradasBondi :+= parada3Del24
    paradasBondi :+= direccionDelCentro

    tarjetaYendoAlTrabajo.laBoca :+= direccionDeLaBoca
    tarjetaYendoAlTrabajo.centro :+= direccionDelCentro

    val linea24 = COLECTIVO(paradasBondi, informador)
    val recorridoAlTrabajo = new Recorrido(direccionDeLaBoca, direccionDelCentro, linea24)
    val recorridoEnTren = new Recorrido(paradaTren4, paradaTren5, sarmiento)

    val viajeCombinado = new ViajeCompuesto(recorridoAlTrabajo, recorridoEnTren)

    assertEquals(3.00 , viajeCombinado.costoDelViaje(tarjetaYendoAlTrabajo), 1.00)

  }

  @Test
  def siTenemosUnViajeCombinadoEntreDosSubtesConLaTarjetaTurismoTeCobraUnSoloViaje {
    var tarjetaTurista = new TarjetaTurista

    tarjetaTurista.zona :+= paradaSubteB1
    tarjetaTurista.zona :+= paradaSubteB5

    val recorridoTurista = new Recorrido(paradaSubteB1, paradaSubteB5, lineaB)
    val recorridoEnSubteD = new Recorrido(paradaSubteD1, paradaSubteD3, lineaD)

    val viajeCombinado = new ViajeCompuesto(recorridoTurista, recorridoEnSubteD)

    assertEquals(4.05 , viajeCombinado.costoDelViaje(tarjetaTurista), 0.01)
  }

  @Test
  def siTenemosUnViajeCombinadoEntreUnSubteYUnTrenConLaTarjetaTurismoTeCobraDescuentoSoloEnElQueAplica {
    var tarjetaTurista = new TarjetaTurista

    tarjetaTurista.zona :+= paradaSubteB1
    tarjetaTurista.zona :+= paradaSubteB5

    val recorridoTurista = new Recorrido(paradaSubteB1, paradaSubteB5, lineaB)
    val recorridoEnTren = new Recorrido(paradaTren4, paradaTren5, sarmiento)

    val viajeCombinado = new ViajeCompuesto(recorridoTurista, recorridoEnTren)

    assertEquals(6.05 , viajeCombinado.costoDelViaje(tarjetaTurista), 0.01)
  }
}