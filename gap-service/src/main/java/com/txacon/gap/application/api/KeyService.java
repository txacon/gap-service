package com.txacon.gap.application.api;

import java.util.List;

public interface KeyService<T> {

    T findByName(T name);

    List<T> findAll();

    T add(T name);
}
