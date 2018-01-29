// File Upload
// 
function ekUpload(){
  function Init() {

    console.log("Upload Initialised");
      
    var fileSelect    = document.getElementById('file-upload'),
        fileDrag      = document.getElementById('file-drag'),
        submitButton  = document.getElementById('submit-button');

    fileSelect.addEventListener('change', fileSelectHandler, false);

    // Is XHR2 available?
    var xhr = new XMLHttpRequest();
    if (xhr.upload) {
      // File Drop
      fileDrag.addEventListener('dragover', fileDragHover, false);
      fileDrag.addEventListener('dragleave', fileDragHover, false);
      fileDrag.addEventListener('drop', fileSelectHandler, false);
    }
  }

  function fileDragHover(e) {
    var fileDrag = document.getElementById('file-drag');

    e.stopPropagation();
    e.preventDefault();

    fileDrag.className = (e.type === 'dragover' ? 'hover' : 'modal-body file-upload');
  }

  function fileSelectHandler(e) {
    // Fetch FileList object
    files = e.target.files || e.dataTransfer.files;
     
    // Cancel event and hover styling
    fileDragHover(e);

    // Process all File objects
    for (var i = 0, f; f = files[i]; i++) {
      parseFile(f);
      uploadFile(f);
    }
  }

  // Output
  function output(msg) {
    // Response
    var m = document.getElementById('messages');
    m.innerHTML = msg;
  }

  function parseFile(file) {

    console.log(file.name);
    output(
      '<strong>' + encodeURI(file.name) + '</strong>'
    );
    
    // var fileType = file.type;
    // console.log(fileType);
    var imageName = file.name;

    var isGood = (/\.(?=gif|jpg|png|jpeg)/gi).test(imageName);
    if (isGood) {
      document.getElementById('start').classList.add("hidden");
      document.getElementById('response').classList.remove("hidden");
      document.getElementById('notimage').classList.add("hidden");
      // Thumbnail Preview
      document.getElementById('file-image').classList.remove("hidden");
      document.getElementById('file-image').src = URL.createObjectURL(file);
    }
    else {
      document.getElementById('file-image').classList.add("hidden");
      document.getElementById('notimage').classList.remove("hidden");
      document.getElementById('start').classList.remove("hidden");
      document.getElementById('response').classList.add("hidden");
      document.getElementById("file-upload-form").reset();
    }
  }

  function setProgressMaxValue(e) {
    var pBar = document.getElementById('file-progress');

    if (e.lengthComputable) {
      pBar.max = e.total;
    }
  }

  function updateFileProgress(e) {
    var pBar = document.getElementById('file-progress');

    if (e.lengthComputable) {
      pBar.value = e.loaded;
    }
  }

  function uploadFile(file) {

    var xhr = new XMLHttpRequest(),
      fileInput = document.getElementById('class-roster-file'),
      pBar = document.getElementById('file-progress'),
      fileSizeLimit = 1024; // In MB
    if (xhr.upload) {
      // Check if file is less than x MB
      if (file.size <= fileSizeLimit * 1024 * 1024) {
        // Progress bar
        pBar.style.display = 'inline';
        xhr.upload.addEventListener('loadstart', setProgressMaxValue, false);
        xhr.upload.addEventListener('progress', updateFileProgress, false);

        // File received / failed
        xhr.onreadystatechange = function(e) {
          if (xhr.readyState == 4) {
            // Everything is good!

            // progress.className = (xhr.status == 200 ? "success" : "failure");
            // document.location.reload(true);
          }
        };

        // Start upload
        xhr.open('POST', document.getElementById('file-upload-form').action, true);
        xhr.setRequestHeader('X-File-Name', file.name);
        xhr.setRequestHeader('X-File-Size', file.size);
        xhr.setRequestHeader('Content-Type', 'multipart/form-data');
        xhr.send(file);
      } else {
        output('Please upload a smaller file (< ' + fileSizeLimit + ' MB).');
      }
    }
  }

  // Check for the various File API support.
  if (window.File && window.FileList && window.FileReader) {
    Init();
  } else {
    document.getElementById('file-drag').style.display = 'none';
  }
}
ekUpload();
var files = null;
var token = null;

$.get( "app/services/token", function( data ) {
                    //alert( "Data Loaded: " + data );
                    console.log(data);
                    var obj = JSON.parse(data);
                    token = obj.apiGatewaySessionId;
                });

function uploadImgNow() {
                console.log("testing testing");
                var uploadURL = "app/services/upload";
                    if (files.length === 1) {
                        //only perform upload if there is ONE file selected
                        var myFormData = new FormData();
                        myFormData.append('file', files[0]);
                        myFormData.append('token', token);
                        //myFormData.append('customerId', "whitney");

                        var uploadRequest = $.ajax({
                          url: uploadURL,
                          type: 'POST',
                          processData: false, // important
                          contentType: false, // important
                          dataType : 'json',
                          data: myFormData,
                          success: onUploadSuccess,
                          error: onUploadError
                        });

                    }
                

                function onUploadSuccess(response, textStatus, jqXHR) {
                    console.log("file successfully uploaded");
                    document.getElementById("imgContentUpload").innerHTML = "<div style='height: 100%;margin: 0; '><div style='height: 100% ;color: white;font-family: ' Roboto ', Helvetica;font-size: 25px;'><div style='position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);text-align: center;'><img style='width:10%'src='img/succes.png' alt=''><p style='color:#4CAF50;font-size: 24px;'>Image Uploaded !</p><button style='background-color: #00BCD4;border: none;color: white;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;' class='md-raised md-primary' onClick='window.location.reload()'>Upload new Image</button></div></div></div>";
                }

                function onUploadError(jqXHR, textStatus, errorThrown) {
                    alert.show("File Upload failed - " + jqXHR.responseText);
                    document.getElementById("imgContentUpload").innerHTML = "<div style='height: 100%;margin: 0; '><div style='height: 100% ;color: white;font-family: ' Roboto ', Helvetica;font-size: 25px;'><div style='position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);text-align: center;'><img style='width:10%'src='img/error.png' alt=''><p style='color:#F44336;font-size: 24px;'>There is a Problem Uploading Your Image</p><button style='background-color: #00BCD4;border: none;color: white;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;' class='md-raised md-primary' onClick='window.location.reload()'>Try again</button></div></div></div>";
                }
}