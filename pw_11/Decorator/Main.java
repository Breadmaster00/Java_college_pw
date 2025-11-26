package Decorator;

import Decorator.Messages.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Message hello_message = new Message("Hello everynya!");

        List<MessageDecorator> messages = new ArrayList<>();
        messages.add(new SignedMessage(hello_message, "кот"));
        messages.add(new TimestampedMessage(hello_message, LocalDate.now()));
        messages.add(new EncryptedDecorator(hello_message));

        for (IMessage message : messages) {
            message.printMessage();
            System.out.println("\n---------\n");
        }
    }
}
