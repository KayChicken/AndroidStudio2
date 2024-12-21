package domain.repositories;

import java.io.File;
import java.io.IOException;

public interface AIRepository {


    public String classifyMushroom(File file);


    public void trainModel(String trainingData);
}
