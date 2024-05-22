package br.com.fiap.revisaoapi.dto;

import org.springframework.hateoas.Link;

public class CarroDTO {
    private Long id;
    private String modelo;
    private String marca;
    private Double cilindrada;
    private int anoDeFabricacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() { return modelo; }

    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public Double getCilindrada() { return cilindrada; }

    public void setCilindrada(Double cilindrada) { this.cilindrada = cilindrada; }

    public int getAnoDeFabricacao() { return anoDeFabricacao; }

    public void setAnoDeFabricacao(int anoDeFabricacao) { this.anoDeFabricacao=anoDeFabricacao; }

    public void add(Link link) {
    }
}
