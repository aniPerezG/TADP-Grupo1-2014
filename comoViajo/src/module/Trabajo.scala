package module

class Trabajo extends Tarjeta {

   def totalAPagar(viaje: Viaje) = {
      viaje.precioBase - 1.50
   }
   val origenesPromo = List(new Zona("LaBoca"), new Zona("Liniers"))
   val destinoPromo = new Zona("El Centro")
   
   condicion = {viaje:Viaje => origenesPromo.exists(zona => zona.lugares.contains(viaje.origen)) &&
     						   destinoPromo.lugares.contains(viaje.destino)}
}