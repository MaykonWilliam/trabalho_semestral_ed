package domain.relationships;

public class OneToOneRelationship<T> {
    private T entity;
    private final Class<T> entityClass;
    private final String foreignKeyField;

    public OneToOneRelationship(Class<T> entityClass, String foreignKeyField) {
        this.entityClass = entityClass;
        this.foreignKeyField = foreignKeyField;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public String getForeignKeyFieldName() {
        return foreignKeyField;
    }
}
