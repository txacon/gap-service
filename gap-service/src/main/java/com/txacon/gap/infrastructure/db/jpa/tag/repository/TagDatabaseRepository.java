package com.txacon.gap.infrastructure.db.jpa.tag.repository;

import com.txacon.gap.domain.tags.entities.TagName;
import com.txacon.gap.domain.tags.port.TagRepository;
import com.txacon.gap.infrastructure.db.jpa.tag.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TagDatabaseRepository implements TagRepository {

    private final CrudTagRepository repository;
    private final TagMapper mapper;

    @Override
    public Optional<TagName> findByName(TagName tagName) {
        return repository.findById(tagName.name()).map(mapper::toDomain);
    }

    @Override
    public List<TagName> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public TagName save(TagName tagName) {
        return mapper.toDomain(repository.save(mapper.toEntity(tagName)));
    }
}
