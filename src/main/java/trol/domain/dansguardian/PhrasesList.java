package trol.domain.dansguardian;

import trol.domain.squid.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PhrasesList {
    Path path;
    String name;
    List<String> phrasesList;

    public PhrasesList(String stringPath) throws IOException {
        path = Paths.get(stringPath);
        name = path.getFileName().toString();
        phrasesList.addAll(FileHelper.createLineListFromPath(path));
    }

    public String getConfigString(){
        //TODO Opakować path znaczkami żeby zrobił inscluda do listy głównej
        return null;
    }
}
