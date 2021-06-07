package Algo;

import Function.Fitness;
import Type.Bin;
import Type.Info;

import java.util.ArrayList;
import java.util.Collections;

public class FirstFistDecreasingRand {
    private int objectif;

    public FirstFistDecreasingRand(Info info){
        info.setBins(new ArrayList<>());
        Collections.shuffle(info.getData());

        //Tant que tous les items ne sont pas affectés
        for(int i = 0; i < info.getData().size(); i++){
            int itemValue = info.getData().get(i);
            boolean ajouter = false;
            //On teste toutes les boites disponibles
            for (Bin b:info.getBins()) {
                //Si le bin rentre dans la boite
                if (itemValue <= b.getSizeAvailable()){
                    b.addData(itemValue);
                    ajouter = true;
                    break;
                }
            }

            //S'il n'entre dans aucune boite
            if(!ajouter){
                Bin bin = new Bin(info.getSize());
                bin.addData(itemValue);
                info.addBin(bin);
            }
        }
        Fitness o = new Fitness();
        this.objectif = o.getFitness(info.getBins());
    }

    public int getObjectif() {
        return objectif;
    }
}
