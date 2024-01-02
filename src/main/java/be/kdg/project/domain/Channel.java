package be.kdg.project.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Channel {
    private int id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int subscribers;

    public Channel(int id, String name, LocalDate date, int subscribers) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.subscribers = subscribers;
    }

    public Channel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return "Channel: " +
                "id = " + id +"\n" +
                "name = " + name +"\n" +
                "date = " + date + "\n" +
                "subscribers = " + subscribers + "\n";
    }
}
