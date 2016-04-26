package UP;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Chat {

    private static String strDateFormat = "dd.MM.yyyy HH:mm:ss";

    private static void loadSaveMessagesChat(Messages mes, Scanner sc) throws IOException {

        int actChat1 = 0;
        System.out.println("1. Загрузка.\n"
                + "2. Сохранение.\n"
                + "0. Назад."
        );
        actChat1 = sc.nextInt();

        switch (actChat1) {
            case 0:
                break;

            case 1: {
                System.out.println("Введите имя файла: ");
                File f = new File(sc.next());
                if (f.exists()) {
                    mes.readMessages(f);
                } else {
                    System.out.println("Файл с таким именем не существует.");
                }
                break;
            }

            case 2:
                System.out.println("Введите имя файла: ");
                File f = new File(sc.next());
                mes.writeMessages(f);
                break;
        }

    }

    private static void deleteByIdChat(Messages mes, Scanner sc) throws IOException {
        System.out.println("Выберите идентификатор для удаления сообщения :");
        String id = sc.next();
        mes.searchByID(id);
    }

    private static void searchByAuthorChat(Messages mes, Scanner sc) throws IOException {
        System.out.println("Введите имя автора :");
        String name = sc.next();
        mes.searchByAuthor(name);
    }

    private static void searchByKeywordChat(Messages mes, Scanner sc) throws IOException {
        System.out.println("Введите ключевое слово(лексему) :");
        sc.nextLine();
        String keyword = sc.nextLine();
        mes.searchByKeyword(keyword);
    }

    private static void searchByRegularChat(Messages mes, Scanner sc) throws IOException {
        System.out.println("Введите регулярное выражение :");
        sc.nextLine();
        String regular = sc.nextLine();
        mes.searchByRegular(regular);
    }

    private static void searchByDateChat(Messages mes, Scanner sc) throws ParseException, IOException {
        SimpleDateFormat dateFormatate = new SimpleDateFormat(strDateFormat);
        System.out.println("Введите период времени(в формате " + strDateFormat + ") :");
        System.out.println("От: ");
        sc.nextLine();
        Date fromDate = dateFormatate.parse(sc.nextLine());
        System.out.println("До: ");
        Date toDate = dateFormatate.parse(sc.nextLine());
        mes.searchByDate(fromDate, toDate);
    }

    private static void otherFunctions(Messages mes, Scanner sc) throws IOException, ParseException {
        int numberAct = 0;
        do {
            System.out.println("Выберите действие :\n"
                    + "1. Поиск в истории сообщения по автору.\n"
                    + "2. Поиск в истории сообщения по ключевому слову (лексеме целиком).\n"
                    + "3. Поиск в истории сообщения по регулярному выражению.\n"
                    + "4. Просмотр истории сообщений за определенный период.\n"
                    + "0. Назад."
            );
            numberAct = sc.nextInt();
            switch (numberAct) {

                case 1: {
                    searchByAuthorChat(mes, sc);
                    break;
                }

                case 2: {
                    searchByKeywordChat(mes, sc);
                    break;
                }

                case 3: {
                    searchByRegularChat(mes, sc);
                    break;
                }

                case 4: {
                    searchByDateChat(mes, sc);
                    break;
                }

            }
        } while (numberAct != 0);
    }

    public static void main(String[] args) throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);
        Messages mes = new Messages();
        int numberActChat = 0;

        do {
            System.out.println("Выберите действие :\n"
                    + "1. Загрузка/сохранение сообщений из файла/в файл.\n"
                    + "2. Добавить сообщение.\n"
                    + "3. Просмотр истории сообщений в хронологическом порядке.\n"
                    + "4. Удаление сообщения по идентификатору.\n"
                    + "5. Дополнительные функции.\n"
                    + "0. Выход."
            );
            numberActChat = sc.nextInt();
            switch (numberActChat) {
                case 0: {
                    System.out.println("До свидания!");
                    break;
                }

                case 1: {
                    loadSaveMessagesChat(mes, sc);
                    break;
                }

                case 2: {
                    Message message = new Message(sc);
                    mes.addMessage(message);
                    break;
                }

                case 3: {
                    mes.print();
                    break;
                }

                case 4: {
                    deleteByIdChat(mes, sc);
                    break;
                }

                case 5: {
                    otherFunctions(mes, sc);
                }

            }

        } while (numberActChat != 0);
    }


}
