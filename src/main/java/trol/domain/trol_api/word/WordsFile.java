package trol.domain.trol_api.word;

import java.time.LocalTime;
import java.util.List;

public class WordsFile {
    String name;
    boolean isTimed;
    LocalTime timeBegin;
    LocalTime timeEnd;
    boolean isBlocked;
    List<String> wordsList;
}
