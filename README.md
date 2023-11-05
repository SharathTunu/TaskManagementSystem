# TaskManagementSystem
 Project to implement task management
In this project, you will create a task management system that uses linked lists, stacks, and queues to manage tasks. The system will allow users to add, remove, prioritize, and complete tasks. Use the following guidelines for the project;
Define a Task class: This class will represent a single task and contain the following properties: taskName (unique identifier for the task), priority (an integer, lower value indicates higher priority), and due date (indicating a date hen the task is due).
Create a TaskScheduler class: This class will manage a linked list, stack and queue that will contain information about the tasks. Implement all the following methods: addTask, removeTask, complete- Task, displayTask, prioritySorting and undoAction
Workflow:
1. Create a linked list data structure to store tasks. Each task will be a node in the linked list. Initialize the linked list with a list of tasks that will be read from a pre-populated file.
2. Implement a stack to manage a user’s ”undo” actions. When a user completes a task or removes a task, it should be possible to undo the action using the stack.
3. Implement a queue to manage a user’s ”to-do” list. Users can add new tasks to the queue and remove tasks from the queue as they complete them.
4. Users can add a new task to their to-do list. They should specify the task name, priority level, and due date. The task is added to the end of the queue.
5. Users can remove a task from their to-do list. They can either remove the task at the front of the queue (the next task to be completed) or a specific task based on its name.
6. Users can mark a task as completed. The completed task is moved from the to-do list to a completed task list.
7. Users can choose to sort their to-do list based on priority levels. Higher-priority tasks should appear at the front of the queue.
8. Users can undo their last action (add, remove, or complete). The undone task is re-added to the to-do list or the completed task is moved back to the to-do list.
9. Users can view their to-do list, completed tasks, or both. Display the task name, priority, and due date for each task.
User interface: Create a simple command-line or graphical interface to allow users to interact with the data. Users should be able to perform all the actions specified in the workflow.
You need to create a file with data for 5 tasks that can be used by the readFromFile() method. Also, for this program, your task management system should be able to handle at least 20 tasks.



