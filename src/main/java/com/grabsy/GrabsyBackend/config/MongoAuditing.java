package com.grabsy.GrabsyBackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.time.Instant;

@Configuration
@EnableMongoAuditing
public class MongoAuditing {
}
