package com.txacon.gap;

import com.txacon.gap.domain.common.port.KeyEntityRepository;
import com.txacon.gap.domain.pricerange.entities.PriceRange;
import com.txacon.gap.domain.pricerange.port.PriceRangeRepository;
import com.txacon.gap.domain.rating.entities.AggregateRating;
import com.txacon.gap.domain.rating.port.RatingRepository;
import com.txacon.gap.domain.role.entities.Role;
import com.txacon.gap.domain.role.entities.RoleName;
import com.txacon.gap.domain.role.port.RoleRepository;
import com.txacon.gap.domain.tags.entities.TagName;
import com.txacon.gap.domain.tags.port.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class LoadMasterTablesCommandLineRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final TagRepository tagRepository;
    private final RatingRepository ratingRepository;
    private final PriceRangeRepository priceRangeRepository;

    @Override
    public void run(String... args) throws Exception {
        inserRoles();
        insertKeyEntity(tagRepository, TagName.FISH);
        insertKeyEntity(ratingRepository, AggregateRating.FIVE_START);
        insertKeyEntity(priceRangeRepository, PriceRange.CHEAP);
    }

    private <T extends Enum> void insertKeyEntity(KeyEntityRepository<T> keyEntityRepository, T enumValue) {
        Arrays.stream(enumValue.getDeclaringClass().getEnumConstants()).forEach(e -> {
            if (!keyEntityRepository.findByName(enumValue).isPresent()) {
                keyEntityRepository.save(enumValue);
            }
        });
    }

    private void inserRoles() {
        Arrays.stream(RoleName.values()).forEach(e -> {
            if (!roleRepository.findByName(e).isPresent()) {
                roleRepository.save(Role.builder().role(e).build());
            }
        });
    }
}
