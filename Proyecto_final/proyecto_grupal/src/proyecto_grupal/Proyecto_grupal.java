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
/**
 *
 * @author acast
 */
public class Proyecto_grupal {

    //Variables del sistema
    private static final long serialVersionUID = 1006747373773049367L; //Serial ID de la clase
    protected final static int NUM_INPUT = 6; //Neuronas de entrada
    protected final static int NUM_OUTPUT = 1; //Neuronas de salida
    protected final static int NUM_HIDDEN = 6; //Neuronas ocultas
    protected final static double RATE = 0.5; //Tasa de aprendizaje
    protected final static double MOMENTUM = 0.7; //Momentum
    
    public static void main(String[] args) {
        
    }
    
}
