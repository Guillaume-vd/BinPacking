package Algo;

import Function.Fitness;
import Function.Voisinage;
import Type.Info;

import java.util.ArrayList;
import java.util.List;

public class TabuSearch {
    private int score;

    public TabuSearch(Info info){
        info.setBins(new ArrayList<>()); //Réinitialisation de la liste de Bin

        //Appel des différents
        UnBinParItem unBinParItem = new UnBinParItem(info);
        Fitness o = new Fitness();
        Voisinage v = new Voisinage();

        this.score = o.getFitness(info.getBins());
        int bestScore = this.score;
        int scoreVoisin = 0;
        int newScore = 0;
        List<List<Integer>> tabu = new ArrayList<>();

        //On fait n fois l'algorithme
        for (int i = 0; i < info.getTabouIteration(); i++){
            scoreVoisin = 0;
            //System.out.println(i + " " + this.score + " " + info.getBins().size());

            for (int j = 0; j < info.getBins().size() - 1; j++) { //Pour chaque Bin source
                for (int k = 0; k < info.getBins().size() - 1; k++) { //Pour chaque Bin destination
                    for (int l = 0; l < info.getBins().get(j).getSize(); l++) { //Pour chaque item source

                        //Vérification si les bins sont présent dans la liste tabou
                        boolean acceptable = acceptable(info, tabu, j, k);
                        if (!acceptable) {
                            continue;
                        }

                        if (v.deplaceTabu(info, j, l, k)){ //Si le déplacement est réalisable
                            newScore = o.getFitness(info.getBins());
                            if (scoreVoisin <= newScore) { //Si le score est moins bon on mets les éléments dans la liste tabou
                                scoreVoisin = newScore;
                                List<Integer> temp = new ArrayList<>();
                                temp.add(k);
                                temp.add(info.getBins().get(k).getSize());
                                temp.add(j);
                                tabu.add(temp);

                                //Si on atteint la taille max de la liste tabou
                                if (tabu.size() > info.getTabuMax())
                                    tabu.remove(0);
                            }
                        }

                        //Utile dans la boucle???
                        //Vérification si les bins sont présent dans la liste tabou, après le déplacement d'un item
                        acceptable = acceptable(info, tabu, j, k);
                        if (!acceptable) {
                            continue;
                        }

                        for (int m = 0; m < info.getBins().get(k).getData().size(); m++) { //Pour chaque item destination

                            if (v.swapTabu(info, j, l, k, m)) { //Si le déplacement est réalisable
                                newScore = o.getFitness(info.getBins());
                                if (scoreVoisin <= newScore) { //Si le score est moins bon on le met dans la liste tabou
                                    scoreVoisin = newScore;
                                    List<Integer> temp = new ArrayList<>();
                                    temp.add(k);
                                    temp.add(info.getBins().get(k).getSize());
                                    temp.add(j);
                                    temp.add(m);
                                    tabu.add(temp);

                                    //Si on atteint la taille max de la liste tabou
                                    if (tabu.size() > info.getTabuMax())
                                        tabu.remove(0);
                                }
                            }
                        }

                    }
                }
            }

            //Si le score est moins bon on retire un éléments de la liste tabou
            if (scoreVoisin > newScore) {
                tabu.remove(0);
            } else if (scoreVoisin > bestScore) { //Sinon on met à jour le score
                bestScore = scoreVoisin;
            }
        }
        this.score = o.getFitness(info.getBins());
    }

    /**
    * Vérifie si la les bins ne sont pas dans la liste tabou
     * On ne déplace pas un item d'une taille X avec un autre d'une même taille X
     */
    public boolean acceptable(Info info, List<List<Integer>> tabu, int j, int k) {
        List<Integer> acceptable = new ArrayList<>();
        acceptable.add(k);
        acceptable.add(info.getBins().get(k).getSize());
        acceptable.add(j);
        acceptable.add(info.getBins().get(j).getSize());

        for (int i = 0; i < tabu.size(); i++) {
            if (tabu.get(i) == acceptable) {
                return false;
            }
        }
        return true;
    }

    public int getScore() {
        return this.score;
    }
}
