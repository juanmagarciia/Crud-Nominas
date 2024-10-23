package com.juanma.model;

/**
 *
 * @author juanm
 */
public class Nomina {
    /**
     * Tabla de Sueldos
     */
    private static final int SUELDO_BASE[] =
{50000, 70000, 90000, 110000, 130000,
150000, 170000, 190000, 210000, 230000};
    
    
    /**
     * Calcula el sueldo de un empleado en función de su categoría y años de experiencia.
     * El sueldo se calcula sumando al sueldo base correspondiente a la categoría del empleado 
     * un incremento de 5000 por cada año trabajado.
     * 
     * @param empleado El objeto {@link Empleado} del cual se obtiene la categoría y los años de experiencia.
     * @return El sueldo total calculado para el empleado.
     */
    
        private int categoria;
        private int anyos;
        private int sueldo;

        /**
         * Constructor que inicializa una nueva nómina con la categoría y años dados.
         *
         * @param categoria La categoría del empleado.
         * @param anyos     Los años de experiencia del empleado.
         */
        
        public Nomina(int categoria, int anyos) {
          
        }
        /**
         * Constructor por defecto que crea una nómina sin inicializar.
         */

        public Nomina() {
		}
        

        /**
         * Calcula el sueldo total de un empleado en función de su categoría
         * y años de experiencia. El sueldo se calcula sumando el sueldo base
         * correspondiente a la categoría del empleado más un incremento de
         * 5000 por cada año trabajado.
         *
         * @param categoria La categoría del empleado.
         * @param anyos     Los años de experiencia del empleado.
         * @return El sueldo total calculado para el empleado.
         */

		public int calcularSueldo(int categoria,int anyos) {
            int sueldoBase = SUELDO_BASE[categoria - 1]; 
            return sueldoBase + (5000 * anyos);
        }
		
		 /**
	     * Establece el sueldo de la nómina.
	     *
	     * @param sueldo El sueldo a establecer.
	     */

        public void setSueldo(int sueldo) {
            this.sueldo = sueldo;
        }
        

        /**
         * Obtiene el sueldo de la nómina.
         *
         * @return El sueldo actual de la nómina.
         */

        public int getSueldo() {
            return sueldo;
        }
        

        /**
         * Obtiene la categoría de la nómina.
         *
         * @return La categoría del empleado.
         */

        public int getCategoria() {
            return categoria;
        }

        /**
         * Obtiene los años de experiencia de la nómina.
         *
         * @return Los años de experiencia del empleado.
         */
        
        public int getAnyos() {
            return anyos;
        }

    
    
}

