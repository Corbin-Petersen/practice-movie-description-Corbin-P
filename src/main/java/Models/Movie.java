package Models;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private int rating;

    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Overrides


    @Override
    public String toString() {
        return "TITLE: " + title + "\n" + "RATING: " + rating + " stars\n" + "DESCRIPTION: " + description;
    }
}
