package com.tygershammer.tygersammer.repos;

import com.tygershammer.tygersammer.models.Unit;
import org.springframework.data.repository.CrudRepository;

public interface UnitRepoInterface extends CrudRepository<Unit, Long> {

    Unit findByName(String name);
}
