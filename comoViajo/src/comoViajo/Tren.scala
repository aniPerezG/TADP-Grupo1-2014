package comoViajo

class Tren(val informadorTransportes: InformacionTransportes, var paradas: Array[Direccion]) extends Transporte{

    var tablaDePrecios : Map[Int,Double] = Map()
      
    def costoRecorrido(unRecorrido : Recorrido) : Double = {
      
      var cantParadas = this.paradasDe(unRecorrido)
      var tuplaConElPosiblePrecioAPagar = tablaDePrecios.find{case (cantidadMaxima, precioAPagar) => cantidadMaxima >= cantParadas}
      if(tuplaConElPosiblePrecioAPagar.isDefined) {
        return tuplaConElPosiblePrecioAPagar.get._2 //En caso de que el option devuelto por el find sea Some obtengo el elemento del diccionario(el segundo componente de la tupla)
      }
      else {
        return tablaDePrecios.last._2  //En caso de que el option devuelto por el find sea none devuelvo el mayor precio
      }
    }
    
     def tiempoRecorrido (unRecorrido : Recorrido) : Double = {
      
      return 3 * this.paradasDe(unRecorrido)
    }
     
//    def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, otroTren : Transporte) : Double = {
//        return 6
//    }
//  
//    def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, unSubte: Subte) : Double = {
//        return 5
//    }
//  
//    def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, unColectivo : Colectivo) : Double = {
//        return unColectivo.tiempoDeCombinacionEntre(unRecorrido, otroRecorrido, this)
//    }
     
    def  tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, otroTransporte : Transporte) : Double = {
    
      val unColectivo = new Colectivo(informadorTransportes,Array())
      val unTren = new Tren(informadorTransportes,Array())
      
      if(otroTransporte.getClass().equals(this.getClass())) {
        return 6
      }
      else if(otroTransporte.getClass().equals(unColectivo.getClass())){
        return otroTransporte.tiempoDeCombinacionEntre(otroRecorrido, unRecorrido, this)
      }
      else {
        return 5
    }
    
  }
    
    def costoDeCombinar(combinacion : Recorrido, unSubte :Subte) : Double = {
      return combinacion.costoBase
    }
         
}