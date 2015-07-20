package ua.coral.ugcc.common.dao;

import ua.coral.ugcc.common.dto.impl.Token;

import java.io.Serializable;

public interface TokenDao extends Dao, Serializable {

    Token getLastToken();
}
