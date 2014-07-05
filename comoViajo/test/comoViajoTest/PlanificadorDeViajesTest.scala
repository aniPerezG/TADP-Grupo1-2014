package comoViajoTest

import comoViajo._
import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import scala.collection.mutable.MutableList

class PlanificadorDeViajesTest {
  
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
    	
    	val recorridoEnColectivo = new Recorrido (paradaColec1,paradaColec2,linea17)
    	val recorridoEnSubteB = new Recorrido(paradaSubteB1,paradaSubteB5,lineaB)
    	val recorridoEnSubteD = new Recorrido(paradaSubteD1,paradaSubteD3,lineaD)
    	val recorridoEnTren = new Recorrido(paradaTren4,paradaTren5,sarmiento)
    	
    	
    	val viajeEnColectivo = new ViajeSimple(recorridoEnColectivo)	
      val viajeEnSubte = new ViajeCompuesto(recorridoEnSubteB,recorridoEnSubteD)    	
      val viajeCombinadoTrenYSubte= new ViajeCompuesto(recorridoEnSubteB,recorridoEnTren)
      val viajeCombinadoColectivoYTren = new ViajeCompuesto(recorridoEnColectivo,recorridoEnTren)
    	
    	var listaDeViajes = MutableList(viajeEnColectivo,viajeEnSubte,viajeCombinadoColectivoYTren,viajeCombinadoTrenYSubte)
    	val planificador = new PlanificadorViaje(informador,listaDeViajes)
    	
    	val menorCosto = new MenorCosto()
    	val menorTiempo = new MenorTiempo()
  }

  @Test
  def elPlanificadorMeDevuelveElViajeDeMenorCosto {
 
    val seteo = setUp
    
    var viajeMasBarato = seteo.planificador.viajeMasConveniente(seteo.menorCosto)
    assertTrue(viajeMasBarato == seteo.viajeEnColectivo)
   
  }
   
  @Test
  def elPlanificadorMeDevuelveElViajeDeMenorTiempo {
    
    val seteo = setUp
    
    var viajeMasRapido = seteo.planificador.viajeMasConveniente(seteo.menorCosto)
    assertTrue(viajeMasRapido == seteo.viajeEnColectivo)
    
  }





}