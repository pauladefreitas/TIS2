const avaliacaoEndpoint = "http://localhost:8080/avaliacao";

async function getAvaliacao() {
    try {
      let key = "Authorization";
      const response = await fetch(avaliacaoEndpoint, {
        method: "GET",
        headers: new Headers({
          Authorization: localStorage.getItem(key),
        }),
      });
      
      if (response.ok) {
        const data = await response.json();
        const avaliacoesPendentes = data.filter(av => av.status === 'PENDENTE');
        mostrarAvaliacoes(avaliacoesPendentes);
      } else {
        console.error("Erro ao obter os avaliacoes:", response.statusText);
      }
    } catch (error) {
      console.error("Erro ao realizar a requisição:", error);
    }
  }
  

  function mostrarAvaliacoes(avaliacoes) {
    var avaliacoesContainer = $('#avaliacoesContainer');
    avaliacoesContainer.empty();
    avaliacoes.forEach(function(avaliacao) {
      var avaliacaoHtml = criarHtmlAvaliacao(avaliacao);
      avaliacoesContainer.append(avaliacaoHtml);
    });
  
    $('.btn-success').click(function() {
      var avaliacaoId = $(this).data('id');
      atualizarStatus(avaliacaoId, "ACEITA");
    });
  
    $('.btn-danger').click(function() {
      var avaliacaoId = $(this).data('id');
      atualizarStatus(avaliacaoId, "NEGADA");
    });
  }
  
  function criarHtmlAvaliacao(avaliacao) {
    return `
      <div id="avaliacao-${avaliacao.id}" class="card border-success mb-3" style="max-width: 18rem;">
        <div class="card-header">${avaliacao.user.username}</div>
        <div class="card-body text-success">
          <h5 class="card-title">${avaliacao.evaluationDate}</h5>
          <p class="card-text">${avaliacao.nota}</p>
          <p class="card-text">${avaliacao.serviceName}</p>
          <p class="card-text">${avaliacao.comment}</p>
          <button type="submit" class="btn btn-success btn-sm" data-id="${avaliacao.id}">Confirmar</button>
          <button type="submit" class="btn btn-danger btn-sm" data-id="${avaliacao.id}">Negar</button>
        </div>
      </div>`;
  }

  async function atualizarStatus(avaliacaoId, novoStatus) {
    try {
      const response = await fetch(`${avaliacaoEndpoint}/status/${avaliacaoId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: localStorage.getItem("Authorization"),
        },
        body: JSON.stringify({ status: novoStatus }),
      });
  
      if (response.ok) {
        $(`#avaliacao-${avaliacaoId}`).remove();
        console.log(`Status do avaliacao ${avaliacaoId} atualizado para ${novoStatus}.`);
      } else {
        
        console.error("Erro ao atualizar o status do avaliacao:", response.statusText);
      }
    } catch (error) {
      console.error("Erro ao realizar a requisição de atualização:", error);
    }
  }  

getAvaliacao();