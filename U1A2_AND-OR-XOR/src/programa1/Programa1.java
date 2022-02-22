package programa1;

/**
 *
 * @author Allan Castro
 */
public class Programa1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	int TablaVerdad[][] = {{0,0},{0,1},{1,0},{1,1}};
	float ProductoPunto[] = new float [4];
		
	//Calculo de AND 
	for(int i = 0; i<4;i++){
	    ProductoPunto[i] = pp(TablaVerdad[i][0], TablaVerdad[i][1], 1, 1, 1.5);
	}
	//Barrido de TablaVerdad
	System.out.println("************* AND *********** \n");
	System.out.println("Peso 1: 1 , Peso 2: 1, Umbral: 1.5 \n");
        
	for(int i = 0; i<4;i++){
	    System.out.println("[ " + TablaVerdad[i][0] + " , " + TablaVerdad[i][1]  + " ] = " + ProductoPunto[i]);
	}
		
		
	//Calculo de OR 
	for(int i = 0; i<4;i++){
	    ProductoPunto[i] = pp(TablaVerdad[i][0], TablaVerdad[i][1], 1, 1, 0.9);
	}
	//Barrido de TablaVerdad
	System.out.println("************* OR *********** \n");
	System.out.println("Peso 1: 1 , Peso 2: 1, Umbral: 0.9 \n");
		
	for(int i = 0; i<4;i++){
	    System.out.println("[ " + TablaVerdad[i][0] + " , " + TablaVerdad[i][1]  + " ] = " + ProductoPunto[i]);
	}
		
	//Calculo de XOR 
	int pp1, pp2;
	for(int i = 0; i<4;i++){
	    //Paso 1, primer producto punto
	    pp1 = pp(TablaVerdad[i][0], TablaVerdad[i][1], 1, 1, 1.5);
	    //Paso 2, segundo producto punto
	    pp2 = pp(TablaVerdad[i][0], TablaVerdad[i][1], 1, 1, 0.5);
	    //Paso 3, tercer producto punto y resultado
	    //Se mandan al revez los producto punto por que aqui no aplica la multiplicación cruzada
	    ProductoPunto[i] = pp(pp2, pp1, -1, 1, 0.5);
	}
	//Barrido de TablaVerdad
	System.out.println("************* XOR *********** \n");
	System.out.println("Peso 1: 1 , Peso 2: 1, Umbral 1: 1.5 , Umbral 2: 0.5 , Umbral 3: 0.5 \n");
		
	for(int i = 0; i<4;i++){
	    System.out.println("[ " + TablaVerdad[i][0] + " , " + TablaVerdad[i][1]  + " ] = " + ProductoPunto[i]);
	}
		
}
//Función para calculo del producto punto
public static int pp(int a, int b, int p1, int p2, double t){
    //Multiplicación cruzada y suma de los productos (a * p2) + (b * p1)
    float producto = (a * p2) + (b * p1);
    //Si es mayor al Umbral entonces retorna 1 si no, 0
    if (producto > t){
        return 1;    
    }else{
        return 0;
    }
    
}   
}