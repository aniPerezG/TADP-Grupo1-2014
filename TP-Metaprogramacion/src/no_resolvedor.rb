require '../src/conflicto_trait_exception'

class NoResolvedor

  def resolver_conflicto trait, metodo_nombre, metodo_codigo
    trait.method metodo_nombre do
      raise ConflictoTraitException.new 'Los traits tienen el mismo metodo definido'
    end
  end
end