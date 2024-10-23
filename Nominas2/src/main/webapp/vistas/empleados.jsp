<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Empleados</title>
    <link rel="stylesheet" type="text/css" href="style.css">
<!-- Incluye tu archivo CSS si es necesario -->
</head>
<body>
   
   <!-- Mostrar mensajes de error o éxito -->
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <c:if test="${not empty success}">
        <div style="color: green;">${success}</div>
    </c:if>
    <table border="1">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>DNI</th>
                <th>Sexo</th>
                <th>Categoría</th>
                <th>Años Trabajados</th>
                
            </tr>
        </thead>
        <tbody>
            <!-- Iterar sobre la lista de empleados -->
            <c:forEach var="empleado" items="${empleados}">
                <tr>
                    <td>${empleado.nombre}</td>
                    <td>${empleado.dni}</td>
                    <td>${empleado.sexo}</td>
                    <td>${empleado.categoria}</td>
                    <td>${empleado.anyos}</td>
                    <td>
<!--                         Botón de "Editar" que redirige al servlet con el DNI del empleado -->
                        <form action="editarEmpleado" method="get"> 
                            <input type="hidden" name="dni" value="${empleado.dni}">
                            <input type="submit" value="Editar">
                        </form>
                    </td>
                       </tr>
            </c:forEach>
        </tbody>
    </table>
    
    </br>
     <!-- Formulario para agregar un nuevo empleado -->
    <h2>Agregar Empleado</h2>
    <form action="crear" method="post">
        <input type="hidden" name="redirectPage" value="crear.jsp" /> <label
            for="nombre">Nombre:</label> <input type="text" id="nombre"
            name="nombre" required> <label for="dni">DNI:</label> <input
            type="text" id="dni" name="dni" required> <label for="sexo">Sexo:</label>
        <select id="sexo" name="sexo" required>
            <option value="M">Masculino</option>
            <option value="F">Femenino</option>
        </select> <label for="categoria">Categoría:</label> <input type="text"
            id="categoria" name="categoria" required> <label
            for="anyos">Años Trabajados:</label> <input type="number"
            id="anyos" name="anyos" required> 

        <button type="submit">Crear Empleado</button>
    </form>
      </br>
    <button onclick="history.back()">Volver</button>
      </br>
      </br>
    <a href="index.jsp">Volver al menú principal</a>
</body>
</html>