package Algo;

import Function.Fitness;
import Type.Bin;
import Type.Info;

import java.util.ArrayList;

public class UnBinParItem {
    private int objectif;

    public UnBinParItem(Info info){
        info.setBins(new ArrayList<>());

        for (Integer i:info.getData()) {
            Bin bin = new Bin(info.getSize());
            bin.addData(i);
            info.addBin(bin);
        }

        Fitness o = new Fitness();
        this.objectif = o.getFitness(info.getBins());
    }

    public int getObjectif() {
        return objectif;
    }
}
