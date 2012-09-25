package com.jedisWrapper.ids;

/**
 * Identifiers based on Long extend this
 * User: Jason
 * Date: 06/08/11
 * Time: 23:01
 *
 */
public abstract class LongIdentifier implements Identifier<Long> {
    protected final Long id;

    public LongIdentifier( Long id) {
        this.id = id;
    }

    public Long getIdentifier() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        //noinspection SimplifiableIfStatement
        if (this == o) return true;

        return (o instanceof LongIdentifier) && id.equals(((LongIdentifier) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
