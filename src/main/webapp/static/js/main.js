const BASE_URL = window.location.origin;

function fetchData(route) {
    console.log("Requested route: " + route)
    window.history.pushState(null, null, route);
    if (route.startsWith(ctxPath)) {
        route = route.replace(ctxPath + "/","")
    }
    let toFetch = BASE_URL + ctxPath +'/api' + route;
    console.log("Fetching " + toFetch)
    fetch(toFetch)
        .then(response => response.json())
        .then(renderNews)
        .catch(error => {
            let newsDiv = document.getElementById("news");
            newsDiv.innerHTML = "<div class='alert alert-danger' role='alert'>Something went wrong</div>";
        });
}

function menuHandler(event) {
    let elem = event.target;
    let url = elem.getAttribute("href");
    if (!url.startsWith("http")) {
        fetchData(url);
        event.preventDefault();
    }
}

function paging(event) {
    const params = new URLSearchParams(window.location.search);
    let pageValue = event.target.id === "prev" ? -1 : 1;
    let newPage = +params.get("page") + pageValue;
    params.set("page", newPage);

    fetchData("?" + params.toString());
}

function bindEventListeners() {
    let menu = document.querySelectorAll(".nav-link");
    menu.forEach((menuItem) => {
        menuItem.addEventListener("click", menuHandler, true);
    });

    let prev = document.getElementById("prev");
    let next = document.getElementById("next");
    next.addEventListener("click", paging);
    prev.addEventListener("click", paging);
}

function renderNews(news) {
    let newsDiv = document.getElementById("news");
    newsDiv.innerHTML = "";

    news.forEach((newsItem) => {
        let card = `<div class="card-body">
                        <div class="card-title"><a href="${newsItem.url}" target="_blank">${newsItem.title}</a></div>
                        <p class="card-text">${newsItem.time_ago}<br />${newsItem.user}</p>
                    </div>`;
        let cardElement = document.createElement('div');
        cardElement.classList.add("card");
        cardElement.classList.add("text-white");
        cardElement.classList.add("bg-dark");
        cardElement.classList.add("mb-3");
        cardElement.style.width = "18rem";
        cardElement.innerHTML = card;
        newsDiv.appendChild(cardElement);
    });
}

function getRoute() {
    let path = window.location.pathname === ctxPath ? "/newest" : window.location.pathname;
    let search = window.location.search === "" ? "?page=1" : window.location.search;
    console.log("get rout path: " + path)
    console.log("get rout search: " + search)
    return path + search;
}

(function () {
    bindEventListeners();
    let route = "?category=newest&page=1";
    console.log("route is: " + route)
    console.log("ctxPath is: " + ctxPath)
    console.log("base urls is: " + BASE_URL)
    fetchData(route);
})();

