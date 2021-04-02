package com.txacon.gap.infrastructure.db.jpa.customer.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.txacon.gap.domain.customer.entities.Phone;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.PhoneEntity;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PhoneMapperImplTest {

  private final EasyRandom easyRandom = new EasyRandom();

  @InjectMocks
  private PhoneMapperImpl phoneMapper;

  @Test
  void toDomain() {
    final PhoneEntity input = easyRandom.nextObject(PhoneEntity.class);

    final Phone result = phoneMapper.toDomain(input);

    assertThat(result, notNullValue());
    assertThat(result.getCountryPrefix(), is(input.getCountryPrefix()));
    assertThat(result.getPhoneNumber(), is(input.getPhoneNumber()));
    assertThat(result.getId(), is(input.getId()));
  }

  @Test
  void toEntity() {
    final Phone input = easyRandom.nextObject(Phone.class);

    final PhoneEntity result = phoneMapper.toEntity(input);

    assertThat(result, notNullValue());
    assertThat(result.getCountryPrefix(), is(input.getCountryPrefix()));
    assertThat(result.getPhoneNumber(), is(input.getPhoneNumber()));
    assertThat(result.getId(), is(input.getId()));
  }
}