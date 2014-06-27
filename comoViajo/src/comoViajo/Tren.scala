package comoViajo

class Tren extends Transporte {

      
    def costoRecorrido(unRecorrido : Recorrido) : Double = {
      //TODO Usar diccionario con el limite de estaciones y el precio?
      //al cambiar de una linea de tren a otra, se debe abonar un nuevo pasaje
      return 0.0
    }
    
     def tiempoRecorrido (unRecorrido : Recorrido) : Double = {
      
      return 3 * this.paradasDe(unRecorrido)
    }
     
     def tiempoDeCombinacion(unRecorrido : Recorrido ,combinacion : Recorrido) : Double = {
       return combinacionCon(combinacion.transporte)
     }
     
     def combinacionCon(tranporteDeCombinacion : Subte) : Double = {
       return 5
     }
     
     def combinacionCon(tranporteDeCombinacion : Tren) : Double = {
       return 6
     }
}