package com.txacon.gap.infrastructure.db.jpa.tag.repository;

import com.txacon.gap.infrastructure.db.jpa.tag.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CrudTagRepository extends JpaRepository<TagEntity, Long> {

    Optional<TagEntity> findByTagName(String tagName);

}
