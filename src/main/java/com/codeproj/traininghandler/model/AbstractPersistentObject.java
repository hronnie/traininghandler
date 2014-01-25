package com.codeproj.traininghandler.model;

public abstract class AbstractPersistentObject {
    private String id = IdGenerator.createId();

    private Integer version = null;


    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer value) {
        this.version = value;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AbstractPersistentObject )) {
            return false;
        }
            
        AbstractPersistentObject other  = (AbstractPersistentObject ) o;
            
        // if the id is missing, return false
        if (id == null) return false;
            
        // equivalence by id
        return id.equals(other.getId());
    }

    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    public String toString() {
        return this.getClass().getName()
            + "[id=" + id + "]";
    }

    public boolean isCreation() {
        return version == null;
    }

    public void regenerateId() {
        id = IdGenerator.createId();
        version = null;
    }

    public AbstractPersistentObject() {
    }

}