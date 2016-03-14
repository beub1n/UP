package UP;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Chat{

    public static void main(String []args) throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);
        Messages mes = new Messages();
        int x = 0;

        do {
            System.out.println("Выберите действие :\n"
                                + "1. Загрузка/сохранение сообщений из файла/в файл.\n"
                                + "2. Добавить сообщение.\n"
                                + "3. Просмотр истории сообщений в хронологическом порядке.\n"
                                + "4. Удаление сообщения по идентификатору.\n"
                                + "5. Дополнительные функции.\n"
                                + "0. Выход."
            );
            x = sc.nextInt();
            switch (x) {
                case 0: {
                    System.out.println("До свидания!");
                    break;
                }

                case 1: {
                    int x1 = 0;
                    System.out.println("1. Загрузка.\n"
                            + "2. Сохранение.\n"
                            + "0. Назад."
                    );
                    x1 = sc.nextInt();

                    switch (x1) {
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
                    break;
                }

                case 2: {
                    Message message = new Message();
                    message.setMessage();
                    mes.addMessage(message);
                    break;
                }

                case 3: {
                    mes.print();
                    break;
                }

                case 4: {
                    System.out.println("Выберите идентификатор для удаления сообщения :");
                    String id = sc.next();
                    mes.searchByID(id);
                    break;
                }

                case 5: {
                    int x5 = 0;
                    do {
                        System.out.println("Выберите действие :\n"
                                + "1. Поиск в истории сообщения по автору.\n"
                                + "2. Поиск в истории сообщения по ключевому слову (лексеме целиком).\n"
                                + "3. Поиск в истории сообщения по регулярному выражению.\n"
                                + "4. Просмотр истории сообщений за определенный период.\n"
                                + "0. Назад."
                        );
                        x5 = sc.nextInt();
                        switch (x5) {

                            case 1: {
                                System.out.println("Введите имя автора :");
                                String name = sc.next();
                                mes.searchByAuthor(name);
                                break;
                            }

                            case 2: {
                                System.out.println("Введите ключевое слово(лексему) :");
                                sc.nextLine();
                                String keyword = sc.nextLine();
                                mes.searchByKeyword(keyword);
                                break;
                            }

                            case 3: {
                                System.out.println("Введите регулярное выражение :");
                                sc.nextLine();
                                String regular = sc.nextLine();
                                mes.searchByRegular(regular);
                                break;
                            }

                            case 4: {
                                SimpleDateFormat dateFormatate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                                System.out.println("Введите период времени(в формате dd.MM.yyyy HH:mm:ss) :");
                                System.out.println("От: ");
                                sc.nextLine();
                                Date fromDate = dateFormatate.parse(sc.nextLine());
                                System.out.println("До: ");
                                Date toDate = dateFormatate.parse(sc.nextLine());
                                mes.searchByDate(fromDate, toDate);
                                break;
                            }

                            default: {
                                break;
                            }
                        }
                    } while (x5 != 0);
                }

                default: {
                    break;
                }
            }

        } while(x != 0);
    }


}
