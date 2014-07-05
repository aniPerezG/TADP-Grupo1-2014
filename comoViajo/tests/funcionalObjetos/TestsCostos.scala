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

class TestsCostos {

  def setUp() = new {
    val paradaColec1 = new Direccion("Onsari", 600)
    val paradaColec2 = new Direccion("Onsari", 2500)
    val paradaColec3 = new Direccion("Onsari", 3500)

    val paradaSubteB1 = new Direccion("Corrientes", 5000)
    val paradaSubteB2 = new Direccion("Corrientes", 4500)
    val paradaSubteB3 = new Direccion("Corrientes", 4000)
    val paradaSubteB4 = new Direccion("Corrientes", 3500)
    val paradaSubteB5 = new Direccion("Corrientes", 3000)

    val paradaSubteD1 = new Direccion("Corrientes", 5000)
    val paradaSubteD2 = new Direccion("Santa Fe", 4200)
    val paradaSubteD3 = new Direccion("Santa Fe", 3400)
    val paradaSubteD4 = new Direccion("Santa Fe", 2600)
    val paradaSubteD5 = new Direccion("Santa Fe", 1800)

    val paradaTren1 = new Direccion("La via", 0)
    val paradaTren2 = new Direccion("La via", 1000)
    val paradaTren3 = new Direccion("La via", 3000)
    val paradaTren4 = new Direccion("La via", 5000)
    val paradaTren5 = new Direccion("La via", 7000)
    val paradaTren6 = new Direccion("La via", 9000)
    val paradaTren7 = new Direccion("La via", 11000)

    var paradasDeColec: Array[Direccion] = new Array[Direccion](3)
    var paradasDeSubte: Array[Direccion] = new Array[Direccion](5)
    var paradasDeTren: Array[Direccion] = new Array[Direccion](7)
    var paradasDeSubteB: Array[Direccion] = new Array[Direccion](5)
    var paradasDeSubteD: Array[Direccion] = new Array[Direccion](5)

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

    var tablaDePreciosDelTren = Map(3 -> 2.0, 5 -> 4.5, 7 -> 8.75)

    val informador = new StubInformacionTransportes()
    var linea17 = COLECTIVO(paradasDeColec, informador)
    var lineaB = SUBTE(paradasDeSubte, informador)
    var lineaD = SUBTE(paradasDeSubteD, informador)
    var sarmiento = TREN(paradasDeTren, informador, tablaDePreciosDelTren)

  }

  @Test
  def testCostoBaseDeUnRecorrido = {
    val seteo = setUp
    var paradas: Array[Direccion] = Array(new Direccion("Corrientes", 41000), new Direccion("Corrientes", 4500))
    var primeraDire: Direccion = new Direccion("avellaneda", 923)
    var segundaDire: Direccion = new Direccion("scalabrini", 1600)
    var unRecorrido: Recorrido = new Recorrido(primeraDire, segundaDire, SUBTE(paradas, seteo.informador))

    assertEquals(4.5, unRecorrido.costoBase, 0.1)

  }

  @Test
  def elCostoDeUnRecorridoConTarjetaDiscapacitadoDaCorrecto() {
    var tarjetaDiscapacitado: TarjetaDiscapacitado = new TarjetaDiscapacitado

    val seteo = setUp
    var recorridoEnColectivo = new Recorrido(seteo.paradaColec1, seteo.paradaColec3, seteo.linea17)
    var viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)

