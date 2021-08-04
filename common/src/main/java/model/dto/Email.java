package model.dto;

public class Email {
    private String id;
    private String domain;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAddress() {
        return this.id + "@" + this.domain;
    }
}
