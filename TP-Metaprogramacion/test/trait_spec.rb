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

 # it 'una clase usa dos traits pero a una le resta un metodo conflictivo' do
 #    sin_conflicto = TodoBienTodoLegal.new
 #    sin_conflicto.metodo3.should == "mundo"
 #    sin_conflicto.metodo2(84).should == 42
 #    sin_conflicto.metodo1.should = "hola"
 #  end

  end
