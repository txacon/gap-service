const server = 'http://localhost:8081/gap-service';

const localStorage = {
  token: undefined,
  username: undefined,
  password: undefined,
  user: undefined,
  businessId: undefined,
  productId: undefined
}

const setProductId = (productId) => {
  localStorage.productId = productId;
}

const setBusinessId = (businessId) => {
  localStorage.businessId = businessId;
}

const restaurantsLoad = () => {
  $.ajax({
    type: 'GET',
    url: server + '/businesses',
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data));
    $("#business-list").empty();
    data.forEach(item => {
      var onsItem = document.createElement('ons-list-item');
      onsItem.setAttribute('modifier', "chevron");
      onsItem.setAttribute('onclick', "setBusinessId(" + item.id + "); loadPage('html/restaurant.html');");
      onsItem.innerHTML = '<ons-icon icon="md-information"></ons-icon>&ThinSpace; ' + item.name;
      document.getElementById('business-list').appendChild(onsItem);
    });
  }).fail((error) => {
    console.error("Error: " + error)
    ons.notification.alert('Error en la obtención de los businesses');
  })
}

const restaurantsLoadToSelect = () => {
  $.ajax({
    type: 'GET',
    url: server + '/businesses/all',
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data));
    $("#business-list").empty();
    data.forEach(item => {
      var onsItem = document.createElement('ons-list-item');
      onsItem.setAttribute('modifier', "chevron");
      onsItem.setAttribute('onclick', "setBusinessId(" + item.id + "); loadPage('html/productsSelection.html');");
      onsItem.innerHTML = '<ons-icon icon="md-information"></ons-icon>&ThinSpace; ' + item.name;
      document.getElementById('business-list').appendChild(onsItem);
    });
  }).fail((error) => {
    console.error("Error: " + error)
    ons.notification.alert('Error en la obtención de los businesses');
  })
}

const restaurantLoad = () => {
  $.ajax({
    type: 'GET',
    url: server + '/businesses/' + localStorage.businessId,
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.error("Xhr response: " + JSON.stringify(data));
    loadBusiness(data);
  }).fail((error) => {
    console.error("Error: " + error)
    ons.notification.alert('Error en la obtención de los businesses');
  })
}


const productsLoad = () => {
  $.ajax({
    type: 'GET',
    url: server + '/businesses/' + localStorage.businessId + '/products',
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data));
    $("#product-list").empty();
    data.forEach(item => {
      var onsItem = document.createElement('ons-list-item');
      onsItem.setAttribute('modifier', "chevron");
      onsItem.setAttribute('onclick', "setProductId(" + item.id + "); loadPage('html/product.html');");
      onsItem.innerHTML = '<ons-icon icon="md-information"></ons-icon>&ThinSpace; ' + item.name;
      document.getElementById('product-list').appendChild(onsItem);
    });
  }).fail((error) => {
    console.error("Error: " + error)
    ons.notification.alert('Error en la obtención de los businesses');
  })
}

const productsSelection = () => {
  $.ajax({
    type: 'GET',
    url: server + '/businesses/' + localStorage.businessId + '/products',
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data));
    $("#product-list").empty();
    var counter = 0
    data.forEach(item => {
      var onsItem = document.createElement('ons-list-item');
      onsItem.setAttribute('expandable', '');
      var label_check = document.createElement('label');
      label_check.setAttribute('class', 'left');
      var check_box = document.createElement('ons-checkbox');
      check_box.setAttribute('input-id', 'check-' + counter);
      label_check.appendChild(check_box);
      var label_name = document.createElement('label');
      label_name.setAttribute('for', 'check-' + counter);
      label_name.setAttribute('class', 'center');
      label_name.innerHTML = item.name;
      var label_price = document.createElement('label');
      label_price.innerHTML = item.wholeSalePrice + " €";
      label_price.setAttribute('class', 'right');

      var div_expandable = document.createElement('div');
      div_expandable.setAttribute('class', 'expandable-content');
      var lable_numele = document.createElement('label');
      lable_numele.setAttribute('class', 'left');
      lable_numele.innerHTML = 'Cantidad: '
      var num_ele = document.createElement('ons-input');
      num_ele.setAttribute('type', 'number');
      num_ele.setAttribute('class', 'right');
      num_ele.setAttribute('placeholder', 'Cantidad');
      num_ele.value = 1;
      var label_description = document.createElement('label');
      label_description.innerHTML = item.description;
      var div_description = document.createElement('div');
      div_description.appendChild(label_description);
      var div_numele = document.createElement('div');
      div_numele.appendChild(lable_numele);
      div_numele.appendChild(num_ele);
      div_expandable.appendChild(div_description);
      div_expandable.appendChild(document.createElement("br"))
      div_expandable.appendChild(div_numele);

      onsItem.appendChild(label_check);
      onsItem.appendChild(label_name);
      onsItem.appendChild(label_price);
      onsItem.appendChild(div_expandable);
      document.getElementById('product-list').appendChild(onsItem);
      counter = counter + 1;
    });
  }).fail((error) => {
    console.error("Error: " + error)
    ons.notification.alert('Error en la obtención de los businesses');
  })
}

