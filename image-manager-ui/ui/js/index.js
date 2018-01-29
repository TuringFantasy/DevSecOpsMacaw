angular.module('MyApp', ['ngMaterial', 'ngMessages', 'material.svgAssetsCache'])
    .config(function ($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('cyan')
            .accentPalette('amber');
    });