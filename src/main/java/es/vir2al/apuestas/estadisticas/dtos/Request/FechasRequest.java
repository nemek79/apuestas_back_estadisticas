package es.vir2al.apuestas.estadisticas.dtos.Request;


/**
 * FechasRequest
 */

public class FechasRequest {

  private String fechaInicial;
  private String fechaFinal;

  public String getFechaInicial() {
    return fechaInicial;
  }

  public void setFechaInicial(String fechaInicial) {
    this.fechaInicial = fechaInicial;
  }

  public String getFechaFinal() {
    return fechaFinal;
  }

  public void setFechaFinal(String fechaFinal) {
    this.fechaFinal = fechaFinal;
  }

}
