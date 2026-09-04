import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<Task>();
    public static Gson gson = new Gson();

    public static void saveTasks(){
        String jsonString = gson.toJson(tasks);
        try {
            FileWriter writer = new FileWriter("tasks.json");
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static void loadTasks(){
        File file = new File("tasks.json");
        if (file.exists()) {
            try {
                String jsonString = Files.readString(Path.of("tasks.json"));
                Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
                tasks = gson.fromJson(jsonString, listType);
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
    }

    public static void addTask(){
        System.out.print("Enter task name: ");
        String taskName = input.nextLine();
        System.out.print("Enter task description: ");
        String taskDescription = input.nextLine();
        tasks.add(new Task(taskName, taskDescription, false));
    }

    public static void viewCompletedTasks() {
        System.out.println("[0] Exit");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isTaskStatus()){
                System.out.printf("[%d] %s%n", i+1, tasks.get(i).getTaskName());
            }
        }

        System.out.print("Enter: ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 0){
            return;
        } else if (choice >= 1 && choice <= tasks.size() && tasks.get(choice-1).isTaskStatus()){
            System.out.println(tasks.get(choice - 1).toString());

            String answer;

            do {
                System.out.print("Make task active (y/n) or delete (d): ");
                answer = input.nextLine();
                if (!answer.equals("y") && !answer.equals("n") && !answer.equals("d")) {
                    System.out.println("Invalid choice, try again.");
                }
            } while (!answer.equals("y") && !answer.equals("n") && !answer.equals("d"));

            if (answer.equals("y")) {
                System.out.println("Task is now active.");
                tasks.get(choice - 1).unmarkComplete();
            } else if (answer.equals("d")) {
                do {
                    System.out.print("Are you sure you want to delete this task? (y/n): ");
                    answer = input.nextLine();
                    if (!answer.equals("y") && !answer.equals("n")) {
                        System.out.println("Invalid choice, try again.");
                    }
                } while (!answer.equals("y") && !answer.equals("n"));
                if (answer.equals("y")) {
                    System.out.println("Task removed.");
                    tasks.remove(choice - 1);
                } else {
                    return;
                }
            }
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static void viewActiveTasks(){
        System.out.println("[0] Exit");
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isTaskStatus()){
                System.out.printf("[%d] %s%n", i+1, tasks.get(i).getTaskName());
            }
        }

        System.out.print("Enter: ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 0){
            return;
        } else if (choice >= 1 && choice <= tasks.size() && !tasks.get(choice-1).isTaskStatus()){
            System.out.println(tasks.get(choice - 1).toString());

            String answer;
            do {
                System.out.print("Task completed? (y/n) or delete task (d): ");
                answer = input.nextLine();
                if (!answer.equals("y") && !answer.equals("n") && !answer.equals("d")) {
                    System.out.println("Invalid choice, try again.");
                }
            } while (!answer.equals("y") && !answer.equals("n") && !answer.equals("d"));

            if (answer.equals("y")) {
                System.out.println("Task marked as completed.");
                tasks.get(choice - 1).markComplete();
            }
            else if (answer.equals("d")) {
                do {
                    System.out.print("Are you sure you want to delete this task? (y/n): ");
                    answer = input.nextLine();
                    if (!answer.equals("y") && !answer.equals("n")) {
                        System.out.println("Invalid choice, try again.");
                    }
                } while (!answer.equals("y") && !answer.equals("n"));
                if (answer.equals("y")) {
                    System.out.println("Task removed.");
                    tasks.remove(choice - 1);
                } else {
                    return;
                }
            }
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        loadTasks();

        while(running) {

            int completedTasks = 0;
            int activeTasks = 0;

            for (Task task : tasks) {
                if (task.isTaskStatus()) {
                    completedTasks++;
                } else {
                    activeTasks++;
                }
            }

            System.out.println("Main menu");
            System.out.println("---------");
            System.out.println("[0] Exit" + "\n[1] Add new task" + "\n[2] View active tasks (" + activeTasks +")" + "\n[3] View completed tasks (" + completedTasks +")");
            System.out.print("Enter: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 0:
                    saveTasks();
                    System.out.println("Exit");
                    running = false;
                    break;
                case 1:
                    addTask();
                    break;
                case 2:
                    viewActiveTasks();
                    break;
                case 3:
                    viewCompletedTasks();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}