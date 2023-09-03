package ayudantia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MainTest {
    private static final Double[][] tablaTemp = {{10.0, 20.0, 30.0}, {5.0, 15.0, 25.0}, {0.0, 10.0, 20.0}};
    @BeforeAll
    public static void setUp() {
        System.out.println("Iniciando tests...");
    }
    @Test
    public void testGetHoraDiaTempMasBaja() {
        String[] resultEsperado = {"Miércoles", "0", "0.0"};
        Assertions.assertArrayEquals(resultEsperado, Main.getHoraDiaTempMasBaja(tablaTemp));
    }

    @Test
    public void testGetHoraDiaTempMasAlta() {
        String[] resultEsperado = {"Lunes", "2", "30.0"};
        Assertions.assertArrayEquals(resultEsperado, Main.getHoraDiaTempMasAlta(tablaTemp));
    }

    @Test
    public void testGetPromedioTempSemana() {
        Double resultEsperado = 15.0;
        Assertions.assertEquals(resultEsperado, Main.getPromedioTempSemana(tablaTemp));

        // Caso extremo 1: Tabla de temperaturas vacía
        Double[][] tablaTemp1 = new Double[0][0];
        Double resultado1 = Main.getPromedioTempSemana(tablaTemp1);
        Assertions.assertNull(resultado1);

        // Caso extremo 2: Matriz de 1x1 con un solo valor nulo
        Double[][] tablaTemp2 = {{null}};
        Double resultado2 = Main.getPromedioTempSemana(tablaTemp2);
        Assertions.assertNull(resultado2);

        // Caso extremo 3: Matriz de 1x1 con un valor válido
        Double[][] tablaTemp3 = {{25.0}};
        Double resultado3 = Main.getPromedioTempSemana(tablaTemp3);
        Assertions.assertEquals(25.0, resultado3, 0.001); // Tolerancia de 0.001 para la comparación de valores de punto flotante

        // Caso extremo 4: Matriz de 3x3 con valores nulos
        Double[][] tablaTemp4 = {
            {null, null, null},
            {null, null, null},
            {null, null, null}
        };
        Double resultado4 = Main.getPromedioTempSemana(tablaTemp4);
        Assertions.assertEquals(null, resultado4);

        // Caso extremo 5: Matriz de 3x3 con valores válidos
        Double[][] tablaTemp5 = {
            {20.0, 25.0, 30.0},//25
            {15.0, 18.0, 22.0},//18.333
            {28.0, 32.0, 35.0}
        };
        Double resultado5 = Main.getPromedioTempSemana(tablaTemp5);
        Assertions.assertEquals(25, resultado5, 0.001); // Promedio calculado manualmente con 3 decimales de precisión
    }
}


