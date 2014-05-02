require 'rspec'
require '../test/escenario'

describe 'Usando Traits' do

  it 'Una clase usa el un trait ok' do
    o = MiClase.new
    o.metodo1.should == "mundo"
    o.metodo2(3).should == 42
  end

end