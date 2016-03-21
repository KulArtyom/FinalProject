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
                System.out.println(Constans.MAIN_TEXT_N);
                System.out.println(Constans.MAIN_TEXT_ENTER_OPERATION);

                int maxSizemain = menuMain();
                choice = menuChoice(1, maxSizemain);
                File filexml = new File(Constans.FILE_PATH_XML);
                File filejs = new File(Constans.FILE_PATH_JSON);

                switch (choice) {
                    case 1:
                        if (!filexml.exists() && !filejs.exists()) {
                            Thread thread = new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    System.out.print(Constans.MAIN_TEXT_ENTER_WAIT_DOWNLOAD);
                                    for (int i = 0; i < 15; i++) {
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.print(Constans.MAIN_TEXT_ENTER_T);
                                    }
                                }
                            });
                            thread.start();
                            thread.join();
                            Manager.getInstance().downloadFiles(Constans.LINK_XML, Constans.FILE_PATH_XML);
                            Manager.getInstance().downloadFiles(Constans.LINK_JSON, Constans.FILE_PATH_JSON);
                            System.out.println(Constans.MAIN_TEXT_N);
                            System.out.println(Constans.MAIN_TEXT_ENTER_SUCCESFULLY_DOWNLOADED);
                        } else {
                            System.out.println(Constans.MAIN_TEXT_FILES_ALREADY_EXISTS);
                            int max_operation_download = menuChoicedownload();
                            int choice_download = menuChoice(1, max_operation_download);
                            if (choice_download == 1) {
                                Manager.getInstance().downloadFiles(Constans.LINK_XML, Constans.FILE_PATH_XML);
                                Manager.getInstance().downloadFiles(Constans.LINK_JSON, Constans.FILE_PATH_JSON);
                                System.out.println(Constans.MAIN_TEXT_FILES_OWERWRITE);
                            } else if (choice_download == 2) {
                                continue;
                            }
                        }

                        break;

                    case 2:
                        if (!filexml.exists() && !filejs.exists()) {
                            System.out.println(Constans.MAIN_TEXT_FILES_DONT_FIND);
                        } else {
                            int max_operation_delete = menuChoiceDelete();
                            int choice_delete = menuChoice(1, max_operation_delete);
                            if (choice_delete == 1) {
                                Manager.getInstance().deleteFiles(Constans.FILE_PATH_XML);
                                Manager.getInstance().deleteFiles(Constans.FILE_PATH_JSON);
                                System.out.println(Constans.MAIN_TEXT_FILES_SUCCESFULLY_DELETED);
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
                        System.out.println(Constans.MAIN_TEXT_FILES_SUCCESFULLY_PARSED);
                        break;

                    case 4:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showRoot(Manager.getInstance().getRoot());
                            System.out.println(Constans.MAIN_TEXT_SHOWS_ROOT);
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println(Constans.MAIN_TEXT_FIRST_DOWNLOAD);
                            System.out.println(Constans.MAIN_TEXT_FIRST_ROOT_NULL);
                        }
                        break;
                    case 5:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().cleanRoot();
                            System.out.println(Constans.MAIN_TEXT_FIRST_ROOT_CLEAN);
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println(Constans.MAIN_TEXT_FIRST_DOWNLOAD);
                            System.out.println(Constans.MAIN_TEXT_FIRST_ROOT_NULL);
                        }
                        break;
                    case 6:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showCustomers(Manager.getInstance().searchCustomers());
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println(Constans.MAIN_TEXT_FIRST_DOWNLOAD);
                            System.out.println(Constans.MAIN_TEXT_FIRST_ROOT_NULL);
                        }
                        break;
                    case 7:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showCustomers(Manager.getInstance().searchDate());
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println(Constans.MAIN_TEXT_FIRST_DOWNLOAD);
                            System.out.println(Constans.MAIN_TEXT_FIRST_ROOT_NULL);
                        }
                        break;
                    case 8:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showCustomers(Manager.getInstance().searchAuto());
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println(Constans.MAIN_TEXT_FIRST_DOWNLOAD);
                            System.out.println(Constans.MAIN_TEXT_FIRST_ROOT_NULL);
                        }
                        break;
                    case 9:
                        if (Manager.getInstance().getRoot() != null) {
                            Manager.getInstance().showCustomers(Manager.getInstance().searchBirthDay());
                        } else if (Manager.getInstance().getRoot() == null) {
                            System.out.println(Constans.MAIN_TEXT_FIRST_DOWNLOAD);
                            System.out.println(Constans.MAIN_TEXT_FIRST_ROOT_NULL);
                        }
                        break;
                    case 0:
                        System.exit(0);
                        break;

                }
            } catch (MyException e) {
                System.out.println(Constans.TEXT_ERROR_MYEXCEP + e.getRussianMessage());

            } catch (IOException e) {
                System.out.println(Constans.TEXT_ERROR_IOEXCEP + e.getMessage());

            } catch (Exception e) {
                System.out.println(Constans.TEXT_ERROR_EXCEP + e.getMessage());
            }

        } while (choice != 0);


    }

    //Меню
    public static int menuMain() {
        final int max = 9;
        System.out.println(Constans.MAIN_MENU_TEXT_1);
        System.out.println(Constans.MAIN_MENU_TEXT_2);
        System.out.println(Constans.MAIN_MENU_TEXT_3);
        System.out.println(Constans.MAIN_MENU_TEXT_4);
        System.out.println(Constans.MAIN_MENU_TEXT_5);
        System.out.println(Constans.MAIN_MENU_TEXT_6);
        System.out.println(Constans.MAIN_MENU_TEXT_7);
        System.out.println(Constans.MAIN_MENU_TEXT_8);
        System.out.println(Constans.MAIN_MENU_TEXT_9);
        System.out.println(Constans.MAIN_MENU_TEXT_0);
        return max;
    }

    //метод выбора функций меню
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
                    System.out.println(Constans.MAIN_TEXT_FIRST_INCORRECT_VALUE);
                }
            }
        } while (true);
        return choise;
    }

    //метод выбора скачки файла
    public static int menuChoicedownload() {
        int max = 2;
        System.out.println(Constans.MENU_CHOICE_DOWNLOADED_ENTER);
        System.out.println(Constans.MENU_CHOICE_DOWNLOADED_1);
        System.out.println(Constans.MENU_CHOICE_DOWNLOADED_2);

        return max;
    }

    //метод выбора удаления файла
    public static int menuChoiceDelete() {
        final int max = 2;
        System.out.println(Constans.MENU_CHOICE_DELETED_ENTER);
        System.out.println(Constans.MENU_CHOICE_DELETED_1);
        System.out.println(Constans.MENU_CHOICE_DELETED_2);
        return max;
    }

    //метод выбора файла для парсинга
    public static int menuChoiceFile() {
        final int max = 3;
        System.out.println(Constans.MENU_CHOICE_FILE_ENTER);
        System.out.println(Constans.MENU_CHOICE_FILE_1);
        System.out.println(Constans.MENU_CHOICE_FILE_2);
        System.out.println(Constans.MENU_CHOICE_FILE_3);
        return max;
    }

    // метод выбора парсера
    public static int menuChoiceParse() {
        final int max = 4;
        System.out.println(Constans.MENU_CHOICE_PARSE_ENTER);
        System.out.println(Constans.MENU_CHOICE_PARSE_1);
        System.out.println(Constans.MENU_CHOICE_PARSE_2);
        System.out.println(Constans.MENU_CHOICE_PARSE_3);
        System.out.println(Constans.MENU_CHOICE_PARSE_4);
        return max;
    }


}
