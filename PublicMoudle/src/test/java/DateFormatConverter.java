import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatConverter {
    public static Date dateChangeFormat(Date date) {
        // 将 Date 对象转换为 LocalDateTime 对象
        LocalDateTime dateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // 将 LocalDateTime 对象转换为 LocalDate 对象
        LocalDate localDate = dateTime.toLocalDate();

        // 将 LocalDate 对象转换为 Date 对象
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        // 示例日期
        Date date = new Date(); // 当前时间
        System.out.println("Original Date: " + date);

        // 转换日期格式
        Date newDate = dateChangeFormat(date);
        System.out.println("Formatted Date: " + newDate);
    }
}
