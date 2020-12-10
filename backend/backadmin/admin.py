from django.contrib import admin
from django.apps import apps

models = apps.get_models()
for model in models:
    if not model.__name__ == 'Group':
        admin.site.register(model)