const localStorage = {
  token : undefined,
  username : undefined,
  password : undefined,
  user:  undefined
}

const loadPage = (page) => {
  document.querySelector('#navigator').bringPageTop(page, { animation: 'fade' });
};


const getUserInfoCall = () => {
  $.ajax({
    type: 'GET',
    url: 'http://localhost:8082/customers/me',
    contentType: 'application/json',
    beforeSend: function (xhr) {   //Include the bearer token in header
      xhr.setRequestHeader("Authorization", 'Bearer '+ localStorage.token);
    },
  }).done((data) => {
    localStorage['user'] = new Object(data);
    loadPage('html/home.html')
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en el usuario o password');
  })
}

const getAuthTokenCall = (username, password) => {
  $.ajax({
    type: 'POST',
    url: 'http://localhost:8082/login',
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
    console.log("Error: " + error)
    ons.notification.alert('Error en el usuario o password');
  })
}

const createUserCall = (user) => {
  $.ajax({
    type: 'POST',
    url: 'http://localhost:8082/customers',
    data: JSON.stringify(user),
    contentType: 'application/json'
  }).done((data) => {
    console.log("Xhr response: " + JSON.stringify(data))
    loadPage('index.html')
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en la creación de usuario');
  })
}

const logout = () => {
  localStorage = undefined;
  loadPage('index.html');
}


const login = () => {
  const username = document.querySelector('#username').value;
  const password = document.querySelector('#password').value;
  getAuthTokenCall(username, password);
}

const loadUserData = () => {
  var user = localStorage.user;
  document.querySelector('#name').value = user.name;
  document.querySelector('#midname').value = user.midName;
  document.querySelector('#lastname').value = user.lastName;
  document.querySelector('#email').value = user.email;
  document.querySelector('#password').value = "";
  document.querySelector('#active').value = user.active;
}

const createUser = () => {
  var user = new Object();
  user.name = document.querySelector('#name').value;
  user.midName = document.querySelector('#midname').value;
  user.lastName = document.querySelector('#lastname').value;
  user.email = document.querySelector('#email').value;
  user.password = document.querySelector('#password').value;
  createUserCall(user);
}


document.addEventListener('init', (event) => {
  if (event.target.matches('#user-update.page')) {
    ons.notification.alert('Page 1 is initiated.');
    loadUserData();
  }
});

document.addEventListener('prechange', (event) => {
  document.querySelector('ons-toolbar .center')
    .innerHTML = event.tabItem.getAttribute('label');
});