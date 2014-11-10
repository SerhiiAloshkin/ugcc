package ua.coral.ugcc.common.dao;

import ua.coral.ugcc.common.dto.Dto;

public interface Dao {

    void add(Dto object);

    void remove(Dto object);

    void update(Dto object);

    int count();
}
