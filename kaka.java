import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringProcessors {

    // Задача 1: Повторение строки
    public static String repeatString(String s, int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N не может быть отрицательным");
        }
        return s.repeat(N); // Используем метод String.repeat() в Java 11+ "Из-за джавы у меня сначала была ошибка кода"
    }

    // Задача 2: Подсчет вхождений
    public static int countOccurrences(String str, String subStr) {
        if (subStr == null || subStr.isEmpty()) {
            throw new IllegalArgumentException("Вторая строка не может быть пустой или null");
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();
        }

        return count;
    }

    // Задача 3: Замена символов
    public static String replaceDigits(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Входная строка не может быть null");
        }

        return input.replace("1", "один")
                .replace("2", "два")
                .replace("3", "три");
    }

    public static void main(String[] args) {
        // Примеры использования функций:

        // Задача 1
        String s = " Да ";
        int N = 3;
        System.out.println(repeatString(s, N)); //Ожидается " Да  Да  Да "

        // Задача 2
        String str1 = " зря , ой как зря ";
        String str2 = " я полез сюда ";
        System.out.println(countOccurrences(str1, str2)); //Ожидается 0

        // Задача 3
        String input = "123123";
        System.out.println(replaceDigits(input)); //Ожидается - одиндватриодиндватри
    }
}



public class kaka {

    // Задача 4: Удаление каждого второго символа в StringBuilder
    public static void removeEverySecondCharacter(StringBuilder sb) {
        for (int i = 1; i < sb.length(); i++) {
            sb.deleteCharAt(i);
            // После удаления символа сдвигаем индекс на 1,
            // чтобы удалить каждый второй символ в оставшейся строке
            i--;
        }
    }

    // Задача 5: Обратный порядок слов с сохранением пробелов
    public static String reverseWordsKeepingSpaces(String input) {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return "";
        }

        String[] words = trimmed.split("\\s+");
        StringBuilder result = new StringBuilder();

        // Заполняем пробелами длину строки
        char[] spaces = new char[input.length()];
        int spaceIndex = 0;

        // Оставляем пробелы на местах
        for (char ch : input.toCharArray()) {
            if (ch == ' ') {
                spaces[spaceIndex++] = ' ';
            } else {
                spaces[spaceIndex++] = '\0'; // Заносим пустой символ, чтобы потом знать, что это не пробел
            }
        }

        // Разворачиваем слова
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            // Добавляем пробелы после каждого слова, если это не последнее слово
            if (i > 0) {
                result.append(' ');
            }
        }

        // Вставляем пробелы в исходное место
        StringBuilder finalResult = new StringBuilder(input.length());
        int resultIndex = 0;

        for (char ch : spaces) {
            if (ch == ' ') {
                finalResult.append(' ');
            } else if (resultIndex < result.length()) {
                finalResult.append(result.charAt(resultIndex++));
            }
        }

        return finalResult.toString();
    }

    // Задача 6: Замена шестнадцатеричных чисел на десятичные
    public static String replaceHexWithDecimal(String input) {
        Pattern pattern = Pattern.compile("0x([0-9A-Fa-f]+)");
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String hexValue = matcher.group(1);
            int decimalValue = Integer.parseInt(hexValue, 16);
            matcher.appendReplacement(result, String.valueOf(decimalValue));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public static void main(String[] args) {
        // Примеры использования методов:

        // Задача 4
        StringBuilder sb = new StringBuilder("abcdef");
        removeEverySecondCharacter(sb);
        System.out.println(sb); // ожидается "ace"

        // Задача 5
        String inputStr = "   aaa bbb   cc dd   ";
        System.out.println(reverseWordsKeepingSpaces(inputStr)); // ожидается "   dd cc bbb aaa   "

        // Задача 6
        String hexInput = "Васе 0x00000010 лет";
        System.out.println(replaceHexWithDecimal(hexInput)); // ожидается "Васе 16 лет"
    }
}

class Payments {
    // Поля класса
    private String fullName; // ФИО человека
    private int day;         // День платежа
    private int month;       // Месяц платежа
    private int year;        // Год платежа
    private int amount;      // Сумма платежа в копейках

    // Конструктор
    public Payments(String fullName, int day, int month, int year, int amount) {
        this.fullName = fullName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    // Геттеры и сеттеры
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // Переопределение метода equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payments payment)) return false;
        return day == payment.day &&
                month == payment.month &&
                year == payment.year &&
                amount == payment.amount &&
                Objects.equals(fullName, payment.fullName);
    }

    // Переопределение метода hashCode
    @Override
    public int hashCode() {
        return Objects.hash(fullName, day, month, year, amount);
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        return "Payment{" +
                "fullName='" + fullName + '\'' +
                ", date=" + day + "/" + month + "/" + year +
                ", amount=" + amount + " коп." +
                '}';
    }

    // Пример использования
    public static void main(String[] args) {
        Payments payment1 = new Payments("мальчик", 15, 6, 2023, 15000);
        Payments payment2 = new Payments("девочка", 13, 9, 2023, 20000);

        System.out.println(payment1);
        System.out.println(payment2);

        // Сравнение объектов
        System.out.println(payment1.equals(payment2)); // ожидается false
    }
}


class FinanceReports {
    private final Payments[] payments;      // Массив платежей
    private final String author;           // ФИО составителя отчета
    private final LocalDate creationDate;  // Дата создания отчета

    // Конструктор
    public FinanceReports(Payments[] payments, String author, LocalDate creationDate) {
        this.payments = new Payments[payments.length];
        for (int i = 0; i < payments.length; i++)
            this.payments[i] = new Payments(payments[i].getFullName(), payments[i].getDay(), payments[i].getMonth(), payments[i].getYear(), payments[i].getAmount());
        this.author = author;
        this.creationDate = creationDate;
    }

