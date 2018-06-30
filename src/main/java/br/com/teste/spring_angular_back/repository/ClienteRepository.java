package br.com.teste.spring_angular_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.spring_angular_back.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}