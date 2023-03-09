

function loadSidebar(){
  fetch('sidebar.html')
  .then(response => response.text())
  .then(data => {
    // Insert the sidebar HTML into the sidebar element
    document.querySelector('#sidebar').innerHTML = data;
  });

}
