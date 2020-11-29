package com.txacon.gap.domain.common.port;

import java.util.List;
import java.util.Optional;

public interface KeyEntityRepository<T> {

    Optional<T> findByName(T name);

    List<T> findAll();

    T save(T name);

}
