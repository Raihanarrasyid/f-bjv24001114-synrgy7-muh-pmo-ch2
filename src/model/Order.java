package model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    private List<String> receiptItems = new ArrayList<>();
    private int total = 0;
    private Scanner scanner = new Scanner(System.in);

    public void addItem(String itemName, int price) {
        System.out.println("=========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("=========================");
        System.out.println(itemName + "\t| " + price);
        System.out.print("(input 0 untuk kembali)\nqty => ");
        int quantity = scanner.nextInt();
        if (quantity == 0)
            return;

        int subtotal = price * quantity;
        receiptItems.add(itemName + "\t\t" + quantity + "\t" + subtotal);
        total += subtotal;
    }

    public void displayOrder() {
        if (receiptItems.isEmpty()) {
            System.out.println("====================");
            System.out.println("Mohon masukkan input");
            System.out.println("Minimal 1 Jumlah pesanan!");
            System.out.println("pilihan anda");
            System.out.println("====================");
            System.out.println("(Y) untuk lanjut");
            System.out.println("(n) untuk keluar");
            System.out.println("====================");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("n")) {
                return;
            } else if (choice.equalsIgnoreCase("y")) {
                return;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        } else {
            for (String item : receiptItems) {
                System.out.println(item);
            }
            System.out.println("------------------------------------------+");
            System.out.println("Total\t\t" + total + "\n");
        }
    }

    public void generateReceipt() {
        if (receiptItems.isEmpty()) {
            System.out.println("Tidak ada pesanan untuk disimpan sebagai struk pembayaran.");
            return;
        }

        try (FileWriter writer = new FileWriter("receipt.txt")) {
            writer.write("==========================\n");
            writer.write("BinarFud\n");
            writer.write("==========================\n\n");
            writer.write("Terima kasih sudah memesan di BinarFud\n\n");
            writer.write("Dibawah ini adalah pesanan anda\n\n");
            for (String item : receiptItems) {
                writer.write(item + "\n");
            }
            writer.write("----------------------------------+\n");
            writer.write("Total\t\t" + total + "\n\n");
            writer.write("Pembayaran : BinarCash\n\n");
            writer.write("=========================\n");
            writer.write("Simpan struk ini sebagai\nbukti pembayaran\n");
            writer.write("=========================");
            System.out.println("Struk pembayaran telah disimpan sebagai file 'receipt.txt'");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk pembayaran.");
            e.printStackTrace();
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}