package com.txacon.gap.application.adapter;

import com.txacon.gap.application.api.TagService;
import com.txacon.gap.domain.common.port.KeyEntityRepository;
import com.txacon.gap.domain.tags.entities.TagName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends KeyAbstractService<TagName> implements TagService {
    @Autowired
    public TagServiceImpl(KeyEntityRepository<TagName> repository) {
        super(repository);
    }
}
