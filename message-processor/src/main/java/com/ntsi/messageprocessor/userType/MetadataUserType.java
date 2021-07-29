package com.ntsi.messageprocessor.userType;

import com.google.gson.Gson;
import com.ntsi.gpxgateway.gson.GPXGson;
import model.dto.Metadata;
import model.dto.Version;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.postgresql.util.PGobject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class MetadataUserType extends  MutableUserType{
    public static final String TYPE = "com.karros.common.model.usertype.MetadataUserType";

    private static final int[] SQL_TYPES = { Types.LONGVARCHAR };

    private Gson gson = GPXGson.getBuilder(GPXGson.GsonAdapter.ISO_8601_NO_MILLI).create();

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
            throws HibernateException, SQLException {
        if (rs.getString(names[0]) != null) {
            String definitionJson = rs.getString(names[0]);
            if (owner == null) {
                return definitionJson;
            }

            return gson.fromJson(definitionJson, Metadata.class);
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        String json = (value == null ? null : gson.toJson(value));
        PGobject pgo = new PGobject();
        pgo.setType("jsonb");
        pgo.setValue(json);
        st.setObject(index, pgo);
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return DeepCopyable.deepCopy((Metadata) o);
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class<Version> returnedClass() {
        return Version.class;
    }
}
