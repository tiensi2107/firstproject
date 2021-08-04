package model.dto;

import model.gson.TrackerGson;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Link {
    private String href;
    private String text;
    private String type;


    private Link(String href, String text, String type) {
        this.href = href;
        this.text = text;
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Link fromJson(String json) {
        return TrackerGson.getGson().fromJson(json, Link.class);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("href", href)
                .append("text", text)
                .append("type", type)
                .toString();
    }

}
