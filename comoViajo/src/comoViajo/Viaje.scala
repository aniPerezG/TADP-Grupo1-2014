package comoViajo

class Viaje {
  
  var recorridos : List[Recorrido] = List()
  
  def costoDelViaje (unUsuario : Usuario) : Double = {
    
    return recorridos.map{unRecorrido => unUsuario.tarjeta.precioDeTarjeta(unRecorrido)}.sum
  }
  
  def tiempoDelViaje : Double = {
    
    return recorridos.map{unRecorrido => unRecorrido.obtenerTiempoRecorrido}.sum + this.obtenerTiempoDeCombinaciones
  }

  def obtenerTiempoDeCombinaciones : Double = {
    
    var resultado = 0.0
    if (recorridos.length > 1) 
    {
    
      for( i <- 1 until recorridos.length)
      {
        
        resultado += recorridos(i).tiempoDeCombinacionCon(recorridos(i-1))
      } 
      
    }
   
   return resultado
  }
  
}