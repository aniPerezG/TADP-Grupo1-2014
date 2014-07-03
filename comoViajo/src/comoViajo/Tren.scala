package comoViajo

class Tren(var paradas: Array[Direccion]) extends Transporte {

    var tablaDePrecios : Map[Int,Double] = Map()
      
    def costoRecorrido(unRecorrido : Recorrido) : Double = {
      
      var cantParadas = this.paradasDe(unRecorrido)
      //TODO Usar diccionario con el limite de estaciones y el precio?
      //al cambiar de una linea de tren a otra, se debe abonar un nuevo pasaje
      return 0.0
    }
    
     def tiempoRecorrido (unRecorrido : Recorrido) : Double = {
      
      return 3 * this.paradasDe(unRecorrido)
    }
     
     def tiempoDeCombinacionEntre(unRecorrido : Recorrido ,combinacion : Recorrido) : Double = {
        return combinacion.transporte.combinacionCon(this) 
     }
     
     def combinacionCon(tranporteDeCombinacion : Subte) : Double = {
       return 5
     }
     
     def combinacionCon(tranporteDeCombinacion : Tren) : Double = {
       return 6
     }
}