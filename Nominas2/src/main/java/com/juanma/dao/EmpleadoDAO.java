package com.juanma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.juanma.conexion.Conexion;
import com.juanma.model.Empleado;
import com.juanma.model.Nomina;


public class EmpleadoDAO {
	
	/**
     * Obtiene el salario de un empleado a partir de su DNI.
     *
     * @param dni El DNI del empleado cuyo salario se desea obtener.
     * @return El salario del empleado, o 0 si no se encuentra.
     */
	
	public int obtenerSalarioPorDNI(String dni) {
	     String SELECT_EMPLEADO_POR_DNI = "SELECT n.sueldo FROM nominas n  JOIN empleados e ON n.dni = e.dni  WHERE e.dni = ?";
	        int sueldo = 0;

	        try (Connection connection = Conexion.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLEADO_POR_DNI)) {
	             
	            preparedStatement.setString(1, dni);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                sueldo = rs.getInt("sueldo");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return sueldo;
	    }
	/**
     * Obtiene una lista de todos los empleados de la base de datos.
     *
     * @return Una lista de objetos Empleado.
     */
	
	public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                String sexo = rs.getString("sexo");
                int categoria = rs.getInt("categoria");
                int anyos = rs.getInt("anyos");
                empleados.add(new Empleado(nombre, dni, sexo, categoria, anyos));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
	
	/**
     * Busca empleados que coincidan con un parámetro dado en DNI, nombre, sexo, categoría o años.
     *
     * @param parametro El parámetro de búsqueda que puede ser DNI, nombre, sexo, categoría o años.
     * @return Una lista de objetos Empleado que coinciden con el parámetro.
     */
	
