import java.util.Random;

public class NeuronaSigmoide {

    private final int numeroDeEntradas;
    private final int numeroDePesos;
    private double sesgo;
    private final double[] pesos;

    public NeuronaSigmoide(int numeroDeEntradas) {
        this.numeroDeEntradas = numeroDeEntradas;
        numeroDePesos = numeroDeEntradas;
        pesos = new double[numeroDePesos];
        Random random = new Random();
        for (int i = 0; i < numeroDePesos; i++) {
            pesos[i] = random.nextDouble() - 0.5;
        }
        sesgo = (random.nextDouble() - 0.5);
    }

    private double getSumaPonderada(double[] entradas) {
        if (entradas.length != numeroDeEntradas) {
            throw new IllegalArgumentException(
                    "Número de entradas inválido para la neurona."
            );
        }
        double sumaPonderada = 0;
        for (int i = 0; i < entradas.length; i++) {
            sumaPonderada += entradas[i] * pesos[i];
        }
        return sumaPonderada + sesgo;
    }

    private double getSigmoide(double sumaPonderada) {
        double EXP_N = Math.exp(sumaPonderada);
        return EXP_N / (1 + EXP_N);
    }

    public double getSalida(double[] entradas) {
        double sumaPonderada = getSumaPonderada(entradas);
        return getSigmoide(sumaPonderada);
    }

    public double getErrorCuadratico(double resultadoObjetivo, double salidaObtenida) {
        return Math.pow(salidaObtenida - resultadoObjetivo, 2) * (0.5);
    }

    public double getDiferencialError(double resultadoObjetivo, double salidaObtenida) {
        return (salidaObtenida - resultadoObjetivo);
    }

    public double getPeso(int i) {
        return pesos[i];
    }

    public void setPeso(double peso, int i) {
        pesos[i] = peso;
    }

    public double getSesgo() {
        return sesgo;
    }

    public void setSesgo(double sesgo) {
        this.sesgo = sesgo;
    }

}
