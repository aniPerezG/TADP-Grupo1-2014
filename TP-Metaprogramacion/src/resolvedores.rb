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