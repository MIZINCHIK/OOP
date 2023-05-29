package io.github.mizinchik.dsl;

import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Contains a date of a class that took place
 * as a part of OOP NSU course.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClassHour extends Config {
    LocalDate date;
}
