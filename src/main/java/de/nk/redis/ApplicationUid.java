package de.nk.redis;

public class ApplicationUid {
    private String application;
    private String uid;

    public ApplicationUid() {
    }

    public ApplicationUid(String application, String uid) {
        this.application = application;
        this.uid = uid;
    }

    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
