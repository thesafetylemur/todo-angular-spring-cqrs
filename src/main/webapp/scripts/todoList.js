
(function(angular) {
    'use strict';
    angular
        .module('TodoList', [
            'TodoItem',
            'TodoService'
        ])
        .controller('TodoListController', TodoListController);

    function TodoListController(TodoService) {
        var vm = this;
        vm.todoLists = [];

        TodoService.getTodoLists().then(function(response) {
            vm.todoLists = response;
        })

        vm.createTodoList = function(name) {
            TodoService.createTodoList(name)
                .then(function(response) {
                    vm.todoLists.push(response);
                    vm.newName = "";
                })
        }
    }
})(window.angular);