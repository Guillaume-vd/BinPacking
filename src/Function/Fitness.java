package Function;

import Type.Bin;

import java.util.List;

public class Fitness {
    public Fitness(){}

    public int getFitness(List<Bin> bins){
        int objectif = 0;
        for (Bin b:bins) {
            objectif += Math.pow(b.getSize() - b.getSizeAvailable(),2);
        }
        return objectif;
    }
}
