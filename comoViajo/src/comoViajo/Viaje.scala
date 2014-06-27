package comoViajo

class Viaje {
  
  var recorridos : List[Recorrido] = List()
  
  def costoDelViaje (unUsuario : Usuario) : Double = {
    
    return recorridos.map{unRecorrido => unUsuario.tarjeta.precioDeTarjeta(unRecorrido)}.sum
  }
  
  def tiempoDelViaje : Int = {
    
    return recorridos.map{unRecorrido => unRecorrido.obtenerTiempoRecorrido}.sum
  }
  
    

}