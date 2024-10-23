package com.juanma.model;

/**
 *
 * @author juanm
 */
public class Empleado{
  private int id;
	 private String nombre;
	    private String dni;
	    private String sexo;
	    private int categoria;
	    private int anyos;
	    

	    /**
	     * Constructor que inicializa un nuevo empleado con los valores dados.
	     *
	     * @param nombre   El nombre del empleado.
	     * @param dni      El DNI del empleado.
	     * @param sexo     El sexo del empleado.
	     * @param categoria La categoría del empleado.
	     * @param anyos    Los años de experiencia del empleado.
	     */
	    
	    public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) {
	    	
	        this.nombre = nombre;
	        this.dni = dni;
	        this.sexo = sexo;
	        this.categoria = categoria;
	        this.anyos = anyos;
	    }

	    /**
	     * Constructor por defecto que crea un empleado sin inicializar.
	     */
	    public Empleado() {
	    }

	    /**
	     * Obtiene el nombre del empleado.
	     *
	     * @return El nombre del empleado.
	     */
	    public String getNombre() {
	        return nombre;
	    }

	    /**
	     * Establece el nombre del empleado.
	     *
	     * @param nombre El nombre del empleado.
	     */
	    
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    /**
	     * Obtiene el DNI del empleado.
	     *
	     * @return El DNI del empleado.
	     */
	    
	    public String getDni() {
	        return dni;
	    }

	    /**
	     * Establece el DNI del empleado.
	     *
	     * @param dni El DNI del empleado.
	     */
	    
	    public void setDni(String dni) {
	        this.dni = dni;
	    }

	    /**
	     * Obtiene el sexo del empleado.
	     *
	     * @return El sexo del empleado.
	     */
	    
	    public String getSexo() {
	        return sexo;
	    }
	    /**
	     * Establece el sexo del empleado.
	     *
	     * @param sexo El sexo del empleado.
	     */
	    
	    public void setSexo(String sexo) {
	        this.sexo = sexo;
	    }
	    
	    /**
	     * Obtiene la categoría del empleado.
	     *
	     * @return La categoría del empleado.
	     */

	    public int getCategoria() {
	        return categoria;
	    }

	    /**
	     * Establece la categoría del empleado.
	     *
	     * @param i La nueva categoría del empleado.
	     */
	    
	    public void setCategoria(int i) {
	        this.categoria = i;
	    }

	    /**
	     * Obtiene los años de experiencia del empleado.
	     *
	     * @return Los años de experiencia del empleado.
	     */
	    
	    public int getAnyos() {
	        return anyos;
	    }

	    /**
	     * Establece los años de experiencia del empleado.
	     *
	     * @param anyos Los años de experiencia del empleado.
	     */
	    public void setAnosTrabajados(int anyos) {
	        this.anyos = anyos;
	    }
	    
	    /**
		 * Obtiene el ID del producto.
		 * 
		 * @return El ID del producto.
		 */

		public int getId() {
			return id;
		}

		/**
		 * Establece el ID del producto.
		 * 
		 * @param id El nuevo ID del producto.
		 */

		public void setId(int id) {
			this.id = id;
		}

		/**
		 * Obtiene el nombre del producto.
		 * 
		 * @return El nombre del producto.
		 */
}
