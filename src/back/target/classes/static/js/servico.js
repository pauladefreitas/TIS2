document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('form-contato');
    const btnInsert = document.getElementById('btnInsert');
    const tableBody = document.getElementById('table-contatos');

    async function createService(service) {
        try {
            console.log('Request data:', service);

            const response = await fetch('http://localhost:8080/service', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(service)
            });

            console.log('Response status:', response.status);
            if (response.ok) {
                const data = await response.json();
                console.log('Serviço criado:', data);
                alert('Serviço cadastrado com sucesso!');
                loadServices();
            }
        } catch (error) {
            console.error('Erro ao criar serviço:', error);
        }
    }

    async function loadServices() {
        try {
            const response = await fetch('http://localhost:8080/service');
            if (response.ok) {
                const services = await response.json();
                tableBody.innerHTML = '';
                services.forEach(service => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${service.id}</td>
                        <td>${service.nome}</td>
                        <td>${service.email}</td>
                        <td>${service.servico}</td>
                        <td>${service.duracao}</td>
                        <td>${service.data}</td>
                        <td>${service.horarios.join(', ')}</td>
                        <td>
                            <button class="btn btn-outline-secondary" onclick="editService(${service.id})">Editar</button>
                            <button class="btn btn-outline-danger" onclick="deleteService(${service.id})">Excluir</button>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            }
        } catch (error) {
            console.error('Erro ao carregar serviços:', error);
        }
    }

    btnInsert.addEventListener('click', function() {
        const service = {
            nome: document.getElementById('inputNome').value,
            servico: document.getElementById('inputServico').value,
            email: document.getElementById('inputEmail').value,
            duracao: document.getElementById('inputDUracao').value,
            data: document.getElementById('data').value,
            horarios: Array.from(document.querySelectorAll('#horariosDropdown input[type="checkbox"]:checked')).map(checkbox => checkbox.value),
        };
        createService(service);
    });

    loadServices();
});
