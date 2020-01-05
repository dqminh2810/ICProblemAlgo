import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {


        //testExample();
        //testExample2();
        //testRandom(10, 20);



        for(int i=530; i<640; i+=10){
            for(int j=17; j<28; j++){
                System.out.print("f["+i+"]["+j+"]: ");
                testPourcentage(2, 20, 30, i, j);
            }
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
        Graphe g = new Graphe(9, 9);

        Utils.showComposantes(composantes);


        g.buildGraphe(composantes);
        System.out.println(g.toString());
        for(int i=1; i<g.getAretes_tab().length; i++){
            for(int j=1; j<g.getAretes_tab().length; j++){
                System.out.print(g.getAretes_tab()[i][j] + ", ");
            }
            System.out.print("\n");
        }
        g.buildGraphe2(composantes);
        System.out.println(g.toString());
        for(int i=1; i<g.getAretes_tab().length; i++){
            for(int j=1; j<g.getAretes_tab().length; j++){
                System.out.print(g.getAretes_tab()[i][j] + ", ");
            }
            System.out.print("\n");
        }
        System.out.println();
        System.out.println("****************************************************************");
        System.out.println();
    }

    /**
     * Tester en utilisant l'autre  example
     */
    public static void testExample2(){
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
        sommets2.add(s3);
        sommets2.add(s4);
        sommets3.add(s3);
        sommets3.add(s4);
        sommets3.add(s5);
        sommets4.add(s3);
        sommets4.add(s4);
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

        Utils.showComposantes(composantes);

        g.buildGraphe(composantes);
        System.out.print(g.toString());
        System.out.println();
        System.out.println("****************************************************************");
        System.out.println();
    }

    /**
     * Tester en utilisant la méthode randomGen (Générer automatiquement des composantes)
     * @param p: Nombre de sommets pour chaque composante
     * @param t: Nombre de composantes
     */
    public static void testRandom(int p, int t){
        ArrayList<Composante> composantes = new ArrayList<>();
        composantes = Utils.randomGen(p, t);
        Graphe g = new Graphe();
        g.buildGraphe(composantes);
        /*
        System.out.println(g.toString());
        System.out.println();
        System.out.println("****************************************************************");
        System.out.println();
        */
    }

    /**
     * Evaluer l’efficacité de l'algorithme choisi - Calculer le pourcentage d’instances pour lesquelles l'algorithme choisi a trouvé une solution.
     * @param version: Version de l'algorithme afin de monter le graphe
     * @param p: Nombre de sommet pour chaque composante
     * @param t: Nombre de composante
     * @param k: Nombre d'arête maximum pour problème
     * @param delta: Degré maximum p pour problème
     */
    public static void testPourcentage(int version, int p, int t, int k, int delta){
        int res=0;
        for(int i=0; i<1000; i++){
            ArrayList<Composante> composantes = new ArrayList<>();
            composantes = Utils.randomGen(p, t);
            Graphe g = new Graphe();
            switch (version){
                case 1: g.buildGraphe(composantes); break;
                case 2: g.buildGraphe2(composantes); break;
                default: System.out.print("Error version to build - version does not exist"); System.exit(0);
            }
            g.buildGraphe(composantes);
            /*
            System.out.println("Nb Sommet: "+g.getNbSommet()+" --- Nb Arete: "+g.getNbAretes()+ " --- Degree: "+g.getDegre());
            System.out.println("Pour monter un graphe connexe à partir de ces sous-ensembles, il nous faut de: ");assert composantes != null;
            System.out.println("Maximum "+Utils.calculerNbAretesMax(composantes)+" arêtes");
            System.out.println("Maximum "+Utils.calculerDegreeMax(composantes)+" degré");

            System.out.println();
            */
            //if nbAretes<=k && nbAretes<=kmax && Degree<=delta && Degree<=deltaMax
            if((g.getNbAretes()<=k && (g.getDegre()<=delta)))
                res+=1;
        }

        System.out.println("Pourcentage: "+res/10);
    }



}
