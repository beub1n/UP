package UP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Messages {

    private static String logfileName = "logfile.txt";
    private Type itemsListType = new TypeToken<ArrayList<Message>>() {
    }.getType();
    private List<Message> messages = new ArrayList<>();
    private File logfile = new File(logfileName);

    private static void writeLogfile(File file, String str) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(str + "\r\n");
        writer.close();
    }

    public void writeMessages(File file) throws IOException {
        Messages.writeLogfile(logfile, "Save to file");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        Gson gson = new GsonBuilder().create();
        gson.toJson(messages, writer);
        writer.close();
    }

    public void readMessages(File file) throws IOException {
        Messages.writeLogfile(logfile, "Load from file");
        Reader reader = new BufferedReader(new FileReader(file));
        Gson gson = new GsonBuilder().create();
        messages = gson.fromJson(reader, itemsListType);
        reader.close();
    }

    public void addMessage(Message message) throws IOException {
        Messages.writeLogfile(logfile, message.getAuthor() + "added a message");
        messages.add(message);
    }

    public void print() throws IOException {
        Messages.writeLogfile(logfile, "Print messages");
        if (messages.size() == 0) {
            System.out.println("Чат пуст.");
            return;
        }
        for (Message m : messages) {
            System.out.println(m);
        }
    }

    public void searchByID(String id) throws IOException {
        Messages.writeLogfile(logfile, "Delete a message by id");
        for (Message m : messages) {
            if (m.getId().toString().equals(id)) {
                messages.remove(m);
                return;
            }
        }
        System.out.println("Сообщение, с введенным идентификатором не существует.");
    }

    public void searchByAuthor(String name) throws IOException {
        Messages.writeLogfile(logfile, "Search messages by author");
        boolean empty = true;
        for (Message m : messages) {
            if (m.getAuthor().equals(name)) {
                empty = false;
                System.out.println(m);
            }
        }
        if (empty == true) {
            System.out.println("Пользователь с таким именем не найден.");
        }
    }

    public void searchByKeyword(String keyword) throws IOException {
        Messages.writeLogfile(logfile, "Search messages by keyword");
        boolean empty = true;
        for (Message m : messages) {
            if (m.getMessage().contains(keyword)) {
                empty = false;
                System.out.println(m);
            }
        }
        if (empty == true) {
            System.out.println("Ничего не найдено.");
        }
    }

    public void searchByRegular(String regular) throws IOException {
        Messages.writeLogfile(logfile, "Search messages by regular");
        Pattern pat = Pattern.compile(regular);
        boolean empty = true;
        for (Message m : messages) {
            Matcher mat = pat.matcher(m.getMessage());
            if (mat.matches()) {
                empty = false;
                System.out.println(m);
            }
        }
        if (empty == true) {
            System.out.println("Ничего не найдено.");
        }
    }

    public void searchByDate(Date fromDate, Date toDate) throws IOException {
        Messages.writeLogfile(logfile, "Search a message by date");
        for (Message message : messages) {
            if (message.getTime().before(toDate) && message.getTime().after(fromDate)) {
                System.out.println(message);
            }
        }
    }

}