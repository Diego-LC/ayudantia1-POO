import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testMetodo1() {
        assertTrue(Main.esPalindromo("racecar"));
        assertFalse(Main.esPalindromo("hello"));
        assertTrue(Main.esPalindromo("A man a plan a canal Panama"));
        assertTrue(Main.esPalindromo(" "));
    }

    @Test
    public void testMetodo2() {
        double[] notas = {4.0, 3.5, 4.0, 3.0, 2.5};
        double[] notas2 = {5.0, 5.0, 5.0, 5.0, 5.0};
        double[] notas3 = {1.0, 1.0, 1.0, 1.0, 1.0};
        assertEquals(1.0, Main.promedioNotas(notas3), 0.001);
        assertEquals(5.0, Main.promedioNotas(notas2), 0.001);
        assertEquals(3.575, Main.promedioNotas(notas), 0.001);
    }

    @Test
    public void testMetodo3() {
        int[] puntajes1 = {10, 10};
        int[] puntajes2 = {5, 15};
        int[] puntajes3 = {15, 5};
        int[] puntajes4 = {20, 20};
        assertEquals("Empate.", Main.determinarGanador(puntajes1));
        assertEquals("Jugador 2 ganó.", Main.determinarGanador(puntajes2));
        assertEquals("Jugador 1 ganó.", Main.determinarGanador(puntajes3));
        assertEquals("Empate.", Main.determinarGanador(puntajes4));
    }

    @Test
    public void testEsPalabraValida() {
        assertTrue(Main.esPalabraValida("hola"));
        assertFalse(Main.esPalabraValida("hola1"));
        assertFalse(Main.esPalabraValida(""));
        assertFalse(Main.esPalabraValida(" "));
        assertFalse(Main.esPalabraValida("123"));
        assertFalse(Main.esPalabraValida("hola "));
    }
}
