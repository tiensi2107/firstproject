package com.ntsi.messageprocessor.model.dto;

import com.ntsi.gpxgateway.gson.GPXGson;
import com.ntsi.messageprocessor.userType.DeepCopyable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Version implements DeepCopyable {
    private String value;

    private String namespaceURI;

    public Version() {
    }

    public Version(String _value, String _namespaceURI) {
        this.value = _value;
        this.namespaceURI = _namespaceURI;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public void setNamespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
    }

    public static Version fromJson(String json) {
        return GPXGson.getGson().fromJson(json, Version.class);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("namespaceURI", namespaceURI)
                .toString();
    }

    @Override
    public Object deepCopy() {
        Version copy = new Version();
        copy.setValue(this.value);
        copy.setNamespaceURI(this.namespaceURI);
        return copy;
    }
}
