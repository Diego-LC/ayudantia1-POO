package ayudantia;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static final Double[] promedios = new Double[7];
    private static final String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private static final Double[][] tablaTemp = tablaTemperaturas();
    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        System.out.print("\n----Elige una opcion:-----\n");
        System.out.println("1. Mostrar día más caluroso.");
        System.out.println("2. Mostrar día más frío.");
        System.out.println("3. Mostrar hora y día de la temperatura más baja.");
        System.out.println("4. Mostrar hora y día de la temperatura más alta.");
        System.out.println("5. Promedio de la temperatura en la semana.");
        System.out.println("6. Mostar tabla de temperaturas.");
        System.out.println("7. Salir");
        System.out.print("Opcion: ");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        elegirOpcion(opcion);
        sc.close();
    }

    public static void elegirOpcion(int opcion){
        switch (opcion) {
            case 1 -> {
                String[] dia = getDiaMasCaluroso(tablaTemp);
                System.out.println("El dia mas caluroso es: " + dia[0] + " con un promedio de: " + dia[1] + " grados");
            }
            case 2 -> {
                String[] diaFrio = getDiaMasFrio(tablaTemp);
                System.out.println("El dia mas frio es: " + diaFrio[0] + " con un promedio de: " + diaFrio[1] + " grados");
            }
            case 3 -> {
                String[] horaDiaTempMasBaja = getHoraDiaTempMasBaja(tablaTemp);
                System.out.println("La hora y dia con la temperatura mas baja es: " + horaDiaTempMasBaja[0] + " a las " + horaDiaTempMasBaja[1] + " con: " + horaDiaTempMasBaja[2] + " grados");
            }
            case 4 -> {
                String[] horaDiaTempMasAlta = getHoraDiaTempMasAlta(tablaTemp);
                System.out.println("La hora y dia con la temperatura mas alta es: " + horaDiaTempMasAlta[0] + " a las " + horaDiaTempMasAlta[1] + " con: " + horaDiaTempMasAlta[2] + " grados");
            }
            case 5 ->
                    System.out.println("El promedio de la temperatura en la semana es: " + getPromedioTempSemana(tablaTemp));
            case 6 -> mostarTabla();
            case 7 -> System.out.println("Salir");
            default -> System.out.println("Opcion no valida");
        }
        if(opcion != 7){menu();}
    }

    public static Double[][] tablaTemperaturas(){
        Double[][] tabla = new Double[7][24];
        Random r = new Random();
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 24; j++){
                // Truncamos a 1 decimal
                tabla[i][j] = Math.floor((r.nextDouble() * 30) * 10) / 10;
            }
        }
        return tabla;
    }

    public static Double getPromedioTemp(Double[] temperaturas){
        Double promedio = 0.0;
        for(int i = 0; i < temperaturas.length; i++){
            promedio += temperaturas[i];
        }
        return promedio / temperaturas.length;
    }

    public static String[] getDiaMasCaluroso(Double[][] tablaTemp){
        if (tablaTemp == null || tablaTemp[0].length == 0){
            return null;
        }
        for(int i = 0; i < 7; i++){
            promedios[i] = getPromedioTemp(tablaTemp[i]);
        }
        Double mayor = promedios[0];
        int index = 0;
        for(int i = 0; i < promedios.length; i++){
            if(promedios[i] > mayor){
                mayor = promedios[i];
                index = i;
            }
        }
        String[] diaMasCaluroso = {dias[index], mayor.toString()};
        return diaMasCaluroso;
    }

    public static String[] getDiaMasFrio(Double[][] tablaTemp){
        for(int i = 0; i < tablaTemp.length; i++){
            promedios[i] = getPromedioTemp(tablaTemp[i]);
        }
        Double menor = promedios[0];
        int index = 0;
        for(int i = 0; i < promedios.length; i++){
            if(promedios[i] < menor){
                menor = promedios[i];
                index = i;
            }
        }
        String[] diaMasFrio = {dias[index], menor.toString()};
        return diaMasFrio;
    }

    public static String[] getHoraDiaTempMasBaja(Double[][] tablaTemp){
        Double menor = tablaTemp[0][0];
        int indexDia = 0;
        int indexHora = 0;
        for(int i = 0; i < tablaTemp.length; i++){
            for(int j = 0; j < tablaTemp[i].length; j++){
                if(tablaTemp[i][j] < menor){
                    menor = tablaTemp[i][j];
                    indexDia = i;
                    indexHora = j;
                }
            }
        }
        String[] horaDiaTempMasBaja = {dias[indexDia], String.valueOf(indexHora), menor.toString()};
        return horaDiaTempMasBaja;
    }

    public static String[] getHoraDiaTempMasAlta(Double[][] tablaTemp){
        Double mayor = tablaTemp[0][0];
        int indexDia = 0;
        int indexHora = 0;
        for(int i = 0; i < tablaTemp.length; i++){
            for(int j = 0; j < tablaTemp[i].length; j++){
                if(tablaTemp[i][j] > mayor){
                    mayor = tablaTemp[i][j];
                    indexDia = i;
                    indexHora = j;
                }
            }
        }
        String[] horaDiaTempMasAlta = {dias[indexDia], String.valueOf(indexHora), mayor.toString()};
        return horaDiaTempMasAlta;
    }

    public static Double getPromedioTempSemana(Double[][] tablaTemp){
        if (tablaTemp == null || tablaTemp.length ==0){
            return null;
        }
        Double[] promedio = new Double[tablaTemp.length*tablaTemp[0].length];
        for(int i = 0; i < tablaTemp.length; i++){
            System.arraycopy(tablaTemp[i], 0, promedio, i * tablaTemp[i].length, tablaTemp[i].length);
        }
        if (promedio[0] == null){
            return null;
        }
        return getPromedioTemp(promedio);
    }

    public static void mostarTabla(){
        System.out.println("Tabla de temperaturas");
        for(int i = 0; i < tablaTemp.length; i++){
            System.out.print(dias[i] + ": ");
            for(int j = 0; j < tablaTemp[i].length; j++){
                System.out.print(tablaTemp[i][j] + " ");
            }
            System.out.println();
        }
    }

}