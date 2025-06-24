// preencherJogos.js
const baseUrl = "http://localhost:8080/jogo";

async function carregarJogos() {
  try {
    const response = await fetch(baseUrl);
    if (!response.ok) throw new Error("Erro ao buscar jogos.");

    const jogos = await response.json();
    const showcase = document.querySelector(".games-showcase");
    showcase.innerHTML = ""; // limpa conteúdo atual

    jogos.forEach(jogo => {
      const card = document.createElement("div");
      card.className = "game-card";

      card.innerHTML = `
        <div class="game-card-image-placeholder">
          <img src="/${jogo.capa_diretorio}" alt="Capa do jogo" class="controller-icon">
        </div>
        <h2 class="game-title">${jogo.nome}</h2>
        <p class="game-price">R$ ${parseFloat(jogo.preco/100).toFixed(2)}</p>
      `;

      showcase.appendChild(card);
    });
  } catch (error) {
    console.error("Erro ao carregar jogos:", error);
  }
}

document.addEventListener("DOMContentLoaded", carregarJogos);
