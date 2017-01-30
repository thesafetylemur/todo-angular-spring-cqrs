(function(angular) {
    'use strict';
    angular
        .module('TodoService', [])
        .service('TodoService', TodoService);

    function TodoService($http) {
        var baseUrl = "/todoLists";

        this.getTodoLists = function() {
            return $http.get(baseUrl)
                .then(function success(response) {
                    return response.data;
                })
        };

        this.getTodoList = function(id) {
            return $http.get(baseUrl + "/" + id)
                .then(function success(response) {
                    return response.data;
                })
        };

        this.createTodoList = function(name) {
            return $http.post(baseUrl, {"name": name})
                .then(function success(response) {
                    return response.data;
                })
        };

        this.renameTodoList = function(id, name) {
            return $http.put(baseUrl + "/" + id, {"name": name});
        };

        this.archiveTodoList = function() {
            return $http.delete(baseUrl + "/" + id)
                .then(function success() {
                    console.log("success!");
                })
        };

        this.getTodoListItems = function(id) {
            return $http.get(baseUrl + "/" + id + "/items")
                .then(function success(response) {
                    return response.data;
                })
        };

        this.createTodoItem = function(id, name) {
            return $http.post(baseUrl + "/" + id + "/items", {"name": name})
                .then(function success(response) {
                    return response.data;
                })
        }
    }
})(window.angular);