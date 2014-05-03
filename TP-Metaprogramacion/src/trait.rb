require '../src/no_resolvedor'

class Trait

  attr_accessor :metodos, :criterio_de_resolucion_conflictos

  # METODOS DE INSTANCIA

    def initialize
      self.criterio_de_resolucion_conflictos= NoResolvedor.new
    end

    def method(selector, &bloque)
      metodos[selector] = bloque
    end

    def metodos
      @metodos = @metodos || {}
      @metodos
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
    trait_compuesto.agregar_nuevos_metodos (otroTrait.metodos)
    trait_compuesto
  end

  def agregar_nuevos_metodos metodos_a_agregar
    metodos_a_agregar.each {
      | metodo_nombre, metodo_codigo |
      self.agregar_metodo metodo_nombre, metodo_codigo
    }
  end

  def agregar_metodo (metodo_nombre, metodo_codigo)

    if(@metodos.include? metodo_nombre)
      criterio_de_resolucion_conflictos.resolver_conflicto(self, metodo_nombre, metodo_codigo)
    else
      method(metodo_nombre, &metodo_codigo)
    end

  end

  #Logica de la resta

  def - metodo_nombre

      trait_resultante = Trait.new()
      trait_resultante.metodos = @metodos.clone
      trait_resultante.metodos.delete(metodo_nombre)
      #Averiguar si es excepcion o solo una impresion por pantalla
      return trait_resultante
  end

  # METODOS DE CLASE

    def self.define(nombre, &bloque)
      @trait = Trait.new
      @trait.instance_eval(&bloque)
      # Acá ya tengo el Trait creado con sus métodos => Defino un literal con el nombre
      # que me da la posibilidad de que cualquiera use el Trait
      Object.const_set(nombre, @trait)
      @trait
    end

end

class Class

  def uses(trait)
    trait.definir_metodos_a(self)
  end

end

