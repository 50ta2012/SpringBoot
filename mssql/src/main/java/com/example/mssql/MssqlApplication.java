package com.example.mssql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

// import com.example.mssql.entity.Profile;
import com.example.mssql.entity.ProfileRepository;

@SpringBootApplication
public class MssqlApplication {

  public static void main(String[] args) {
    SpringApplication.run(MssqlApplication.class, args);
  }

  @Autowired
  ProfileRepository profileRepository;

  @Bean
  CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return arg -> {
      // Profile profile = new Profile("Berlin", 30, "Rotary");
      // System.out.println(profileRepository.save(profile));

      // System.out.println(profileRepository.findAll());
    };
  }

}
