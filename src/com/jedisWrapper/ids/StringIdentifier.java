package com.jedisWrapper.ids;

/**
 * Base class for String Identifiers.
 * User: Jason
 * Date: 18/08/11
 * Time: 06:52
 */
public class StringIdentifier implements Identifier<String> {
    private final String id;

    public StringIdentifier(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && id.equals(((StringIdentifier) o).id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    @Override
    public String toString(){
        return getIdentifier();
    }
}
