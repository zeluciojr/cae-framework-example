package com.zeluciojr.enrollments.adapters.dependencies.db;

import com.cae.rdb.TableSchemaFromEntity;
import com.zeluciojr.enrollments.adapters.dependencies.db.utils.FromStringToUUID;
import com.zeluciojr.enrollments.adapters.dependencies.db.utils.FromUUIDToString;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.entities.Experience;
import com.zeluciojr.enrollments.core.entities.Role;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "experiences")
public class ExperiencesTable extends TableSchemaFromEntity<Experience, String> {

    public ExperiencesTable(Experience entity) {
        super(entity);
    }

    public ExperiencesTable() {
        super(new Experience());
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

    public String getRoleId(){
        return FromUUIDToString.getIdAsStringFrom(this.getEntity().getRole());
    }

    public void setRoleId(String roleId){
        this.getEntity().setRole(null);
        FromStringToUUID.convertToUUID(roleId).ifPresent(uuid -> this.getEntity().setRole(Role.of(uuid)));
    }

    public LocalDateTime getStartedAt(){
        return this.getEntity().getStartedAt();
    }

    public void setStartedAt(LocalDateTime startedAt){
        this.getEntity().setStartedAt(startedAt);
    }

    public LocalDateTime getEndedAt(){
        return this.getEntity().getEndedAt();
    }

    public void setEndedAt(LocalDateTime endedAt){
        this.getEntity().setEndedAt(endedAt);
    }

    public String getEnrollmentId(){
        return FromUUIDToString.getIdAsStringFrom(this.getEntity().getEnrollment());
    }

    public void setEnrollmentId(String enrollmentId){
        this.getEntity().setEnrollment(null);
        FromStringToUUID.convertToUUID(enrollmentId).ifPresent(uuid -> this.getEntity().setEnrollment(Enrollment.of(uuid)));
    }

}
