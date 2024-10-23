package com.juanma.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juanma.dao.EmpleadoDAO;
import com.juanma.model.Empleado;



@WebServlet("/editarEmpleado")
public class EditarEmpleadoController extends HttpServlet {
	
	/**
     * Maneja la solicitud GET para cargar el formulario de edición del empleado.
     *
     * @param request La solicitud HTTP que contiene el DNI del empleado a editar.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */

    // Manejo de la solicitud GET para cargar el formulario de edición
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");

        // Obtén el empleado de la base de datos
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado empleado = empleadoDAO.getEmpleadoByDNI(dni);

        // Verifica que el empleado no sea nulo
        if (empleado != null) {
            // Envía el empleado a la página JSP
            request.setAttribute("empleado", empleado);
            request.getRequestDispatcher("vistas/editar.jsp").forward(request, response);
        } else {
            // Manejo de error si el empleado no se encuentra
            request.setAttribute("error", "Empleado no encontrado.");
            request.getRequestDispatcher("vistas/empleados.jsp").forward(request, response);
        }
    }
    
    /**
     * Maneja la solicitud POST para actualizar los datos del empleado.
     *
     * @param request La solicitud HTTP que contiene los datos del empleado a actualizar.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        String sexo = request.getParameter("sexo");
        String categoriaStr = request.getParameter("categoria");
        String anosTrabajadosStr = request.getParameter("anyos");
        String sueldoStr = request.getParameter("sueldo");

        // Verifica si los parámetros necesarios están presentes y no son nulos
        if (categoriaStr == null || anosTrabajadosStr == null) {
            request.setAttribute("error", "Algunos campos necesarios están vacíos.");
            request.getRequestDispatcher("vistas/editar.jsp").forward(request, response);
            return; // Termina la ejecución si hay un error
        }

        // Parsea los valores a tipos numéricos
        int categoria = Integer.parseInt(categoriaStr);
        int anosTrabajados = Integer.parseInt(anosTrabajadosStr);
        Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anosTrabajados);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        boolean actualizado = empleadoDAO.modificarEmpleadoConSueldo(empleado);
        
        if (actualizado) {
            request.setAttribute("success", "Empleado actualizado exitosamente.");
        } else {
            request.setAttribute("error", "Error al actualizar el empleado. Inténtalo de nuevo.");
        }

        // Cargar la lista de empleados
        List<Empleado> empleados = empleadoDAO.getAllEmpleados(); // Obtener todos los empleados
        
        // Establecer los atributos para la solicitud
        request.setAttribute("empleados", empleados);

        // Redirigir a la página de empleados
        request.getRequestDispatcher("vistas/empleados.jsp").forward(request, response);
    }



}