const productLoad = () => {
  $.ajax({
    type: 'GET',
    url: server + '/businesses/' + localStorage.businessId + '/products/' + localStorage.productId,
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.error("Xhr response: " + JSON.stringify(data));
    productLoadForm(data);
  }).fail((error) => {
    console.error("Error: " + error)
    ons.notification.alert('Error en la obtención de los businesses');
  })
}

const downloadMenu = () => {
  var params = {
    access_token: 'An access_token',
    other_header: 'other_header'
  };
  var url = server + '/businesses/' + localStorage.businessId + '/menu'
  window.open(url, '_blank');
}

const getUserInfoCall = () => {
  $.ajax({
    type: 'GET',
    url: server + '/customers/me',
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    },
  }).done((data) => {
    localStorage['user'] = new Object(data);
  }).fail((error) => {
    console.error("Error: " + error)
    ons.notification.alert('Error en la obtención de información de usuario');
  })
}

const getAuthTokenCall = (username, password) => {
  $.ajax({
    type: 'POST',
    url: server + '/login',
    data: JSON.stringify({ username: username, password: password }),
    contentType: 'application/json'
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data))
    localStorage.token = data.token;
    localStorage.username = username;
    localStorage.password = password;
    getUserInfoCall();
    loadPage('html/home.html')
  }).fail((error) => {
    console.error("Error: " + error)
    ons.notification.alert('Error en el usuario o password');
  })
}

const createUserCall = (user) => {
  $.ajax({
    type: 'POST',
    url: server + '/customers',
    data: JSON.stringify(user),
    contentType: 'application/json'
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data))
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en la creación de usuario');
  })
}


const updateUserCall = (user) => {
  $.ajax({
    type: 'PUT',
    url: server + '/customers',
    data: JSON.stringify(user),
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data))
    loadPage('index.html')
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en la creación de usuario');
  })
}


const createBusinessCall = (business) => {
  $.ajax({
    type: 'POST',
    url: server + '/businesses',
    data: JSON.stringify(business),
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data))
    loadPage('html/restaurants.html');
    restaurantsLoad();
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en la creación de negocio');
  })
}

const updateBusinessCall = (business) => {
  $.ajax({
    type: 'PUT',
    url: server + '/businesses',
    data: JSON.stringify(business),
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data))
    loadPage('html/restaurants.html');
    restaurantsLoad();
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en la creación de negocio');
  })
}

const createProductCall = (product) => {
  $.ajax({
    type: 'POST',
    url: server + '/businesses/' + localStorage.businessId + "/products",
    data: JSON.stringify(product),
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data))
    loadPage('html/products.html')
    productsLoad();
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en la creación de producto');
  })
}

const updateProductCall = (product) => {
  $.ajax({
    type: 'PUT',
    url: server + '/businesses/' + localStorage.businessId + "/products",
    data: JSON.stringify(product),
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data));
    loadPage('html/products.html');
    productsLoad();
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en la actualización de producto');
  })
}

const deleteProductCall = (product) => {
  $.ajax({
    type: 'DELETE',
    url: server + '/businesses/' + localStorage.businessId + "/products/" + product.id,
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer ' + localStorage.token);
    }
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data));
    loadPage('html/products.html');
    productsLoad();
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en la actualización de producto');
  })
}

const loadPage = (page) => {
  document.querySelector('#navigator').bringPageTop(page, { animation: 'fade' });
}

const setCurrent = (currentId) => {
  localStorage.current = currentId;
}

const logout = () => {
  localStorage = undefined;
  loadPage('index.html');
}

const createBusiness = () => {
  var business = extractFormBusinessData();
  business.id = undefined;
  createBusinessCall(business);
}

const updateBusiness = () => {
  var business = extractFormBusinessData();
  updateBusinessCall(business);
}

const login = () => {
  const username = getValue('emailLogin');
  const password = getValue('passwordLogin');
  getAuthTokenCall(username, password);
}

const createProduct = () => {
  var product = extractProductForm();
  createProductCall(product);
}

const updateProduct = () => {
  var product = extractProductForm();
  updateProductCall(product);
}

const deleteProduct = () => {
  var product = extractProductForm();
  deleteProductCall(product);
}

const loadUserData = () => {
  var user = localStorage.user;
  setValue('id', user.id);
  setValue('username', user.username);
  setValue('firstName', user.firstName);
  setValue('lastName', user.lastName);
  setValue('email', user.email);
  setValue('password', user.password);
  setValue('active', user.active)
}

const createUser = () => {
  var user = new Object();
  user.username = getValue('username');
  user.firstName = getValue('firstName');
  user.lastName = getValue('lastName');
  user.email = getValue('email');
  user.password = getValue('password');
  createUserCall(user);
}

