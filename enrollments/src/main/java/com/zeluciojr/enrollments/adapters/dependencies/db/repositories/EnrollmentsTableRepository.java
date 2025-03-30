package com.zeluciojr.enrollments.adapters.dependencies.db.repositories;

import com.cae.rdb.DefaultBasicCrudOperations;
import com.zeluciojr.enrollments.adapters.dependencies.db.EnrollmentsTable;

public class EnrollmentsTableRepository extends DefaultBasicCrudOperations<EnrollmentsTable, String> {

    public static final EnrollmentsTableRepository SINGLETON = new EnrollmentsTableRepository();

}
