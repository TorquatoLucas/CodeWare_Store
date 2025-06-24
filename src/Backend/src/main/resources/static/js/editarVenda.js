const url = "http://localhost:8080/venda";

// Carrega os dados da venda usando o ID da URL
document.addEventListener("DOMContentLoaded", async () => {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (!id) {
        alert("ID da venda não fornecido.");
        return;
    }

    try {
        const response = await fetch(`${url}/${id}`);
        const venda = await response.json();

        document.getElementById("data").value = venda.data;
        document.getElementById("valor").value = venda.valor;

        
        await carregarClientes();
        await carregarJogos();

        
        document.getElementById("clienteId").value = venda.cliente.id;
        document.getElementById("jogoId").value = venda.jogo.id;

    } catch (err) {
        alert("Erro ao carregar venda para edição.");
    }

});

// Envia as alterações para o backend
document.getElementById("form-editar").addEventListener("submit", async function (e) {
    e.preventDefault();

    const id = document.getElementById("venda-id").value;

    const vendaAtualizada = {
        data: document.getElementById("data").value,
        jogoId: parseInt(document.getElementById("jogoId").value),
        clienteId: parseInt(document.getElementById("clienteId").value),
        valor: parseInt(document.getElementById("valor").value)
    };


    try {
        const response = await fetch(`${url}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(vendaAtualizada)
        });

        if (response.ok) {
            alert("Venda atualizado com sucesso!");
            window.location.href = "gerenciarVendas.html";
        } else {
            alert("Erro ao atualizar venda.");
        }
    } catch (err) {
        alert("Erro de rede ao tentar atualizar.");
    }
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

