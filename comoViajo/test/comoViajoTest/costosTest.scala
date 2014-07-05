package comoViajoTest

import comoViajo._

import org.junit.Assert._
import org.junit.Test
import org.junit.Before

class costosTest {
  
  def setUp  = new {
    	val paradaColec1 = new Direccion("Onsari", 600)
			val paradaColec2 = new Direccion("Onsari",2500 )
			val paradaColec3 = new Direccion("Onsari" ,3500)
    	
    	val paradaSubteB1 = new Direccion("Corrientes" , 5000)
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
    	
			
			var paradasDeColec : Array[Direccion] = new Array[Direccion](3)
			var paradasDeSubteB : Array[Direccion] = new Array[Direccion](5)
			var paradasDeSubteD : Array[Direccion] = new Array[Direccion](5)
			var paradasDeTren : Array[Direccion] = new Array[Direccion](7)
			
			paradasDeColec :+=paradaColec1
			paradasDeColec :+=paradaColec2
			paradasDeColec :+=paradaColec3
			
			paradasDeSubteB :+=paradaSubteB1
			paradasDeSubteB :+=paradaSubteB2
			paradasDeSubteB :+=paradaSubteB3
			paradasDeSubteB :+=paradaSubteB4
			paradasDeSubteB :+=paradaSubteB5
			
			paradasDeSubteD :+=paradaSubteD1
			paradasDeSubteD :+=paradaSubteD2
			paradasDeSubteD :+=paradaSubteD3
			paradasDeSubteD :+=paradaSubteD4
			paradasDeSubteD :+=paradaSubteD5
			
			paradasDeTren :+=paradaTren1
			paradasDeTren :+=paradaTren2
			paradasDeTren :+=paradaTren3
			paradasDeTren :+=paradaTren4
			paradasDeTren :+=paradaTren5
			paradasDeTren :+=paradaTren6
			paradasDeTren :+=paradaTren7

			var tablaDePreciosDelTren = Map(3->2.0, 5->4.5, 7->8.75)
			
			val informador = new StubInformacionTransportes()
    	var linea17 : Colectivo = new Colectivo(informador,paradasDeColec)
    	var lineaB : Subte = new Subte(informador,paradasDeSubteB)
    	var lineaD : Subte = new Subte(informador,paradasDeSubteD)
    	var sarmiento : Tren = new Tren(informador,paradasDeTren)
		
    	sarmiento.tablaDePrecios = tablaDePreciosDelTren
      
  }
  
  @Test
  def elCostoDeUnRecorridoConTarjetaDiscapacitadoDaCorrecto {
    var tarjetaDiscapacitado : TarjetaDiscapacitado = new TarjetaDiscapacitado
    
    val seteo = setUp
		var recorridoEnColectivo = new Recorrido (seteo.paradaColec1,seteo.paradaColec3,seteo.linea17)
    var viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)
    
