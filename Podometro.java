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
        //Calculo de la longitud de la zancada.
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
     *    horaFin – hora de fin de la caminata
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        //Tiempo de caminata.
        if (horaInicio %100 > horaFin % 100){
            tiempo = tiempo + (horaFin /100 - horaInicio/100 - 1) * 60 + (60 - (horaInicio %100 - horaFin % 100));
        }
        else if(horaInicio %100 <= horaFin % 100){
            tiempo = tiempo + (horaFin /100 - horaInicio/100) + (horaFin %100 - horaInicio % 100);
        }
        //Caminatas nocturnas.       
        if (horaInicio > 2100 | horaInicio < 500){
            caminatasNoche ++;
        }
        //Día de la caminata.
        switch (dia) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables = totalPasosLaborables + pasos;
            break;
            case 6: totalPasosSabado = totalPasosSabado + pasos;
            break;
            case 7: totalPasosDomingo = totalPasosDomingo + pasos;
            break;
        }
        //Distancia recorrida dependiendo del día.
        totalDistanciaSemana = totalPasosLaborables * (longitudZancada / 100);
        totalDistanciaFinSemana = (totalPasosDomingo + totalPasosSabado) * (longitudZancada / 100);
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
        System.out.println ("Altura: "+ altura / 100+ " mtos");
        System.out.println ("Sexo: "+ sexo);
        System.out.println ("Longitud zancada: "+ longitudZancada / 100 + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     */
    public void printEstadísticas() {
        System.out.println ("Estadísticas");
        System.out.println ("***************************");
        System.out.println ("Distancia recorrida toda la semana: "
            +totalDistanciaSemana / 1000+ "Km");
        System.out.println ("Distancia recorrida fin de semana: " +totalDistanciaFinSemana / 1000+ "Km");
        System.out.println ("");
        System.out.println ("Nº pasos días laborables: "+ totalPasosLaborables);
        System.out.println ("Nº pasos SÁBADO: "+ totalPasosSabado);
        System.out.println ("Nº pasos DOMINGO: "+ totalPasosDomingo);
        System.out.println ("Nº caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        System.out.println ("Tiempo total caminado en la semana: " + tiempo /60 + "h " + tiempo  %60 + "min.");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        //Calculo de día con mayor numero de pasos.
        String diamaspasos;
        if (totalPasosLaborables > totalPasosSabado && 
        totalPasosLaborables > totalPasosDomingo){
            return "Laborables";
        }

        else if (totalPasosSabado > totalPasosLaborables && 
        totalPasosSabado > totalPasosDomingo){
            return "Sabado";
        }

        else {
            return "Domingo";
        }
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
