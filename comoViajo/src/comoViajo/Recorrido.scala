package comoViajo

class Recorrido(var paradaDeSubida : Direccion, var paradaDeBajada: Direccion, var transporte : Transporte, var siguienteRecorrido : Recorrido) {

    def precioBase : Double = {
      ???
    }

  def obtenerTiempoRecorrido : Int = {
      ???
    }
    
    //Fijarnos de hacer precio degandolo en el transporte
}