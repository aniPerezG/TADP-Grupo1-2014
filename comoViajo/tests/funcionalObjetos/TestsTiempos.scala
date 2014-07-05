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

class TestsTiempos {

  def setUp = new {
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
    var paradasDeSubteB: Array[Direccion] = new Array[Direccion](5)
    var paradasDeSubteD: Array[Direccion] = new Array[Direccion](5)
    var paradasDeTren: Array[Direccion] = new Array[Direccion](7)

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
    var lineaB = SUBTE(paradasDeSubteB, informador)
    var lineaD = SUBTE(paradasDeSubteD, informador)
    var sarmiento = TREN(paradasDeTren, informador, tablaDePreciosDelTren)

  }

  @Test
  def elTiempoDeUnViajeEnMicroSimpleLoDeterminaSuRecorrido {
    val seteo = setUp

    val recorridoEnColectivo = new Recorrido(seteo.paradaColec1, seteo.paradaColec2, seteo.linea17)
    val viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)

    assertEquals(8, viajeEnColectivo.tiempoDelViaje, 0.1)
  }

  @Test
  def elTiempoDeUnViajeSimpleEnSubteLoDeterminaSuRecorrido {
    val seteo = setUp

    val recorridoEnSubte = new Recorrido(seteo.paradaSubteB1, seteo.paradaSubteB5, seteo.lineaB)
    val viajeEnSubte = new ViajeSimple(recorridoEnSubte)

    assertEquals(8 , viajeEnSubte.tiempoDelViaje, 0.1)
  }

  @Test
  def elTiempoDeUnViajeSimpleEnTrenLoDeterminaSuRecorrido {
    val seteo = setUp

    val recorridoEnTren = new Recorrido(seteo.paradaTren4, seteo.paradaTren5, seteo.sarmiento)
    val viajeEnTren = new ViajeSimple(recorridoEnTren)

    assertEquals(3 , viajeEnTren.tiempoDelViaje, 0.1)
  }

    @Test
    def elTiempoDeUnViajeCombinandoUnTrenYUnBondiEsLaSumaDeSusRecorridosMásLoQueTardaLaCombinacion {
      val seteo = setUp
      
      val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
			val recorridoEnColectivo = new Recorrido (seteo.paradaColec1,seteo.paradaColec2,seteo.linea17)
      
      val viajeCompuesto = new ViajeCompuesto(recorridoEnColectivo,recorridoEnTren)
      
      assertEquals(18.5 , viajeCompuesto.tiempoDelViaje, 0.1)

    }
    
    @Test
    def elTiempoDeUnViajeCombinandoUnTrenYUnSubteEsLaSumaDeSusRecorridosMásLoQueTardaLaCombinacion {
      val seteo = setUp
      
      val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
      val recorridoEnSubte = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
      
      val viajeCompuesto = new ViajeCompuesto(recorridoEnSubte,recorridoEnTren)
      
      assertEquals(16 , viajeCompuesto.tiempoDelViaje, 0.1)

    }
    
    @Test
    def elTiempoDeUnViajeCombinandoDosSubtesEsLaSumaDeSusRecorridosMásLoQueTardaLaCombinacion {
      val seteo = setUp
      
      val recorridoEnSubteB = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
      val recorridoEnSubteD = new Recorrido(seteo.paradaSubteD1,seteo.paradaSubteD3,seteo.lineaD)
      val viajeEnSubte = new ViajeCompuesto(recorridoEnSubteB,recorridoEnSubteD)
      
      assertEquals(16 , viajeEnSubte.tiempoDelViaje, 0.1)

    }
}