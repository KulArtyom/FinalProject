package ui;

import Exception.MyException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {


        int choice = Integer.MAX_VALUE;

        do {
            try {
                System.out.println("\n");
                System.out.println("Для того чтобы провести операцию нажмите: ");

                int maxSizemain = menuMain();
                choice = menuChoice(1, maxSizemain);
                File filexml = new File(Constans.FILE_PATH_XML);
                File filejs = new File(Constans.FILE_PATH_JSON);
                switch (choice) {
                    case 1:
                        if (!filexml.exists() && !filejs.exists()) {
                            Manager.getInstance().downloadFiles(Constans.LINK_XML, Constans.FILE_PATH_XML);
                            Manager.getInstance().downloadFiles(Constans.LINK_JSON, Constans.FILE_PATH_JSON);
                            System.out.println("Files successfully downloaded\n");
                        } else {
                            System.out.println("Files already exists");
                            int max_operation_download = menuChoicedownload();
                            int choice_download = menuChoice(1, max_operation_download);
                            if (choice_download == 1) {
                                Manager.getInstance().downloadFiles(Constans.LINK_XML, Constans.FILE_PATH_XML);
                                Manager.getInstance().downloadFiles(Constans.LINK_JSON, Constans.FILE_PATH_JSON);
                                System.out.println("Files successfully overwrite");
                            } else if (choice_download == 2) {
                                continue;
                            }
                        }

                        break;

                    case 2:
                        if (!filexml.exists() && !filejs.exists()) {
                            System.out.println("Files don't find\n");
                        } else {
                            int max_operation_delete = menuChoiceDelete();
                            int choice_delete = menuChoice(1, max_operation_delete);
                            if (choice_delete == 1) {
                                Manager.getInstance().deleteFiles(Constans.FILE_PATH_XML);
                                Manager.getInstance().deleteFiles(Constans.FILE_PATH_JSON);
                                System.out.println("Files successfully deleted\n");
                            } else if (choice_delete == 2) {
                                continue;
                            }
                        }

                        break;

                    case 3:

                        int max_operation_file = menuChoiceFile();
                        int choice_file = menuChoice(1, max_operation_file);
                        if (choice_file == 1) {
                            Manager.getInstance().setFilePath(Constans.FILE_PATH_XML);
                        } else if (choice_file == 2) {
                            Manager.getInstance().setFilePath(Constans.FILE_PATH_JSON);
                        } else if (choice_file == 3) {
                            continue;
                        }

                        int max_operation_parse = menuChoiceParse();
                        int choice_parse = menuChoice(1, max_operation_parse);
                        if (choice_parse == 1) {
                            Manager.getInstance().setChoiceParse(Constans.SAX);

                        } else if (choice_parse == 2) {
                            Manager.getInstance().setChoiceParse(Constans.JSON);
                        } else if (choice_parse == 3) {
                            Manager.getInstance().setChoiceParse(Constans.GSON);
                        } else if (choice_parse == 4) {
                            continue;
                        }
                        System.out.println("File successfully parsed\n");
                        break;

                    case 4:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showRoot(Manager.getInstance().getRoot());
                            System.out.println("Root successful shows\n");
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println("Сначала скачайте и распарсите файлы");
                            System.out.println("Root = null");
                        }
                        break;
                    case 5:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().cleanRoot();
                            System.out.println("Root successful clean\n");
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println("Сначала скачайте и распарсите файлы");
                            System.out.println("Root = null");
                        }
                        break;
                    case 6:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showCustomers(Manager.getInstance().searchCustomers());
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println("Сначала скачайте и распарсите файлы");
                            System.out.println("Root = null");
                        }
                        break;
                    case 7:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showCustomers(Manager.getInstance().searchDate());
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println("Сначала скачайте и распарсите файлы");
                            System.out.println("Root = null");
                        }
                        break;
                    case 8:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showCustomers(Manager.getInstance().searchAuto());
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println("Сначала скачайте и распарсите файлы");
                            System.out.println("Root = null");
                        }
                        break;
                    case 9:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showCustomers(Manager.getInstance().searchBirthDay());
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println("Сначала скачайте и распарсите файлы");
                            System.out.println("Root = null");
                        }
                        break;
                    case 0:
                        Manager.getInstance().deleteFiles(Constans.FILE_PATH_XML);
                        Manager.getInstance().deleteFiles(Constans.FILE_PATH_JSON);
                        System.exit(0);
                        break;

                }
            } catch (MyException e) {
                System.out.println("MyException main error " + e.getRussianMessage());

            } catch (IOException e) {
                System.out.println("IOException main error " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Exception error " + e.getMessage());
            }

        } while (choice != 0);


    }

    //Меню
    public static int menuMain() {
        final int max = 9;
        System.out.println("\"1\" - Скачать файлы xml и json");
        System.out.println("\"2\" - Удалить файлы");
        System.out.println("\"3\" - Распарсить файл");
        System.out.println("\"4\" - Вывести объект root");
        System.out.println("\"5\" - Удалить объект root");
        System.out.println("\"6\" - Произвести поиск по ФИО");
        System.out.println("\"7\" - Вывести клиентов которые обращались более 6 месяцев назад");
        System.out.println("\"8\" - Произвести поиск клиентов по марке авто");
        System.out.println("\"9\" - Вывести клиентов с днем рождения в этом месяце");
        System.out.println("\"0\" - Выход");
        return max;
    }

    public static int menuChoice(int choiceFist, int choiceLates) {

        int choise = 0;

        do {
            System.out.print("\n");
            System.out.print("Введите значение от " + choiceFist + " до " + choiceLates + ": ");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                choise = scanner.nextInt();
                System.out.println();
                if (choise >= choiceFist && choise <= choiceLates) {
                    break;
                } else {
                    System.out.println("Вы ввели неверное значение");
                }
            } else {
                System.out.println("Вы ввели неверное значение");
            }
        } while (true);
        return choise;
    }

    public static int menuChoicedownload() {
        int max = 2;
        System.out.println("Введите: ");
        System.out.println("\"1\" - Перезаписать файлы");
        System.out.println("\"2\" - Вернуться в гланое меню");

        return max;
    }

    public static int menuChoiceDelete() {
        final int max = 2;
        System.out.println("Введите: ");
        System.out.println("\"1\" - удалить файлы xml и json");
        System.out.println("\"2\" - выйти в главное меню");
        return max;
    }

    public static int menuChoiceFile() {
        final int max = 3;
        System.out.println("Выберите файл который хотите распарсить: ");
        System.out.println("\"1\" - файл xml");
        System.out.println("\"2\" - файл json");
        System.out.println("\"3\" - выйти в главное меню");
        return max;
    }

    public static int menuChoiceParse() {
        final int max = 4;
        System.out.println("Введите: ");
        System.out.println("\"1\" - выбрать парсер - \"SAX\"");
        System.out.println("\"2\" - выбрать парсер - \"JSON\"");
        System.out.println("\"3\" - выбрать парсер - \"GSON\"");
        System.out.println("\"4\" - выйти в главное меню");
        return max;
    }


}
