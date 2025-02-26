# Generated by Django 3.1.4 on 2020-12-14 19:44

import django.contrib.auth.models
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('auth', '0012_alter_user_first_name_max_length'),
    ]

    operations = [
        migrations.CreateModel(
            name='Address',
            fields=[
                ('address_id', models.BigAutoField(primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
                ('city', models.CharField(blank=True, max_length=255, null=True)),
                ('country', models.CharField(blank=True, max_length=255, null=True)),
                ('state', models.CharField(blank=True, max_length=255, null=True)),
                ('street1', models.CharField(blank=True, max_length=255, null=True)),
                ('street2', models.CharField(blank=True, max_length=255, null=True)),
                ('zipcode', models.IntegerField(blank=True, null=True)),
            ],
            options={
                'db_table': 'address',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='AggregateRating',
            fields=[
                ('aggregate_rating_id', models.CharField(max_length=255, primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
            ],
            options={
                'db_table': 'aggregate_rating',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Business',
            fields=[
                ('business_id', models.BigAutoField(primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
                ('active', models.BooleanField()),
                ('city', models.CharField(blank=True, max_length=255, null=True)),
                ('close_hour', models.TimeField(blank=True, null=True)),
                ('country', models.CharField(blank=True, max_length=255, null=True)),
                ('description', models.CharField(blank=True, max_length=255, null=True)),
                ('email', models.CharField(blank=True, max_length=255, null=True)),
                ('fiscal_id', models.CharField(blank=True, max_length=255, null=True)),
                ('name', models.CharField(blank=True, max_length=255, null=True)),
                ('open_hour', models.TimeField(blank=True, null=True)),
                ('phone', models.CharField(blank=True, max_length=255, null=True)),
                ('phone_prefix', models.CharField(blank=True, max_length=255, null=True)),
                ('state', models.CharField(blank=True, max_length=255, null=True)),
                ('street1', models.CharField(blank=True, max_length=255, null=True)),
                ('street2', models.CharField(blank=True, max_length=255, null=True)),
                ('zipcode', models.CharField(blank=True, max_length=255, null=True)),
            ],
            options={
                'db_table': 'business',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='PaymentMethod',
            fields=[
                ('payment_method_id', models.CharField(max_length=255, primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
            ],
            options={
                'db_table': 'payment_method',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Phone',
            fields=[
                ('phone_id', models.BigAutoField(primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
                ('country_prefix', models.CharField(blank=True, max_length=255, null=True)),
                ('phone_number', models.CharField(blank=True, max_length=255, null=True)),
            ],
            options={
                'db_table': 'phone',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='PriceRange',
            fields=[
                ('price_range_id', models.CharField(max_length=255, primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
            ],
            options={
                'db_table': 'price_range',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Product',
            fields=[
                ('product_id', models.BigAutoField(primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
                ('active', models.BooleanField()),
                ('description', models.CharField(blank=True, max_length=255, null=True)),
                ('name', models.CharField(blank=True, max_length=255, null=True)),
                ('photo_link', models.CharField(blank=True, max_length=255, null=True)),
                ('retail_price', models.DecimalField(blank=True, decimal_places=2, max_digits=19, null=True)),
                ('whole_sale_price', models.DecimalField(blank=True, decimal_places=2, max_digits=19, null=True)),
            ],
            options={
                'db_table': 'product',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='ProductTags',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
            ],
            options={
                'db_table': 'product_tags',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Role',
            fields=[
                ('role_id', models.BigAutoField(primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
                ('name', models.CharField(blank=True, max_length=255, null=True, unique=True)),
            ],
            options={
                'db_table': 'role',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Tag',
            fields=[
                ('tag_id', models.CharField(max_length=255, primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
            ],
            options={
                'db_table': 'tag',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Customer',
            fields=[
                ('last_login', models.DateTimeField(blank=True, null=True, verbose_name='last login')),
                ('customer_id', models.BigAutoField(primary_key=True, serialize=False)),
                ('create_at', models.DateTimeField(blank=True, null=True)),
                ('last_modified', models.DateTimeField(blank=True, null=True)),
                ('is_active', models.BooleanField(default=True)),
                ('is_staff', models.BooleanField(default=True)),
                ('is_superuser', models.BooleanField(default=True)),
                ('email', models.CharField(blank=True, max_length=255, null=True, unique=True)),
                ('last_name', models.CharField(blank=True, max_length=255, null=True)),
                ('first_name', models.CharField(blank=True, max_length=255, null=True)),
                ('username', models.CharField(blank=True, max_length=255, null=True)),
                ('password', models.CharField(blank=True, max_length=255, null=True)),
                ('groups', models.ManyToManyField(blank=True, help_text='The groups this user belongs to. A user will get all permissions granted to each of their groups.', related_name='user_set', related_query_name='user', to='auth.Group', verbose_name='groups')),
                ('user_permissions', models.ManyToManyField(blank=True, help_text='Specific permissions for this user.', related_name='user_set', related_query_name='user', to='auth.Permission', verbose_name='user permissions')),
            ],
            options={
                'db_table': 'customer',
                'unique_together': {('email', 'password')},
            },
            managers=[
                ('objects', django.contrib.auth.models.UserManager()),
            ],
        ),
        migrations.CreateModel(
            name='BusinessPaymentMethod',
            fields=[
                ('business', models.OneToOneField(on_delete=django.db.models.deletion.DO_NOTHING, primary_key=True, serialize=False, to='backadmin.business')),
            ],
            options={
                'db_table': 'business_payment_method',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='CustomerRole',
            fields=[
                ('customer', models.OneToOneField(on_delete=django.db.models.deletion.DO_NOTHING, primary_key=True, related_name='customer_roles', serialize=False, to='backadmin.customer')),
            ],
            options={
                'db_table': 'customer_role',
                'managed': False,
            },
        ),
    ]
