cadastro.addEventListener('click', signup);

async function signup(event) {
    event.preventDefault(); // Prevent the default form submission behavior
    
    let username = document.getElementById("inome").value;
    let password = document.getElementById("isenha").value;
    let email = document.getElementById("iemail").value;

    console.log(username, password, email);

    const response = await fetch("http://localhost:8080/user", {
        method: "POST",
        headers: new Headers({
            "Content-Type": "application/json; charset=utf8",
        }),
        body: JSON.stringify({
            username: username,
            password: password,
            email: email,
            role: "user"
        }),
    });

    if (!response.ok) {
        throw new Error('Erro ao cadastrar. Por favor, tente novamente mais tarde.');
    }

    window.location.href = '/login';
}
