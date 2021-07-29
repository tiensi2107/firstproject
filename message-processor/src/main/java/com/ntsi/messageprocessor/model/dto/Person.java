package com.ntsi.messageprocessor.model.dto;

public class Person {
    private String name;
    private Email email;
    private Link link;

    public Person(io.jenetics.jpx.Person person) {
        if (person != null) {
            this.name = person.getName().orElse(null);
            this.email = new Email(person.getEmail().orElse(null));
            this.link = new Link(person.getLink().orElse(null));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
