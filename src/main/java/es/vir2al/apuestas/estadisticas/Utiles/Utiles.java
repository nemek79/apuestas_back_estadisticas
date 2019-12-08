package es.vir2al.apuestas.estadisticas.Utiles;

/**
 * Utiles
 */
public class Utiles {

  public static Float calcularYield(Float importeGanado, Float importeApostado) {

    if ( importeApostado == 0 ) return 0f;

    return importeGanado / importeApostado * 100;
  }
  
}