const updateUser = () => {
  var user = new Object();
  user.id = getValue('id')
  user.username = getValue('username');
  user.firstName = getValue('firstName');
  user.lastName = getValue('lastName');
  user.email = getValue('email');
  user.password = getValue('password');
  updateUserCall(user);
}

const loadBusiness = (business) => {
  setValue('active', business.active);
  setValue('city', business.city);
  setValue('closeHour', business.closeHour);
  setValue('country', business.country);
  setValue('description', business.description);
  setValue('email', business.email);
  setValue('fiscalId', business.fiscalId);
  setValue('name', business.name);
  setValue('openHour', business.openHour);
  setValue('phone', business.phone);
  setValue('phonePrefix', business.phonePrefix);
  setValue('state', business.state);
  setValue('street1', business.street1);
  setValue('street2', business.street2);
  setValue('zipcode', business.zipcode);
  setValue('aggregateRating', business.aggregateRating);
  setValue('id', business.id);
  setValue('priceRange', business.priceRange);
}

const resetRestaurantForm = () => {
  setValue('active', undefined);
  setValue('city', undefined);
  setValue('closeHour', undefined);
  setValue('country', undefined);
  setValue('description', undefined);
  setValue('email', undefined);
  setValue('fiscalId', undefined);
  setValue('name', undefined);
  setValue('openHour', undefined);
  setValue('phone', undefined);
  setValue('phoneprefix', undefined);
  setValue('state', undefined);
  setValue('street1', undefined);
  setValue('street2', undefined);
  setValue('zipcode', undefined);
  setValue('aggregateRating', undefined);
  setValue('id', undefined);
  setValue('priceRange', undefined);
}


const extractProductForm = () => {
  var product = new Object();
  product.id = getValue('product_id');
  product.active = getValue('product_active');
  product.description = getValue('product_description');
  product.name = getValue('product_name');
  product.photoLink = getValue('product_photoLink');
  product.retailPrice = getValue('product_retailPrice');
  product.wholeSalePrice = getValue('product_wholeSalePrice');
  return product;
}


const productLoadForm = (product) => {
  setValue('product_id', product.id);
  setValue('product_active', product.active);
  setValue('product_description', product.description);
  setValue('product_name', product.name);
  setValue('product_photoLink', product.photoLink);
  setValue('product_retailPrice', product.retailPrice);
  setValue('product_wholeSalePrice', product.wholeSalePrice);
}

const resetProductForm = () => {
  setValue('product_id', undefined);
  setValue('product_active', undefined);
  setValue('product_description', undefined);
  setValue('product_name', undefined);
  setValue('product_photoLink', undefined);
  setValue('product_retailPrice', undefined);
  setValue('product_wholeSalePrice', undefined);
}


const getValue = (fieldName) => {
  if (!document.querySelector('#' + fieldName)) {
    console.error("No found field: " + fieldName);
    return undefined;
  }
  return document.querySelector('#' + fieldName).value;
}

const setValue = (fieldName, value) => {
  if (!document.querySelector('#' + fieldName)) {
    console.error("No found field: " + fieldName);
    return undefined;
  }
  if (value === undefined){
    document.querySelector('#' + fieldName).empty();
  }
  else {
    document.querySelector('#' + fieldName).value = value;
  }
}


document.addEventListener('init', (event) => {
  if (event.target.matches('#user-update.page')) {
    loadUserData();
  }
  else if (event.target.matches('#product-update.page')) {
    productLoad();
  }
  else if (event.target.matches('#restaurant-update.page')) {
    restaurantLoad();
  }
  else if (event.target.matches('#restaurants.page')) {
    restaurantsLoad();
  }
  else if (event.target.matches('#new_order.page')) {
    restaurantsLoadToSelect();
  }
  else if (event.target.matches('#products-selection.page')) {
    productsSelection();
  }
  else if (event.target.matches('#product-new.page')) {
    resetProductForm();
  }
  else if (event.target.matches('#restaurant-new.page')) {
    resetRestaurantForm();
  }

});

document.addEventListener('prechange', (event) => {
  document.querySelector('ons-toolbar .center')
    .innerHTML = event.tabItem.getAttribute('label');
});

const extractFormBusinessData = () => {
  var business = new Object();
  business.active = getValue('active');
  business.city = getValue('city');
  business.closeHour = getValue('closeHour');
  business.country = getValue('country');
  business.description = getValue('description');
  business.email = getValue('email');
  business.fiscalId = getValue('fiscalId');
  business.name = getValue('name');
  business.openHour = getValue('openHour');
  business.phone = getValue('phone');
  business.phonePrefix = getValue('phonePrefix');
  business.state = getValue('state');
  business.street1 = getValue('street1');
  business.street2 = getValue('street2');
  business.zipcode = getValue('zipcode');
  business.aggregateRating = getValue('aggregateRating');
  business.own = localStorage.user.id;
  business.id = getValue('id');
  business.priceRange = getValue('priceRange');
  return business;
}
