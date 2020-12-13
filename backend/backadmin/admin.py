import sys

from django.apps import apps
from django.contrib import admin
from import_export.admin import ImportExportModelAdmin
from import_export.fields import Field
from import_export.resources import Resource

current_module = sys.modules[__name__]


def model_fields_array(model):
    return [field.name for field in model._meta.fields]


def get_field_dict(model_fields=[]):
    return {k.name: Field(column_name=k.name, attribute=k.name, saves_null_values=True) for k in model_fields}


def create_resource(resource_class_name: str = '', model=None, module: str = '', meta_options=None):
    attrs = {'__module__': module}
    resource_class = type(resource_class_name, (Resource,), attrs)
    resource_class._meta.export_order = model_fields_array(model)
    setattr(resource_class, 'fields', get_field_dict(model._meta.fields))
    return resource_class


def create_ie_admin(class_name: str = '', resource_class=None):
    return type(class_name, (ImportExportModelAdmin,), {'resource_class': resource_class})


def declare_resource(model):
    ie_admin = None
    if 'backadmin.models' in module:
        resource_name = model.__name__ + 'Resource'
        ie_admin = model.__name__ + 'AdminImportExport'
        globals()[resource_name] = create_resource(resource_class_name=resource_name,
                                                   model=model,
                                                   module=current_module,
                                                   meta_options=None)
        globals()[ie_admin] = create_ie_admin(ie_admin, globals()[resource_name])
    return ie_admin


models = apps.get_models()
for model in models:
    name = model.__name__
    if name == 'Group':
        continue
    module = model.__module__
    ie_admin_name = declare_resource(model)
    if ie_admin_name:
        admin.site.register(model, globals()[ie_admin_name])
    else:
        admin.site.register(model)

# for name, obj in inspect.getmembers(current_module):
#     if inspect.isclass(obj):
#         print(obj)
