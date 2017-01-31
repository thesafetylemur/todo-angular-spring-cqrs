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

        this.createTodoItem = function(listId, name) {
            return $http.post(baseUrl + "/" + listId + "/items", {"name": name})
                .then(function success(response) {
                    return response.data;
                })
        }

        // TODO: Consider modifying the item urls to drop the listId.

        this.toggleTodoItem = function (listId, itemId, completed) {
            return $http.put(baseUrl + "/" + listId + "/items/" + itemId, {"completed": completed})
                .then(function success(response) {
                    return response.data;
                }, function error(response) {
                    console.log(response);
                });
        }

        this.renameTodoItem = function(listId, itemId, name) {
            return $http.put(baseUrl +  + "/" + listId + "/items/" + itemId, {"name": name});
        }

        this.archiveTodoItem = function(listId, itemId) {
            return $http.delete(baseUrl +  + "/" + listId + "/items/" + itemId);
        }
    }
})(window.angular);