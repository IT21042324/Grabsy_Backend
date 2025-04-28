package com.grabsy.GrabsyBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // Tells Spring to look for @Transactional and wrap those methods in a transaction proxy
public class MongoConfig {
    /**
     * Exposes a MongoTransactionManager bean in the context.
     *
     * @param dbFactory the factory that provides MongoDB connections
     * @return a transaction manager that can begin/commit/rollback MongoDB transactions
     */
    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);

//        The MongoTransactionManager uses the same factory that the repositories use,
//        so all @Transactional methods will participate in the same Mongo session.

//        When you inject MongoDatabaseFactory:
//        It internally knows your MongoDB URI (application.properties),
//        It knows to connect to your Atlas database,
//        It handles connection pooling and session management.
    }
}
