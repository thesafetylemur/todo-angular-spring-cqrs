(function(angular) {
    'use strict';
    angular
        .module('TodoItem', [
            'TodoService',
            'toaster'
        ])
        .config(TodoItemConfig)
        .controller('TodoItemController', TodoItemController);


    function TodoItemConfig($routeProvider) {
        $routeProvider
            .when('/:listId', {
                templateUrl: '../items.html',
                controller: 'TodoItemController',
                controllerAs: 'todoItemController',
                resolve: {
                    items: function(TodoService, $route) {
                        return TodoService.getTodoListItems(
                                $route.current.params.listId);
                    }
                }
            });
    }

    function TodoItemController($routeParams, toaster, TodoService, items) {
        var vm = this;
        vm.listId = $routeParams.listId;
        vm.items = items;

        vm.createTodoItem = function(item) {
            TodoService.createTodoItem(vm.listId, item).then(function(response){
                vm.items.push(response);
                vm.newItem = "";
            });
        }

        vm.toggleCompleted = function(item) {
            TodoService.toggleTodoItem(
                    item.todoListId,
                    item.todoItemId,
                    item.completed
                ).catch(function() {
                    // Oops, something broke. Gotta revert our item's state.
                    item.completed = !item.completed;
                    toaster.pop(
                            "error",
                            "Oops, something broke.",
                            "Try again later... :(");
                });
        }
    }
})(window.angular);