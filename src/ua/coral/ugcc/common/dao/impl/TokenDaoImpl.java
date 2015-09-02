package ua.coral.ugcc.common.dao.impl;

import ua.coral.ugcc.common.dao.TokenDao;
import ua.coral.ugcc.common.dto.impl.Token;
import ua.coral.ugcc.common.mapper.Mapper;

import java.util.List;

import com.google.appengine.api.datastore.Entity;

public class TokenDaoImpl extends DaoImpl implements TokenDao {

    @Override
    protected Class<Token> getEntityClass() {
        return Token.class;
    }

    @Override
    protected String getId() {
        return "id";
    }

    @Override
    public Token getLastToken() {
        final List<Entity> entities = getEntities(-1);
        if (entities.isEmpty()) {
            return new Token();
        }
        return Mapper.getObject(Token.class, getEntities(-1).get(0));
    }
}
