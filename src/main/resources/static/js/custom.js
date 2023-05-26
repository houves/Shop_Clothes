const image = document.getElementById("image");
const imagePreview = document.getElementById("imagePreview");
image.onchange = evt => {
  const [file] = image.files
  if (file) {
    imagePreview.src = URL.createObjectURL(file);
    imagePreview.style.width = "200px";
    imagePreview.style.height = "200px";
    imagePreview.style.margin = "16px 0";
  }
}

document.addEventListener('click', function(event) {
  var dropdowns = document.getElementsByClassName('dropdown-content');
  for (var i = 0; i < dropdowns.length; i++) {
    var openDropdown = dropdowns[i];
    if (!openDropdown.classList.contains('hidden') && !openDropdown.parentNode.contains(event.target)) {
      openDropdown.classList.add('hidden');
    }
  }
});


