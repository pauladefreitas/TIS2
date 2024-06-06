<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $servername = "localhost"; 
    $username = "seu_usuario"; 
    $password = "sua_senha"; 
    $dbname = "clinicaspa"; 
    
    $conn = new mysqli($servername, $username, $password, $dbname);

  
    if ($conn->connect_error) {
        die("Erro de conexão com o banco de dados: " . $conn->connect_error);
    }

    $sql = "SELECT id_avaliacao, id_cliente, id_servico, data_avaliacao, pontuacao, comentario, id_funcionario, recomendacao FROM avaliacoes";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
      
        $avaliacoes = array();

        while($row = $result->fetch_assoc()) {
            $avaliacoes[] = $row;
        }

        echo json_encode($avaliacoes);
    } else {
        echo "Nenhuma avaliação encontrada.";
    }

    $conn->close();
} else {
    echo "Erro: O formulário não foi submetido corretamente.";
}
?>
