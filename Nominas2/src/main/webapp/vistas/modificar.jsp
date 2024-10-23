<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Modificar Empleado</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h1>Modificar Empleado</h1>
	<form action="modificar" method="post">
		<label for="dni">DNI:</label> <input type="text" id="dni" name="dni"
			required> <label for="categoria">Categoría:</label> <input
			type="number" id="categoria" name="categoria" min="1" max="10"
			required> <label for="anyos">Años Trabajados:</label> <input
			type="number" id="anyos" name="anyos" min="0" required> <input
			type="submit" value="Modificar">
	</form>
	  </br>
	<button onclick="history.back()">Volver</button>
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
						<td>
							<!--                       Botón de "Eliminar" que redirige al servlet con el DNI del empleado -->
							<form action="eliminarEmpleado" method="post">
								<input type="hidden" name="dni" value="${empleado.dni}">
								<input type="submit" value="Eliminar">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>