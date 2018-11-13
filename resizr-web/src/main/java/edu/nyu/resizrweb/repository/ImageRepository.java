package edu.nyu.resizrweb.repository;

import edu.nyu.resizrweb.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
}
