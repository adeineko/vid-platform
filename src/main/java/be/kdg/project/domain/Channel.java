package be.kdg.project.domain;

import java.time.LocalDate;

public class Channel {
    private String name;
    private LocalDate date;
    private int subscribers;

    public Channel(String name, LocalDate date, int subscribers) {
            this.name = name;
            this.date = date;
            this.subscribers = subscribers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
    public int getSubscribers() {
        return subscribers;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name +
                "date = " + date;
    }
}
