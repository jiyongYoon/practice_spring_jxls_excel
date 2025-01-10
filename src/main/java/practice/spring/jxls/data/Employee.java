package practice.spring.jxls.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter // 데이터 가져와서 기입할 때 getter 사용하여 필수!
public class Employee {

  private String name;
  private LocalDate birthDate;
  private BigDecimal payment;


  public static List<Employee> generateSampleEmployeeData() {
    List<Employee> employees = new ArrayList<>();

    employees.add(Employee.builder()
        .name("Elsa")
        .birthDate(LocalDate.of(1970, 7, 10))
        .payment(new BigDecimal("1500"))
        .build());

    employees.add(Employee.builder()
        .name("Oleg")
        .birthDate(LocalDate.of(1973, 4, 30))
        .payment(new BigDecimal("2300"))
        .build());

    employees.add(Employee.builder()
        .name("Neil")
        .birthDate(LocalDate.of(1975, 10, 5))
        .payment(new BigDecimal("2500"))
        .build());

    employees.add(Employee.builder()
        .name("Maria")
        .birthDate(LocalDate.of(1978, 1, 7))
        .payment(new BigDecimal("1700"))
        .build());

    employees.add(Employee.builder()
        .name("John")
        .birthDate(LocalDate.of(1969, 5, 30))
        .payment(new BigDecimal("2800"))
        .build());

    return employees;
  }
}
