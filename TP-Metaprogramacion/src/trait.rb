require '../src/resolvedores'

class Trait

  attr_accessor :metodos, :criterio_de_resolucion_conflictos, :traits, :metodos_conflictivos

  # METODOS DE INSTANCIA

    def initialize
      self.criterio_de_resolucion_conflictos= NoResolvedor.new
      self.metodos = {}
      self.traits = []
      self.metodos_conflictivos = {}

    end

    def method(selector, &bloque)
      self.metodos[selector] = bloque
    end

    def codigos_conflictivos(selector)
      self.metodos_conflictivos[selector] = self.metodos_conflictivos[selector] || []
    end

    def metodo_conflictivo(selector, &bloque)
      self.codigos_conflictivos(selector) << bloque
    end

    def definir_metodos_a(clase)
      self.metodos.each do | key, value |
        clase.send(:define_method, key, value)
      end
    end

  #Lógica de la composición

  def + (otroTrait)
    trait_compuesto = Trait.new
    trait_compuesto.metodos = self.metodos.clone
    trait_compuesto.traits = self.traits.clone
    trait_compuesto.traits << otroTrait
    trait_compuesto
  end

  def agregar_nuevos_metodos metodos_a_agregar
    metodos_a_agregar.each {
      | metodo_nombre, metodo_codigo |
      self.agregar_metodo metodo_nombre, metodo_codigo
    }
  end

  def agregar_metodo (metodo_nombre, metodo_codigo)

    if(self.metodos.include? metodo_nombre)
      metodo_conflictivo metodo_nombre, &self.metodos[metodo_nombre] unless self.metodos_conflictivos.has_key? :metodo_nombre
      metodo_conflictivo metodo_nombre, &metodo_codigo
    else
      method(metodo_nombre, &metodo_codigo)
    end

  end

  def aplanar
    traits.reduce self do
    |seed, elem|
      seed.agregar_nuevos_metodos(elem.metodos.clone)
      seed
    end
    self.metodos_conflictivos.each {
      |metodo_nombre, codigos_conflictivos|
      metodo_definitivo = self.criterio_de_resolucion_conflictos.bloque_a_ejecutar codigos_conflictivos
      method(metodo_nombre, &metodo_definitivo)
    }


  end

  def aplanar_con (clase)
    self.aplanar
    self.definir_metodos_a(clase)
  end

  #Logica de la resta

  def - metodo_nombre
      trait_resultante = Trait.new()
      trait_resultante.metodos = self.metodos.clone
      trait_resultante.metodos.delete(metodo_nombre)
      trait_resultante
  end

  #Logica de alias

  def << bloque
    trait_final = Trait.new
    trait_final.metodos = @metodos.clone
    bloque.call trait_final
    trait_final

  end

  def codigo_metodo (nombre_metodo)
    metodos[nombre_metodo]
  end


  # METODOS DE CLASE

    def self.define(nombre, &bloque)
      @trait = Trait.new
      @trait.instance_eval(&bloque )
      Object.const_set(nombre, @trait)
      @trait
    end

    def self.resolvete_con(trait, estrategia)
      trait.criterio_de_resolucion_conflictos = estrategia
      trait
    end

end

class Symbol

  def > otroSimbolo
    lambda do
    |trait|
      codigo = trait.codigo_metodo(self)
      trait.agregar_metodo(otroSimbolo,codigo)
    end
  end

end

class Class

  def ejecuta_el_primero(trait)
   Trait.resolvete_con(trait, EjecutaElPrimero.new)
  end

  def ejecuta_todos(trait)
    Trait.resolvete_con(trait, EjecutaTodos.new)
  end

  def ejecuta_con_funcion(trait, unaFuncion)
    instancia = EjecutaAplicandoFuncion.new (unaFuncion)
  Trait.resolvete_con(trait, instancia)
  end

  def ejecuta_con_criterio(trait, unCriterio)
    instancia = EjecutaElPrimeroSegunCondicion.new (unCriterio)
    Trait.resolvete_con(trait, instancia)
  end

  def uses(trait)
    trait.aplanar_con(self)
  end

end