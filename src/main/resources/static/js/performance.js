document.addEventListener("DOMContentLoaded", function () {
    const dropdown = document.getElementById('funcionario');
    const avaliacoesContainer = document.getElementById('avaliacoes');
    let currentOffset = 0;
    const limit = 2;

    const mockAvaliacoes = {
        1: [
            {
                comment: "Ótimo serviço!",
                evaluationDate: "2024-05-30",
                id: 1,
                nota: 5,
                serviceName: "Massagem",
                status: "CONFIRMADO",
                user: { id: 1, username: 'Luís' }
            },
            {
                comment: "Muito bom!",
                evaluationDate: "2024-05-28",
                id: 2,
                nota: 4,
                serviceName: "Facial",
                status: "CONFIRMADO",
                user: { id: 2, username: 'Paula' }
            },
            {
                comment: "Excelente!",
                evaluationDate: "2024-05-27",
                id: 3,
                nota: 5,
                serviceName: "Manicure",
                status: "CONFIRMADO",
                user: { id: 3, username: 'Laura' }
            },
            {
                comment: "Razoável.",
                evaluationDate: "2024-05-26",
                id: 4,
                nota: 3,
                serviceName: "Pedicure",
                status: "CONFIRMADO",
                user: { id: 4, username: 'Carlos' }
            }
        ],
        2: [
            {
                comment: "Serviço mediano.",
                evaluationDate: "2024-05-27",
                id: 5,
                nota: 3,
                serviceName: "Pedicure",
                status: "CONFIRMADO",
                user: { id: 5, username: 'Carlos' }
            }
        ]
    };

    const mockFuncionarios = {
        1: { id: 1, name: "João Silva" },
        2: { id: 2, name: "Maria Oliveira" },
        3: { id: 3, name: "Carlos Souza" }
    };

    function populateDropdown() {
        Object.values(mockFuncionarios).forEach(funcionario => {
            const option = document.createElement('option');
            option.value = funcionario.id;
            option.textContent = funcionario.name;
            dropdown.appendChild(option);
        });
    }

    dropdown.addEventListener('change', function () {
        const selectedValue = dropdown.value;
        currentOffset = 0;
        carregarElementos(selectedValue);
    });

    function carregarElementos(valorSelecionado) {
        avaliacoesContainer.innerHTML = '';
        if (!mockAvaliacoes[valorSelecionado]) {
            avaliacoesContainer.innerHTML = '<p>Nenhuma avaliação encontrada.</p>';
            return;
        }

        const funcionario = mockFuncionarios[valorSelecionado];
        const avaliacoes = mockAvaliacoes[valorSelecionado];
        const notas = avaliacoes.map(avaliacao => avaliacao.nota);
        const mediaNotas = (notas.reduce((a, b) => a + b, 0) / notas.length).toFixed(1);

        const headerElement = document.createElement('div');
        headerElement.classList.add('avaliacao-header');
        headerElement.innerHTML = `
            <h4>${funcionario.name}</h4>
            <p class="media-notas">Média de notas desse funcionário: ${mediaNotas}</p>
            <button type="button" class="btn-performance" data-toggle="modal" data-target="#evaluationModal">Avaliação de Performance</button>
        `;
        avaliacoesContainer.appendChild(headerElement);

        loadMoreAvaliacoes(valorSelecionado);
    }

    function loadMoreAvaliacoes(valorSelecionado) {
        const avaliacoes = mockAvaliacoes[valorSelecionado];
        const nextOffset = currentOffset + limit;
        const avaliacoesToShow = avaliacoes.slice(currentOffset, nextOffset);

        avaliacoesToShow.forEach(avaliacao => {
            const avaliacaoElement = document.createElement('div');
            avaliacaoElement.classList.add('avaliacao');

            avaliacaoElement.innerHTML = `
                <p><strong>Usuário:</strong> ${avaliacao.user.username}</p>
                <p><strong>Data:</strong> ${avaliacao.evaluationDate}</p>
                <p><strong>Serviço:</strong> ${avaliacao.serviceName}</p>
                <p><strong>Nota:</strong> ${avaliacao.nota}</p>
                <p><strong>Comentário:</strong> ${avaliacao.comment}</p>
            `;
            avaliacoesContainer.appendChild(avaliacaoElement);
        });

        currentOffset = nextOffset;

        if (currentOffset < avaliacoes.length) {
            const loadMoreButton = document.createElement('button');
            loadMoreButton.classList.add('btn', 'btn-secondary', 'btn-block');
            loadMoreButton.textContent = 'Carregar Mais';
            loadMoreButton.onclick = function () {
                loadMoreAvaliacoes(valorSelecionado);
                loadMoreButton.remove();
            };
            avaliacoesContainer.appendChild(loadMoreButton);
        }
    }

    document.getElementById('confirmEvaluation').addEventListener('click', function () {
        const message = document.getElementById('message').value;
        const date = document.getElementById('date').value;

        if (message && date) {
            const modalBody = document.querySelector('#evaluationModal .modal-body');
            const modalFooter = document.querySelector('#evaluationModal .modal-footer');

            modalBody.innerHTML = `
                <p>Avaliação de performance agendada com sucesso!</p>
            `;

            modalFooter.innerHTML = '';

            $('#evaluationModal').on('hidden.bs.modal', function () {
                location.reload();
            });
        } else {
            alert('Por favor, preencha todos os campos.');
        }
    });

    populateDropdown();
});