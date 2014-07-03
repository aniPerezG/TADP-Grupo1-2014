package comoViajo

class Viaje() {
  
  def costoDelViaje (unaTarjeta : Tarjeta) : Double/* = {
      
    return recorridos.map{unRecorrido => unaTarjeta.precioDeTarjeta(unRecorrido)}.sum
  }*/
  
   def costoDelViaje () : Double /*= {
    
    return recorridos.map{unRecorrido => unRecorrido.precioBase}.sum
  }*/
  
  
  def tiempoDelViaje : Double /*= {
    
    return recorridos.map{unRecorrido => unRecorrido.obtenerTiempoRecorrido}.sum + this.obtenerTiempoDeCombinaciones
  }*/

  def obtenerTiempoDeCombinaciones : Double /*= {
    
    var resultado = 0.0
    if (recorridos.length > 1) 
    {
    
      for( i <- 1 until recorridos.length)
      {
        
        resultado += recorridos(i).tiempoDeCombinacionCon(recorridos(i-1))
      } 
      
    }
   
   return resultado
  }*/
  
}