package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConFactory
        = new JedisConnectionFactory();
    jedisConFactory.setHostName("localhost");
    jedisConFactory.setPort(6379);
    return jedisConFactory;
  }
//  @Bean
//  JedisConnectionFactory jedisConnectionFactory() {
//    return new JedisConnectionFactory();
//  }
  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    return template;
  }

  @Bean
  CommandLineRunner initDatabase(StudentRepository studentRepository) {

    return args -> {
      Student engStudent = new Student(
          "Eng2015001", "John Doe", Student.Gender.MALE, 1);
      Student medStudent = new Student(
          "Med2015001", "Gareth Houston", Student.Gender.MALE, 2);
      studentRepository.save(engStudent);
      studentRepository.save(medStudent);
    };
  }
}
