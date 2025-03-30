package com.zeluciojr.enrollments.adapters.dependencies.db.repositories;

import com.cae.mapped_exceptions.specifics.InternalMappedException;
import com.cae.rdb.DefaultBasicCrudOperations;
import com.zeluciojr.enrollments.adapters.dependencies.db.PeopleTable;
import org.hibernate.Transaction;

import javax.persistence.EntityTransaction;
import java.util.Optional;

public class PeopleTableRepository extends DefaultBasicCrudOperations<PeopleTable, String> {

    public static final PeopleTableRepository SINGLETON = new PeopleTableRepository();

    //This MUST be simplified. It is on the roadmap to be addressed. Hold on!
    public Optional<PeopleTable> findByLegalId(String value) {
        try (var session = this.getSessionFactoryOrThrow().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                var hql = "SELECT e FROM " + this.getEntityName() + " e WHERE e.legalId = '" + value + "'";
                var resultSet = session.createQuery(hql, this.getEntityType()).uniqueResultOptional();
                transaction.commit();
                return resultSet;
            } catch (Exception exception) {
                Optional.ofNullable(transaction).ifPresent(EntityTransaction::rollback);
                throw new InternalMappedException(
                        "Something went wrong while trying to execute findByLegalId",
                        exception.toString()
                );
            }
        }
    }
}
