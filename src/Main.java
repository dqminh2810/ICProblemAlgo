import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        testExample();
        testRandom(2, 4);
    }

    /**
     * Construire des instances al´eatoires pour le problème
     * @param p Nombre des sous-ensembles
     * @param t Nombre sommet pour chaque sous-ensemble
     * @return ArrayList
     */
    public static ArrayList<Composante> randomGen(int p, int t){
        if(p>100 || p<1 || t<1){
            return null;
        }else{
            ArrayList<Composante> composantes = new ArrayList<>();
            for(int i=0; i<t; i++){
                Composante c = new Composante();
                ArrayList<Sommet> sommets = new ArrayList<>();
                for(int s=1; s<100; s++){
                    sommets.add(new Sommet(s));
                }
                Collections.shuffle(sommets);
                for(int j=0; j<p; j++){
                    c.ajouter_sommet(sommets.remove(j));
                }
                composantes.add(c);
            }
            // Afficher informations ce qu'on vient de créer
            System.out.println("Vous venez de créer "+ t +" sous-ensembles, chacun contient "+ p +" sommets");
            for (Composante c: composantes){
                System.out.print("[");
                for(Sommet s: c.getSommets()){
                    if(c.getSommets().lastIndexOf(s) == c.getSommets().size()-1)
                        System.out.print(s.getValue());
                    else System.out.print(s.getValue()+", ");
                }
                System.out.println("]");
            }
            return composantes;
        }
    }

    /**
     * Tester en utilisant l'example dans le premier exercice
     */
    public static void testExample(){
        //SOMMETS
        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        Sommet s5 = new Sommet(5);
        Sommet s6 = new Sommet(6);
        Sommet s7 = new Sommet(7);
        Sommet s8 = new Sommet(8);
        ArrayList<Sommet> sommets1 = new ArrayList<>();
        ArrayList<Sommet> sommets2 = new ArrayList<>();
        ArrayList<Sommet> sommets3 = new ArrayList<>();
        ArrayList<Sommet> sommets4 = new ArrayList<>();
        sommets1.add(s1);
        sommets1.add(s2);
        sommets1.add(s3);
        sommets2.add(s2);
        sommets2.add(s4);
        sommets2.add(s5);
        sommets3.add(s3);
        sommets3.add(s4);
        sommets3.add(s5);
        sommets4.add(s4);
        sommets4.add(s5);
        sommets4.add(s6);
        sommets4.add(s7);
        // COMPOSANTES
        Composante c1 = new Composante(sommets1);
        Composante c2 = new Composante(sommets2);
        Composante c3 = new Composante(sommets3);
        Composante c4 = new Composante(sommets4);

        ArrayList<Composante> composantes = new ArrayList<>();
        composantes.add(c1);
        composantes.add(c2);
        composantes.add(c3);
        composantes.add(c4);
        Graphe g = new Graphe();

        g.buildGraphe(composantes);
        System.out.println("Vous venez de créer "+ composantes.size() +" sous-ensembles");
        for (Composante c: composantes){
            System.out.print("[");
            for(Sommet s: c.getSommets()){
                if(c.getSommets().lastIndexOf(s) == c.getSommets().size()-1)
                    System.out.print(s.getValue());
                else System.out.print(s.getValue()+", ");
            }
            System.out.println("]");
        }
        System.out.print(g.toString());
        System.out.println("Etant donnée k=6 et des sous-ensemble créés. Existe-il une solution pour le problème IC : "+g.checkICProblem(6));
        System.out.println("Etant donnée k=7 et des sous-ensemble créés. Existe-il une solution pour le problème IC : "+g.checkICProblem(7));
        System.out.println();
        System.out.println("****************************************************************");
        System.out.println();
    }

    /**
     * Tester en utilisant la méthode randomGen
     */
    public static void testRandom(int p, int t){
        ArrayList<Composante> composantes = new ArrayList<>();
        composantes = randomGen(p, t);
        Graphe g = new Graphe();
        g.buildGraphe(composantes);
        System.out.println(g.toString());
        System.out.println();
        System.out.println("****************************************************************");
        System.out.println();
    }
}
