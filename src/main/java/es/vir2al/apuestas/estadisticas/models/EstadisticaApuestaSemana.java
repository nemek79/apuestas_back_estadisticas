package es.vir2al.apuestas.estadisticas.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * EstadisticasApuestaSemana
 */

@Entity
@Table(name="t_est_apuestas_semana")
public class EstadisticaApuestaSemana implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "semana")
  protected Integer semana;
  @Column(name = "ano")
  protected Integer ano;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  protected Long id;
  @Column(name = "num_apuestas")
  protected Integer numApuestas = 0;
  @Column(name = "num_encurso")
  protected Integer numEnCurso = 0;
  @Column(name = "num_ganadas")
  protected Integer numGanadas = 0;
  @Column(name = "num_perdidas")
  protected Integer numPerdidas = 0;
  @Column(name = "num_pendientes")
  protected Integer numPendientes = 0;
  @Column(name = "num_suspendidas")
  protected Integer numSuspendidas = 0;
  @Column(name = "num_canceladas")
  protected Integer numCanceladas = 0;
  @Column(name = "num_parciales")
  protected Integer numParciales = 0;
  @Column(name = "num_push")
  protected Integer numPush = 0;
  @Column(name = "importe_apostado")
  protected Float importeApostado = 0f;
  @Column(name = "importe_potencial")
  protected Float importePotencial = 0f;
  @Column(name = "importe_ganado")
  protected Float importeGanado = 0f;
  @Column(name = "importe_perdido")
  protected Float importePerdido = 0f;
  @Column(name = "importe_total")
  protected Float importeTotal = 0f;
  @Column(name = "yield")
  protected Float yield = 0f;
  
  public void reset(){

    this.numApuestas = 0;
    this.numEnCurso = 0;
    this.numGanadas = 0;
    this.numPerdidas = 0;
    this.numPendientes = 0;
    this.numSuspendidas = 0;
    this.numCanceladas = 0;
    this.numParciales = 0;
    this.numPush = 0;
    this.importeApostado = 0f;
    this.importePotencial = 0f;
    this.importeGanado = 0f;
    this.importePerdido = 0f;
    this.importeTotal = 0f;
    this.yield = 0f;

  }

  public Integer getSemana() {
    return semana;
  }

  public void setSemana(Integer semana) {
    this.semana = semana;
  }

  public Integer getAno() {
    return ano;
  }

  public void setAno(Integer ano) {
    this.ano = ano;
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
