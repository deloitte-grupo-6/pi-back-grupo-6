package br.com.delove.model;

import java.time.LocalDateTime;

public class Agendamento {

    private Long id;
    private Long idInteressado;
    private Long idDoador;
    private Long idPet;
    private LocalDateTime dataAgendamento;
    private int posFila;

    public Agendamento(Long idInteressado, Long idDoador, Long idPet, LocalDateTime dataAgendamento, int posFila) {
        this.idInteressado = idInteressado;
        this.idDoador = idDoador;
        this.idPet = idPet;
        this.dataAgendamento = dataAgendamento;
        this.posFila = posFila;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdInteressado() {
        return idInteressado;
    }

    public void setIdInteressado(Long idInteressado) {
        this.idInteressado = idInteressado;
    }

    public Long getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(Long idDoador) {
        this.idDoador = idDoador;
    }

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public int getPosFila() {
        return posFila;
    }

    public void setPosFila(int posFila) {
        this.posFila = posFila;
    }
}
