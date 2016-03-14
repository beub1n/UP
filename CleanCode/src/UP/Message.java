package UP;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Message {
    private UUID id;
    private String message;
    private String author;
    private Date time;

    public void setMessage() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите имя автора :");
        author = sc.nextLine();

        System.out.println("Введите сообщение :");
        message = sc.nextLine();

        id = UUID.randomUUID();
        time = new Date();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(author + ": " + message);
        return sb.toString();
    }

    public UUID getId() {
        return id;
    }
    public String getAuthor() { return author; }
    public String getMessage() { return message; }
    public Date getTime() { return time; }
}