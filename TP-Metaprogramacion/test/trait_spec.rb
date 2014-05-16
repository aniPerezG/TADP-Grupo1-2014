require 'rspec'
require '../test/escenario'

describe 'Usando Traits' do

  it 'Una clase usa el un trait ok' do
    o = MiClase.new
    o.metodo1.should == "mundo"
    o.metodo2(3).should == 42
  end

  it 'Una clase suma dos traits y los usa' do
    conflicto =  Conflicto.new
    conflicto.metodo2(84).should == 42
    conflicto.metodo3.should == "mundo"
    expect {conflicto.metodo1}.to raise_error(ConflictoTraitException)
  end

  it 'Una clase usa un trait pero le resta un metodo' do
    sin_metodo = SinUnMetodo.new
    sin_metodo.metodo3.should == "mundo"
    expect {sin_metodo.metodo1}.to raise_error (NoMethodError)
  end

 it 'Una clase usa dos traits pero a una le resta un metodo conflictivo' do
    sin_conflicto = TodoBienTodoLegal.new
    sin_conflicto.metodo3.should == "mundo"
    sin_conflicto.metodo2(84).should == 42
    sin_conflicto.metodo1.should == "hola"
 end

  it 'Una clase que usa alias en un metodo' do
    clase = ConAlias.new
    clase.metodo1.should == "hola"
    clase.saludo.should == "hola"
    clase.metodo2(84).should == 42
  end

  it 'Una clase resuelve sus conflictos entre MiTrait y MiOtroTrait ejecutando el metodo de MiTrait' do
  clase = ResuelveEjecutandoElPrimero.new
  clase.metodo1.should == "hola"
  end

  it'Una clase resuelve sus conflictos entre MiTrait y MiOtroTrait ejecutando uno tras otro' do
    clase = ResuelveUsandoTodos.new
    clase.metodo1.should == "kawuabonga"
    clase.metodo4(1, 2).should == 6
  end

  it "Una clase resuelve sus conflictos aplicando una funcion a los resultados de los metodos conflictivos del Trait" do
    clase = ResuelveUsandoFuncion.new
    clase.metodo1.should == "holakawuabonga"
  end

  it "Una clase resuelve sus conflictos entre distintos traits ejecutando el primero que cumple una condicion" do
    clase = ResuelveUsandoCriterio.new
    clase.metodo1.should == "hola"
  end

  it "Una clase quiere resolver sus conflictos ejecutando el primero que cumple la condicion pero ninguno la cumple y arroja una excepcion" do
    clase =  ResuelveUsandoCriterio.new
    expect {clase.metodo3}.to raise_error(ConflictoTraitException)
  end

  end
