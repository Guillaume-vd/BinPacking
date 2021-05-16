package Algo;

import Function.Objectif;
import Function.Voisinage;
import Type.Bin;
import Type.Info;

import java.util.ArrayList;
import java.util.List;

public class TabuSearch {
    private int score;

    public TabuSearch(Info info){
        info.setBins(new ArrayList<>());
        UnBinParItem unBinParItem = new UnBinParItem(info);
        Objectif o = new Objectif();
        Voisinage v = new Voisinage();

        this.score = o.getObjectif(info.getBins());
        List<Bin> tabu = new ArrayList<>();
        tabu.add(info.getBins().get(0));
        List<Bin> neidhborhood;
        Bin bestCandidate = info.getBins().get(0);
        Bin best = info.getBins().get(0);

         /*while (false){
           neidhborhood = getNeighbors(bestCandidate);
            bestCandidate = neidhborhood.get(0);
            for (Bin b:neidhborhood){
                //refaire avec liste de bin cas b ou cas best actuel
                if(!tabu.contains(b) && o.getObjectif(b) > o.getObjectif(bestCandidate)){
                    bestCandidate = b;
                }
            }

            if(o.getObjectif(bestCandidate) > o.getObjectif(best)){
                best = bestCandidate;
            }

            tabu.add(bestCandidate);

            if(tabu.size() > info.getTabuMax()){
                tabu.remove(0);
            }
        }*/
    }

    public List<Bin> getNeighbors(Bin bin){
        List<Bin> neighbors = new ArrayList<>();
        return neighbors;
    }

    public int getScore() {
        return this.score;
    }
}
