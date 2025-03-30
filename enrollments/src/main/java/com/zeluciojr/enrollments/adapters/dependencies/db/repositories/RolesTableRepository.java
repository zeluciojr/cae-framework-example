package com.zeluciojr.enrollments.adapters.dependencies.db.repositories;

import com.cae.rdb.DefaultBasicCrudOperations;
import com.zeluciojr.enrollments.adapters.dependencies.db.RolesTable;

public class RolesTableRepository extends DefaultBasicCrudOperations<RolesTable, String> {

    public static final RolesTableRepository SINGLETON = new RolesTableRepository();

}
