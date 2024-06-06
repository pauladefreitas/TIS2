  document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('form');
  
  
    form.addEventListener('submit', async function (event) {
      event.preventDefault();
  
  
      const service = document.getElementById('servico').value;
      const date = document.getElementById('data').value;
      const score = document.getElementById('pontuacao').value;
      const comment = document.getElementById('comentario').value;
  
      const evaluationData = {
        user: { id: 14 },
        serviceName: service,
        evaluationDate: date,
        nota: parseInt(score),
        comment: comment,
        status: "PENDENTE"
      };
  
      console.log('Enviando avaliação:', evaluationData);
      const json = JSON.stringify(evaluationData);
      await fetch('http://localhost:8080/avaliacao', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: json
      }).then(async response => {
        if (!response.ok) {
          throw new Error('Erro ao enviar avaliação. Por favor, tente novamente mais tarde.');
        }
        console.log('Avaliação enviada com sucesso:', response);
        const mainElement = document.querySelector('main');
        mainElement.innerHTML = '';
  
  
        const successContainer = document.createElement('div');
        successContainer.className = 'success-container';
  
  
        const successMessage = document.createElement('p');
        successMessage.textContent = 'Avaliação enviada com sucesso!';
  
  
        const backButton = document.createElement('button');
        backButton.className = 'back-button';
        backButton.textContent = 'Voltar';
        backButton.addEventListener('click', function () {
          window.location.reload();
        });
  
  
        successContainer.appendChild(successMessage);
        successContainer.appendChild(backButton);
        mainElement.appendChild(successContainer);
      }).catch(error => {
        console.error('Erro ao enviar avaliação:', error);
        const mainElement = document.querySelector('main');
        mainElement.innerHTML = '';
  
  
        const errorContainer = document.createElement('div');
        errorContainer.className = 'error-container';
  
  
        const errorMessage = document.createElement('p');
        errorMessage.textContent = 'Erro ao enviar avaliação. Por favor, tente novamente mais tarde.';
  
  
        const backButton = document.createElement('button');
        backButton.className = 'back-button';
        backButton.textContent = 'Voltar';
        backButton.addEventListener('click', function () {
          window.location.reload();
        });
  
  
        errorContainer.appendChild(errorMessage);
        errorContainer.appendChild(backButton);
        mainElement.appendChild(errorContainer);
      });
    });
  });
  
  
  