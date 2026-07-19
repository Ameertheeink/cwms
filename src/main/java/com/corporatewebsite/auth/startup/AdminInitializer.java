package com.corporatewebsite.auth.startup;

import com.corporatewebsite.auth.entity.AdminUser;
import com.corporatewebsite.auth.enums.Role;
import com.corporatewebsite.auth.repository.AdminUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private static final Logger logger =
            LoggerFactory.getLogger(AdminInitializer.class);

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(AdminUserRepository adminUserRepository,
                            PasswordEncoder passwordEncoder) {

        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (adminUserRepository.count() > 0) {

            logger.info("Admin user already exists.");

            return;
        }

        AdminUser admin = new AdminUser();

        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        admin.setActive(true);

        adminUserRepository.save(admin);

        logger.info("Default admin user created successfully.");
    }

}