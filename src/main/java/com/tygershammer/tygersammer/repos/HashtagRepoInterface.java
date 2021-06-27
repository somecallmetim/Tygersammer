package com.tygershammer.tygersammer.repos;

import com.tygershammer.tygersammer.models.Hashtag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HashtagRepoInterface extends CrudRepository<Hashtag, Long> {
    Optional<Hashtag> findByHashtag(String hashtag);
}
