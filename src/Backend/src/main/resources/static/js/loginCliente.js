const baseUrl = "http://localhost:8080/cliente";

    async function postLogin(cliente) {
  try {
    const response = await fetch("http://localhost:8080/cliente/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cliente),
    });

    if (!response.ok) throw new Error("Erro ao enviar login.");

    const resultado = await response.json();
    alert("Login feito com sucesso!");
    
    // Redireciona somente após sucesso
    window.location.href = "http://localhost:8080/html/home.html";
    
  } catch (error) {
    console.error("Erro no POST:", error.message);
    alert("Erro ao fazer login.");
  }
}


    document.addEventListener("DOMContentLoaded", () => {
      const btnLogar = document.getElementById("btnLogar");

      btnLogar.addEventListener("click", () => {
        const login = document.getElementById("login").value;
        const senha = document.getElementById("senha").value;

        const novoCliente = { login, senha};
        postLogin(novoCliente);
      });
    });