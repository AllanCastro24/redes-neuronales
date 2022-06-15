package com.heatonresearch.book.introneuralnet.ch5.xor;

import com.heatonresearch.book.introneuralnet.neural.feedforward.FeedforwardLayer;
import com.heatonresearch.book.introneuralnet.neural.feedforward.FeedforwardNetwork;
import com.heatonresearch.book.introneuralnet.neural.feedforward.train.anneal.NeuralSimulatedAnnealing;
import java.io.IOException;
import com.heatonresearch.book.introneuralnet.common.ReadCSV;

public class XOR {
	public static int filas;
	public static double csv[][] = new double[51][7];
        public static double csv2[][] = new double[51][7];
	public static void main(final String args[]) throws IOException {
		final ReadCSV archivo = new ReadCSV("entrada.csv");		
		filas = archivo.rows("entrada.csv");
		final FeedforwardNetwork network = new FeedforwardNetwork();
		network.addLayer(new FeedforwardLayer(6));          					
		network.addLayer(new FeedforwardLayer(5));								
		network.addLayer(new FeedforwardLayer(1));								
		network.reset();
		int j = 0;	
                //guarda archivos en un matriz
		while (archivo.next()) {														
			final double p1 = Double.parseDouble(archivo.get("pregunta1"));
			final double p2 = Double.parseDouble(archivo.get("pregunta2"));
			final double p3 = Double.parseDouble(archivo.get("pregunta3"));
			final double p4 = Double.parseDouble(archivo.get("pregunta4"));
			final double p5 = Double.parseDouble(archivo.get("pregunta5"));
			final double p6 = Double.parseDouble(archivo.get("pregunta6"));
			final double ideal = Double.parseDouble(archivo.get("ideal"));
			
			csv[j][0] = p1;
			csv[j][1] = p2;
			csv[j][2] = p3;
			csv[j][3] = p4;
			csv[j][4] = p5;
			csv[j][5] = p6;
			csv[j][6] = ideal;
			j += 1;
		}
		//se manda a llamar para guardar en otra matriz
		final double xorData[][] = getGrid2();
                final double xorIdeal[][] = getIdeal();
        //se manda a entrenar con el metodo del recocio
                final NeuralSimulatedAnnealing train = new NeuralSimulatedAnnealing(network, xorData, xorIdeal, 10, 2, 50000);
		//final Train train = new Backpropagation(network, xorData, xorIdeal,0.7, 0.9);
		int epoch = 1;
		do {
			train.iteration();
			System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;
		} while ((epoch < 5000) && (train.getError() > 0.001));
		System.out.println("Resultados de la red:");
                for (int i = 0; i < xorIdeal.length -1; i++) {
			final double actual[] = network.computeOutputs(xorData[i]);
			if ((actual[0] >= 0) && (actual[0] <= 0.33)){
                           System.out.println(xorData[i][0] + "," + xorData[i][1]+ "," + xorData[i][2]+ "," + xorData[i][3]+ "," + xorData[i][4]+ "," + xorData[i][5]
					+ ", actual=" + actual[0] + ",ideal=" + xorIdeal[i][0] + ",Su recomendación es: Disney XD");
                        }else if((actual[0] > 0.33) && (actual[0] <= 0.66)){
                        System.out.println(xorData[i][0] + "," + xorData[i][1]+ "," + xorData[i][2]+ "," + xorData[i][3]+ "," + xorData[i][4]+ "," + xorData[i][5]
					+ ", actual=" + actual[0] + ",ideal=" + xorIdeal[i][0] + ",Su recomendación es: Cartoon Network");
                        }else if((actual[0] > 0.66) && (actual[0] <= 1)){
                        System.out.println(xorData[i][0] + "," + xorData[i][1]+ "," + xorData[i][2]+ "," + xorData[i][3]+ "," + xorData[i][4]+ "," + xorData[i][5]
					+ ", actual=" + actual[0] + ",ideal=" + xorIdeal[i][0] + ",Su recomendación es: Nick Classic");
                        }
                        
		}
	}
        //guarda solamente el ideal
	static double[][] getIdeal() {
        final double array[][] = new double[filas][1];

        for (int i = 0; i < filas - 1; i++) {
            array[i][0] = csv[i][6];
        }
        return array;
    }
	//guarda matriz sin el ideal
	static double[][] getGrid2() {
        final double array[][] = new double[filas][6];
        for (int i = 0; i < filas - 1; i++) {
            array[i][0] = csv[i][0];
            array[i][1] = csv[i][1];
            array[i][2] = csv[i][2];
            array[i][3] = csv[i][3];
            array[i][4] = csv[i][4];
            array[i][5] = csv[i][5];
        }
        return array;
    }
}