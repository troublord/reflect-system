window.onload = loadSidebar();

function loadSidebar(){
  fetch('../sidebar.html')
  .then(response => response.text())
  .then(data => {
    // Insert the sidebar HTML into the sidebar element
    document.querySelector('#sidebar').innerHTML = data;
  });

}

function performProperGrammer(){
	const activitiesCount = parseInt(document.querySelector("#activitiesCount").innerText);
    document.querySelector("#activitiesText").innerText = showActivitiesInProperGrammer(activitiesCount);
}

function showActivitiesInProperGrammer(activitiesCount) {
	const activitiesStr = activitiesCount > 1 ? "Activities" : "Activity";
	return `${activitiesStr}`;
}	

function addSortToTable(){
	const nodeList = document.querySelectorAll(".table-sortable th");
      const headerCells = [...nodeList].slice(0,-1);
      headerCells.forEach(headerCell => {
        headerCell.addEventListener("click", () => {
          const tableElement = headerCell.parentElement.parentElement.parentElement;
          const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
          const currentIsAscending = headerCell.classList.contains("th-sort-asc");
          sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
        });
      });
}

/**
 * sorts the html table
 * @param {HTMLTableElement} table => the table to sort
 * @param {number} column => the index of the column to sort
 * @param {boolean} asc => ascending order 
 */
function sortTableByColumn(table,column,asc = true){
  const dirModifier = asc ? 1:-1;
  const tBody = table.tBodies[0];
  const rows = Array.from(tBody.querySelectorAll("tr"));

  //sort each row
  const sortedRows = rows.sort((a,b) => {
    const aColText = a.querySelector(`td:nth-child(${column+1})`).textContent.trim();
    const bColText = b.querySelector(`td:nth-child(${column+1})`).textContent.trim();

    return aColText > bColText ? (1 * dirModifier) : (-1 * dirModifier);
  })
  
  // remove all existing tr from the table 
  while(tBody.firstChild){
	  tBody.removeChild(tBody.firstChild);
  }

  tBody.append(...sortedRows);
  
  // remember how the colummn is currently sorted by add classname asc or desc to th
  table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc","th-sort-desc"));
  table.querySelector(`th:nth-child(${column+1})`).classList.toggle("th-sort-asc",asc);
  table.querySelector(`th:nth-child(${column+1})`).classList.toggle("th-sort-desc",!asc);
}

function addSearchToTable(){
	const searchBar = document.querySelector("#searchBar");
	searchBar.addEventListener("keyup", (event) => handleSearch(searchBar));
}

function handleSearch(searchBar) {

  const searchTerm = searchBar.value.toLowerCase();
  const activities = document.querySelectorAll("#content_table_body tr");
  
  activities.forEach((activity) => {
    const name = activity.querySelector("td:first-child").textContent.toLowerCase();
    const status = activity.querySelector("td:nth-child(2)").textContent.toLowerCase();
    
    if (name.includes(searchTerm) || status.includes(searchTerm)) {
      activity.style.display = "table-row";
    } else {
      activity.style.display = "none";
    }
  });
}




