angular
    .module('MyApp')
    .controller('uploadController', uploadController);

uploadController.$inject = ['$scope'];

function uploadController($scope) {
    
    $scope.selectedCustomer = undefined;
    
    $scope.customers = null;
    
    $scope.code = undefined;
    
    $.get( "app/services/token", function( data ) {
        var obj = JSON.parse(data);
        window.token = obj.apiGatewaySessionId;
        
        getCustomers();
    });
    
    function getCustomers() {
        var params = {};
        
        var args = {};
        args.methodName = "getCustomers";
        
        var methodArgs = [];
         params.params = methodArgs;
         args.params = params;
         args.token = window.token;

         var serviceRequestDescriptor = {
             "serviceRequestDescriptor": args
         };

         var str = JSON.stringify(serviceRequestDescriptor);
         $.post("app/services/api", str).done(function (data) {
             var result = JSON.parse(data);
             var customers = result.serviceResult;
             $scope.customers = customers;
         });
    }
    
    $scope.uploadImgNow = function() {
        console.log($scope.selectedCustomer);
        console.log($scope.code);
        if ($scope.selectedCustomer === undefined) {
            alert("Select Customer");
            return;
        }
        if ($scope.code === undefined) {
            alert("Code is required");
            return;
        }
        
        var uploadURL = "app/services/upload";
        if (files.length === 1) {
            var customerID = $scope.selectedCustomer.id;
            var code = $scope.code;
            //only perform upload if there is ONE file selected
            var myFormData = new FormData();
            myFormData.append('file', files[0]);
            myFormData.append('token', token);
            myFormData.append('customer', customerID);
            myFormData.append('code', code);

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
        
    }
    
    function onUploadSuccess(response, textStatus, jqXHR) {
        console.log("file successfully uploaded");
        document.getElementById("imgContentUpload").innerHTML = "<div style='height: 100%;margin: 0; '><div style='height: 100% ;color: white;font-family: ' Roboto ', Helvetica;font-size: 25px;'><div style='position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);text-align: center;'><img style='width:10%'src='img/succes.png' alt=''><p style='color:#4CAF50;font-size: 24px;'>Image Uploaded !</p><button style='background-color: #00BCD4;border: none;color: white;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;' class='md-raised md-primary' onClick='window.location.reload()'>Upload new Image</button></div></div></div>";
    }

    function onUploadError(jqXHR, textStatus, errorThrown) {
        alert.show("File Upload failed - " + jqXHR.responseText);
        document.getElementById("imgContentUpload").innerHTML = "<div style='height: 100%;margin: 0; '><div style='height: 100% ;color: white;font-family: ' Roboto ', Helvetica;font-size: 25px;'><div style='position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);text-align: center;'><img style='width:10%'src='img/error.png' alt=''><p style='color:#F44336;font-size: 24px;'>There is a Problem Uploading Your Image</p><button style='background-color: #00BCD4;border: none;color: white;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;' class='md-raised md-primary' onClick='window.location.reload()'>Try again</button></div></div></div>";
    }

};