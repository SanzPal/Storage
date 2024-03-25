class TodoList {

    constructor() {
        this.tasks = JSON.parse(localStorage.getItem('tasks')) || [];
        this.inputField = JSON.parse(localStorage.getItem('inputField')) || '';
    }

    // Save tasks to local storage
    persistTasks() {
        localStorage.setItem('tasks', JSON.stringify(this.tasks));
    }

    // Add a new task
    addTask(taskText) {
        const task = {
            text: taskText,
            completed: false
        };
        this.tasks.push(task);
        this.persistTasks();
        this.inputField = '';
        this.render();
    }

    // Toggle completion status
    toggleTaskCompletion(index) {
        this.tasks[index].completed = !this.tasks[index].completed;
        this.persistTasks();
        this.render();
    }

    // Delete a task
    deleteTask(index) {
        this.tasks.splice(index, 1);
        this.persistTasks();
        this.render();
    }

    // Change the input field
    setInputField(text) {
        this.inputField = text;
        this.render();
    }

    // Render the todo list
    render() {
        const rootElement = document.getElementById('root');
        rootElement.innerHTML = '';

        this.tasks
            .filter(task => {
                return (
                    task.text.toLowerCase().includes(this.inputField.toLowerCase())
                    || this.inputField === ''
                );
            })
            .forEach((task, index) => {
                const taskElement = taskElementGenerator(task, index);
                rootElement.appendChild(taskElement);
            })
    }
}

// Generate HTML element for a task
function taskElementGenerator(task, index) {
    const taskElement = document.createElement('div');
    taskElement.classList.add('item-block');
    taskElement.innerHTML = `
        <button
            class="ui icon button"
            data-attr="done"
            onclick = "todoList.toggleTaskCompletion(${index})"
        >
            ${
                task.completed 
                ? '<i class="check circle green icon"></i>'
                : '<i class="circle outline icon"></i>'
            }
        </button>

        <span>${task.text}</span>
        <button
            class="ui icon button"
            data-attr="delete"
            onclick = "todoList.deleteTask(${index})"
        >
            <i class="trash red icon"></i>
        </button>
    `;
    return taskElement
}

// Capitalize any string
function capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()  
}

/***************************/

const newItemForm = document.getElementById('create-items')
const inputItem = document.getElementById('inputItem')
const btnSearch = document.querySelector('.btn-search')

const todoList = new TodoList();

inputItem.addEventListener('input', (event) => {
    event.preventDefault();
    todoList.setInputField(event.target.value);
})

newItemForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const inputValue = capitalize(inputItem.value.trim());
    if(inputValue === '') return;
    
    todoList.addTask(inputValue);
}) 

btnSearch.addEventListener('click', (event) => {
    event.preventDefault();
    todoList.setInputField(inputItem.value.trim());
})

// Rendering the initial tasks
todoList.render();



// const items = sessionStorage.items ? JSON.parse(sessionStorage.items) : []
// const sectionItems = document.querySelector('.list-items')
// const template = document.getElementById('todo-container')

// sectionItems.addEventListener("keydown", (event) => {
//     const text = event.target
    
//     if (event.keyCode === 13 && text.classList.contains('text-block')) {
//         event.preventDefault()
//         text.contentEditable = false
//         text.classList.remove('edit')  
//         const indexOfChangedItem = searchIndex(text.parentNode)
//         items[indexOfChangedItem] = text.innerText
//         persist(items) 
//     }
// })