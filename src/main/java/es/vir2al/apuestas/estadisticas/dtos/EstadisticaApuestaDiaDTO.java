package es.vir2al.apuestas.estadisticas.dtos;

import java.io.Serializable;
import java.util.Date;

import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaDia;

/**
 * EstadisticaApuestaDiaDTO
 */

public class EstadisticaApuestaDiaDTO implements Serializable {

  private static final long serialVersionUID = -5403505821775919803L;

  private Date fecha;
  protected Long id;
  protected Integer numApuestas;
  protected Integer numEnCurso;
  protected Integer numGanadas;
  protected Integer numPerdidas;
  protected Integer numPendientes;
  protected Integer numSuspendidas;
  protected Integer numCanceladas;
  protected Integer numParciales;
  protected Integer numPush;
  protected Float importeApostado;
  protected Float importePotencial;
  protected Float importeGanado;
  protected Float importePerdido;
  protected Float importeTotal;
  protected Float yield;  

  public EstadisticaApuestaDiaDTO(EstadisticaApuestaDia estadistica) {

    this.fecha = estadistica.getFecha();
    this.id = estadistica.getId();
    this.numApuestas = estadistica.getNumApuestas();
    this.numEnCurso = estadistica.getNumEnCurso();
    this.numGanadas = estadistica.getNumGanadas();
    this.numPerdidas = estadistica.getNumPerdidas();
    this.numPendientes = estadistica.getNumPendientes();
    this.numSuspendidas = estadistica.getNumSuspendidas();
    this.numCanceladas = estadistica.getNumCanceladas();
    this.numParciales = estadistica.getNumParciales();
    this.numPush = estadistica.getNumPush();
    this.importeApostado = estadistica.getImporteApostado();
    this.importePotencial = estadistica.getImportePotencial();
    this.importeGanado = estadistica.getImporteGanado();
    this.importePerdido = estadistica.getImportePerdido();
    this.importeTotal = estadistica.getImporteTotal();
    this.yield = estadistica.getYield();

  }

  public EstadisticaApuestaDia asEstadisticaApuestaDia() throws Exception {

    EstadisticaApuestaDia estadistica = new EstadisticaApuestaDia();

    estadistica.setFecha(this.fecha);
    estadistica.setId(this.id);
    estadistica.setNumApuestas(this.numApuestas);
    estadistica.setNumEnCurso(this.numEnCurso);
    estadistica.setNumGanadas(this.numGanadas);
    estadistica.setNumPerdidas(this.numPerdidas);
    estadistica.setNumPendientes(this.numPendientes);
    estadistica.setNumSuspendidas(this.numSuspendidas);
    estadistica.setNumCanceladas(this.numCanceladas);
    estadistica.setNumParciales(this.numParciales);
    estadistica.setNumPush(this.numPush);
    estadistica.setImporteApostado(this.importeApostado);
    estadistica.setImportePotencial(this.importePotencial);
    estadistica.setImporteGanado(this.importeGanado);
    estadistica.setImportePerdido(this.importePerdido);
    estadistica.setImporteTotal(this.importeTotal);
    estadistica.setYield(this.yield);

    return estadistica;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getNumApuestas() {
    return numApuestas;
  }

  public void setNumApuestas(Integer numApuestas) {
    this.numApuestas = numApuestas;
  }

  public Integer getNumEnCurso() {
    return numEnCurso;
  }

  public void setNumEnCurso(Integer numEnCurso) {
    this.numEnCurso = numEnCurso;
  }

  public Integer getNumGanadas() {
    return numGanadas;
  }

  public void setNumGanadas(Integer numGanadas) {
    this.numGanadas = numGanadas;
  }

  public Integer getNumPerdidas() {
    return numPerdidas;
  }

  public void setNumPerdidas(Integer numPerdidas) {
    this.numPerdidas = numPerdidas;
  }

  public Integer getNumPendientes() {
    return numPendientes;
  }

  public void setNumPendientes(Integer numPendientes) {
    this.numPendientes = numPendientes;
  }

  public Integer getNumSuspendidas() {
    return numSuspendidas;
  }

  public void setNumSuspendidas(Integer numSuspendidas) {
    this.numSuspendidas = numSuspendidas;
  }

  public Integer getNumCanceladas() {
    return numCanceladas;
  }

  public void setNumCanceladas(Integer numCanceladas) {
    this.numCanceladas = numCanceladas;
  }

  public Integer getNumParciales() {
    return numParciales;
  }

  public void setNumParciales(Integer numParciales) {
    this.numParciales = numParciales;
  }

  public Integer getNumPush() {
    return numPush;
  }

  public void setNumPush(Integer numPush) {
    this.numPush = numPush;
  }

  public Float getImporteApostado() {
    return importeApostado;
  }

  public void setImporteApostado(Float importeApostado) {
    this.importeApostado = importeApostado;
  }

  public Float getImportePotencial() {
    return importePotencial;
  }

  public void setImportePotencial(Float importePotencial) {
    this.importePotencial = importePotencial;
  }

  public Float getImporteGanado() {
    return importeGanado;
  }

  public void setImporteGanado(Float importeGanado) {
    this.importeGanado = importeGanado;
  }

  public Float getImportePerdido() {
    return importePerdido;
  }

  public void setImportePerdido(Float importePerdido) {
    this.importePerdido = importePerdido;
  }

  public Float getImporteTotal() {
    return importeTotal;
  }

  public void setImporteTotal(Float importeTotal) {
    this.importeTotal = importeTotal;
  }

  public Float getYield() {
    return yield;
  }

  public void setYield(Float yield) {
    this.yield = yield;
  }

  
}
