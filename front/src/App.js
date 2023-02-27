import { useState } from 'react';
import './App.css';
import Form from './Form';
import Table from './Table';

function App() {


  const [btnCadastrar, setBtnCadastrar] = useState(true);


  return (
    <div>

      <Form botao={btnCadastrar} />
      <Table />

    </div>
  );
}

export default App;
