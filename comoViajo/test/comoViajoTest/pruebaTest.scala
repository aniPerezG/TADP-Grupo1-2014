package comoViajoTest

import comoViajo._

import org.junit.Assert._
import org.junit.Test

class PruebaTestCase {
 
	@Test
		def testCreamosUnRecorridoYLePedimosSuCosto  =  {
			val direc1 = new Direccion("Onsari", 600)
			val direc2 = new Direccion("Onsari",2500 )
			val direc3 = new Direccion("Onsari" ,3500)
			
			var paradas : Array[Direccion] = new Array[Direccion](3)
			
			paradas :+direc1
			paradas :+direc2
			paradas :+direc3
			
			val informador = new StubInformacionTransportes()
			var linea17 = new Colectivo(informador,paradas)
			val meSubo = new Direccion("onsari",800)
			val meBajo = new Direccion("onsari", 3500)
			var miRecorrido = new Recorrido (meSubo,meBajo,linea17)
			
			assertTrue(2.5 == miRecorrido.costoBase)
			
	}
}