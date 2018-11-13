package edu.nyu.resizrweb.repository;

import edu.nyu.resizrweb.entity.ImageEntity;
import edu.nyu.resizrweb.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
    public List<ImageEntity> findAllByUser(User user);
}
