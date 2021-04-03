package com.txacon.gap.integration.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThrows;

import com.txacon.gap.application.api.CustomerService;
import com.txacon.gap.application.exceptions.CustomerAlreadyExistsException;
import com.txacon.gap.application.exceptions.CustomerNotFoundException;
import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.infrastructure.rest.api.CustomerController;
import com.txacon.gap.infrastructure.rest.dto.customer.CustomerDTO;
import com.txacon.gap.integration.CucumberSpringConfiguration;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class AddCustomerSteps extends CucumberSpringConfiguration {

  @Autowired
  private CustomerController customerController;

  @Autowired
  private CustomerService customerService;


  @Given("^A system without the following clients$")
  public void aSystemWithoutCustomers(DataTable dataTable) {
    dataTable.asList().forEach(e ->
        assertThrows(CustomerNotFoundException.class, () -> customerService.getByEmail(e))
    );
  }

  @Given("^A system with the following clients$")
  public void systemWithFollowingClients(DataTable dataTable) {
    final List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    rows.stream().map(e ->
        CustomerDTO.builder()
            .active(true)
            .email(e.get("email"))
            .password(e.get("password"))
            .firstName(e.get("firstName"))
            .lastName(e.get("lastName"))
            .username(e.get("username"))
            .build()
    ).forEach(customerController::createCustomer);
  }

  @When("^The customers enters their data in the system$")
  public void addCustomerData(DataTable dataTable) {
    final List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    rows.stream().map(e ->
        CustomerDTO.builder()
            .active(true)
            .email(e.get("email"))
            .password(e.get("password"))
            .firstName(e.get("firstName"))
            .lastName(e.get("lastName"))
            .username(e.get("username"))
            .build()
    ).forEach(customerController::createCustomer);
  }

  @Given("A system without customers")
  public void aSystemWithoutCustomers() {

  }

  @Then("Clients have been created with the following data")
  public void clientsHaveBeenCreatedWithTheFollowingData(DataTable dataTable) {
    final List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    rows.forEach(e -> {
      final String email = e.get("email");
      final String firstName = e.get("firstName");
      final String lastName = e.get("lastName");
      final String username = e.get("username");
      final Customer result = customerService.getByEmail(email);
      assertThat(result, is(notNullValue()));
      assertThat(result.getId(), is(notNullValue()));
      assertThat(result.getFirstName(), is(firstName));
      assertThat(result.getLastName(), is(lastName));
      assertThat(result.getUsername(), is(username));
    });
  }

  @Then("^The customers enters their data in the system, and the system return error$")
  public void customerAllreadyExists(DataTable dataTable) {
    final List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    rows.stream().map(e ->
        CustomerDTO.builder()
            .active(true)
            .email(e.get("email"))
            .password(e.get("password"))
            .firstName(e.get("firstName"))
            .lastName(e.get("lastName"))
            .username(e.get("username"))
            .build()
    ).forEach(e ->
        assertThrows(CustomerAlreadyExistsException.class,
            () -> customerController.createCustomer(e)));
  }
}
