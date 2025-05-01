package com.grabsy.GrabsyBackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
// import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// TODO : Finish implementing this class and figuring out how and what testing approach to go with
@SpringBootTest
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-test.properties")
public class CatalogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /**
     * This line creates a MongoDBContainer instance from the Testcontainers library.
     * It uses the mongo:6.0 Docker image to spin up a temporary MongoDB container for testing purposes.
     * This container provides an isolated MongoDB instance that can be used during the test.
     * Docker needs to be installed for this to work.
     */
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");

    /**
     * This is a static initializer block. It is executed once when the class is loaded into memory.
     * In this case, it starts the MongoDB container (mongoDBContainer.start()), ensuring that the container is running before any tests are executed.
     */
    static {
        mongoDBContainer.start();
    }

    /**
     * The @DynamicPropertySource annotation is used to dynamically set properties for the Spring application context during tests.
     * The setProperties method registers the MongoDB container's connection URI (mongoDBContainer::getReplicaSetUrl) as the value for the spring.data.mongodb.uri property.
     * This ensures that the application connects to the MongoDB container during the test.
     * @param registry
     */
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    // @Test
    public void testCatalogBrowseProduct() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/catalog"))
                .andExpect(status().isOk());
    }
}
