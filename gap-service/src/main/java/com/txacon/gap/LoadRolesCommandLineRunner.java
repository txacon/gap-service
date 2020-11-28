package com.txacon.gap;

import com.txacon.gap.domain.role.entities.Role;
import com.txacon.gap.domain.role.entities.RoleName;
import com.txacon.gap.domain.role.port.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class LoadRolesCommandLineRunner implements CommandLineRunner {

    private final RoleRepository respository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(RoleName.values()).forEach(e -> {
            if (!respository.findByName(e).isPresent()) {
                respository.save(Role.builder().role(e).build());
            }
        });
    }
}
