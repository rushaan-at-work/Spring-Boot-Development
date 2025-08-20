package com.newProject.mvc.Repsitories;

import com.newProject.mvc.Entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository //VideoEntity is the domain type or the Entity class and Long is datatype of primary key.
        <VideoEntity, Long>{
    List<VideoEntity> findByName(String name);
    List<VideoEntity> findByNameContainsIgnoreCase(String partialName);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String partialDescription);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName, String partialDescription);

    List<VideoEntity> findByNameContains(String name);
    List<VideoEntity> findByDescriptionContains(String name);

}
