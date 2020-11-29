package com.txacon.gap.infrastructure.db.jpa.tag.repository;

import com.txacon.gap.infrastructure.db.jpa.tag.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CrudTagRepository extends JpaRepository<TagEntity, String> {

}
