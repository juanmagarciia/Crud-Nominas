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

@WebServlet("/crear")
public class CrearEmpleadoController extends HttpServlet {
    private EmpleadoDAO empleadoDAO;

    /**
     * Inicializa el servlet y crea una instancia del DAO de empleados.
     */
    @Override
    public void init() {
        empleadoDAO = new EmpleadoDAO();
    }
    
    /**
     * Maneja la solicitud GET para mostrar el formulario de creación de empleado.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Muestra el formulario para crear empleado
        request.getRequestDispatcher("vistas/crear.jsp").forward(request, response);
    }
    
    /**
     * Maneja la solicitud POST para crear un nuevo empleado.
     *
     * @param request La solicitud HTTP que contiene los datos del nuevo empleado.
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
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anosTrabajados = Integer.parseInt(request.getParameter("anyos"));
        

        Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anosTrabajados);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        boolean creado = empleadoDAO.crearEmpleado(empleado);

        if (creado) {
            // Establece un mensaje de éxito
            request.setAttribute("success", "Empleado creado exitosamente.");
        } else {
            // Establece un mensaje de error
            request.setAttribute("error", "Error al crear el empleado. Inténtalo de nuevo.");
        }

     // Cargar la lista de empleados
        List<Empleado> empleados = empleadoDAO.getAllEmpleados(); // Obtener todos los empleados

        // Establecer los atributos para la solicitud
        request.setAttribute("empleados", empleados);
 
        // Redirigir a la página de empleados
        request.getRequestDispatcher("vistas/empleados.jsp").forward(request, response);
    }


}