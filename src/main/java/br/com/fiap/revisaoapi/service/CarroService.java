package br.com.fiap.revisaoapi.service;

import br.com.fiap.revisaoapi.dto.CarroDTO;
import br.com.fiap.revisaoapi.model.Carro;
import br.com.fiap.revisaoapi.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarroService {
    private final CarroRepository carroRepository;
    private static final Pageable customPageable = PageRequest.of(0, 3, Sort.by("modelo").ascending());

    @Autowired
    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public Page<CarroDTO> findAll() {
        return carroRepository.findAll(customPageable).map(this::toDTO);
    }

    public CarroDTO findById(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.map(this::toDTO).orElse(null);
    }

    public Carro save(Carro carro) {
        return carroRepository.save(carro);
    }

    public Carro update(Long id, Carro carro) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isPresent()) {
            Carro carroUpdate = carroOptional.get();
            carroUpdate.setModelo(carro.getModelo());
            carroUpdate.setMarca(carro.getMarca());
            carroUpdate.setCilindrada(carro.getCilindrada());
            carroUpdate.setAnoDeFabricacao(carro.getAnoDeFabricacao());
            carro = carroRepository.save(carroUpdate);
            return carro;
        }
        return null;
    }

    public void delete(Long id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        carroOptional.ifPresent(carroRepository::delete);
    }

    private CarroDTO toDTO(Carro carro) {
        CarroDTO carroDTO = new CarroDTO();
        carroDTO.setId(carro.getId());
        carroDTO.setModelo(carro.getModelo());
        carroDTO.setMarca(carro.getMarca());
        carroDTO.setCilindrada(carro.getCilindrada());
        carroDTO.setAnoDeFabricacao(carro.getAnoDeFabricacao());

        return carroDTO;
    }



}
