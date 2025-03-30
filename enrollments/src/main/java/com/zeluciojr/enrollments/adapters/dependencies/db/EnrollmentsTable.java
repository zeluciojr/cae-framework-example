package com.zeluciojr.enrollments.adapters.dependencies.db;

import com.cae.rdb.TableSchemaFromEntity;
import com.zeluciojr.enrollments.adapters.dependencies.db.utils.FromStringToUUID;
import com.zeluciojr.enrollments.adapters.dependencies.db.utils.FromUUIDToString;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.entities.Person;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "enrollments")
public class EnrollmentsTable extends TableSchemaFromEntity<Enrollment, String> {

    public EnrollmentsTable(Enrollment entity) {
        super(entity);
    }

    public EnrollmentsTable(){
        super(new Enrollment());
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

    public String getPersonId(){
        return FromUUIDToString.getIdAsStringFrom(this.getEntity().getPerson());
    }

    public void setPersonId(String personId){
        this.getEntity().setPerson(null);
        FromStringToUUID.convertToUUID(personId).ifPresent(uuid -> this.getEntity().setPerson(Person.of(uuid)));
    }

    public Boolean getActive(){
        return this.getEntity().getActive();
    }

    public void setActive(Boolean active){
        this.getEntity().setActive(active);
    }

}
