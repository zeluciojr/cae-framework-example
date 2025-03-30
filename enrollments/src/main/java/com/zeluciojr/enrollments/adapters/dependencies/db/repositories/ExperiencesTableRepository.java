package com.zeluciojr.enrollments.adapters.dependencies.db.repositories;

import com.cae.mapped_exceptions.specifics.InternalMappedException;
import com.cae.rdb.DefaultBasicCrudOperations;
import com.zeluciojr.enrollments.adapters.dependencies.db.ExperiencesTable;
import org.hibernate.Transaction;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class ExperiencesTableRepository extends DefaultBasicCrudOperations<ExperiencesTable, String> {

    public static final ExperiencesTableRepository SINGLETON = new ExperiencesTableRepository();

    //This MUST be simplified. It is on the roadmap to be addressed. Hold on!
    public List<ExperiencesTable> findAllByEnrollmentId(String enrollmentId) {
        try (var session = this.getSessionFactoryOrThrow().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                var hql = "SELECT e FROM " + this.getEntityName() + " e WHERE e.enrollmentId = '" + enrollmentId + "'";
                var resultSet = session.createQuery(hql, this.getEntityType()).getResultList();
                transaction.commit();
                return resultSet;
            } catch (Exception exception) {
                Optional.ofNullable(transaction).ifPresent(EntityTransaction::rollback);
                throw new InternalMappedException(
                        "Something went wrong while trying to execute findAllByEnrollmentId",
                        exception.toString()
                );
            }
        }
    }
}
