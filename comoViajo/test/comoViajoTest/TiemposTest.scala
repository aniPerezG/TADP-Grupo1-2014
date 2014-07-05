package comoViajoTest

import comoViajo._

import org.junit.Assert._
import org.junit.Test
import org.junit.Before

class TiemposTest {
  
  
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
    def elTiempoDeUnViajeEnMicroSimpleLoDeterminaSuRecorrido {
      val seteo = setUp
      
			val recorridoEnColectivo = new Recorrido (seteo.paradaColec1,seteo.paradaColec2,seteo.linea17)
    	val viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)
      
      assertTrue(60 == viajeEnColectivo.tiempoDelViaje)
    }
    
    @Test
    def elTiempoDeUnViajeSimpleEnSubteLoDeterminaSuRecorrido {
      val seteo = setUp
    
      val recorridoEnSubte = new Recorrido(seteo.paradaSubteB1,seteo.paradaSubteB5,seteo.lineaB)
      val viajeEnSubte = new ViajeSimple(recorridoEnSubte)
      
      assertTrue(8 == viajeEnSubte.tiempoDelViaje)
    }
    
    @Test
    def elTiempoDeUnViajeSimpleEnTrenLoDeterminaSuRecorrido {
      val seteo = setUp
      
      val recorridoEnTren = new Recorrido(seteo.paradaTren4,seteo.paradaTren5,seteo.sarmiento)
      val viajeEnTren = new ViajeSimple(recorridoEnTren)
      
      assertTrue(3 == viajeEnTren.tiempoDelViaje)
    }
    
    
    
    

}