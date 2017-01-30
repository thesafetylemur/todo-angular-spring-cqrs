(function(angular) {
    'use strict';
    angular
        .module('TodoAngularSpringCqrs', [
            'ngRoute',
            'TodoList',
            'TodoService'
        ]);
})(window.angular);