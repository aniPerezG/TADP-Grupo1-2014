require '../src/conflicto_trait_exception'

class NoResolvedor

  def bloque_a_ejecutar metodo_trait1, metodo_trait2
    Proc.new do
      raise ConflictoTraitException.new 'Los traits tienen el mismo metodo definido'
    end
  end
end

class EjecutaElPrimero

  def bloque_a_ejecutar metodo_trait1, metodo_trait2
    Proc.new do
      | *args |
      metodo_trait1.call(*args)
    end
  end
end


class EjecutaTodos

  def bloque_a_ejecutar metodo_trait1, metodo_trait2
    Proc.new do
      | *args |
      metodo_trait1.call(*args)
      metodo_trait2.call(*args)
    end
  end
end

class EjecutaAplicandoFuncion

  attr_accessor :funcion

  def initialize unaFuncion
    self.funcion = unaFuncion
  end

  def bloque_a_ejecutar metodo_trait1, metodo_trait2

    bloque_a_ejecutar_con_funcion(metodo_trait1, metodo_trait2, self.funcion)

  end

  def bloque_a_ejecutar_con_funcion metodo_trait1, metodo_trait2, funcion
  Proc.new do
  | *args |
    funcion.call(metodo_trait1.call(*args), metodo_trait2.call(*args))
  end
    end
end