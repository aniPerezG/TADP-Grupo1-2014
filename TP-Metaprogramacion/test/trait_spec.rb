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

end