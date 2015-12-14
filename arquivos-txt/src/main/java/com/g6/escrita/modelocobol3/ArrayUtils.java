package com.g6.escrita.modelocobol3;

/**
 * 
 * @author fabianomatias
 * @email fabianormatias@gmail.com
 * @since 30/10/2015
 *
 */
public class ArrayUtils {

	/**
     * <p>Checks if a Array is empty ({}) or null.</p>
     *
     * <pre>
     * ArrayUtils.isEmpty(null)      = true
     * ArrayUtils.isEmpty({})        = true
     * ArrayUtils.isEmpty(" ")       = false
     * ArrayUtils.isEmpty("John")     = false
     * ArrayUtils.isEmpty("  John  ") = false
     * ArrayUtils.isEmpty("{"John", "Mary"}") = false
     * </pre>
     *
     * @param object  the Array to check, may be null
     * @return <code>true</code> if the Array is empty or null
     */
    public static boolean isEmpty(Object[] object) {
        return object == null || object.length == 0;
    }
    
}