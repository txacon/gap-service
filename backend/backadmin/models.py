# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.contrib.auth.base_user import AbstractBaseUser
from django.contrib.auth.models import AbstractUser
from django.db import models


class Address(models.Model):
    address_id = models.BigAutoField(primary_key=True)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)
    city = models.CharField(max_length=255, blank=True, null=True)
    country = models.CharField(max_length=255, blank=True, null=True)
    state = models.CharField(max_length=255, blank=True, null=True)
    street1 = models.CharField(max_length=255, blank=True, null=True)
    street2 = models.CharField(max_length=255, blank=True, null=True)
    zipcode = models.IntegerField(blank=True, null=True)
    customer = models.ForeignKey('Customer', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'address'


class AggregateRating(models.Model):
    aggregate_rating_id = models.CharField(primary_key=True, max_length=255)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'aggregate_rating'


class Business(models.Model):
    business_id = models.BigAutoField(primary_key=True)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)
    active = models.BooleanField()
    city = models.CharField(max_length=255, blank=True, null=True)
    close_hour = models.TimeField(blank=True, null=True)
    country = models.CharField(max_length=255, blank=True, null=True)
    description = models.CharField(max_length=255, blank=True, null=True)
    email = models.CharField(max_length=255, blank=True, null=True)
    fiscal_id = models.CharField(max_length=255, blank=True, null=True)
    name = models.CharField(max_length=255, blank=True, null=True)
    open_hour = models.TimeField(blank=True, null=True)
    phone = models.CharField(max_length=255, blank=True, null=True)
    phone_prefix = models.CharField(max_length=255, blank=True, null=True)
    state = models.CharField(max_length=255, blank=True, null=True)
    street1 = models.CharField(max_length=255, blank=True, null=True)
    street2 = models.CharField(max_length=255, blank=True, null=True)
    zipcode = models.CharField(max_length=255, blank=True, null=True)
    aggregate_rating = models.ForeignKey(AggregateRating, models.DO_NOTHING)
    customer = models.ForeignKey('Customer', models.DO_NOTHING)
    price_range = models.ForeignKey('PriceRange', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'business'


class BusinessPaymentMethod(models.Model):
    business = models.OneToOneField(Business, models.DO_NOTHING, primary_key=True)
    payment_method = models.ForeignKey('PaymentMethod', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'business_payment_method'
        unique_together = (('business', 'payment_method'),)


class Role(models.Model):
    role_id = models.BigAutoField(primary_key=True)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)
    name = models.CharField(unique=True, max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'role'


class Customer(AbstractUser):
    customer_id = models.BigAutoField(primary_key=True)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)
    is_active = models.BooleanField(default=True)
    is_staff = models.BooleanField(default=True)
    is_superuser = models.BooleanField(default=True)
    email = models.CharField(unique=True, max_length=255, blank=True, null=True)
    last_name = models.CharField(max_length=255, blank=True, null=True)
    first_name = models.CharField(max_length=255, blank=True, null=True)
    username = models.CharField(max_length=255, blank=True, null=True)
    password = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        db_table = 'customer'
        unique_together = (('email', 'password'),)

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['password', 'username', 'first_name', 'last_name']

    def date_joined(self):
        return self.create_at


class CustomerRole(models.Model):
    customer = models.OneToOneField(Customer, models.DO_NOTHING, primary_key=True,related_name='customer_roles')
    role = models.OneToOneField(Role, models.DO_NOTHING,related_name='customer_roles')

    class Meta:
        managed = False
        db_table = 'customer_role'
        unique_together = (('customer', 'role'),)


class PaymentMethod(models.Model):
    payment_method_id = models.CharField(primary_key=True, max_length=255)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'payment_method'


class Phone(models.Model):
    phone_id = models.BigAutoField(primary_key=True)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)
    country_prefix = models.CharField(max_length=255, blank=True, null=True)
    phone_number = models.CharField(max_length=255, blank=True, null=True)
    customer = models.ForeignKey(Customer, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'phone'


class PriceRange(models.Model):
    price_range_id = models.CharField(primary_key=True, max_length=255)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'price_range'


class Product(models.Model):
    product_id = models.BigAutoField(primary_key=True)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)
    active = models.BooleanField()
    description = models.CharField(max_length=255, blank=True, null=True)
    name = models.CharField(max_length=255, blank=True, null=True)
    photo_link = models.CharField(max_length=255, blank=True, null=True)
    retail_price = models.DecimalField(max_digits=19, decimal_places=2, blank=True, null=True)
    whole_sale_price = models.DecimalField(max_digits=19, decimal_places=2, blank=True, null=True)
    business = models.ForeignKey(Business, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'product'

class Tag(models.Model):
    tag_id = models.CharField(primary_key=True, max_length=255)
    create_at = models.DateTimeField(blank=True, null=True)
    last_modified = models.DateTimeField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tag'

class ProductTags(models.Model):
    tag = models.OneToOneField(Product, models.DO_NOTHING, related_name='product_tags')
    product = models.OneToOneField(Tag, models.DO_NOTHING, related_name='product_tags')

    class Meta:
        managed = False
        db_table = 'product_tags'




