package TaskManagementService;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class Task {
    public String taskName; //(Name of the task a unique value)
    public Integer priority; // (lower value indicates higher priority)
    public String due_date_str; //(indicating the time when the task should be completed)
    public LocalDateTime due_date;
    public String status = "To-Do";

    public void str2time() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        this.due_date = LocalDateTime.parse(due_date_str, formatter);
    }

}