    assertTrue(0 == viajeEnColectivo.costoDelViaje(tarjetaDiscapacitado))
    
  }
  
  @Test
  def elCostoDeUnRecorridoConTarjetaYendoAlTrabajoDaCorrecto {
    val seteo = setUp
    var tarjetaYendoAlTrabajo : TarjetaTrabajador = new TarjetaTrabajador
    
    val direccionDeLaBoca = new Direccion("Brandsen" , 200)
    val direccionDelCentro = new Direccion("Riobamba" , 700)
    
    val parada1Del24 = new Direccion("Av. Patricios",150)
    val parada3Del24 = new Direccion("Lavalle",1500)
    
    var paradasBondi = new Array[Direccion](4)
    paradasBondi :+= parada1Del24
    paradasBondi :+= direccionDeLaBoca
    paradasBondi :+= parada3Del24
    paradasBondi :+= direccionDelCentro
    
    
    
    tarjetaYendoAlTrabajo.laBoca :+= direccionDeLaBoca
    tarjetaYendoAlTrabajo.centro :+= direccionDelCentro
    
    val linea24 = new Colectivo(seteo.informador, paradasBondi)
    val recorridoAlTrabajo = new Recorrido(direccionDeLaBoca, direccionDelCentro, linea24)
    val viajeAlLaburo = new ViajeSimple(recorridoAlTrabajo)
    
    assertTrue(1.0 == viajeAlLaburo.costoDelViaje(tarjetaYendoAlTrabajo))

    
  }
  
  @Test
  def elCostoDeUnRecorridoConTarjetaTurismoDaCorrecto {
    val seteo = setUp
    var tarjetaTurista = new TarjetaTurista
    
    tarjetaTurista.zona :+= seteo.paradaSubteB1
    tarjetaTurista.zona :+= seteo.paradaSubteB5
    
    val recorridoTurista = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
    val viajeTurista = new ViajeSimple(recorridoTurista)
    
    assertTrue(4.05 == viajeTurista.costoDelViaje(tarjetaTurista))
  }
  
  @Test
  def elCostoDeUnViajeSimpleEnColectivoMeDaCorrecto {
    	val seteo = setUp
			val recorridoEnColectivo = new Recorrido (seteo.paradaColec1,seteo.paradaColec2,seteo.linea17)
    	val viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)
    	
    	assertTrue(2.5 == viajeEnColectivo.costoDelViaje())

  }
  
  @Test
  def elCostoDeUnViajeSimpleEnSubteMeDaCorrecto {
    
    val seteo = setUp
    
    val recorridoEnSubte = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
    val viajeEnSubte = new ViajeSimple(recorridoEnSubte)
    
    assertTrue(4.5 == viajeEnSubte.costoDelViaje())
    
  }
  
  @Test
  def elCostoDeUnViajeSimpleEnTrenMeDaCorrecto {
    val seteo = setUp
    
    val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
    val viajeEnTren = new ViajeSimple(recorridoEnTren)
    
    assertTrue(2.0 == viajeEnTren.costoDelViaje())
    
  }
  
  @Test
  def siTenemosUnViajeCombinadoConDosSubtesSaleComoSiFueraConUnoSolo {
    val seteo = setUp
    
    val recorridoEnSubteB = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
    val recorridoEnSubteD = new Recorrido(seteo.paradaSubteD1,seteo.paradaSubteD3,seteo.lineaD)
    val viajeEnSubte = new ViajeCompuesto(recorridoEnSubteB,recorridoEnSubteD)
    
    assertTrue(4.5 == viajeEnSubte.costoDelViaje())
  }
  
  @Test 
  def siTenemosUnViajeCombinadoEntreUnTrenYUnSubteDaElPrecioSumadoDeAmbosRecorridos {
    val seteo = setUp
    
    val recorridoEnSubteB = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
    val viajeCombinado = new ViajeCompuesto(recorridoEnSubteB,recorridoEnTren)
    
    assertTrue(6.5 == viajeCombinado.costoDelViaje())
  }
  
  @Test
  def siTenemosUnViajeCombinadoEntreTrenYUnColectivoDaLaSumaDeAmbosRecorridos {
    val seteo = setUp
		val recorridoEnColectivo = new Recorrido (seteo.paradaColec1,seteo.paradaColec2,seteo.linea17)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
    val viajeCombinado = new ViajeCompuesto(recorridoEnColectivo,recorridoEnTren)
    
    assertTrue(4.5 == viajeCombinado.costoDelViaje())
    
  }
  
  @Test
  def siTenemosUnViajeCombinadoEntreUnTrenYUnColectivoConLaTarjetaDiscapacitadoNosSaleGratisTodoElViaje {
    val tarjetaDiscapacitado : TarjetaDiscapacitado = new TarjetaDiscapacitado
    
    val seteo = setUp
		val recorridoEnColectivo = new Recorrido (seteo.paradaColec1,seteo.paradaColec3,seteo.linea17)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
    
    val viajeCombinado = new ViajeCompuesto(recorridoEnColectivo,recorridoEnTren)
    
    assertTrue(0 == viajeCombinado.costoDelViaje(tarjetaDiscapacitado))
    
  }
  
  @Test
  def siTenemosUnViajeCombinandoUnColectivoAlTrabajoYUnSubte {
    val seteo = setUp
    var tarjetaYendoAlTrabajo : TarjetaTrabajador = new TarjetaTrabajador
    
    val direccionDeLaBoca = new Direccion("Brandsen" , 200)
    val direccionDelCentro = new Direccion("Riobamba" , 700)
    
    val parada1Del24 = new Direccion("Av. Patricios",150)
    val parada3Del24 = new Direccion("Lavalle",1500)
    
    var paradasBondi = new Array[Direccion](4)
    paradasBondi :+= parada1Del24
    paradasBondi :+= direccionDeLaBoca
    paradasBondi :+= parada3Del24
    paradasBondi :+= direccionDelCentro
    
    
    
    tarjetaYendoAlTrabajo.laBoca :+= direccionDeLaBoca
    tarjetaYendoAlTrabajo.centro :+= direccionDelCentro
    
    val linea24 = new Colectivo(seteo.informador, paradasBondi)
    val recorridoAlTrabajo = new Recorrido(direccionDeLaBoca, direccionDelCentro, linea24)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
    
    val viajeCombinado = new ViajeCompuesto(recorridoAlTrabajo,recorridoEnTren)
    
    assertTrue(3.00 == viajeCombinado.costoDelViaje(tarjetaYendoAlTrabajo))
    
  }
  
  @Test
  def siTenemosUnViajeCombinadoEntreDosSubtesConLaTarjetaTurismoTeCobraUnSoloViaje {
    val seteo = setUp
    var tarjetaTurista = new TarjetaTurista
    
    tarjetaTurista.zona :+= seteo.paradaSubteB1
    tarjetaTurista.zona :+= seteo.paradaSubteB5
    
    val recorridoTurista = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
    val recorridoEnSubteD = new Recorrido(seteo.paradaSubteD1,seteo.paradaSubteD3,seteo.lineaD)
    
    val viajeCombinado = new ViajeCompuesto(recorridoTurista,recorridoEnSubteD)
    
    assertTrue(4.05 == viajeCombinado.costoDelViaje(tarjetaTurista))
  }
  
  @Test
  def siTenemosUnViajeCombinadoEntreUnSubteYUnTrenConLaTarjetaTurismoTeCobraDescuentoSoloEnElQueAplica {
    val seteo = setUp
    var tarjetaTurista = new TarjetaTurista
    
    tarjetaTurista.zona :+= seteo.paradaSubteB1
    tarjetaTurista.zona :+= seteo.paradaSubteB5
    
    val recorridoTurista = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
    val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
    
    val viajeCombinado = new ViajeCompuesto(recorridoTurista,recorridoEnTren)
    
    assertTrue(6.05 == viajeCombinado.costoDelViaje(tarjetaTurista))
  }
  
  
}