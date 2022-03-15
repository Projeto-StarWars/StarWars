package br.com.starwars;

import br.com.starwars.repository.RebelRepository;
import br.com.starwars.services.RebelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class RebelServiceTest {

    @InjectMocks
    private RebelService service;

    @InjectMocks
    private RebelRepository repository;

    @BeforeEach
    void before() {

    }
}
