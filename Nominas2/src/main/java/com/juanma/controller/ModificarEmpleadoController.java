package com.juanma.controller;

import com.juanma.dao.EmpleadoDAO;
import com.juanma.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/modificar")
public class ModificarEmpleadoController extends HttpServlet {
    private EmpleadoDAO empleadoDAO;
    
    /**
     * Inicializa el servlet y crea una instancia del DAO de empleados.
     */
    @Override
    public void init() {
        empleadoDAO = new EmpleadoDAO();
    }
    /**
     * Maneja la solicitud GET para mostrar el formulario de gestión de empleados.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("vistas/gestionarEmpleados.jsp").forward(request, response);
    }
    
    
    /**
     * Maneja la solicitud POST para buscar empleados por un parámetro dado.
     *
     * @param request La solicitud HTTP que contiene el parámetro de búsqueda.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String busqueda = request.getParameter("busqueda");

        // Buscar empleados usando el parámetro proporcionado
        List<Empleado> empleados = empleadoDAO.buscarEmpleadosPorParametro(busqueda);

        // Pasar la lista de empleados al JSP
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("vistas/gestionarEmpleados.jsp").forward(request, response);
    }
    
}
