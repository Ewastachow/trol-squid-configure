package trol.domain.dansguardian;

import trol.domain.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PhrasesIncludeList {
    Path path;
    List<PhrasesList> contentList;

    //TODO Implement schema for Phrases

    public PhrasesIncludeList(String pathString) throws IOException {
        path = Paths.get(pathString);
        contentList = new ArrayList<>();
        List<String> fileLines = FileHelper.createLineListFromPath(this.path);
        for(String i: fileLines){
            String stringPath = FileHelper.getPathStringFromIncludeLine(i);
            if(!stringPath.equals(null)) contentList.add(new PhrasesList(stringPath));
        }
    }
}
