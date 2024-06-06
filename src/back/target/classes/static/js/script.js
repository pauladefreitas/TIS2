document.addEventListener("DOMContentLoaded", function() {
  const calendar = document.getElementById('calendar');
  const popup = document.getElementById('popup');
  const popupContent = document.getElementById('popup-content');
  let selectedDate = null;
  let selectedTime = null;

  function renderCalendar() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth();
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    const firstDayOfMonth = new Date(year, month, 1).getDay();

    const monthNames = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];

    const calendarHeader = `<div class="calendar-header">${monthNames[month]} ${year}</div>`;
    let calendarBody = '<div class="calendar-days">';

    for (let i = 0; i < firstDayOfMonth; i++) {
      calendarBody += '<div class="calendar-day"></div>';
    }

    for (let i = 1; i <= daysInMonth; i++) {
      const className = i === now.getDate() && month === now.getMonth() ? 'calendar-day today' : 'calendar-day';
      calendarBody += `<div class="${className}" data-date="${year}-${month + 1}-${i}">${i}</div>`;
    }

    calendarBody += '</div>';
    calendar.innerHTML = calendarHeader + calendarBody;

    const calendarDays = document.querySelectorAll('.calendar-day');
    calendarDays.forEach(day => {
      day.addEventListener('click', showPopup);
    });
  }

  function showPopup(event) {
    const clickedDate = event.target.getAttribute('data-date');
    selectedDate = new Date(clickedDate);

    let popupHTML = '<div class="popup-header">Selecione um horário</div>';
    popupHTML += '<div class="popup-times">';

    const startTime = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), selectedDate.getDate(), 8, 0);
    const endTime = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), selectedDate.getDate(), 19, 30);

    while (startTime <= endTime) {
      const timeString = startTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
      popupHTML += `<div class="popup-time" data-time="${timeString}">${timeString}</div>`;
      startTime.setMinutes(startTime.getMinutes() + 10); // Incrementa 10 minutos
    }

    popupHTML += '</div>';
    popupHTML += '<button id="btn-agendar">Agendar</button>';
    popupContent.innerHTML = popupHTML;

    const times = document.querySelectorAll('.popup-time');
    times.forEach(time => {
      time.addEventListener('click', selectTime);
    });

    const btnAgendar = document.getElementById('btn-agendar');
    btnAgendar.addEventListener('click', agendar);

    popup.style.display = 'flex';
  }

  function selectTime(event) {
    selectedTime = event.target.getAttribute('data-time');
    console.log('Horário selecionado:', selectedTime);
  }

async function agendar() {
    if (!selectedDate || !selectedTime) {
        alert('Por favor, selecione uma data e um horário.');
        return;
    }
    const service = document.getElementById('floatingSelect').value;
    const requestData = {
        approved: false,
        clientEmail: "jotaa45@outlook.com", // Substitua pelo email do cliente
        dataAgendamento: selectedDate.toISOString().split('T')[0], // Formato YYYY-MM-DD
        horaAgendamento: selectedTime,
        managerId: "defaultManager",
        status: "PENDENTE",
        tipoServico: service,
        user: { id: 14 } 
    };

    try {
        console.log('Request data:', requestData); // Log request data for debugging

        const response = await fetch('http://localhost:8080/agendamento', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        console.log('Response status:', response.status); // Log response status for debugging
        if (response.ok) {
            const data = await response.json();
            console.log('Agendamento criado:', data);
            alert('Agendamento realizado com sucesso!');
        }
    } catch (error) {
        console.error('Erro ao criar agendamento:', error);

    }

    closePopup();
}




  function closePopup() {
    popup.style.display = 'none';
    popupContent.innerHTML = '<button>fechar</button>';
  }

  renderCalendar();
});