	public List<Empleado> buscarEmpleadosPorParametro(String parametro) {
	    List<Empleado> empleados = new ArrayList<>();
	    String sql = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados WHERE dni = ? OR nombre = ? OR sexo = ? OR categoria = ? OR anyos = ?";

	    try (Connection conn = Conexion.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        // Asumimos que el parámetro es de tipo String
	        stmt.setString(1, parametro);  // Para DNI
	        stmt.setString(2, parametro);  // Para nombre
	        stmt.setString(3, parametro);  // Para sexo

	        // Si el parámetro es un número (por ejemplo, para categoría o años)
	        try {
	            int intParametro = Integer.parseInt(parametro);
	            stmt.setInt(4, intParametro);  // Para categoría
	            stmt.setInt(5, intParametro);  // Para años
	        } catch (NumberFormatException e) {
	            stmt.setNull(4, java.sql.Types.INTEGER);  // Si no es un entero, establece null
	            stmt.setNull(5, java.sql.Types.INTEGER);  // Si no es un entero, establece null
	        }

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	
	            String nombre = rs.getString("nombre");
	            String dni = rs.getString("dni");
	            String sexo = rs.getString("sexo");
	            int categoria = rs.getInt("categoria");
	            int anyos = rs.getInt("anyos");
	            empleados.add(new Empleado(nombre, dni, sexo, categoria, anyos));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return empleados;
	}
	
	 /**
     * Obtiene un empleado a partir de su DNI.
     *
     * @param dni El DNI del empleado a buscar.
     * @return El objeto Empleado, o null si no se encuentra.
     */
	
    public Empleado getEmpleadoByDNI(String dni) {
        Empleado empleado = null;
        String sql = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados WHERE dni = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	
                String nombre = rs.getString("nombre");
                String sexo = rs.getString("sexo");
                int categoria = rs.getInt("categoria");
                int anyos = rs.getInt("anyos");
                empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }
    
    /**
     * Busca empleados que contengan el término de búsqueda en su nombre o DNI.
     *
     * @param busqueda El término de búsqueda para encontrar empleados.
     * @return Una lista de objetos Empleado que coinciden con la búsqueda.
     */
    
    public List<Empleado> buscarEmpleados(String busqueda) {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados WHERE nombre LIKE ? OR dni LIKE ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Agregar % para búsqueda parcial
            String likeBusqueda = "%" + busqueda + "%";
            stmt.setString(1, likeBusqueda);
            stmt.setString(2, likeBusqueda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                int anyos = rs.getInt("anyos");
                empleados.add(new Empleado());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
    
    /**
     * Crea un nuevo empleado en la base de datos y su nómina asociada.
     *
     * @param empleado El objeto Empleado a crear.
     * @return true si la creación fue exitosa, false en caso contrario.
     */
    
 // Crear empleado y devolver si fue exitoso
    public boolean crearEmpleado(Empleado empleado) {
        String sqlEmpleado = "INSERT INTO empleados (dni,nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
        String sqlNomina = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";
        Nomina nomina = new Nomina();
        
        System.out.println("VAmos a crear un empleado..");

        try (Connection conn = Conexion.getConnection()) {
            // Iniciar una transacción
            conn.setAutoCommit(false);
            String dni = empleado.getDni();

            try (PreparedStatement stmtEmpleado = conn.prepareStatement(sqlEmpleado, Statement.RETURN_GENERATED_KEYS)) {
            	stmtEmpleado.setString(1, dni);
            	stmtEmpleado.setString(2, empleado.getNombre());
                stmtEmpleado.setString(3, empleado.getSexo());
                stmtEmpleado.setInt(4, empleado.getCategoria());
                stmtEmpleado.setInt(5, empleado.getAnyos());

                // Ejecutar la inserción del empleado
                int rowsAffected = stmtEmpleado.executeUpdate();
                System.out.println(rowsAffected);

                // Comprobar si se ha insertado al menos un empleado
                if (rowsAffected > 0) {
                    // Obtener el ID generado del empleado
                        // Ahora insertar el salario en la tabla de nóminas
                        try (PreparedStatement stmtNomina = conn.prepareStatement(sqlNomina)) {
                            stmtNomina.setString(1, dni); // Asignar el dni del empleado creado
                            int sueldo = nomina.calcularSueldo(empleado.getCategoria(), empleado.getAnyos());
                            System.out.println(sueldo);
                            stmtNomina.setInt(2, sueldo); // Establecer
                            int filas = stmtNomina.executeUpdate();
                            System.out.println(filas);
                        } catch (SQLException e) {
                        	System.out.println("QUE PASA");
                    }

                    // Hacer commit de la transacción
                    conn.commit();
                    return true; // Inserción exitosa
                } else {
                    conn.rollback(); // Revertir en caso de que no se haya insertado
                    return false; // No se pudo insertar el empleado
                }
            } catch (SQLException e) {
                conn.rollback(); // Si hay un error, revertir la transacción
                e.printStackTrace();
                return false; // Error en la inserción
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error al obtener conexión
        }
    }
    
    /**
     * Actualiza los datos de un empleado en la base de datos.
     *
     * @param empleado El objeto Empleado con los nuevos datos.
     */
    
    // Método para actualizar el empleado 
    public void updateEmpleado(Empleado empleado) {
        String sql = "UPDATE empleados SET categoria = ?, anyos = ? WHERE dni = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empleado.getCategoria());
            stmt.setInt(2, empleado.getAnyos());
            stmt.setString(3, empleado.getDni());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Modifica un empleado y su sueldo asociado en la base de datos.
     *
     * @param empleado El objeto Empleado con los nuevos datos y sueldo.
     * @return true si la modificación fue exitosa, false en caso contrario.
     */
    
 // Modificar empleado y devolver si fue exitoso
    public boolean modificarEmpleadoConSueldo(Empleado empleado) {
        // Asegurarse de que el DNI esté presente
        String sqlEmpleado = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
        String sqlNomina = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
        Nomina nomina = new Nomina();
        try (Connection conn = Conexion.getConnection()) {
            // Iniciar una transacción
            conn.setAutoCommit(false);

            try (PreparedStatement stmtEmpleado = conn.prepareStatement(sqlEmpleado);
                    PreparedStatement stmtNomina = conn.prepareStatement(sqlNomina)) {

                // Actualizar los datos del empleado
                System.out.println("Actualizando empleado...");
                stmtEmpleado.setString(1, empleado.getNombre()); // Primero el nombre
                stmtEmpleado.setString(2, empleado.getSexo());
                stmtEmpleado.setInt(3, empleado.getCategoria());
                stmtEmpleado.setInt(4, empleado.getAnyos());
                stmtEmpleado.setString(5, empleado.getDni()); // Último el DNI (WHERE)

                int filasActualizadasEmpleado = stmtEmpleado.executeUpdate();
                System.out.println("Filas afectadas en empleados: " + filasActualizadasEmpleado);

                // Actualizar el sueldo en la tabla de nóminas
                System.out.println("Actualizando sueldo...");
                stmtNomina.setInt(1, nomina.calcularSueldo(empleado.getCategoria(), empleado.getAnyos())); // Asegúrate                                                                                                    // es
                                                                                                                        // double
                stmtNomina.setString(2, empleado.getDni()); // Usamos el DNI directamente aquí

                int filasActualizadasNomina = stmtNomina.executeUpdate();
                System.out.println("Filas afectadas en nominas: " + filasActualizadasNomina);

                // Verificar si ambas consultas afectaron al menos una fila
                if (filasActualizadasEmpleado > 0 && filasActualizadasNomina > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback(); // Si no se afectaron filas, revertir la transacción
                    System.out.println("Rollback: No se encontraron registros a actualizar.");
                    return false;
                }

            } catch (SQLException e) {
                conn.rollback(); // Si hay un error, revertir la transacción
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

