package br.com.fiap.revisaoapi.repository;

import br.com.fiap.revisaoapi.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}