    // Конструктор копирования
    public FinanceReports(FinanceReports report) {
        this(report.getPayments(), report.getAuthor(), report.getCreationDate());
    }

    // Получение количества платежей
    public int getPaymentCount() {
        return payments.length;
    }

    // Доступ к i-му платежу (на чтение и запись)
    public Payments getPayment(int index) {
        if (index < 0 || index >= payments.length) {
            throw new IndexOutOfBoundsException("Индекс вне границ массива платежей");
        }
        return payments[index]; // Возвращаем ссылку на объект Payment
    }

    public void setPayment(int index, Payments payment) {
        if (index < 0 || index >= payments.length) {
            throw new IndexOutOfBoundsException("Индекс вне границ массива платежей");
        }
        this.payments[index] = new Payments(payment.getFullName(), payment.getDay(), payment.getMonth(), payment.getYear(), payment.getAmount());
    }

    // Получение ФИО составителя отчета
    public String getAuthor() {
        return author;
    }

    // Получение даты создания отчета
    public LocalDate getCreationDate() {
        return creationDate;
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[Автор: %s, дата: %s, Платежи: [", author, creationDate));
        for (int i = 0; i < payments.length; i++) {
            Payments p = payments[i];
            sb.append(String.format("Плательщик: %s, дата: %d.%d.%d, сумма: %d руб. %d коп.%s",
                    p.getFullName(),
                    p.getDay(),
                    p.getMonth(),
                    p.getYear(),
                    p.getAmount() / 100,
                    p.getAmount() % 100,
                    (i < payments.length - 1) ? ", " : ""));
        }
        sb.append("]]");
        return sb.toString();
    }

    // Пример использования
    public static void main(String[] args) {
        Payments payment1 = new Payments("мальчик", 15, 6, 2023, 15000);
        Payments payment2 = new Payments("девочка", 13, 9, 2023, 20000);
        Payments[] payments = {payment1, payment2};

        FinanceReports report = new FinanceReports(payments, "кентуха", LocalDate.now());
        System.out.println(report);

        // Копирование отчета
        FinanceReports copiedReport = new FinanceReports(report);
        System.out.println(copiedReport);

        // Изменение оригинала для проверки независимости
        payment1.setAmount(25000); // меняем сумму первого платежа в оригинале
        System.out.println("Оригинал после изменения:");
        System.out.println(report);
        System.out.println("Копия без изменений:");
        System.out.println(copiedReport);
    }

    public Payments[] getPayments() {
        Payments[] payments1 = new Payments[0];
        return payments1;
    }
}


class FinanceReportProcessor {

    // Метод для получения платежей людей, чьи фамилии начинаются с указанного символа
    public static FinanceReports getPaymentsByLastNameInitial(FinanceReports report, char initial) {
        List<Payments> filteredPayments = new ArrayList<>();
        for (Payments payment : report.getPayments()) {
            if (payment.getFullName().charAt(0) == initial) {
                filteredPayments.add(payment);
            }
        }
        return new FinanceReports(filteredPayments.toArray(new Payments[0]), report.getAuthor(), report.getCreationDate());
    }

    // Метод для получения всех платежей, размер которых меньше заданной величины
    public static FinanceReports getPaymentsLessThanAmount(FinanceReports report, int threshold) {
        List<Payments> filteredPayments = new ArrayList<>();
        for (Payments payment : report.getPayments()) {
            if (payment.getAmount() < threshold) {
                filteredPayments.add(payment);
            }
        }
        return new FinanceReports(filteredPayments.toArray(new Payments[0]), report.getAuthor(), report.getCreationDate());
    }

    // Метод для вычисления суммарного платежа на указанную дату
    public static int getTotalPaymentsOnDate(FinanceReports report, String dateStr) {
        DateTimeFormatter.ofPattern("dd.MM.yy");
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Некорректный формат даты. Необходимо использовать dd.MM.yy.");
        }

        int total = 0;
        for (Payments payment : report.getPayments()) {
            if (payment.getDay() == date.getDayOfMonth() &&
                    payment.getMonth() == date.getMonthValue() &&
                    payment.getYear() == date.getYear()) {
                total += payment.getAmount();
            }
        }
        return total;
    }

    // Метод для получения месяцев без платежей в указанном году
    public static List<String> getMonthsWithNoPaymentsInYear(FinanceReports report, int year) {
        boolean[] monthsWithPayments = new boolean[12];

        for (Payments payment : report.getPayments()) {
            if (payment.getYear() == year) {
                monthsWithPayments[payment.getMonth() - 1] = true;
            }
        }

        List<String> emptyMonths = new ArrayList<>();
        for (int i = 0; i < monthsWithPayments.length; i++) {
            if (!monthsWithPayments[i]) {
                emptyMonths.add(getMonthName(i + 1));
            }
        }

        return emptyMonths;
    }

    // Метод для получения названия месяца
    private static String getMonthName(int month) {
        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return monthNames[month - 1];
    }

    //Метод для расчета налога на доход
    public static double calculateIncomeTax(FinanceReports report) {
        double result;
        int totalIncome = 0;

        for (Payments payment : report.getPayments()) {
            totalIncome += payment.getAmount();
        }

        if (totalIncome <= 2400000) { // переводим в копейки
            result = 0;
        } else if (totalIncome <= 24000000) { // в пределах до 240000 руб. (24000000 коп.)
            result = (totalIncome - 24000000) * 0.13;// 240000 руб.
        } else {
            result = (2400000 * 0.13) + (totalIncome - 24000000) * 0.20;// 240000 руб. с 13% и остальное 20%
        }
        return result;
    }
}


