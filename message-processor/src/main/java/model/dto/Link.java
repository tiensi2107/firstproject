package model.dto;

import com.ntsi.gpxgateway.gson.GPXGson;
import com.ntsi.messageprocessor.userType.DeepCopyable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Link implements DeepCopyable {
    private String href;
    private String text;
    private String type;

    public Link(io.jenetics.jpx.Link link) {
        if (link != null) {
            this.href = link.getHref() != null ? link.getHref().getPath() : null;
            this.text = link.getText().orElse(null);
            this.type = link.getType().orElse(null);
        }
    }

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
        return GPXGson.getGson().fromJson(json, Link.class);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("href", href)
                .append("text", text)
                .append("type", type)
                .toString();
    }

    @Override
    public Object deepCopy() {
        return new Link(this.href, this.text, this.type);
    }
}
