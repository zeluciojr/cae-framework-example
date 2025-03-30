package com.zeluciojr.enrollments.assemblers.db;

import com.cae.env_vars.EnvVarRetriever;
import com.cae.rdb.CaeRdbConnectionFactory;
import com.cae.rdb.DefaultBasicCrudOperations;
import com.cae.rdb.TableSchema;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.EnrollmentsTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.ExperiencesTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.PeopleTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.RolesTableRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DbBootstrap extends CaeRdbConnectionFactory {

    public static final DbBootstrap SINGLETON = new DbBootstrap();

    @Override
    protected List<DefaultBasicCrudOperations<? extends TableSchema<?>, ?>> initializeAllRepositories() {
        return List.of(
                EnrollmentsTableRepository.SINGLETON,
                ExperiencesTableRepository.SINGLETON,
                PeopleTableRepository.SINGLETON,
                RolesTableRepository.SINGLETON
        );
    }

    @Override
    protected String getJdbcUrl() {
        return EnvVarRetriever.getEnvVarByNameAsString("DB_JDBC_URL");
    }

    @Override
    protected String getDbUser() {
        return EnvVarRetriever.getEnvVarByNameAsString("DB_USER");
    }

    @Override
    protected String getDbUserSecret() {
        return EnvVarRetriever.getEnvVarByNameAsString("DB_PASS");
    }

    @Override
    protected String getDbPoolConnectionDriver() {
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    protected Integer getDbPoolConnectionMaxSize() {
        return 20;
    }

    @Override
    protected Integer getDbPoolConnectionMinIdle() {
        return 5;
    }

    @Override
    protected Integer getDbPoolConnectionIdleTimeout() {
        return 30000;
    }

    @Override
    protected String getDialectOption() {
        return  "org.hibernate.dialect.MySQL8Dialect";
    }

    @Override
    protected Boolean getShowSqlOption() {
        return false;
    }

    @Override
    protected String getDdlAutoTypeOption() {
        return "update";
    }
}
