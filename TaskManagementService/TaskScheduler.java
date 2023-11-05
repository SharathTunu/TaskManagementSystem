package TaskManagementService;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import TaskManagementService.Task;


public class TaskScheduler {
    public CustomLL taskList = new CustomLL();
    public CustomLL removedTaks = new CustomLL();
    public CustomLL completedTaks = new CustomLL();
    public String last_action = "No action";

    public void readFromFile() {
        // Populates the all tasks list
        String csvFile = System.getProperty("user.dir") + "/TaskManagementService/taskData.csv";
        System.out.println("Reading Data from: " + csvFile);


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            Integer n =0;
            while ((line = br.readLine()) != null) {
                if (n == 0) {
                    n = 1;
                    continue; // Skip 1st iteration...since the first line does not have task info
                }
                String[] values = line.split(",");
                
                // You can access data from the "values" array based on the CSV file structure.
                // Optional: * Check the time stamp value..if it's in the past skip **
                //2. Define a new task object
                //3. add the properties to the task object
                //4. insert the data 
                Task info =new Task();   
                info.taskName = values[0];
                info.priority= Integer.parseInt(values[1]);
                info.due_date_str = values[2];
                info.str2time();
                this.taskList.push(info);                
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void updateTaskLists(String data_str, String update_type) {
        Task info = new Task();
        String[] values = data_str.split(",");
        info.taskName = values[0];
        info.priority= Integer.parseInt(values[1]);
        info.due_date_str = values[2];
        info.str2time();
        if (update_type.equals("completed")){this.completedTaks.push(info);}
        else if (update_type.equals("removed")) { this.removedTaks.push(info); }
        else if (update_type.equals("todo")) {this.taskList.push(info);}
    }

    public void addTask(String data_str) {
        updateTaskLists(data_str, "todo");
        this.last_action= "todo";
        // this.filghtList.head = this.filghtList.mergeSort(this.filghtList.head);
        // addRunways();
    }

    public void updateTodoList(String task_name, String update_type) {
        if (!task_name.equals("")) {
            String data = this.taskList.deleteNode(task_name); // Remove the task from the todo list
            if (!data.equals("")){updateTaskLists(data, update_type);} // Add the removed to task to their appropriate lists
            else {System.out.println("Could not find the task with such name in ToDo pile");}// error handling
        }
        else{
            System.out.println("Entered an invalid task id");// error handling
        }
    }

    public void removeTask(String task_name) {
        updateTodoList(task_name, "removed");
        this.last_action= "removed";
    }

    public void completeTask(String task_name) {
        updateTodoList(task_name, "completed");
        this.last_action= "completed";
    }

    public void sortTodos() {
        // Sorts based on due date first and then sorts again on priority
        this.taskList.head = this.taskList.mergeSort(this.taskList.head);
    }

    public void undoLast() {

        if (this.last_action.equals("completed")){
            String data = this.completedTaks.deleteLastNode();
            addTask(data);
        }
        else if (this.last_action.equals("removed")){
            String data = this.removedTaks.deleteLastNode();
            addTask(data);
        }
        else if (this.last_action.equals("todo")){
            this.taskList.deleteLastNode();
        }
        else {
            System.out.println("The last action was " + this.last_action + " which cannot be undone.");
            System.out.println("Only the following actions can be undone: 1. Completed\n 2. removed\n 3. Todo");
        }
        this.last_action= "undo";

    }

    public static void main(String args[]) {
        TaskScheduler rs = new TaskScheduler();
        rs.readFromFile();
        
        // for(int i=0; i<flightList.size(); i++)
        //     {
        //         System.out.println(flightList.size());
        //         System.out.println(i);
        //         info1 = flightList.get(i);
        //         System.out.println(info1);
        //          L1.addNode(info1);
        //     }
        // //L1.sortList();
        // L1.display();
    }

}