<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Salario de Empleado</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>Consultar Salario de Empleado</h1>

    <form action="salario" method="post">
        <label for="dni">DNI del Empleado:</label>
        <input type="text" id="dni" name="dni" required>
        <button type="submit">Consultar</button>
    </form>

    <c:if test="${not empty sueldo}">
        <h2>Salario del empleado con DNI ${dni} : ${sueldo}</h2>
    </c:if>

    <br>
      </br>
    <button onclick="history.back()">Volver</button>
      </br>
      </br>
    <a href="index.jsp">Volver al Menú Principal</a>
</body>
</html>