const url = "http://localhost:8080/venda"; // URL da sua API

// Função para exibir os jogos na tabela
function show(vendas) {
    const tableBody = document.getElementById("tabela-vendas");
    if (!tableBody) {
        console.error("Elemento <tbody> com ID 'tabela-vendas' não encontrado no HTML.");
        return;
    }

    let rowsHtml = ""; // Inicializa a string para as linhas da tabela

    if (vendas && vendas.length > 0) {
        for (let venda of vendas) {
            // Adapte as propriedades (jogo.nome, jogo.mes, etc.)
            // de acordo com o que a sua API realmente retorna para cada jogo.
            rowsHtml += `
                <tr style="background-color: #252525;" data-id="${venda.id}">
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${venda.data || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${venda.jogo.nome || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${venda.cliente.nome || 'N/A'}</th>
                    <th style="padding: 12px 15px; text-align: left; border-bottom: 1px solid #333;">${venda.valor != null ? (venda.valor / 100).toFixed(2) : 'N/A'}</th>
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
        rowsHtml = `<tr><td colspan="${numberOfColumns}" style="padding: 10px 15px; text-align: center;">Nenhuma venda encontrada.</td></tr>`;
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
        console.error("Falha ao buscar vendas (erro de rede ou outros):", error);
        show([]); // Mostra tabela vazia ou mensagem de erro
    }
}

// Executa getTasks() quando o DOM estiver completamente carregado
// document.addEventListener("DOMContentLoaded", function (event) {
//     getTasks();
// });

document.addEventListener("DOMContentLoaded", function () {
    getTasks();

    const tableBody = document.getElementById("tabela-vendas");

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
        window.location.href = `editarVenda.html?id=${id}`;
    }
    });

    const searchInput = document.querySelector(".search-input");

    searchInput.addEventListener("input", async function () {
        const termo = this.value.trim();

        if (termo === "") {
            getTasks(); // Se busca estiver vazia, mostra todos
            return;
        }

        try {
            const response = await fetch(`${url}/buscar/${encodeURIComponent(termo)}`, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                console.error("Erro ao buscar por nome:", response.status);
                show([]);
                return;
            }

            const data = await response.json();
            show(data);
        } catch (error) {
            console.error("Erro na busca:", error);
            show([]);
        }
    });


});


window.getTasks = getTasks; // Torna global para ser chamada de excluirJogo.js

async function excluirJogo(id) {
    const confirmar = confirm("Deseja realmente excluir esta venda?");
    if (!confirmar) return;

    try {
        const response = await fetch(`${url}/${id}`, {
            method: "DELETE"
        });

        if (response.ok) {
            alert("Venda excluído com sucesso.");
            getTasks(); // Atualiza a lista após exclusão
        } else {
            const erro = await response.text();
            alert("Erro ao excluir venda: " + erro);
        }
    } catch (error) {
        console.error("Erro ao excluir venda:", error);
        alert("Erro de rede ao tentar excluir.");
    }
}


