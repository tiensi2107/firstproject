package com.ntsi.messageprocessor.userType;

import com.google.gson.Gson;
import com.ntsi.gpxgateway.gson.GPXGson;
import com.ntsi.messageprocessor.model.dto.Link;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.postgresql.util.PGobject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ListOfLinkUserType extends MutableUserType{
    private static final String JSONB_TYPE = "jsonb";

    private Gson gson = GPXGson.getGson();

    public static final String TYPE = "com.karros.common.com.ntsi.messageprocessor.model.usertype.ListOfLinksUserType";

    @Override
    public Object deepCopy(Object object) throws HibernateException {
        if (object == null) {
            return null;
        }
        return new ArrayList<>((List<Link>) object);
    }

    @Override
    public Class returnedClass() {
        return TreeSet.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session,
                              Object owner) throws HibernateException, SQLException {
        final String json = resultSet.getString(names[0]);
        try {
            return (json == null) ? null : new TreeSet<>(Arrays.asList(gson.fromJson(json, Link[].class)));
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        final String json = (value == null ? null : gson.toJson(value));
        PGobject pgo = new PGobject();
        pgo.setType(JSONB_TYPE);
        pgo.setValue(json);
        st.setObject(index, pgo);
    }
}
