/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
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
/**
 *
 * @author acast
 */
public class Proyecto_grupal {

    //Variables del sistema
    protected final static int NUM_INPUT = 6; //Neuronas de entrada
    protected final static int NUM_OUTPUT = 1; //Neuronas de salida
    protected final static int NUM_HIDDEN = 3; //Neuronas ocultas
    protected final static double RATE = 0.5; //Tasa de aprendizaje
    protected final static double MOMENTUM = 0.7; //Momentum
    
    public static double IDEAL[][] = { { 1 }, { 1 }, { 1 }};

    public static double INPUT[][] ={ { 0.8, -0.2}, { 0.7, 0.05}, { 1, 0.2}, { 0.21, 0.43}, { -0.76, 0.55}, {0.12, -0.32} };
    
    public static void main(String[] args) throws IOException {
        
        long startTime = System.currentTimeMillis();
        final FeedforwardNetwork network = new FeedforwardNetwork();
        network.addLayer(new FeedforwardLayer(2));
        network.addLayer(new FeedforwardLayer(2));
        network.addLayer(new FeedforwardLayer(1));
      
        network.reset();
        // train the neural network
        final Train train = new Backpropagation(network, INPUT, IDEAL, 0.7, 0.3);
        int epoch = 0;
        do {
            train.iteration();
            System.out.println("Epoca #" + (epoch+1) + " MSE: " + train.getError());
            epoch++;
        } while ((train.getError() > 0.00001) );
        // test the neural network
        System.out.println("Resultado de la Red Neuronal: ");
        double salida = 0;
        for (int i = 0; i < IDEAL.length; i++) {
            final double actual[] = network.computeOutputs(INPUT[i]);
            System.out.println(INPUT[i][0] + "," + INPUT[i][1]+", actual= " + actual[0] + " ,ideal= "  + IDEAL[i][0]);
            salida = actual[i];
        }
        System.out.println("Tiempo: " + (System.currentTimeMillis() - startTime) + " milisegundos");
        
        
        
        if(salida <= 0.33){
            System.out.println("El canal es: Disney XD");
        }else if(salida > 0.33 && salida <0.67){
            System.out.println("El canal es: Cartoon Network");
        }else{
            System.out.println("El canal es: Nick Classic");
        }
    }
}
