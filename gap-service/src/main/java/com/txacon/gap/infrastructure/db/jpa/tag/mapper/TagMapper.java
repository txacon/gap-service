package com.txacon.gap.infrastructure.db.jpa.tag.mapper;

import com.txacon.gap.domain.tags.entities.TagName;
import com.txacon.gap.infrastructure.db.jpa.tag.entities.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public class TagMapper {

  public TagEntity toEntity(TagName tagName) {
    if (tagName == null) {
      return null;
    }
    TagEntity tagEntity = new TagEntity();
    tagEntity.setTagName(tagName.name());
    return tagEntity;
  }

  public TagName toDomain(TagEntity tagEntity) {
    if (tagEntity == null) {
      return null;
    }
    return TagName.valueOf(tagEntity.getTagName());
  }
}
