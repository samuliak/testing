package com.synergy.testing.repo;

import com.synergy.testing.entity.Airplane;
import org.springframework.data.repository.CrudRepository;

public interface AirplaneRepo extends CrudRepository<Airplane, Long> {

}
