package comoViajo

class Recorrido(var paradaDeSubida : Direccion, var paradaDeBajada: Direccion, var transporte : Transporte) {

    //Fijarnos de en el recorrido hacer lo mismo que en listas que no sabemos e que tipo son hasta declararlas y hacer Recorrido[Transporte]
   
    def precioBase : Double = { 
      return transporte.costoRecorrido(this)
    }

    def obtenerTiempoRecorrido : Double = {
      return transporte.tiempoRecorrido(this)
    }

    def tiempoDeCombinacionCon(recorrido: Recorrido) : Double = {
   
       return transporte.tiempoDeCombinacionEntre(this, recorrido)
       
    }
    
    def costoCombinado(unTransporte : Transporte) : Double = {
      return transporte.precioDeCombinarCon(unTransporte)
    }
    
    //Fijarnos de hacer precio degandolo en el transporte
}