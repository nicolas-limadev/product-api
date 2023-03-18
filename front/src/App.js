import { useEffect, useState } from 'react';
import './App.css';
import Form from './Form';
import Table from './Table';

function App() {


  // Object product
  const produto = {
    code: 0,
    name: '',
    marca: ''
  }

  // UseState
  const [btnCadastrar, setBtnCadastrar] = useState(true);
  const [produtos, setProdutos] = useState([]);
  const [objProduto, setObjProduto] = useState(produto);

  // UseEffect
  useEffect(() => {
    fetch("http://localhost:8090/listar")
      .then(retorno => retorno.json())
      .then(retorno_converted => setProdutos(retorno_converted));
  }, []);

  // Obtendo os dados do formulário
  const aoDigitar = (e) => {
    setObjProduto({ ...objProduto, [e.target.name]: e.target.value })
  }
  // Cadastrar produto
  const cadastrar = () => {
    fetch('http://localhost:8090/cadastrar', {
      method: 'post',
      body: JSON.stringify(objProduto),
      headers: {
        'Content-type': 'application/json',
        'Accept': 'application/json'
      }
    })
      .then(retorno => retorno.json())
      .then(retorno_converted => {
        if (retorno_converted.message !== undefined) {
          alert(retorno_converted.message);
        } else {
          setProdutos([...produtos, retorno_converted])
          alert('Produto cadastrado com sucesso!');
          limparFormulario();
        }
      })
  }

  // Alterar produto
  const alterar = () => {
    fetch('http://localhost:8090/alterar', {
      method: 'put',
      body: JSON.stringify(objProduto),
      headers: {
        'Content-type': 'application/json',
        'Accept': 'application/json'
      }
    })
      .then(retorno => retorno.json())
      .then(retorno_converted => {
        if (retorno_converted.message !== undefined) {
          alert(retorno_converted.message);

        } else {

          alert('Produto alterado com sucesso!');

          // Cópia do vetor de produtos
          let vetorTemp = [...produtos];

          // Indice
          let indice = vetorTemp.findIndex((p) => {
            return p.code === objProduto.code;
          });

          // Alterar produto do vetorTemp
          vetorTemp[indice] = objProduto;

          // Atualizar o vetor de produtos
          setProdutos(vetorTemp);

          limparFormulario();
        }
      })
  }

  // Remover produto
  const remover = () => {
    fetch('http://localhost:8090/remover/' + objProduto.code, {
      method: 'delete',
      headers: {
        'Content-type': 'application/json',
        'Accept': 'application/json'
      }
    })
      .then(retorno => retorno.json())
      .then(retorno_converted => {

        // Mensagem
        alert(retorno_converted.message);

        // Cópia do vetor de produtos
        let vetorTemp = [...produtos];

        // Indice
        let indice = vetorTemp.findIndex((p) => {
          return p.code === objProduto.code;
        });

        // Remover produto do vetorTemp
        vetorTemp.splice(indice, 1);

        // Atualizar o vetor de produtos
        setProdutos(vetorTemp);

        // Limpar formulário
        limparFormulario();

      })
  }

  // Limpar formulário
  const limparFormulario = () => {
    setObjProduto(produto);
    setBtnCadastrar(true);
  }


  // Selecionar produto
  const selecionarProduto = (indice) => {
    setObjProduto(produtos[indice]);
    setBtnCadastrar(false);
  }

  return (
    <div>
      <Form botao={btnCadastrar} eventoTeclado={aoDigitar} cadastrar={cadastrar} obj={objProduto} cancelar={limparFormulario} remover={remover} alterar={alterar} />

      <Table vetor={produtos} selecionar={selecionarProduto} />
    </div>
  );
}

export default App;
