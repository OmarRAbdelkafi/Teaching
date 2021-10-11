var init = function() {
  addAllFilms();

  let filter = document.getElementById('filter');
  filter.addEventListener('keyup', filterFilms);
  filter.value='';

  let showDetails = document.getElementById('showDetails');
  showDetails.checked = true;
  showDetails.addEventListener('change', toggleDetails);

}

window.addEventListener('DOMContentLoaded', init);


var addAllFilms = function() {
  let filmContainer = document.getElementById('films');
  for(let i = 0; i < filmData.length; i++) {
    filmContainer.appendChild(createFilm( i ));
  }
}

var createFilm = function(index) {
  let film = filmData[index];
  let divFilm = document.createElement('div');

  let affiche = document.createElement('img');
  affiche.src = film.image;
  affiche.alt = film.title;

  let titre = document.createElement('h3');
  titre.textContent = film.title;

  divFilm.appendChild(affiche);
  divFilm.appendChild(titre);

  divFilm.className = 'film';
  divFilm.id = index+"-film";
  divFilm.addEventListener('mouseover', displayText);
  divFilm.addEventListener('mouseout', removeText);
  divFilm.addEventListener('click', selectFilm);

  return divFilm;
}


var toggleDetails = function() {
  let details = document.getElementById('details');
  if (this.checked) {
    details.style.display = 'block';
  }
  else {
    details.style.display = 'none';
  }
}

var displayText = function() {
  let details = document.getElementById('details');
  let filmIndex = parseInt(this.id);
  details.textContent = filmData[filmIndex].text;
}

var removeText = function() {
  let details = document.getElementById('details');
  details.textContent = '';
}

var filterFilms = function() {
    let filter = document.getElementById('filter');
  let filterText = filter.value.toLowerCase();
  let allFilms = document.querySelectorAll('#films div.film');
  for (let divFilm of allFilms) {
    let title = divFilm.querySelector('h3').textContent.toLowerCase();
    if (title.includes(filterText)) {
      divFilm.style.display = 'inline-block';
    }
    else {
      divFilm.style.display = 'none';
    }
  }
}

var nextFreeSelectSlot = function() {
  let selectUn = document.querySelector('#select1 .film');
  if (selectUn == undefined) {
    return 1;
  }
  let selectDeux = document.querySelector('#select2 .film');
  if (selectDeux == undefined) {
      return 2;
  }
  return 0;
}

var selectFilm = function() {
  let nextFreeSlot = nextFreeSelectSlot();
  if (nextFreeSlot == 0) {
    window.alert('vous avez déjà sélectionné deux films');
  }
  else {
    let selectUn = document.getElementById('select'+nextFreeSlot);
    let currentSpan = selectUn.querySelector('span');
    selectUn.insertBefore(this, currentSpan);

    this.removeEventListener('click', selectFilm);
    this.addEventListener('click', unselectFilm);

      let details = document.getElementById('details');
      details.textContent = '';
  }
}

var unselectFilm = function() {
  let films = document.getElementById('films');
  films.appendChild(this);
  this.addEventListener('click', selectFilm);
    this.removeEventListener('click', unselectFilm);
    // on efface le 'détails'
    let details = document.getElementById('details');
    details.textContent = '';
    // on réapplique le filtre, au cas où
    // un peu couteux pour un seul film...
    filterFilms();

}
