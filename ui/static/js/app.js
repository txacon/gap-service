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
      onsItem.innerHTML = '<ons-icon icon="md-information"></ons-icon>&ThinSpace; ' + item.name ;
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
      onsItem.setAttribute('onclick', "setProductId("+ item.id +"); loadPage('html/product.html');");
      onsItem.innerHTML = '<ons-icon icon="md-information"></ons-icon>&ThinSpace; ' + item.name;
      document.getElementById('product-list').appendChild(onsItem);
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
    url: server + '/businesses/' + localStorage.businessId +"/products",
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
    url: server + '/businesses/' + localStorage.businessId +"/products",
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
    url: server + '/businesses/' + localStorage.businessId +"/products/"+product.id,
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
  document.querySelector('#' + fieldName).value = value;
}


document.addEventListener('init', (event) => {
  if (event.target.matches('#user-update.page')) {
    loadUserData();
  }
  if (event.target.matches('#product-update.page')) {
    productLoad();
  }
  if (event.target.matches('#restaurant-update.page')) {
    restaurantLoad();
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
