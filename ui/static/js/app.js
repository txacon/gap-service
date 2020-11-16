const login = () => {
  const username = document.querySelector('#username').value;
  const password = document.querySelector('#password').value;

  if (username === 'user@test.com' && password === 'pass') {
    const navigator = document.querySelector('#navigator')
    navigator.bringPageTop('html/home.html')
  } else {
    ons.notification.alert('Error en el usuario o password');
  }
}


const loadPage = (page) => {
  document.querySelector('#navigator').bringPageTop(page, { animation: 'fade' });
};

document.addEventListener('prechange', function(event) {
  document.querySelector('ons-toolbar .center')
    .innerHTML = event.tabItem.getAttribute('label');
});
