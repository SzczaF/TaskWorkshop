package pl.coderslab;

import java.awt.*;

public class Task {
    private String taskDescription;
    private String taskDate;
    private boolean isTaskImportant;
    private String defaualtColor = ConsoleColors.GREEN;
    private String defaualt2Color = ConsoleColors.BLUE;
    private String importantColor = ConsoleColors.RED;
    private final String pipeColor = ConsoleColors.WHITE;

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public boolean isTaskImportant() {
        return isTaskImportant;
    }

    public void setTaskImportant(boolean taskImportant) {
        isTaskImportant = taskImportant;
    }

    @Override
    public String toString() {
        String pipe = pipeColor + " | ";
        String importanceMark = isTaskImportant() ? importantColor : defaualt2Color;
        String date = importanceMark + getTaskDate();
        String task = defaualt2Color + getTaskDescription();

        String r = defaualtColor + "Date: " + date + pipe
                + defaualtColor + "Task: " + task;
        return r;
    }
}

