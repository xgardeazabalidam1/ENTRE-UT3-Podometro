/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - Xanti Gardeazabal Iribarren - 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private double totalPasosLaborables;
    private double totalPasosSabado;
    private double totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;
    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosSabado = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;  
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == 'M'){
            longitudZancada = altura * ZANCADA_MUJER;
            longitudZancada = Math.floor(longitudZancada);
        }
        else if (sexo == 'H'){
            longitudZancada = altura * ZANCADA_HOMBRE;
            longitudZancada = Math.ceil(longitudZancada);
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        tiempo = horaFin - horaInicio;
        if (horaInicio > 2100){
            caminatasNoche ++;
        }

        switch (dia) {
            case 1:
            break;
            case 2:
            break;
            case 3:
            break;
            case 4:
            break;
            case 5: totalPasosLaborables = totalPasosLaborables + pasos;
            break;
            case 6: totalPasosSabado = totalPasosSabado + pasos;
            break;
            default: totalPasosDomingo = totalPasosDomingo + pasos;
            break;
        }

        totalDistanciaSemana = totalPasosLaborables * tiempo * pasos * 
        longitudZancada;
        totalPasosSabado = totalPasosSabado * tiempo * pasos * 
        longitudZancada;
        totalPasosDomingo = totalPasosDomingo * tiempo * pasos * 
        longitudZancada;
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println ("Configuración del podómetro");
        System.out.println ("***************************");
        System.out.println ("Altura: "+ altura+ " mtos");
        System.out.println ("Sexo: "+ sexo);
        System.out.println ("Longitud zancada: "+longitudZancada+ 
            " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     */
    public void printEstadísticas() {
        String diamaspasos;
        System.out.println ("Estadísticas");
        System.out.println ("***************************");
        System.out.println ("Distancia recorrida toda la semana: "
            +totalDistanciaSemana+ "Km");
        System.out.println ("Distancia recorrida fin de semana: "
            +totalDistanciaFinSemana+ "Km");
        System.out.println ("");
        System.out.println ("Nº pasos días laborables: ");
        System.out.println (": "+totalPasosSabado);
        System.out.println (": "+totalPasosDomingo);
        System.out.println ("Nº caminatas realizadas a partir de las 21h.: ");
        System.out.println ("Tiempo total caminado en la semana: ");
        System.out.println ("Día/s con mas pasos caminados: " );
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String diamaspasos;
        if (totalPasosLaborables > totalPasosSabado && 
        totalPasosLaborables > totalPasosDomingo){
            return "Laborables";
        }

        if (totalPasosSabado > totalPasosLaborables && 
        totalPasosSabado > totalPasosDomingo){
            return "Sabado";
        }

        if (totalPasosDomingo > totalPasosLaborables && 
        totalPasosDomingo > totalPasosSabado){
            return "Domingo";
        }
        return "sabado";
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        sexo = 'M';
        longitudZancada = 0;
        totalPasosSabado = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