    assertEquals(0.0, viajeEnColectivo.costoDelViaje(tarjetaDiscapacitado), 0.1)

  }

  @Test
  def elCostoDeUnRecorridoConTarjetaYendoAlTrabajoDaCorrecto() {
    val seteo = setUp
    var tarjetaYendoAlTrabajo: TarjetaTrabajador = new TarjetaTrabajador

    val direccionDeLaBoca = new Direccion("Brandsen", 200)
    val direccionDelCentro = new Direccion("Riobamba", 700)

    val parada1Del24 = new Direccion("Av. Patricios", 150)
    val parada3Del24 = new Direccion("Lavalle", 1500)

    var paradasBondi = new Array[Direccion](4)
    paradasBondi :+= parada1Del24
    paradasBondi :+= direccionDeLaBoca
    paradasBondi :+= parada3Del24
    paradasBondi :+= direccionDelCentro

    tarjetaYendoAlTrabajo.laBoca :+= direccionDeLaBoca
    tarjetaYendoAlTrabajo.centro :+= direccionDelCentro

    val linea24 = COLECTIVO(paradasBondi, seteo.informador)
    val recorridoAlTrabajo = new Recorrido(direccionDeLaBoca, direccionDelCentro, linea24)
    val viajeAlLaburo = new ViajeSimple(recorridoAlTrabajo)

    assertEquals(1.0, viajeAlLaburo.costoDelViaje(tarjetaYendoAlTrabajo), 0.01)

  }

  @Test
  def elCostoDeUnRecorridoConTarjetaTurismoDaCorrecto() {
    val seteo = setUp
    var tarjetaTurista = new TarjetaTurista

    tarjetaTurista.zona :+= seteo.paradaSubteB1
    tarjetaTurista.zona :+= seteo.paradaSubteB5

    val recorridoTurista = new Recorrido(seteo.paradaSubteB1, seteo.paradaSubteB5, seteo.lineaB)
    val viajeTurista = new ViajeSimple(recorridoTurista)

    assertEquals(4.05, viajeTurista.costoDelViaje(tarjetaTurista), 0.01)
  }

  @Test
  def elCostoDeUnViajeSimpleEnColectivoMeDaCorrecto() {
    val seteo = setUp
    val recorridoEnColectivo = new Recorrido(seteo.paradaColec1, seteo.paradaColec2, seteo.linea17)
    val viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)

    assertEquals(2.5, viajeEnColectivo.costoDelViaje(), 0.1)

  }

  @Test
  def elCostoDeUnViajeSimpleEnSubteMeDaCorrecto() {

    val seteo = setUp

    val recorridoEnSubte = new Recorrido(seteo.paradaSubteB1, seteo.paradaSubteB5, seteo.lineaB)
    val viajeEnSubte = new ViajeSimple(recorridoEnSubte)

    assertEquals(4.5, viajeEnSubte.costoDelViaje(), 0.1)

  }

  @Test
  def elCostoDeUnViajeSimpleEnTrenMeDaCorrecto() {
    val seteo = setUp

    val recorridoEnTren = new Recorrido(seteo.paradaTren4, seteo.paradaTren5, seteo.sarmiento)
    val viajeEnTren = new ViajeSimple(recorridoEnTren)

    assertEquals(2.0, viajeEnTren.costoDelViaje(), 0.1)

  }
  /**
   * *****
   *
   */

  @Test
  def siTenemosUnViajeCombinadoConDosSubtesSaleComoSiFueraConUnoSolo {
    val seteo = setUp

    val recorridoEnSubteB = new Recorrido(seteo.paradaSubteB1, seteo.paradaSubteB5, seteo.lineaB)
    val recorridoEnSubteD = new Recorrido(seteo.paradaSubteD1, seteo.paradaSubteD3, seteo.lineaD)
    val viajeEnSubte = new ViajeCompuesto(recorridoEnSubteB, recorridoEnSubteD)

    assertEquals(4.5 , viajeEnSubte.costoDelViaje(), 0.1)
  }

  @Test
  def siTenemosUnViajeCombinadoEntreUnTrenYUnSubteDaElPrecioSumadoDeAmbosRecorridos {
    val seteo = setUp

    val recorridoEnSubteB = new Recorrido(seteo.paradaSubteB1, seteo.paradaSubteB5, seteo.lineaB)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4, seteo.paradaTren5, seteo.sarmiento)
    val viajeCombinado = new ViajeCompuesto(recorridoEnSubteB, recorridoEnTren)

    assertEquals(6.5 ,  viajeCombinado.costoDelViaje(), 0.1)
  }

  @Test
  def siTenemosUnViajeCombinadoEntreTrenYUnColectivoDaLaSumaDeAmbosRecorridos {
    val seteo = setUp
    val recorridoEnColectivo = new Recorrido(seteo.paradaColec1, seteo.paradaColec2, seteo.linea17)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4, seteo.paradaTren5, seteo.sarmiento)
    val viajeCombinado = new ViajeCompuesto(recorridoEnColectivo, recorridoEnTren)

    assertEquals(4.5 , viajeCombinado.costoDelViaje(), 0.1)

  }

  @Test
  def siTenemosUnViajeCombinadoEntreUnTrenYUnColectivoConLaTarjetaDiscapacitadoNosSaleGratisTodoElViaje {
    val tarjetaDiscapacitado: TarjetaDiscapacitado = new TarjetaDiscapacitado

    val seteo = setUp
    val recorridoEnColectivo = new Recorrido(seteo.paradaColec1, seteo.paradaColec3, seteo.linea17)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4, seteo.paradaTren5, seteo.sarmiento)

    val viajeCombinado = new ViajeCompuesto(recorridoEnColectivo, recorridoEnTren)

    assertEquals(0 , viajeCombinado.costoDelViaje(tarjetaDiscapacitado), 0.1)

  }

  @Test
  def siTenemosUnViajeCombinandoUnColectivoAlTrabajoYUnSubte {
    val seteo = setUp
    var tarjetaYendoAlTrabajo: TarjetaTrabajador = new TarjetaTrabajador

    val direccionDeLaBoca = new Direccion("Brandsen", 200)
    val direccionDelCentro = new Direccion("Riobamba", 700)

    val parada1Del24 = new Direccion("Av. Patricios", 150)
    val parada3Del24 = new Direccion("Lavalle", 1500)

    var paradasBondi = new Array[Direccion](4)
    paradasBondi :+= parada1Del24
    paradasBondi :+= direccionDeLaBoca
    paradasBondi :+= parada3Del24
    paradasBondi :+= direccionDelCentro

    tarjetaYendoAlTrabajo.laBoca :+= direccionDeLaBoca
    tarjetaYendoAlTrabajo.centro :+= direccionDelCentro

    val linea24 = COLECTIVO(paradasBondi, seteo.informador)
    val recorridoAlTrabajo = new Recorrido(direccionDeLaBoca, direccionDelCentro, linea24)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4, seteo.paradaTren5, seteo.sarmiento)

    val viajeCombinado = new ViajeCompuesto(recorridoAlTrabajo, recorridoEnTren)

    assertEquals(3.00 , viajeCombinado.costoDelViaje(tarjetaYendoAlTrabajo), 1.00)

  }

  @Test
  def siTenemosUnViajeCombinadoEntreDosSubtesConLaTarjetaTurismoTeCobraUnSoloViaje {
    val seteo = setUp
    var tarjetaTurista = new TarjetaTurista

    tarjetaTurista.zona :+= seteo.paradaSubteB1
    tarjetaTurista.zona :+= seteo.paradaSubteB5

    val recorridoTurista = new Recorrido(seteo.paradaSubteB1, seteo.paradaSubteB5, seteo.lineaB)
    val recorridoEnSubteD = new Recorrido(seteo.paradaSubteD1, seteo.paradaSubteD3, seteo.lineaD)

    val viajeCombinado = new ViajeCompuesto(recorridoTurista, recorridoEnSubteD)

    assertEquals(4.05 , viajeCombinado.costoDelViaje(tarjetaTurista), 0.01)
  }

  @Test
  def siTenemosUnViajeCombinadoEntreUnSubteYUnTrenConLaTarjetaTurismoTeCobraDescuentoSoloEnElQueAplica {
    val seteo = setUp
    var tarjetaTurista = new TarjetaTurista

    tarjetaTurista.zona :+= seteo.paradaSubteB1
    tarjetaTurista.zona :+= seteo.paradaSubteB5

    val recorridoTurista = new Recorrido(seteo.paradaSubteB1, seteo.paradaSubteB5, seteo.lineaB)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4, seteo.paradaTren5, seteo.sarmiento)

    val viajeCombinado = new ViajeCompuesto(recorridoTurista, recorridoEnTren)

    assertEquals(6.05 , viajeCombinado.costoDelViaje(tarjetaTurista), 0.01)
  }
}