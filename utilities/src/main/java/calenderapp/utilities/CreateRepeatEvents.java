package calenderapp.utilities;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalDate;
import java.time.LocalTime;

@Retention(RetentionPolicy.RUNTIME)
public @interface CreateRepeatEvents {
    String title();
}
