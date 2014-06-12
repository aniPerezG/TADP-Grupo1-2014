package module

class Trabajo extends Tarjeta {

   def totalAPagar(viaje: Viaje) = 1.50
   val origenesPromo : List[Zona] = [new Zona("LaBoca"), new Zona("Liniers")]
   
   condicion = {viaje:Viaje => origenesPromo.exists(zona => zona.lugares.contains(viaje.origen)) }
}