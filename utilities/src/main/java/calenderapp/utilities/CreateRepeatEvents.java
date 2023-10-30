package calenderapp.utilities;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface CreateRepeatEvents {
    String title();
}
