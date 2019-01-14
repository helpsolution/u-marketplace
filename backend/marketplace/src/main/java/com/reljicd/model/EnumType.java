package com.reljicd.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class EnumType extends GenericEnumType<String, OrderOfSystem.PayingType> {

    public EnumType() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        super(OrderOfSystem.PayingType.class, OrderOfSystem.PayingType.values(), "getValue", Types.OTHER);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names,
                              SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
        return nullSafeGet(rs, names, owner);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
                            SessionImplementor session) throws HibernateException, SQLException {
        nullSafeSet(st, value, index);
    }

}