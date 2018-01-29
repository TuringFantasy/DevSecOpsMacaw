 var myApp = angular.module('MyApp', ['ngMaterial', 'MyApp.directives.cardImages', 'ngMessages', 'material.svgAssetsCache'])
     .config(function ($mdThemingProvider) {
         $mdThemingProvider.theme('default')
             .primaryPalette('cyan')
             .accentPalette('amber');
     });

myApp.controller("listController", function ($scope) {
    console.log("list controller activated");
    
    $scope.selectedCustomer = undefined;
    
    $scope.customers = null;
    
    var getImageURL = "app/services/image";
    
    $.get("app/services/token", function (data) {
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
             //by default select first customer
             $scope.selectedCustomer = $scope.customers[0];
             $scope.$apply();
         });
    }
    
    $scope.$watch("selectedCustomer", function(newVal, oldVal) {
        if (newVal) {
            console.log(newVal);
            getApprovals("ALL", newVal.id);
        }
    });
    
    function getApprovals(type, customerID) {
         var imgListArray = [];
         var params = {};

         var args = {};
         args.methodName = "getApprovals";
         var methodArgs = [type, customerID];
         params.params = methodArgs;
         args.params = params;
         args.token = window.token;

         var serviceRequestDescriptor = {
             "serviceRequestDescriptor": args
         };

         var str = JSON.stringify(serviceRequestDescriptor);
         $.post("app/services/api", str)
             .done(function (data) {
                 var response = JSON.parse(data);
                 var serviceResult = response.serviceResult;
                 console.log(response);
                 var imgListArray = [];
                 var pending = [];
                 var accepted = [];
                 var rejected = [];
                 //var items = "";
                 //var approvalList = $("#approval-list");
                 for (var i = 0; i < serviceResult.length; i++) {
                     var obj = serviceResult[i];
                     var image = obj.image;
                     var imgURL = getImageURL + "?fileName=" + encodeURIComponent(image.name);
                     var objImgList = {
                         name: image.name,
                         URL: imgURL,
                         customerName: obj.customer.name,
                         customerEmail: obj.customer.emailId,
                         customerAddress: obj.customer.address,
                         obj: obj
                     };
                     imgListArray.push(objImgList);
                     if (obj.status === "APPROVED") {
                         accepted.push(objImgList);
                     }
                     if (obj.status === "PENDING") {
                         pending.push(objImgList);
                     }
                     if (obj.status === "DENIED") {
                         rejected.push(objImgList);
                     }
                     //console.log("I have this AS", imgListArray);
                    
                 }
             $scope.allImages = imgListArray;
             $scope.pendingImages = pending;
             $scope.acceptedImages = accepted;
             $scope.rejectedImages = rejected;
             $scope.$apply();
                //console.log("I have this Scope = AS", $scope.listIMG);
               
             });  
     };
});