const localStorage = {
  token: undefined,
  username: undefined,
  password: undefined,
  user: undefined
}
var server = 'http://localhost:8080'

const loadPage = (page) => {
  document.querySelector('#navigator').bringPageTop(page, { animation: 'fade' });
};


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
    loadPage('html/home.html')
  }).fail((error) => {
    console.log("Error: " + error)
    ons.notification.alert('Error en el usuario o password');
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
    console.log("Error: " + error)
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
  const username = document.querySelector('#emailLogin').value;
  const password = document.querySelector('#passwordLogin').value;
  getAuthTokenCall(username, password);
}

const loadUserData = () => {
  var user = localStorage.user;
  document.querySelector('#username').value = user.username;
  document.querySelector('#firstName').value = user.firstName;
  document.querySelector('#lastName').value = user.lastName;
  document.querySelector('#email').value = user.email;
  document.querySelector('#password').value = "";
  document.querySelector('#active').value = user.active;
}

const createUser = () => {
  var user = new Object();
  user.username = document.querySelector('#username').value;
  user.firstName = document.querySelector('#firstName').value;
  user.lastName = document.querySelector('#lastName').value;
  user.email = document.querySelector('#email').value;
  user.password = document.querySelector('#password').value;
  createUserCall(user);
}


document.addEventListener('init', (event) => {
  if (event.target.matches('#user-update.page')) {
    loadUserData();
  }
});

document.addEventListener('prechange', (event) => {
  document.querySelector('ons-toolbar .center')
    .innerHTML = event.tabItem.getAttribute('label');
});