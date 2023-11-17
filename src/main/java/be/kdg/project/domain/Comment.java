package be.kdg.project.domain;

import java.util.List;

public class Comment {
    private String text;
    private int likes;
    private List<Comment> replies;
    private Video video;

    public Comment(String text) {
            this.text = text;
    }

    public String getText() {
            return text;
    }

    public int getLikes() {
            return likes;
    }

    public List<Comment> getReplies() {
            return replies;
    }
    public Video getVideo() {
            return video;
    }
    public void setVideo(Video video) {
            this.video = video;
    }

    @Override
    public String toString() {
        return "Comment " +video.getTitle();
    }
}
