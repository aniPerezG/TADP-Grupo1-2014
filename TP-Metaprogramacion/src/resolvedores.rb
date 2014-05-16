require '../src/conflicto_trait_exception'

class NoResolvedor

  def bloque_a_ejecutar codigos_conflictivos
    Proc.new do
      | *args |
      raise ConflictoTraitException.new 'Los traits tienen el mismo metodo definido'
    end
  end
end

class EjecutaElPrimero

  def bloque_a_ejecutar codigos_conflictivos
    codigos_conflictivos[0]
  end
end


class EjecutaTodos

  def bloque_a_ejecutar codigos_conflictivos
    Proc.new do
      | *args |
      metodo_resultante = nil
      codigos_conflictivos.each do
      |metodo|
        metodo_resultante = metodo.call(*args)
      end
      metodo_resultante

    end
  end
end

class EjecutaAplicandoFuncion

  attr_accessor :funcion

  def initialize unaFuncion
    self.funcion = unaFuncion
  end

  def bloque_a_ejecutar codigos_conflictivos
      funcion = self.funcion
      Proc.new do
        | *args |
        codigos_conflictivos.drop(1).reduce codigos_conflictivos[0] do
          |seed, elem|
          funcion.call(seed.call(*args), elem.call(*args))
        end
      end
  end

end

class EjecutaElPrimeroSegunCondicion

  attr_accessor :criterio

  def initialize unCriterio
    self.criterio = unCriterio
  end

  def bloque_a_ejecutar codigos_conflictivos
    criterio = self.criterio
    Proc.new do
    | *args |
      metodo_resultante = codigos_conflictivos.detect(ifnone = Proc.new do | *args | raise ConflictoTraitException.new 'Ningun Metodo del Trait cumple con la condicion' end) do
      |metodo|
        criterio.call(metodo.call(*args))
      end
      metodo_resultante.call(*args)
    end

  end

  end