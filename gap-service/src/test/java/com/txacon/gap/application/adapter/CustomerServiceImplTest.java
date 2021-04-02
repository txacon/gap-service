package com.txacon.gap.application.adapter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.txacon.gap.application.exceptions.CustomerInvalidException;
import com.txacon.gap.application.exceptions.CustomerNotFoundException;
import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.domain.customer.port.CustomerRepository;
import com.txacon.gap.domain.role.port.RoleRepository;
import java.util.Optional;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceImplTest {

  private final EasyRandom easyRandom = new EasyRandom();

  @Mock
  private CustomerRepository repository;

  @Mock
  private RoleRepository roleRepository;

  @InjectMocks
  private CustomerServiceImpl customerService;

  @Test
  public void shouldGetById() {
    final long customerId = easyRandom.nextLong();
    final Customer customer = easyRandom.nextObject(Customer.class);
    when(repository.findById(customerId)).thenReturn(Optional.of(customer));

    final Customer result = customerService.getById(customerId);

    assertThat(result, is(customer));
  }

  @Test
  public void shouldNotGetById() {
    final long customerId = easyRandom.nextLong();
    when(repository.findById(customerId)).thenReturn(Optional.empty());

    assertThrows(CustomerNotFoundException.class, () -> customerService.getById(customerId));
  }

  @Test
  public void shouldGetByEmail() {
    final String email = easyRandom.nextObject(String.class);
    final Customer customer = easyRandom.nextObject(Customer.class);
    when(repository.findByEmail(email)).thenReturn(Optional.of(customer));

    final Customer result = customerService.getByEmail(email);

    assertThat(result, is(customer));
  }

  @Test
  public void shouldNotGetByEmail() {
    final String email = easyRandom.nextObject(String.class);
    when(repository.findByEmail(email)).thenReturn(Optional.empty());

    assertThrows(CustomerNotFoundException.class, () -> customerService.getByEmail(email));
  }

  @Test
  public void shouldGetByEmailAndPassword() {
    final String email = easyRandom.nextObject(String.class);
    final String password = easyRandom.nextObject(String.class);
    final Customer customer = easyRandom.nextObject(Customer.class);
    when(repository.findByEmailAndPassword(email, password)).thenReturn(Optional.of(customer));

    final Customer result = customerService.getByEmailAndPassword(email, password);

    assertThat(result, is(customer));
  }

  @Test
  public void shouldNotGetByEmailAndPassword() {
    final String email = easyRandom.nextObject(String.class);
    final String password = easyRandom.nextObject(String.class);
    when(repository.findByEmailAndPassword(email, password)).thenReturn(Optional.empty());

    assertThrows(CustomerNotFoundException.class,
        () -> customerService.getByEmailAndPassword(email, password));
  }

  @Test
  public void shouldDeleteById() {
    final long customerId = easyRandom.nextLong();
    final Customer customer = easyRandom.nextObject(Customer.class);
    when(repository.findById(customerId)).thenReturn(Optional.of(customer));

    customerService.deleteById(customerId);

    verify(repository).deleteById(customer.getId());
  }

  @Test
  public void shouldNotDeleteById() {
    final long customerId = easyRandom.nextLong();
    when(repository.findById(customerId)).thenReturn(Optional.empty());

    assertThrows(CustomerNotFoundException.class, () -> customerService.deleteById(customerId));
  }

  @Test
  public void shouldUpdate() {
    final Customer customer = easyRandom.nextObject(Customer.class);
    when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));
    when(repository.update(customer)).thenReturn(customer);

    final Customer result = customerService.update(customer);

    assertThat(result, is(customer));
  }

  @Test
  public void shouldNotUpdate() {
    final Customer customer = easyRandom.nextObject(Customer.class);
    customer.setId(null);

    assertThrows(CustomerInvalidException.class, () -> customerService.update(customer));
  }

  @Test
  public void shouldAddCustomer() {
    final Customer customer = easyRandom.nextObject(Customer.class);
    customer.setId(null);
    when(repository.save(customer)).thenReturn(customer);

    final Customer result = customerService.addCustomer(customer);

    assertThat(result, is(notNullValue()));
    assertThat(result.getAddresses(), is(customer.getAddresses()));
    assertThat(result.getEmail(), is(customer.getEmail()));
    assertThat(result.getFirstName(), is(customer.getFirstName()));
    assertThat(result.getPassword(), is(customer.getPassword()));
    assertThat(result.getLastName(), is(customer.getLastName()));
    assertThat(result.getUsername(), is(customer.getUsername()));
  }

  @Test
  public void shouldNotAddCustomerByEmail() {
    final Customer customer = easyRandom.nextObject(Customer.class);
    customer.setId(null);
    customer.setEmail(null);

    assertThrows(CustomerInvalidException.class, () -> customerService.addCustomer(customer));
  }

  @Test
  public void shouldNotAddCustomerByPassword() {
    final Customer customer = easyRandom.nextObject(Customer.class);
    customer.setId(null);
    customer.setPassword(null);

    assertThrows(CustomerInvalidException.class, () -> customerService.addCustomer(customer));
  }

  @Test
  public void shouldNotAddCustomerById() {
    final Customer customer = easyRandom.nextObject(Customer.class);

    assertThrows(CustomerInvalidException.class, () -> customerService.addCustomer(customer));
  }

}