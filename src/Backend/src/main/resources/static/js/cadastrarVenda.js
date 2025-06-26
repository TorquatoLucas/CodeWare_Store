const baseUrl = "http://localhost:8080/venda";

    async function postVenda(venda) {
  try {
    const response = await fetch("http://localhost:8080/venda", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(venda),
    });

    if (!response.ok) throw new Error("Erro ao enviar venda.");

    const resultado = await response.json();
    alert("Venda cadastrado com sucesso!");
    
    // Redireciona somente após sucesso
    window.location.href = "http://localhost:8080/html/gerenciarVendas.html";
    
  } catch (error) {
    console.error("Erro no POST:", error.message);
    alert("Erro ao cadastrar venda.");
  }
}


    document.addEventListener("DOMContentLoaded", () => {
      const btnCadastrar = document.getElementById("btnCadastrar");

      btnCadastrar.addEventListener("click", async function () {
        const data = document.getElementById("data").value;
        const jogoId = parseInt(document.getElementById("jogoId").value);
        const clienteId = parseInt(document.getElementById("clienteId").value);

        const novaVenda = { data, jogoId, clienteId};
        postVenda(novaVenda);
      });

      carregarClientes();
      carregarJogos();
    });




    

async function carregarClientes() {
  try {
    const response = await fetch("http://localhost:8080/cliente");
    const clientes = await response.json();

    const select = document.getElementById("clienteId");

    clientes.forEach(cliente => {
      const option = document.createElement("option");
      option.value = cliente.id;
      option.textContent = cliente.nome; // ou cliente.nomeCompleto
      select.appendChild(option);
    });
  } catch (e) {
    console.error("Erro ao carregar clientes:", e);
  }
}

async function carregarJogos() {
  try {
    const response = await fetch("http://localhost:8080/jogo");
    const jogos = await response.json();

    const select = document.getElementById("jogoId");

    jogos.forEach(jogo => {
      const option = document.createElement("option");
      option.value = jogo.id;
      option.textContent = jogo.nome;
      select.appendChild(option);
    });
  } catch (e) {
    console.error("Erro ao carregar jogos:", e);
  }
}
