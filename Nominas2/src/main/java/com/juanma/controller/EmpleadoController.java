package com.juanma.controller;
import com.juanma.dao.EmpleadoDAO;
import com.juanma.model.Empleado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoController extends HttpServlet {
    private EmpleadoDAO empleadoDAO;

    /**
     * Inicializa el servlet y crea una instancia del DAO de empleados.
     */
    @Override
    public void init() {
        empleadoDAO = new EmpleadoDAO();
    }
    
    /**
     * Maneja la solicitud GET para obtener y mostrar la lista de empleados.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empleado> empleados = empleadoDAO.getAllEmpleados();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/vistas/empleados.jsp").forward(request, response);
        
        
    }
    
    
}
