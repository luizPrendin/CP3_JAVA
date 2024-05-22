package br.com.fiap.revisaoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tb_carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O modelo do carro é obrigatório")
    @Column(name = "ds_modelo")
    private String modelo;

    @NotBlank(message = "A marca do carro é obrigatório")
    @Column(name = "ds_marca")
    private String marca;

    @NotNull(message = "A cilindrada do carro é obrigatória")
    @DecimalMin(value = "1.0", message = "A cilindrada do carro deve ser no mínimo 1.0")
    @Column(name = "cilindrada")
    private Double cilindrada;

    @NotNull(message = "O ano de fabricação do carro é obrigatório")
    @Min(value = 1920, message = "O ano de fabricação deve ser no mínimo 1920")
    @Max(value = 2024, message = "O ano de fabricação deve ser no máximo 2024")
    @Column(name = "anoDeFabricacao")
    private int anoDeFabricacao;

    public Carro() {
    }

    public Carro(Long id, String modelo, String marca, Double cilindrada, int anoDeFabricacao) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.cilindrada = cilindrada;
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(Double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

}
