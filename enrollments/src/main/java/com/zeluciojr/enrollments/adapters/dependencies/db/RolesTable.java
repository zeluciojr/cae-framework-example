package com.zeluciojr.enrollments.adapters.dependencies.db;

import com.cae.rdb.TableSchemaFromEntity;
import com.zeluciojr.enrollments.adapters.dependencies.db.utils.FromStringToUUID;
import com.zeluciojr.enrollments.adapters.dependencies.db.utils.FromUUIDToString;
import com.zeluciojr.enrollments.core.entities.Role;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "roles")
public class RolesTable extends TableSchemaFromEntity<Role, String> {

    public RolesTable(Role entity) {
        super(entity);
    }

    public RolesTable() {
        super(new Role());
    }

    @Id
    @Override
    public String getPrimaryKey() {
        return FromUUIDToString.getIdAsStringFrom(this.getEntity());
    }

    @Override
    public void setPrimaryKey(String id) {
        this.getEntity().setId(null);
        FromStringToUUID.convertToUUID(id).ifPresent(uuid -> this.getEntity().setId(uuid));
    }

    public String getName() {
        return this.getEntity().getName();
    }

    public void setName(String name) {
        this.getEntity().setName(name);
    }

    public String getDescription() {
        return this.getEntity().getDescription();
    }

    public void setDescription(String description) {
        this.getEntity().setDescription(description);
    }

    public Boolean getActive() {
        return this.getEntity().getActive();
    }

    public void setActive(Boolean active) {
        this.getEntity().setActive(active);
    }
}
