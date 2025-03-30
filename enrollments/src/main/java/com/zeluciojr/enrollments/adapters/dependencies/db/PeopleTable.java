package com.zeluciojr.enrollments.adapters.dependencies.db;

import com.cae.rdb.TableSchemaFromEntity;
import com.zeluciojr.enrollments.adapters.dependencies.db.utils.FromStringToUUID;
import com.zeluciojr.enrollments.adapters.dependencies.db.utils.FromUUIDToString;
import com.zeluciojr.enrollments.core.entities.LegalId;
import com.zeluciojr.enrollments.core.entities.Person;
import com.zeluciojr.enrollments.core.entities.UnknownLegalIdType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Entity(name = "people")
public class PeopleTable extends TableSchemaFromEntity<Person, String> {

    public PeopleTable(Person entity) {
        super(entity);
    }

    public PeopleTable() {
        super(new Person());
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

    public String getFullName(){
        return this.getEntity().getFullName();
    }

    public void setFullName(String fullName){
        this.getEntity().setFullName(fullName);
    }

    public String getPreferredName(){
        return this.getEntity().getPreferredName();
    }

    public void setPreferredName(String preferredName){
        this.getEntity().setPreferredName(preferredName);
    }

    public String getLegalId(){
        return Optional.ofNullable(this.getEntity().getLegalId()).map(LegalId::getValue).orElse(null);
    }

    public void setLegalId(String unknownLegalIdType){
        this.getEntity().setLegalId(UnknownLegalIdType.of(unknownLegalIdType));
    }

}
