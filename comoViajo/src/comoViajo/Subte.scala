package comoViajo

class Subte extends Transporte {
  
  def costoRecorrido(unRecorrido : Recorrido) =  4.50 
  
  def tiempoRecorrido (unRecorrido : Recorrido) : Int = {
      
      return 2 * this.paradasDe(unRecorrido)
    }

  def tiempoDeCombinacion(unRecorrido : Recorrido , combinacion : Recorrido) : Double = {
       return combinacion.transporte.combinacionCon(this) 
  }
  
  def combinacionCon(tranporteDeCombinacion : Subte) : Double = {
       return 4
  }
      
  def combinacionCon(tranporteDeCombinacion : Tren) : Double = {
       return 5
  }
  
}