package model.dto;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class Metadata implements Serializable{
    private String name;

    private String description;

    private Person author;

    private List<Link> links;

    private LocalDateTime time;

    private String keywords;

    public Metadata() {
    }

    public Metadata(io.jenetics.jpx.Metadata metadata) {
        if (metadata != null) {
            this.name = metadata.getName().orElse(null);
            this.description = metadata.getDescription().orElse(null);
            this.author = new Person(metadata.getAuthor().orElse(null));

            List<Link> newLinks = new ArrayList<>();
            List<io.jenetics.jpx.Link> links = metadata.getLinks();
            if (CollectionUtils.isNotEmpty(links)) {
                links.forEach(link -> {
                    newLinks.add(new Link(link));
                });
            }

            this.links = newLinks;
            metadata.getTime().ifPresent(zonedDateTime -> this.time = LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneOffset.UTC));

            this.keywords = metadata.getKeywords().orElse(null);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("description", description)
                .append("author", author)
                .append("links", links)
                .append("time", time)
                .append("keywords", keywords)
                .toString();
    }

}
