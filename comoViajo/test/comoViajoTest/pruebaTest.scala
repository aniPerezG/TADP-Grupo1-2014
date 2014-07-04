package comoViajoTest

import org.junit.Test
import org.junit.Assert._
import org.scalatest._
import org.scalamock.MockFactoryBase
import org.scalamock.scalatest
import comoViajo._
import org.scalamock.MockFunction
import org.scalamock._
import org.scalatest.mock.MockitoSugar
import org.scalamock.MockFunction



class pruebaTestCase extends FlatSpec with MockitoSugar  {

 
	@Test
		def testCreamosUnRecorridoYLePedimosSuCosto  =  {
			val direc1 = new Direccion("Onsari", 600)
			val direc2 = new Direccion("Onsari",2500 )
			val direc3 = new Direccion("Onsari" ,3500)
			
			var paradas : Array[Direccion] = new Array[Direccion](3)
			
			paradas :+direc1
			paradas :+direc2
			paradas :+direc3
			
			val informador = mock(classOf[InformacionTransportes])
			var linea17 = new Colectivo(informador,paradas)
			val meSubo = new Direccion("onsari",800)
			val meBajo = new Direccion("onsari", 3500)
			var miRecorrido = new Recorrido (meSubo,meBajo,linea17)
			
			assert(miRecorrido.costoBase == 2.5)
			
	}
}