package com.tygershammer.tygersammer.repos;

import com.tygershammer.tygersammer.models.Unit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UnitRepoInterface extends CrudRepository<Unit, Long> {

    Unit findByName(String name);
}
