package br.com.fiap.revisaoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


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

    @NotBlank(message = "A cilidrada do carro é obrigatório")
    @Size(min = 1, message = "A cilidrada do carro deve ser no minimo 1.0")
    @Column(name = "cilindrada")
    private Double cilindrada;

    @NotBlank(message = "O ano de fabrição do carro é obrigatório")
    @Min(value = 1920, message = "O ano de fabricação deve ser no mínimo 1920")
    @Max(value = 2024, message = "O ano de fabricação deve ser no máximo 2024")
    @Column(name = "anoDeFabricacao")
    private int anoDeFabricacao;

    @NotBlank(message = "O preço do carro é obrigatório")
    @Size(min = 1000,message = "O carro deve custar no minimo 1000 reais")
    @Column(name = "preco")
    private Double preco;



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

    public @NotBlank(message = "O modelo do carro é obrigatório") String getModelo() {
        return modelo;
    }

    public void setModelo(@NotBlank(message = "O modelo do carro é obrigatório") String modelo) {
        this.modelo = modelo;
    }

    public @NotBlank(message = "A marca do carro é obrigatório") String getMarca() {
        return marca;
    }

    public void setMarca(@NotBlank(message = "A marca do carro é obrigatório") String marca) {
        this.marca = marca;
    }

    public @Size(min = 1, message = "A cilidrada do carro deve ser no minimo 1.0") Double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(@Size(min = 1, message = "A cilidrada do carro deve ser no minimo 1.0") Double cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Size(min = 1920, message = "O ano de fabricação deve ser no minimo 1920 ", max = 2024)
    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(@Size(min = 1920, message = "O ano de fabricação deve ser no minimo 1920 ", max = 2024) int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }
}
