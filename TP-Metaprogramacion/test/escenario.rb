require '../src/trait'

Trait.define :MiTrait do

  method :metodo1 do
    "hola"
  end

  method :metodo2 do | numero |
    numero * 0 + 42
  end

end

class MiClase

  uses MiTrait

  def metodo1
    "mundo"
  end

end