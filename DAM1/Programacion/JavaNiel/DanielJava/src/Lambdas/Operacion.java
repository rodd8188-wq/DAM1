package Lambdas;

@FunctionalInterface	// Definimos que esta interfaz es para generar funciones lambda
public interface Operacion {	// Solo puede tener una función
	int ejecutar(int a, int b);
}
