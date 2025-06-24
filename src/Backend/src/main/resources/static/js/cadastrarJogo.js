const baseUrl = "http://localhost:8080/jogo";

    async function postJogo(jogo) {
  try {
    const response = await fetch("http://localhost:8080/jogo", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(jogo),
    });

    if (!response.ok) throw new Error("Erro ao enviar jogo.");

    const resultado = await response.json();
    alert("Jogo cadastrado com sucesso!");
    
    // Redireciona somente após sucesso
    window.location.href = "http://localhost:8080/html/gerenciarJogos.html";
    
  } catch (error) {
    console.error("Erro no POST:", error.message);
    alert("Erro ao cadastrar jogo.");
  }
}


    document.addEventListener("DOMContentLoaded", () => {
      const btnCadastrar = document.getElementById("btnCadastrar");

      btnCadastrar.addEventListener("click", async function () {
        const nome = document.getElementById("nome").value;
        const descricao = document.getElementById("descricao").value;
        const lancamento = document.getElementById("lancamento").value;
        const preco = document.getElementById("preco").value;
        // const capa_diretorio = document.getElementById("capa_diretorio").value;
        const estudio = document.getElementById("estudio").value;
        const imagem = document.getElementById("imagem").files[0];

        let capa_diretorio = "";

        // Primeiro: faz o upload da imagem
        if (imagem) {
          const formData = new FormData();
          formData.append("imagem", imagem);

          try {
            const uploadResponse = await fetch(`${baseUrl}/upload-imagem`, {
              method: "POST",
              body: formData
            });

            if (uploadResponse.ok) {
              capa_diretorio = await uploadResponse.text(); // Ex: "uploads/imagens/xxx.jpg"
            } else {
              alert("Erro ao fazer upload da imagem.");
              return;
            }
          } catch (error) {
            alert("Erro ao enviar imagem.");
            return;
          }
        }

        const novoJogo = { nome, descricao, lancamento, preco , capa_diretorio, estudio};
        postJogo(novoJogo);
      });
    });