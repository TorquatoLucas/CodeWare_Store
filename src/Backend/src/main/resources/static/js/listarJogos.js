const url = "http://localhost:8080/jogo"; // URL da sua API

// Função para exibir os jogos na tabela
function show(jogos) {
    const tableBody = document.getElementById("tabela-jogos");
    if (!tableBody) {
        console.error("Elemento <tbody> com ID 'tabela-jogos' não encontrado no HTML.");
        return;
    }

    let rowsHtml = ""; // Inicializa a string para as linhas da tabela

    if (jogos && jogos.length > 0) {
        for (let jogo of jogos) {
            // Adapte as propriedades (jogo.nome, jogo.mes, etc.)
            // de acordo com o que a sua API realmente retorna para cada jogo.
            rowsHtml += `
                <tr style="background-color: #252525;" data-id="${jogo.id}">
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${jogo.nome || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${jogo.descricao || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${jogo.lancamento || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${jogo.preco || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${jogo.capa_diretorio || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${jogo.estudio || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">
                        <span class="btn-editar" style="cursor: pointer;" title="Editar">✏️</span>
                    </th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">
                        <span class="btn-excluir" style="cursor: pointer;" title="Excluir">🗑️</span>
                    </th>
                </tr>
            `;
        }
    } else {
        // Mensagem caso não haja jogos ou ocorra um erro
        const headerRow = tableBody.previousElementSibling.rows[0]; // Pega a linha de cabeçalho (thead > tr)
        const numberOfColumns = headerRow ? headerRow.cells.length : 6; // Default to 6 if header not found
        rowsHtml = `<tr><td colspan="${numberOfColumns}" style="padding: 10px 15px; text-align: center;">Nenhum jogo encontrado.</td></tr>`;
    }

    tableBody.innerHTML = rowsHtml; // Insere as linhas geradas no corpo da tabela
}

async function getTasks() {
    try {
        const response = await fetch(url, {
            method: "GET",
            headers: new Headers({
                // Não precisamos mais do cabeçalho 'Authorization'
                'Content-Type': 'application/json' // Ainda é uma boa prática
            }),
        });

        if (!response.ok) {
            // Tratar erros HTTP (ex: 404 Não Encontrado, 500 Erro de Servidor)
            console.error(`Erro ao buscar jogos: ${response.status} ${response.statusText}`);
            let errorData = null;
            try {
                errorData = await response.json();
                console.error('Detalhes do erro:', errorData);
            } catch (e) {
                const errorText = await response.text();
                console.error('Detalhes do erro (texto):', errorText);
            }
            show([]); // Mostra tabela vazia ou mensagem de erro
            return;
        }

        const data = await response.json();
        console.log("Dados recebidos da API:", data); // Verifique a estrutura dos dados aqui!
        show(data);

    } catch (error) {
        console.error("Falha ao buscar jogos (erro de rede ou outros):", error);
        show([]); // Mostra tabela vazia ou mensagem de erro
    }
}

// Executa getTasks() quando o DOM estiver completamente carregado
// document.addEventListener("DOMContentLoaded", function (event) {
//     getTasks();
// });

document.addEventListener("DOMContentLoaded", function () {
    getTasks();

    const tableBody = document.getElementById("tabela-jogos");

    // tableBody.addEventListener("click", function (event) {
    //     const excluirBtn = event.target.closest(".btn-excluir");
    //     if (!excluirBtn) return;

    //     const row = excluirBtn.closest("tr");
    //     const id = row.dataset.id;

    //     if (id) {
    //         excluirJogo(id);
    //     }
    // });

    tableBody.addEventListener("click", function (event) {
    const row = event.target.closest("tr");
    const id = row?.dataset?.id;

    if (!id) return;

    // Verifica se clicou no botão excluir
    if (event.target.closest(".btn-excluir")) {
        excluirJogo(id);
    }

    // Verifica se clicou no botão editar
    if (event.target.closest(".btn-editar")) {
        window.location.href = `editarJogo.html?id=${id}`;
    }
    });

});


window.getTasks = getTasks; // Torna global para ser chamada de excluirJogo.js

async function excluirJogo(id) {
    const confirmar = confirm("Deseja realmente excluir este jogo?");
    if (!confirmar) return;

    try {
        const response = await fetch(`${url}/${id}`, {
            method: "DELETE"
        });

        if (response.ok) {
            alert("Jogo excluído com sucesso.");
            getTasks(); // Atualiza a lista após exclusão
        } else {
            const erro = await response.text();
            alert("Erro ao excluir jogo: " + erro);
        }
    } catch (error) {
        console.error("Erro ao excluir jogo:", error);
        alert("Erro de rede ao tentar excluir.");
    }
}


