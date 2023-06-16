package org.example;

import java.io.*;

public class iPhone {
    private String model;
    private double price;
    private double sale;
    private String date;

    public iPhone(String model, double price, String date, double sale) {
        this.model = model;
        this.price = price;
        this.sale = sale;
        this.date = date;
    }

    public static void main(String[] args) {
        // Ввод модели и цены
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите модель iPhone: ");
            String model = reader.readLine();
            System.out.print("Введите цену iPhone: ");
            double price = Double.parseDouble(reader.readLine());
            System.out.print("Введите Заработок с iPhone: ");
            double sale = Double.parseDouble(reader.readLine());

            // Создание объекта iPhone
            String date = getCurrentDateTime(); // Используем текущую дату и время
            iPhone iphone = new iPhone(model, price, date, sale);

            // Сохранение объекта в файл
            saveToFile(iphone);

            // Сохранение заработка в файле sale_data.txt
            saveSaleData(sale);

            // Вывод информации о загруженном iPhone
            System.out.println("Модель: " + iphone.getModel());
            System.out.println("Цена: " + iphone.getPrice());
            System.out.println("Заработок: " + iphone.getSale());
            System.out.println("Дата: " + iphone.getDate());

            // Обновление общего заработка в файле
            updateTotalEarningsInFile();

        } catch (IOException e) {
            System.out.println("Ошибка при чтении ввода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при преобразовании числа: " + e.getMessage());
        }
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public double getSale() {
        return sale;
    }

    public String getDate() {
        return date;
    }

    private static void saveToFile(iPhone iphone) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("iphone.txt", true))) {
            String data = "Модель: " + iphone.getModel() + "\nЦена: " + iphone.getPrice() + "\nЗаработок: " + iphone.getSale() + "\nДата: " + iphone.getDate() + "\n\n";
            writer.write(data);

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных в файле iphone.txt: " + e.getMessage());
        }
    }

    private static void saveSaleData(double sale) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sale_data.txt", true))) {
            writer.write(sale + "\n");

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных в файле sale_data.txt: " + e.getMessage());
        }
    }

    private static void updateTotalEarningsInFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("iphone.txt", true));
             BufferedReader reader = new BufferedReader(new FileReader("sale_data.txt"))) {

            double totalEarnings = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                double earnings = Double.parseDouble(line);
                totalEarnings += earnings;
            }

            writer.write("Общий заработок за все время: " + totalEarnings + "\n");
            System.out.println("Общий заработок за все время: " + totalEarnings);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при обновлении общего заработка: " + e.getMessage());
        }
    }

    private static String getCurrentDateTime() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }
}
