package com.txacon.gap.infrastructure.db.jpa.bussines.mapper;

import org.mapstruct.Mapper;

import java.time.*;
import java.util.Date;

@Mapper(componentModel = "spring")
public class LocalTimeMapper {
    public LocalTime toDomain(Date date) {
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        return LocalTime.of(zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond());
    }

    public Date toEntity(LocalTime localTime) {
        Instant instant = localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
