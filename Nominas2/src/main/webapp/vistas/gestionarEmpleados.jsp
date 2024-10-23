<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Buscar Empleado</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>Buscar Empleado</h1>

    <form action="modificar" method="post">
        <label for="busqueda">Buscar por cualquier parámetro (DNI, Nombre, Sexo, Categoría, Años):</label>
        <input type="text" id="busqueda" name="busqueda" required>
        <button type="submit">Buscar</button>
    </form>

    <!-- Mostrar los datos de los empleados encontrados -->
    <c:if test="${not empty empleados}">
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
                <c:forEach var="empleado" items="${empleados}">
                    <tr>
                        <td>${empleado.nombre}</td>
                        <td>${empleado.dni}</td>
                        <td>${empleado.sexo}</td>
                        <td>${empleado.categoria}</td>
                        <td>${empleado.anyos}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty empleados}">
        <p>No se encontraron empleados con los parámetros de búsqueda proporcionados.</p>
    </c:if>
<button onclick="history.back()">Volver</button>
</br>
</br>
    <a href="index.jsp">Volver al Menú Principal</a>
</body>
</html>