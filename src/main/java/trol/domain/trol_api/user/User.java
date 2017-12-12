package trol.domain.trol_api.user;

import java.time.LocalTime;

/**
 * Created by Szymon on 12.12.2017.
 */
public class User {
    String address; //TODO: Jak rozwiązać Others? Jakieś predefiniowany adress np. *.* ?
    boolean isTimed;
    LocalTime timeBegin;
    LocalTime timeEnd;
    boolean isBlocked;
}
