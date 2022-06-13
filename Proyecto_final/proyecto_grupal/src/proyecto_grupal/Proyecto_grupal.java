package proyecto_grupal;
import com.heatonresearch.book.introneuralnet.neural.feedforward.FeedforwardLayer;
import com.heatonresearch.book.introneuralnet.neural.feedforward.FeedforwardNetwork;
import com.heatonresearch.book.introneuralnet.neural.feedforward.train.Train;
import com.heatonresearch.book.introneuralnet.neural.feedforward.train.backpropagation.Backpropagation;
import com.heatonresearch.book.introneuralnet.neural.feedforward.train.genetic.TrainingSetNeuralGeneticAlgorithm;
import com.heatonresearch.book.introneuralnet.neural.feedforward.train.anneal.NeuralSimulatedAnnealing;
import com.heatonresearch.book.introneuralnet.neural.prune.Prune;
import com.heatonresearch.book.introneuralnet.common.ReadCSV;
import java.io.IOException;
import java.util.Scanner;
/**
 * @author acast
 */
public class Proyecto_grupal {
    //Variables del sistema
    protected final static int NUM_INPUT = 6; //Neuronas de entrada
    protected final static int NUM_OUTPUT = 1; //Neuronas de salida
    protected final static int NUM_HIDDEN = 3; //Neuronas ocultas
    protected final static double RATE = 0.5; //Tasa de aprendizaje
    protected final static double MOMENTUM = 0.7; //Momentum
    
    public static double IDEAL[][] = { { 0.33 }, { 0.66 }, { 1 }};
    public static double INPUT_FINAL[][]={ {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}};
    public static double INPUT[][] = { {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}};
    
    public static void main(String[] args) {
        
        final FeedforwardNetwork network = new FeedforwardNetwork();
        network.addLayer(new FeedforwardLayer(NUM_INPUT));
        network.addLayer(new FeedforwardLayer(NUM_HIDDEN));
        network.addLayer(new FeedforwardLayer(NUM_OUTPUT));
      
        network.reset();
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de veces que desea entrenar");
        int res = leer.nextInt();
        for(int i = 0;i<res;i++){
            generar_entrenamiento(3);
            // Entrenar
            final Train train = new Backpropagation(network, INPUT, IDEAL, RATE, MOMENTUM);
            int epoch = 0;
            do {
                train.iteration();
                System.out.println("Epoca #" + (epoch+1) + " MSE: " + train.getError());
                epoch++;
            } while ((train.getError() > 0.01) );
            // test the neural network
        }
        System.out.println("Inician preguntas: ");
        double salida = 0,respuesta1,respuesta2,respuesta3,respuesta4,respuesta5,respuesta6;
        int resultado = 0;
        
        System.out.println("¿Que canal llama más su atención?");
        System.out.println("1) Disney XD");
        System.out.println("2) Cartook Network");
        System.out.println("3) Nick Classic");
        resultado = leer.nextInt();
        respuesta1 = generar_umbral(resultado);
        System.out.println(respuesta1);            
        
        System.out.println("¿Cual serie marcó más su infancia?");
        System.out.println("1) Disney XD; Kick Buttowski");
        System.out.println("2) Cartook Network: Hora de aventura");
        System.out.println("3) Nick Classic: Hey Arnold!");
        resultado = leer.nextInt();
        respuesta2 = generar_umbral(resultado);
        System.out.println(respuesta2);
        
        System.out.println("¿Que canal llama más su atención?");
        System.out.println("1) Disney XD");
        System.out.println("2) Cartook Network");
        System.out.println("3) Nick Classic");
        resultado = leer.nextInt();
        respuesta3 = generar_umbral(resultado);
        System.out.println(respuesta3);
        
        System.out.println("¿Que canal llama más su atención?");
        System.out.println("1) Disney XD");
        System.out.println("2) Cartook Network");
        System.out.println("3) Nick Classic");
        resultado = leer.nextInt();
        respuesta4 = generar_umbral(resultado);
        
        System.out.println("¿Que canal llama más su atención?");
        System.out.println("1) Disney XD");
        System.out.println("2) Cartook Network");
        System.out.println("3) Nick Classic");
        resultado = leer.nextInt();
        respuesta5 = generar_umbral(resultado);
        
        System.out.println("¿Que canal llama más su atención?");
        System.out.println("1) Disney XD");
        System.out.println("2) Cartook Network");
        System.out.println("3) Nick Classic");
        resultado = leer.nextInt();
        respuesta6 = generar_umbral(resultado);
        
        INPUT_FINAL[0][0] = respuesta1;
        INPUT_FINAL[0][1] = respuesta2;
        INPUT_FINAL[0][2] = respuesta3;
        INPUT_FINAL[0][3] = respuesta4;
        INPUT_FINAL[0][4] = respuesta5;
        INPUT_FINAL[0][5] = respuesta6;
        
        INPUT_FINAL[1][0] = respuesta1;
        INPUT_FINAL[1][1] = respuesta2;
        INPUT_FINAL[1][2] = respuesta3;
        INPUT_FINAL[1][3] = respuesta4;
        INPUT_FINAL[1][4] = respuesta5;
        INPUT_FINAL[1][5] = respuesta6;
        
        INPUT_FINAL[2][0] = respuesta1;
        INPUT_FINAL[2][1] = respuesta2;
        INPUT_FINAL[2][2] = respuesta3;
        INPUT_FINAL[2][3] = respuesta4;
        INPUT_FINAL[2][4] = respuesta5;
        INPUT_FINAL[2][5] = respuesta6;
        
        for (int i = 0; i < IDEAL.length; i++) {
            System.out.println(IDEAL.length);
            final double actual[] = network.computeOutputs(INPUT_FINAL[i]);
            System.out.println(INPUT_FINAL[i][0] + "," + INPUT_FINAL[i][1]+", actual= " + actual[0] + " ,ideal= "  + IDEAL[i][0]);
            salida = actual[0];if(salida <= 0.33){
            System.out.println("El canal es: Disney XD");
            }else if(salida > 0.33 && salida <0.67){
                System.out.println("El canal es: Cartoon Network");
            }else if(salida > 0.66 && salida <1.1){
                System.out.println("El canal es: Nick Classic");
            }
        }
        
        
    }
    
    public static double generar_umbral(int resultado){
        double umbral = 0;
        if(resultado == 1){
            umbral = Math.random() * ( 0.33 - 0.1 );
        }else if (resultado == 2){
            umbral = Math.random() * ( 0.66 - 0.34 );
        }else{
            umbral = Math.random() * ( 1 - 0.67 );
        }
        
        return umbral;
    }
    
    public static void generar_entrenamiento(int num_entrenamiento){
        for (int i = 0;i<num_entrenamiento;i++){
            INPUT[i][0] = Math.random() * ( 1 - 0 );
            INPUT[i][1] = Math.random() * ( 1 - 0 );
            INPUT[i][2] = Math.random() * ( 1 - 0 );
            INPUT[i][3] = Math.random() * ( 1 - 0 );
            INPUT[i][4] = Math.random() * ( 1 - 0 );
            INPUT[i][5] = Math.random() * ( 1 - 0 );
        }
    }
}