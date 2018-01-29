angular.module('MyApp.directives.cardImages', [])
    .directive('cardImages', function() {
      return{
        restrict: 'E', 
        scope: {
            data:'='
        },  
        templateUrl:"templatesHTML/cardImages.html",  
        controller: function($scope) {
          //console.log($scope.data);
            $scope.$watch("data", function(newVal, oldVal) {
                if (newVal) {
                    //console.log(newVal);
                    $scope.listIMG = newVal;
                    //$scope.$apply();
                }
            });
            
            $scope.onSubmit = function(x) {
                //console.log(x);
                var customerId = x.obj.customer.id;
                var imageId = x.obj.image.id;
                var status = x.obj.status;
                var reason = x.obj.reason;
                updateApproval(customerId, imageId, status, reason);
            }
            
            function updateApproval(customerId, imageId, status, reason) {
                 var params = {};

                 var args = {};
                 args.methodName = "updateApproval";
                 var methodArgs = [customerId, imageId, status, reason];
                 params.params = methodArgs;
                 args.params = params;
                 args.token = window.token;

                 var serviceRequestDescriptor = {
                     "serviceRequestDescriptor": args
                 };

                 var str = JSON.stringify(serviceRequestDescriptor);
                 $.post("app/services/api", str)
                     .done(function (data) {
                         console.log(data);
                     });
                
                  location.reload();
             };
      }
      };              
    });