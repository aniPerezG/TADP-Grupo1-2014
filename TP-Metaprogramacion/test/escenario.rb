require '../src/trait'

Trait.define :MiTrait do

  method :metodo1 do
    "hola"
  end

  method :metodo2 do | numero |
    numero * 0 + 42
  end

  method :metodo4 do |numero1, numero2|
    numero1 + numero2
  end

end

Trait.define :MiOtroTrait do

  method :metodo1 do
    "kawuabonga"
  end

  method :metodo3 do
    "mundo"
  end

  method :metodo4 do |numero1, numero2|
  (numero1 + numero2)*2
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

class ConAlias
  uses MiTrait << (:metodo1 > :saludo)
end

class ResuelveEjecutandoElPrimero
  uses ejecuta_el_primero(MiTrait + MiOtroTrait)
end

class ResuelveUsandoTodos
  uses ejecuta_todos(MiTrait + MiOtroTrait)
end

class ResuelveUsandoFuncion

  uses ejecuta_con_funcion(MiTrait + MiOtroTrait, lambda do
  |arg1, arg2|
    arg1.concat arg2
  end)
end

