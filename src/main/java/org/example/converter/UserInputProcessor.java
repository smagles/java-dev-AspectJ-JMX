package org.example.converter;

import org.example.converter.mbean.LoggingControlMBean;

import java.util.Scanner;
import java.util.logging.Logger;

public class UserInputProcessor {
    private TextCaseConverter textCaseConverter;
    private LoggingControlMBean mbean;
    private static final Logger LOGGER = Logger.getLogger(UserInputProcessor.class.getName());

    public UserInputProcessor(TextCaseConverter textCaseConverter, LoggingControlMBean mbean) {
        this.textCaseConverter = textCaseConverter;
        this.mbean = mbean;
    }

    public void processUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            LOGGER.info("Введіть 'exit' для завершення або 'toggleLogging' для зміни логування:");
            LOGGER.info("Введіть рядок:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                flag = false;
            } else if (input.equalsIgnoreCase("toggleLogging")) {
                try {
                    toggleLogging();
                } catch (Exception e) {
                    LOGGER.info("Помилка при зміні стану логування: " + e.getMessage());
                }
            } else {
                processInput(input);
            }
        }
    }

    private void toggleLogging() {
        if (mbean.isLoggingEnabled()) {
            mbean.disableLogging();
            LOGGER.info("Логування вимкнено.");
        } else {
            mbean.enableLogging();
            LOGGER.info("Логування увімкнено.");
        }
    }

    private void processInput(String input) {
        if (mbean.isLoggingEnabled()) {
            String output = textCaseConverter.convertCase(input);
            LOGGER.info("Результат: " + output);
        } else {
            LOGGER.info("Логування вимкнено. Неможливо виконати перетворення рядка.");
        }
    }
}
