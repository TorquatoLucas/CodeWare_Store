// editarJogo.js
const url = "http://localhost:8080/jogo";

// Carrega os dados do jogo usando o ID da URL
document.addEventListener("DOMContentLoaded", async () => {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (!id) {
        alert("ID do jogo não fornecido.");
        return;
    }

    try {
        const response = await fetch(`${url}/${id}`);
        const jogo = await response.json();

        document.getElementById("jogo-id").value = jogo.id;
        document.getElementById("nome").value = jogo.nome;
        document.getElementById("descricao").value = jogo.descricao;
        document.getElementById("lancamento").value = jogo.lancamento;
        document.getElementById("preco").value = jogo.preco;
        // document.getElementById("capa_diretorio").value = jogo.capa_diretorio;
        document.getElementById("estudio").value = jogo.estudio;
    } catch (err) {
        alert("Erro ao carregar jogo para edição.");
    }
});

// Envia as alterações para o backend
document.getElementById("form-editar").addEventListener("submit", async function (e) {
    e.preventDefault();

    const id = document.getElementById("jogo-id").value;

    
        const imagem = document.getElementById("imagem").files[0];

        let capa_diretorio = "";

        // Primeiro: faz o upload da imagem
        if (imagem) {
          const formData = new FormData();
          formData.append("imagem", imagem);

          try {
            const uploadResponse = await fetch(`${url}/upload-imagem`, {
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

    const jogoAtualizado = {
        nome: document.getElementById("nome").value,
        descricao: document.getElementById("descricao").value,
        lancamento: document.getElementById("lancamento").value,
        preco: document.getElementById("preco").value,
        capa_diretorio: capa_diretorio,
        estudio: document.getElementById("estudio").value
    };

    try {
        const response = await fetch(`${url}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(jogoAtualizado)
        });

        if (response.ok) {
            alert("Jogo atualizado com sucesso!");
            window.location.href = "gerenciarJogos.html";
        } else {
            alert("Erro ao atualizar jogo.");
        }
    } catch (err) {
        alert("Erro de rede ao tentar atualizar.");
    }
});
