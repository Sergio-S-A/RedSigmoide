public class Main {


    public static void main(String[] args) {

        double[][] entradas = {
                {12, 7},   // 12 > 7 → 1
                {3, 9},    // 3 < 9 → 0
                {8, 8},    // 8 == 8 → 0
                {14, 2},   // 14 > 2 → 1
                {5, 11},   // 5 < 11 → 0
                {20, 19},  // 20 > 19 → 1
                {7, 15},   // 7 < 15 → 0
                {13, 13},  // 13 == 13 → 0
                {9, 4},    // 9 > 4 → 1
                {16, 30}   // 16 < 30 → 0
        };

        int[] objetivos = {
                1, // {12, 7}
                0, // {3, 9}
                0, // {8, 8}
                1, // {14, 2}
                0, // {5, 11}
                1, // {20, 19}
                0, // {7, 15}
                0, // {13, 13}
                1, // {9, 4}
                0  // {16, 30}
        };

        NeuronaSigmoide nsA = new NeuronaSigmoide(2);
        NeuronaSigmoide nsB = new NeuronaSigmoide(2);
        NeuronaSigmoide nsC = new NeuronaSigmoide(2);


        double diferencialDeErrorCuadratico;
        double diferencialSalidaC;
        double deltaSalidaC;

        double gradientePesoAC;
        double gradientePesoBC;
        double gradienteSesgoC;

        double pesoAC;
        double pesoBC;

        // Calculo de gradientes para nsA y nsB
        double difenrencialSalidaA;
        double difenrencialSalidaB;
        double x1;
        double x2;

        double gradientePesoAX1;
        double gradientePesoAX2;
        double gradienteSesgoA;

        double gradientePesoBX1;
        double gradientePesoBX2;
        double gradienteSesgoB;

        //Entrenamiento
        int epocas = 100000;
        double tasaDeAprendisaje = 0.05;
        double salidaA = 0;
        double salidaB = 0;
        double salidaC = 0;
        for (int i = 0; i < epocas; i++){
            for(int j = 0; j < entradas.length; j++){
                salidaA = nsA.getSalida(entradas[j]);
                salidaB = nsB.getSalida(entradas[j]);
                salidaC = nsC.getSalida(new double[]{salidaA, salidaB});


                // Calculo de gradientes para nsC
                diferencialDeErrorCuadratico = salidaC - objetivos[j];
                diferencialSalidaC = salidaC * (1- salidaC);
                deltaSalidaC = diferencialDeErrorCuadratico * diferencialSalidaC;

                gradientePesoAC = deltaSalidaC * salidaA;
                gradientePesoBC = deltaSalidaC * salidaB;
                gradienteSesgoC = deltaSalidaC;

                pesoAC = nsC.getPeso(0);
                pesoBC = nsC.getPeso(1);


                // Calculo de gradientes para nsA y nsB
                difenrencialSalidaA = salidaA * (1 - salidaA);
                difenrencialSalidaB = salidaB * (1 - salidaB);
                x1 = entradas[j][0];
                x2 = entradas[j][1];

                gradientePesoAX1 = deltaSalidaC * pesoAC * difenrencialSalidaA * x1;
                gradientePesoAX2 = deltaSalidaC * pesoAC * difenrencialSalidaA * x2;
                gradienteSesgoA = deltaSalidaC * pesoAC * difenrencialSalidaA;

                gradientePesoBX1 = deltaSalidaC * pesoBC * difenrencialSalidaB * x1;
                gradientePesoBX2 = deltaSalidaC * pesoBC * difenrencialSalidaB * x2;
                gradienteSesgoB = deltaSalidaC * pesoBC * difenrencialSalidaB;


                // Ajuste de los sesgos
                nsA.setSesgo(nsA.getSesgo() - gradienteSesgoA*tasaDeAprendisaje);
                nsB.setSesgo(nsB.getSesgo() - gradienteSesgoB*tasaDeAprendisaje);
                nsC.setSesgo(nsC.getSesgo() - gradienteSesgoC*tasaDeAprendisaje);

                // Ajuste de los pesos
                nsA.setPeso(nsA.getPeso(0) - gradientePesoAX1*tasaDeAprendisaje,0);
                nsA.setPeso(nsA.getPeso(1) - gradientePesoAX2*tasaDeAprendisaje,1);

                nsB.setPeso(nsB.getPeso(0) - gradientePesoBX1*tasaDeAprendisaje,0);
                nsB.setPeso(nsB.getPeso(1) - gradientePesoBX2*tasaDeAprendisaje,1);

                nsC.setPeso(pesoAC - gradientePesoAC*tasaDeAprendisaje,0);
                nsC.setPeso(pesoBC - gradientePesoBC*tasaDeAprendisaje,1);

            }
        }


        int correctos = 0;
        for (int j = 0; j < entradas.length; j++) {
            salidaA = nsA.getSalida(entradas[j]);
            salidaB = nsB.getSalida(entradas[j]);
            salidaC = nsC.getSalida(new double[]{salidaA, salidaB});
            int prediccion = (salidaC >= 0.5) ? 1 : 0;
            if (prediccion == objetivos[j]) correctos++;
            System.out.printf("Entrada: (%.1f, %.1f) -> Esperado: %d, Obtenido: %.3f (Predicción: %d)%n",
                    entradas[j][0], entradas[j][1], objetivos[j], salidaC, prediccion);
        }
        System.out.printf("Precisión: %.2f%%\n", (correctos * 100.0 / entradas.length));
    }
}
