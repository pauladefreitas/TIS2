const agendamentoEndpoint = "http://localhost:8080/agendamento";

async function getAgendamento() {
    try {
      let key = "Authorization";
      const response = await fetch(agendamentoEndpoint, {
        method: "GET",
        headers: new Headers({
          Authorization: localStorage.getItem(key),
        }),
      });
      
      if (response.ok) {
        const data = await response.json();
        const agendamentosPendentes = data.filter(agendamento => agendamento.status === 'PENDENTE');
        mostrarAgendamentos(agendamentosPendentes);
      } else {
        console.error("Erro ao obter os agendamentos:", response.statusText);
      }
    } catch (error) {
      console.error("Erro ao realizar a requisição:", error);
    }
  }
  

  function mostrarAgendamentos(agendamentos) {
    var agendamentosContainer = $('#agendamentosContainer');
    agendamentosContainer.empty();
    agendamentos.forEach(function(agendamento) {
      var agendamentoHtml = criarHtmlAgendamento(agendamento);
      agendamentosContainer.append(agendamentoHtml);
    });
  
    $('.btn-success').click(function() {
      var agendamentoId = $(this).data('id');
      atualizarStatus(agendamentoId, "CONFIRMADO");
    });
  
    $('.btn-danger').click(function() {
      var agendamentoId = $(this).data('id');
      atualizarStatus(agendamentoId, "NEGADO");
    });
  }
  
  function criarHtmlAgendamento(agendamento) {
    return `
      <div id="agendamento-${agendamento.id}" class="card border-success mb-3" style="max-width: 18rem;">
        <div class="card-header">${agendamento.tipoServico}</div>
        <div class="card-body text-success">
          <h5 class="card-title">${agendamento.user.username}</h5>
          <p class="card-text">${agendamento.dataAgendamento}</p>
          <p class="card-text">${agendamento.horaAgendamento}</p>
          <button type="submit" class="btn btn-success btn-sm" data-id="${agendamento.id}">Confirmar</button>
          <button type="submit" class="btn btn-danger btn-sm" data-id="${agendamento.id}">Negar</button>
        </div>
      </div>`;
  }

  async function atualizarStatus(agendamentoId, novoStatus) {
    try {
      const response = await fetch(`${agendamentoEndpoint}/${agendamentoId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: localStorage.getItem("Authorization"),
        },
        body: JSON.stringify({ status: novoStatus }),
      });
  
      if (response.ok) {
        $(`#agendamento-${agendamentoId}`).remove();
        console.log(`Status do agendamento ${agendamentoId} atualizado para ${novoStatus}.`);
      } else {
        
        console.error("Erro ao atualizar o status do agendamento:", response.statusText);
      }
    } catch (error) {
      console.error("Erro ao realizar a requisição de atualização:", error);
    }
  }  

getAgendamento();

