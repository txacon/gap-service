package com.txacon.gap.integration.steps;

import static java.lang.Boolean.TRUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.sun.security.auth.UserPrincipal;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.repository.CrudCustomerRepository;
import com.txacon.gap.infrastructure.rest.api.CustomerController;
import com.txacon.gap.infrastructure.rest.dto.customer.CustomerDTO;
import com.txacon.gap.integration.CucumberSpringConfiguration;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Transactional
public class UpdateCustomerSteps extends CucumberSpringConfiguration {

  @Autowired
  private CrudCustomerRepository crud;

  @Autowired
  private CustomerController customerController;

  @Autowired
  private UpdateCustomerWorld world;

  @Given("^A system customer$")
  public void createSystemCustomer(DataTable dataTable) {
    final List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    rows.stream().map(e ->
        CustomerEntity.builder()
            .id(Optional.ofNullable(e.get("id")).map(Long::parseLong).orElse(null))
            .isActive(true)
            .email(e.get("email"))
            .password(e.get("password"))
            .firstName(e.get("firstName"))
            .lastName(e.get("lastName"))
            .username(e.get("username"))
            .build()
    ).forEach(e -> {
      final CustomerEntity customerEntity = crud.save(e);
      assertThat(crud.existsById(customerEntity.getId()), is(TRUE));
      world.setUpdateCustomer(customerEntity);
    });

  }

  @Then("Update customers with following data, and assert that")
  public void updateCustomer(DataTable dataTable) {
    final List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    rows.stream().map(e ->
        CustomerDTO.builder()
            .id(Optional.ofNullable(world.getUpdateCustomer()).map(CustomerEntity::getId)
                .orElse(null))
            .active(true)
            .email(e.get("email"))
            .password(e.get("password"))
            .firstName(e.get("firstName"))
            .lastName(e.get("lastName"))
            .username(e.get("username"))
            .build()
    ).forEach(e -> {
      final ResponseEntity<CustomerDTO> response = customerController
          .updateCustomer(new UserPrincipal(e.getId() + ""), e);
      assertThat(response, is(notNullValue()));
      assertThat(response.getStatusCode(), is(HttpStatus.ACCEPTED));
      final CustomerDTO responseDTO = response.getBody();
      assertThat(responseDTO.getId(), is(e.getId()));
      assertThat(responseDTO.getId(), is(notNullValue()));
      assertThat(responseDTO.getFirstName(), is(e.getFirstName()));
      assertThat(responseDTO.getLastName(), is(e.getLastName()));
      assertThat(responseDTO.getUsername(), is(e.getUsername()));
    });
  }

}
