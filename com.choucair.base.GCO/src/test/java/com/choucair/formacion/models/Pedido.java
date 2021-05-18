package com.choucair.formacion.models;

public class Pedido {

    private String numPedido;
    private String codEmpresa;
    private String codConcepto;
    private String codTercero;
    private String codReferencia;
    private String codPlu;

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodConcepto() {
        return codConcepto;
    }

    public void setCodConcepto(String codConcepto) {
        this.codConcepto = codConcepto;
    }

    public String getCodTercero() {
        return codTercero;
    }

    public void setCodTercero(String codTercero) {
        this.codTercero = codTercero;
    }

    public String getCodReferencia() {
        return codReferencia;
    }

    public void setCodReferencia(String codReferencia) {
        this.codReferencia = codReferencia;
    }

    public String getCodPlu() {
        return codPlu;
    }

    public void setCodPlu(String codPlu) {
        this.codPlu = codPlu;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numPedido='" + numPedido + '\'' +
                ", codEmpresa='" + codEmpresa + '\'' +
                ", codConcepto='" + codConcepto + '\'' +
                ", codTercero='" + codTercero + '\'' +
                ", codReferencia='" + codReferencia + '\'' +
                ", codPlu='" + codPlu + '\'' +
                '}';
    }
}
