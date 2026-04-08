package csd214.bookstore;

import csd214.bookstore.repositories.*;
import csd214.bookstore.services.ActionGameService;
import csd214.bookstore.services.BookstoreService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Persistence Strategy:");
        System.out.println("1. ArrayList (Memory)");
        System.out.println("2. H2 Database (In-Memory SQL)");
        System.out.println("3. MySQL (Production)");
        System.out.print("Choice: ");

        int choice = sc.nextInt();
        IRepository repository;

        // WIRING PHASE
        switch (choice) {
            case 2: repository = new H2Repository(); break;
            case 3: repository = new MySqlRepository(); break;
            default: repository = new InMemoryRepository(); break;
        }
        ActionGameService actionGameService = new ActionGameService(repository);
        BookstoreService bookstoreService = new BookstoreService(repository);

        // INJECTION PHASE
        App app = new App(repository, actionGameService, bookstoreService);
        try {
            System.out.println("\n Booting with: " + repository.getDataSourceType());
            app.run();
        }
        catch (Exception e){
           System.err.println("\n Application Error: " + e.getMessage());
        }
        finally {
            System.out.println("Shutting down and releasing resources...");
            repository.close();
        }
    }
}