package comoViajo

class Subte(var paradas: Array[Direccion])  extends Transporte{ 
  
  def costoRecorrido(unRecorrido : Recorrido) =  4.50 
  
  def tiempoRecorrido (unRecorrido : Recorrido) : Double = {
      
      return 2 * this.paradasDe(unRecorrido)
    }

  def tiempoDeCombinacionEntre(unRecorrido : Recorrido , combinacion : Recorrido) : Double = {
       return this.combinacionCon(combinacion.transporte)  
  }
  
  def combinacionCon(tranporteDeCombinacion : Subte) : Double = {
       return 4
  }
      
  def combinacionCon(tranporteDeCombinacion : Tren) : Double = {
       return 5
  }    
  
  //TODO 
}