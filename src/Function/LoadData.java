package Function;

import Type.Info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadData {
    Info info;

    public LoadData(String filename) throws IOException {
        info = new Info();
        File file = new File(filename);
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/data/" + file));
        String line;
        int nb_line = 1;
        while ((line = reader.readLine()) != null) { //Tant que nous ne sommes pas à la fin
            //Première ligne
            if (nb_line == 1) {
                String[] header = line.split(" ");
                this.info.setSize(Integer.parseInt(header[0]));
                this.info.setNbBin(0);
                this.info.setNbItem(Integer.parseInt(header[1]));
            } else { //Autres lignes
                this.info.addData(Integer.parseInt(line));
            }
            nb_line += 1;
        }
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
