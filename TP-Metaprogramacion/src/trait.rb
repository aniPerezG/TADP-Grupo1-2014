class Trait

  attr_accessor :metodos

  # METODOS DE INSTANCIA

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

#  TODO
#    - Merge del código de Gus
