package Algo;

import Function.Objectif;
import Type.Bin;
import Type.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecuitSimule {
    private int score;

    public RecuitSimule(Info info){
        info.setBins(new ArrayList<>());
        UnBinParItem unBinParItem = new UnBinParItem(info);
        Objectif o = new Objectif();
        Random random = new Random();

        double t = 500.0;
        this.score = o.getObjectif(info.getBins());
        int lastScore = 0;
        int newScore = 0;
        List<Bin> bestBin = info.getBins();
        List<Bin> lastBin;

        for(int k = 0; k < 1000; k++){
            lastBin = info.getBins();
            lastScore = o.getObjectif(info.getBins());
            int choice = random.nextInt(2);
            if(choice == 0){
                this.relocateLoop(info);
            }else {
                this.exchangeLoop(info);
            }
            newScore = o.getObjectif(info.getBins());
            if(score <= newScore){
                score = newScore;
                bestBin = info.getBins();
            } else {
                double p = random.nextDouble();
                if(p > Math.exp(-(newScore-lastScore)/t)){
                    info.setBins(lastBin);
                }
            }
            t = t * 0.95;
        }
    }

    public void relocateLoop(Info info) {
        int nbIteration = 0;
        while (nbIteration < 100) {
            Random random = new Random();
            int source = random.nextInt(info.getBins().size());
            random = new Random();
            int destination = random.nextInt(info.getBins().size());
            random = new Random();
            int itemNumber = random.nextInt(info.getBins().get(source).getData().size());
            if (relocate(info, source, itemNumber, destination))
                break;
            nbIteration++;
        }
    }

    public boolean relocate(Info info, int sourceBin, int itemNumber, int destinationBin) {
        //Si l'item dans le bin
        if (info.getBins().get(sourceBin).getNbBin() <= itemNumber) {
            return false;
        }
        //Si la place n'est pas suffisante
        else if (info.getBins().get(destinationBin).getSizeAvailable() < info.getBins().get(sourceBin).getData().get(itemNumber)) {
            return false;
        }
        //Si c'est bon
        else  {
            info.getBins().get(destinationBin).addData(info.getBins().get(sourceBin).getData().get(itemNumber));
            info.getBins().get(sourceBin).removeData(itemNumber);
            if (info.getBins().get(sourceBin).getData().isEmpty())
                info.getBins().remove(sourceBin);
            return true;
        }
    }

    public void exchangeLoop(Info info) {
        boolean exchange = false;
        while (!exchange) {
            Random random = new Random();
            int source = random.nextInt(info.getBins().size());
            random = new Random();
            int destination = random.nextInt(info.getBins().size());
            random = new Random();
            int sourceItemNumber = random.nextInt(info.getBins().get(source).getData().size());
            random = new Random();
            int destinationItemNumber = random.nextInt(info.getBins().get(destination).getData().size());
            if (exchange(info, source, sourceItemNumber, destination, destinationItemNumber))
                exchange = true;
        }
    }

    public boolean exchange(Info info, int sourceBin, int sourceItemNumber, int destinationBin, int destinationItemNumber) {
        //Si échange dans un même bin
        if (sourceBin == destinationBin) {
            return false;
        }
        //Si l'item n'est pas présente dans le bin source
        else if (info.getBins().get(sourceBin).getData().size() <= sourceItemNumber) {
            return false;
        }
        //Si l'item n'est pas présente dans le bin destination
        else if (info.getBins().get(sourceBin).getData().size() <= destinationItemNumber) {
            return false;
        }
        //Il n'y a pas assez de place dans le bin source
        else if (info.getBins().get(destinationBin).getSizeAvailable() + info.getBins().get(destinationBin).getData().get(destinationItemNumber) < info.getBins().get(sourceBin).getData().get(sourceItemNumber)) {
            return false;
        }
        //Il n'y a pas assez de place dans le bin destination
        else if (info.getBins().get(sourceBin).getSizeAvailable() + info.getBins().get(sourceBin).getData().get(sourceItemNumber) < info.getBins().get(destinationBin).getData().get(destinationItemNumber)) {
            return false;
        } else {
            int size = info.getBins().get(destinationBin).getData().get(destinationItemNumber);
            info.getBins().get(destinationBin).removeData(destinationItemNumber);
            info.getBins().get(destinationBin).addData(info.getBins().get(sourceBin).getData().get(sourceItemNumber));
            info.getBins().get(sourceBin).removeData(sourceItemNumber);
            info.getBins().get(sourceBin).addData(size);
            return  true;
        }
    }

    public int getScore() {
        return score;
    }
}
