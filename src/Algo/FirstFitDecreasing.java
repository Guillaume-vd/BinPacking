package Algo;

import Function.CompareDecroissant;
import Function.Fitness;
import Type.Bin;
import Type.Info;

import java.util.Collections;

public class FirstFitDecreasing {
    private int objectif;

    public FirstFitDecreasing(Info info){
        //Trie par ordre décroissant
        Collections.sort(info.getData(), new CompareDecroissant());

        //tant que tous les items ne sont pas affectés
        for(int i = 0; i < info.getData().size(); i++){
            int itemValue = info.getData().get(i);
            boolean ajouter = false;
            //on teste toutes les boites disponibles
            for (Bin b:info.getBins()) {
                //Si le bin rentre dans la boite
                if (itemValue <= b.getSizeAvailable()){
                    b.addData(itemValue);
                    ajouter = true;
                    break;
                }
            }

            //Si il rentre dans aucune boite
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
