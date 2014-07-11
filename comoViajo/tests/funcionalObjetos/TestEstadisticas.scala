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
import comoViajo.ViajesSimples
import org.junit.Before
import scala.collection.mutable.ArrayBuffer
import comoViajo.ViajeSimple
import comoViajo.ViajeSimple
import comoViajo.ViajeSimple
import comoViajo.ViajeSimple
import comoViajo.Statistics
import comoViajo.PALERMO
import comoViajo.Statistics
import comoViajo.Compania
import comoViajo.GRUPOPLAZA
import comoViajo.METROVIAS
import comoViajo.TBA

class TestsEstadisticas {

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
    paradasDeSubteB = new Array[Direccion](5)
    paradasDeSubteD = new Array[Direccion](5)
    paradasDeTren = new Array[Direccion](7)

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
    linea17 = COLECTIVO(paradasDeColec, informador,GRUPOPLAZA)
    lineaB = SUBTE(paradasDeSubteB, informador, METROVIAS)
    lineaD = SUBTE(paradasDeSubteD, informador,METROVIAS)
    sarmiento = TREN(paradasDeTren, informador, tablaDePreciosDelTren,TBA)

    val recorridoEnColectivo = new Recorrido(paradaColec1, paradaColec2, linea17)
    val viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)

    val recorridoEnSubte = new Recorrido(paradaSubteB1, paradaSubteB5, lineaB)
    val viajeEnSubte = new ViajeSimple(recorridoEnSubte)

    val recorridoEnTren = new Recorrido(paradaTren4, paradaTren5, sarmiento)
    val viajeEnTren = new ViajeSimple(recorridoEnTren)
    
    val recorridoEnSubte2 = new Recorrido(paradaSubteB1, paradaSubteB5, lineaB)
    val viajeEnSubte2 = new ViajeSimple(recorridoEnSubte2) 
    
    ViajesSimples.clean
    ViajesSimples.add(viajeEnColectivo)
    ViajesSimples.add(viajeEnSubte)
    ViajesSimples.add(viajeEnSubte2)
    ViajesSimples.add(viajeEnColectivo)
    
  }

  @Test
  def TestEstadisticaViajesSimples {
    
  
    var ejemplo = new Statistics[ViajeSimple](ViajesSimples.allInstances)
    var query = ejemplo
      .select { viaje => viaje.costoDelViaje }
      .where { viaje => viaje.costoDelViaje > 3 }
      .groupBy { viaje: ViajeSimple => viaje.recorrido.transporte.getClass() }

    println(query.apply.toString) //Solo para verlo!!

    assertEquals(1, query.apply.size)
    

  }
  
@Test
def TestProporcionDeViajesParaUnaZonaDada {
    
    var queryTotal = new Statistics[ViajeSimple](ViajesSimples.allInstances)
    var total = queryTotal.select(identity)
    .reduce(_.size)
    .apply

    var query = new Statistics[ViajeSimple](ViajesSimples.allInstances)
    
    var resultado = query.select(identity)
    .where{_.zonasPorLaQuePasa.contains(PALERMO)}
    .groupBy{viaje : ViajeSimple => viaje.transporte.getClass()}
    .reduce{viajes => (viajes.size * 100.0) / total}
    .apply
    
    println(resultado.toString)
    
    var listaDePorcentajesOrdenados = resultado.valuesIterator.toList.sortBy(identity)
    
    assertEquals(listaDePorcentajesOrdenados(0), 25.0, 0.1)
    assertEquals(listaDePorcentajesOrdenados(1), 25.0, 0.1)
    assertEquals(listaDePorcentajesOrdenados(2), 50.0, 0.1)
    
}

@Test
def TestDeFacturacionTotalSobreCadaCompania {
      
   var query = new Statistics[ViajeSimple](ViajesSimples.allInstances)
   var resultado = query
   .select(identity)
   .groupBy{viaje : ViajeSimple => viaje.transporte.compania}
   .reduce{v => costoTotal(v)}
   .apply
  
    println(resultado.toString)
    println(ViajesSimples.allInstances.toString())
    
    var listaDePreciosOrdenados = resultado.valuesIterator.toList.sortBy(identity)
    
    assertEquals(listaDePreciosOrdenados(0), 2.0, 0.1)
    assertEquals(listaDePreciosOrdenados(1), 2.5, 0.1)
    assertEquals(listaDePreciosOrdenados(2), 9.0, 0.1)
      
}

@Test
def TestDeFacturacionTotalSobreCadaTipoDeTransporte {
  
  var query = new Statistics[ViajeSimple](ViajesSimples.allInstances)
  var resultado = query
   .select(identity)
   .groupBy{viaje : ViajeSimple => viaje.transporte.getClass()}
   .reduce{v => costoTotal(v)}
   .apply
    
    println(resultado.toString)
    
    var listaDePreciosOrdenados = resultado.valuesIterator.toList.sortBy(identity)
    
    assertEquals(listaDePreciosOrdenados(0), 2.0, 0.1)
    assertEquals(listaDePreciosOrdenados(1), 2.5, 0.1)
    assertEquals(listaDePreciosOrdenados(2), 9.0, 0.1)
}



@Test
def TestSobreCostoPromedioDeCadaTipoDeTransporte {
   var query = new Statistics[ViajeSimple](ViajesSimples.allInstances)
   var resultado = query
     .select(identity)
     .groupBy{viaje: ViajeSimple => viaje.transporte.getClass()}
     .reduce{v => costoTotal(v) / v.size}
     .apply
     
   println(resultado.toString)
     
   var listaDePreciosOrdenados = resultado.valuesIterator.toList.sortBy(identity)
    
   assertEquals(listaDePreciosOrdenados(0), 2.0, 0.1)
   assertEquals(listaDePreciosOrdenados(1), 2.5, 0.1)
   assertEquals(listaDePreciosOrdenados(2), 4.5, 0.1)
     
     
}

def costoTotal(l: List[_]) = l.asInstanceOf[List[ViajeSimple]].map(_.costoDelViaje).sum
  
}