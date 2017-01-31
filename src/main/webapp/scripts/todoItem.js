(function(angular) {
    'use strict';
    angular
        .module('TodoItem', [
            'TodoService'
        ])
        .config(TodoItemConfig)
        .controller('TodoItemController', TodoItemController);


    function TodoItemConfig($routeProvider) {
        $routeProvider
            .when('/:listId', {
                templateUrl: '../items.html',
                controller: 'TodoItemController',
                controllerAs: 'todoItemController'
            });
    }

    function TodoItemController($routeParams, TodoService) {
        var vm = this;
        vm.listId = $routeParams.listId;
        vm.items = [];

        TodoService.getTodoListItems(vm.listId).then(function(response){
            vm.items = response;
        });

        vm.createTodoItem = function(item) {
            TodoService.createTodoItem(vm.listId, item).then(function(response){
                vm.items.push(response);
                vm.newItem = "";
            });
        }

        vm.toggleCompleted = function(item) {
            TodoService.toggleTodoItem(item.todoListId, item.todoItemId, item.completed).then(function(response){
            });
        }
    }
})(window.angular);