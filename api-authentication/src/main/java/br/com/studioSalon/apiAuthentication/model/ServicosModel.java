package br.com.studioSalon.apiAuthentication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Document(collection = "servicos")
public class ServicosModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id private String id;
    @Field(name = "tipo_servico") private Categoria tipoServico;
    @Field(name = "descricao") private String descricao;
    @Field(name = "renovacao") private int intervaloRenovacao;
    @Field(name = "valor") private double valorServico;


    public ServicosModel() {}

    public ServicosModel(String id, double valorServico, int intervaloRenovacao, String descricao, Categoria tipoServico) {
        this.id = id;
        this.valorServico = valorServico;
        this.intervaloRenovacao = intervaloRenovacao;
        this.descricao = descricao;
        this.tipoServico = tipoServico;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    public int getIntervaloRenovacao() {
        return intervaloRenovacao;
    }

    public void setIntervaloRenovacao(int intervaloRenovacao) {
        this.intervaloRenovacao = intervaloRenovacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Categoria tipoServico) {
        this.tipoServico = tipoServico;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicosModel that = (ServicosModel) o;
        return intervaloRenovacao == that.intervaloRenovacao && Double.compare(valorServico, that.valorServico) == 0 && Objects.equals(id, that.id) && tipoServico == that.tipoServico && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(tipoServico);
        result = 31 * result + Objects.hashCode(descricao);
        result = 31 * result + intervaloRenovacao;
        result = 31 * result + Double.hashCode(valorServico);
        return result;
    }
}
