require '../src/trait'

Trait.define :MiTrait do

  method :metodo1 do
    "hola"
  end

  method :metodo2 do | numero |
    numero * 0 + 42
  end

end

Trait.define :MiOtroTrait do

  method :metodo1 do
    "kawuabonga"
  end

  method :metodo3 do
    "mundo"
  end

end

class MiClase

  uses MiTrait

  def metodo1
    "mundo"
  end

end

class Conflicto

  uses MiTrait + MiOtroTrait

end

class SinUnMetodo

  uses MiOtroTrait - :metodo1

end

class TodoBienTodoLegal
  uses MiTrait + (MiOtroTrait - :metodo1)
end