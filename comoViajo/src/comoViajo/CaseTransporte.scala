package comoViajo

abstract class CaseTransporte 

case object SUBTE extends CaseTransporte
case object COLECTIVO extends CaseTransporte
case class TREN(tabla : TablaPrecios) extends CaseTransporte
