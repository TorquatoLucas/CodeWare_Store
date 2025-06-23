const baseUrl = "http://localhost:8080/cliente";

    async function postCliente(cliente) {
  try {
    const response = await fetch("http://localhost:8080/cliente", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cliente),
    });

    if (!response.ok) throw new Error("Erro ao enviar cliente.");

    const resultado = await response.json();
    alert("Cliente cadastrado com sucesso!");
    
    // Redireciona somente após sucesso
    window.location.href = "http://localhost:8080/html/index.html";
    
  } catch (error) {
    console.error("Erro no POST:", error.message);
    alert("Erro ao cadastrar cliente.");
  }
}


    document.addEventListener("DOMContentLoaded", () => {
      const btnCadastrar = document.getElementById("btnCadastrar");

      btnCadastrar.addEventListener("click", () => {
        const nome = document.getElementById("nome").value;
        const login = document.getElementById("login").value;
        const cpf = document.getElementById("cpf").value;
        const senha = document.getElementById("senha").value;
        const confirmar = document.getElementById("confirmar").value;

        if (senha !== confirmar) {
          alert("As senhas não coincidem.");
          return;
        }

        const novoCliente = { nome, login, senha, cpf };
        postCliente(novoCliente);
      });
    });