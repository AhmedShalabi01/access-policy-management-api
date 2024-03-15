package org.example.accesspolicymanagementapi.config;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


//@Configuration
//@RequiredArgsConstructor
//public class MongoDBValidationConfig {
//
//    private final MongoTemplate mongoTemplate;
//
//    @Value("classpath:mongodb-validation-schema.json")
//    private Resource validationSchemaResource;
//
//    @PostConstruct
//    public void applyValidationSchema() throws IOException {
//        Path schemaPath = validationSchemaResource.getFile().toPath();
//        String validationSchema = Files.readString(schemaPath, StandardCharsets.UTF_8);
//        mongoTemplate.executeCommand("{collMod: 'AccessPolicies', validator: " + validationSchema+"}");
//}
//}