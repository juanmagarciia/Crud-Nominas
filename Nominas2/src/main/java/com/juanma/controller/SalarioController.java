package com.juanma.controller;

import com.juanma.dao.EmpleadoDAO;
import com.juanma.model.Empleado;
import com.juanma.model.Nomina;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/salario")
public class SalarioController extends HttpServlet {
    private EmpleadoDAO empleadoDAO;

    /**
     * Inicializa el servlet y crea una instancia del DAO de empleados.
     */
    
    @Override
    public void init() {
        empleadoDAO = new EmpleadoDAO();
    }
    
    /**
     * Maneja la solicitud GET para mostrar el formulario de consulta de salario.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/vistas/salario.jsp").forward(request, response);
    }
    
    /**
     * Maneja la solicitud POST para obtener el salario de un empleado por su DNI.
     *
     * @param request La solicitud HTTP que contiene el DNI del empleado.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String mensaje = "";
        int sueldo = 0;

        sueldo = empleadoDAO.obtenerSalarioPorDNI(dni);
        request.setAttribute("dni", dni);

        if (sueldo > 0) {
            request.setAttribute("sueldo", sueldo);
        } else {
            mensaje = "Empleado no encontrado.";
            request.setAttribute("mensaje", mensaje);
        }

        request.getRequestDispatcher("/vistas/salario.jsp").forward(request, response);
    }
}
