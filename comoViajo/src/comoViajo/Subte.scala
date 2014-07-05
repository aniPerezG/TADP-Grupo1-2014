package comoViajo

class Subte(val informadorTransportes: InformacionTransportes, var paradas: Array[Direccion])  extends Transporte{ 
  
  type T = Subte
  
  def costoRecorrido(unRecorrido : Recorrido) =  4.50 
  
  def tiempoRecorrido (unRecorrido : Recorrido) : Double = {
      
      return 2 * this.paradasDe(unRecorrido)
    }
 
//  def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, otroSubte : Transporte) : Double = {
//      return 4
//  }
//  
//  def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, unTren : Tren) : Double = {
//      return 5
//  }
//  def tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, unColectivo : Colectivo) : Double = {
//      return unColectivo.tiempoDeCombinacionEntre(unRecorrido, otroRecorrido, this)
//  }
  
  def  tiempoDeCombinacionEntre(unRecorrido : Recorrido , otroRecorrido : Recorrido, otroTransporte : Transporte) : Double = {
    
    val unColectivo = new Colectivo(informadorTransportes,Array())
    val unTren = new Tren(informadorTransportes,Array())
    
    if(otroTransporte.getClass().equals(this.getClass())) {
      return 4
    }
    else if(otroTransporte.getClass().equals(unColectivo.getClass())){
      return otroTransporte.tiempoDeCombinacionEntre(otroRecorrido, unRecorrido, this)
    }
    else {
      return 5
    }
  }
  
  override def costoDeCombinar(combinacion: Recorrido, unTransporte: Transporte) : Double = {
      if(unTransporte.getClass().equals(this.getClass())) {
    	  return 0.0  
      }
      else {
        return combinacion.costoBase
      }
  }//Sabemos que es un typetest pero no tenemos idea de como para que no tome Transporte, antes teníamos el método que recibía
   //un subte y listo. Pero no le da bola y entra directo en el que recibe el trait. No sabemos como pasar por parámetro "algo 
   // que extienda al trait
  
  override def costoDeCombinar(combinacion : Recorrido, unTransporte: Transporte, unaTarjeta : Tarjeta) : Double = {
    if(unTransporte.getClass().equals(this.getClass())) {
    	  return 0.0  
     }
    else {
    	return unaTarjeta.precioDeTarjeta(combinacion)
    }
  }

}