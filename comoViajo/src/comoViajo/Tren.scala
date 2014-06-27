package comoViajo

class Tren extends Transporte {

      
    def costoRecorrido(unRecorrido : Recorrido) : Double = {
      //TODO Usar diccionario con el limite de estaciones y el precio?
      //al cambiar de una linea de tren a otra, se debe abonar un nuevo pasaje
      return 0.0
    }
    
     def tiempoRecorrido (unRecorrido : Recorrido) : Int = {
      
      return 3 * this.paradasDe(unRecorrido)
    }
     
     def tiempoDeCombinacion(combinacion : Recorrido) : Int = {
       return combinacionCon(combinacion.transporte)
     }
     
     def combinacionCon(tranporteDeCombinacion : Subte) : Int = {
       return 5
     }
     
     def combinacionCon(tranporteDeCombinacion : Tren) : Int = {
       return 6
     }
}