const baseUrl = "http://localhost:8080/cliente";

// Função GET - Lista todos os clientes
async function getClientes() {
  try {
    const response = await fetch(baseUrl);
    if (!response.ok) throw new Error("Erro ao buscar clientes.");
    const clientes = await response.json();
    console.log("Clientes:", clientes);
    return clientes;
  } catch (error) {
    console.error("Erro no GET:", error.message);
  }
}

// Função POST - Envia um novo cliente
async function postCliente(cliente) {
  try {
    const response = await fetch(baseUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cliente),
    });

    if (!response.ok) throw new Error("Erro ao enviar cliente.");
    const resultado = await response.json();
    console.log("Cliente criado:", resultado);
    return resultado;
  } catch (error) {
    console.error("Erro no POST:", error.message);
  }
}

// Exemplo de uso:
const novoCliente = {
  nome: "Fulano de Tal",
  login: "fulano123",
  senha: "senhaSegura123",
  cpf: "98765432100"
};

postCliente(novoCliente);
getClientes();
