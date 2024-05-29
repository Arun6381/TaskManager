
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    public static class Task {

        private final int id;
        private String title;
        private String description;
        private String priority;
        private String status;

        public Task(int id, String title, String description, String priority, String status) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.priority = priority;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Title: " + title + ", Description: " + description
                    + ", Priority: " + priority + ", Status: " + status;
        }
    }

    private final List<Task> tasks;
    private int nextId;

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public void addTask(String title, String description, String priority, String status) {
        Task task = new Task(nextId++, title, description, priority, status);
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    public void editTask(int taskId, String title, String description, String priority, String status) {
        Task task = getTaskById(taskId);
        if (task != null) {
            if (title != null && !title.isEmpty()) {
                task.setTitle(title);
            }
            if (description != null && !description.isEmpty()) {
                task.setDescription(description);
            }
            if (priority != null && !priority.isEmpty()) {
                task.setPriority(priority);
            }
            if (status != null && !status.isEmpty()) {
                task.setStatus(status);
            }
            System.out.println("Task updated: " + task);
        } else {
            System.out.println("No task found with ID: " + taskId);
        }
    }

    public void deleteTask(int taskId) {
        Task task = getTaskById(taskId);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Task deleted: " + task);
        } else {
            System.out.println("No task found with ID: " + taskId);
        }
    }

    public Task getTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void filterTasksByPriority(String priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.toString().contains("Priority: " + priority)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with priority: " + priority);
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Filter Tasks by Priority");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter task priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter task status (Pending/In Progress/Completed): ");
                    String status = scanner.nextLine();
                    taskManager.addTask(title, description, priority, status);
                }
                case 2 -> {
                    System.out.print("Enter task ID to edit: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new title (or leave blank to keep current): ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new description (or leave blank to keep current): ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new priority (High/Medium/Low) (or leave blank to keep current): ");
                    String newPriority = scanner.nextLine();
                    System.out.print("Enter new status (Pending/In Progress/Completed) (or leave blank to keep current): ");
                    String newStatus = scanner.nextLine();
                    taskManager.editTask(taskId, newTitle, newDescription, newPriority, newStatus);
                }
                case 3 -> {
                    System.out.print("Enter task ID to delete: ");
                    int taskId = scanner.nextInt();
                    taskManager.deleteTask(taskId);
                }
                case 4 -> taskManager.viewAllTasks();
                case 5 -> {
                    System.out.print("Enter priority to filter by (High/Medium/Low): ");
                    String filterPriority = scanner.nextLine();
                    taskManager.filterTasksByPriority(filterPriority);
                }
                case 6 -> {
                    System.out.println("Exiting Task Manager.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
