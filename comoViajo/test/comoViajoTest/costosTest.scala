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
    	
    	val paradaSubte1 = new Direccion("Corrientes" , 5000)
    	val paradaSubte2 = new Direccion("Corrientes", 4500)
    	val paradaSubte3 = new Direccion("Corrientes", 4000)
    	val paradaSubte4 = new Direccion("Corrientes", 3500)
    	val paradaSubte5 = new Direccion("Corrientes", 3000)
    	
    	val paradaTren1 = new Direccion("La via", 0)
    	val paradaTren2 = new Direccion("La via", 1000)
    	val paradaTren3 = new Direccion("La via", 3000)
    	val paradaTren4 = new Direccion("La via", 5000)
    	val paradaTren5 = new Direccion("La via", 7000)
    	val paradaTren6 = new Direccion("La via", 9000)
    	val paradaTren7 = new Direccion("La via", 11000)
    	
			
			var paradasDeColec : Array[Direccion] = new Array[Direccion](3)
			var paradasDeSubte : Array[Direccion] = new Array[Direccion](5)
			var paradasDeTren : Array[Direccion] = new Array[Direccion](7)
			
			paradasDeColec :+=paradaColec1
			paradasDeColec :+=paradaColec2
			paradasDeColec :+=paradaColec3
			
			paradasDeSubte :+=paradaSubte1
			paradasDeSubte :+=paradaSubte2
			paradasDeSubte :+=paradaSubte3
			paradasDeSubte :+=paradaSubte4
			paradasDeSubte :+=paradaSubte5
			
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
    	var lineaB : Subte = new Subte(informador,paradasDeSubte)
    	var sarmiento : Tren = new Tren(informador,paradasDeTren)
		
    	sarmiento.tablaDePrecios = tablaDePreciosDelTren
      
  }
  
  @Test
  def elCostoDeUnRecorridoConTarjetaDiscapacitadoDaCorrecto() {
    var tarjetaDiscapacitado : TarjetaDiscapacitado = new TarjetaDiscapacitado
    
    val seteo = setUp
		var recorridoEnColectivo = new Recorrido (seteo.paradaColec1,seteo.paradaColec3,seteo.linea17)
    var viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)
    
    assertTrue(0 == viajeEnColectivo.costoDelViaje(tarjetaDiscapacitado))
    
  }
  
  @Test
  def elCostoDeUnRecorridoConTarjetaYendoAlTrabajoDaCorrecto() {
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
  def elCostoDeUnRecorridoConTarjetaTurismoDaCorrecto() {
    val seteo = setUp
    var tarjetaTurista = new TarjetaTurista
    
    tarjetaTurista.zona :+= seteo.paradaSubte1
    tarjetaTurista.zona :+= seteo.paradaSubte5
    
    val recorridoTurista = new Recorrido(seteo.paradaSubte1,seteo.paradaSubte5,seteo.lineaB)
    val viajeTurista = new ViajeSimple(recorridoTurista)
    
    assertTrue(4.05 == viajeTurista.costoDelViaje(tarjetaTurista))
  }
  
  @Test
  def elCostoDeUnViajeSimpleEnColectivoMeDaCorrecto() {
    	val seteo = setUp
			val recorridoEnColectivo = new Recorrido (seteo.paradaColec1,seteo.paradaColec2,seteo.linea17)
    	val viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)
    	
    	assertTrue(2.5 == viajeEnColectivo.costoDelViaje())

  }
  
  @Test
  def elCostoDeUnViajeSimpleEnSubteMeDaCorrecto() {
    
    val seteo = setUp
    
    val recorridoEnSubte = new Recorrido(seteo.paradaSubte1,seteo.paradaSubte5,seteo.lineaB)
    val viajeEnSubte = new ViajeSimple(recorridoEnSubte)
    
    assertTrue(4.5 == viajeEnSubte.costoDelViaje())
    
  }
  
  @Test
  def elCostoDeUnViajeSimpleEnTrenMeDaCorrecto() {
    val seteo = setUp
    
    val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
    val viajeEnTren = new ViajeSimple(recorridoEnTren)
    
    assertTrue(2.0 == viajeEnTren.costoDelViaje())
    
  }
}