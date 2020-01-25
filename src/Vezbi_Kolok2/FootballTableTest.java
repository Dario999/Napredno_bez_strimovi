package Vezbi_Kolok2;

import javax.print.attribute.HashAttributeSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Partial exam II 2016/2017
 */


class Team{

    String name;
    int numGames;
    int numWins;
    int numDraws;
    int numLosses;
    int dadeniGolovi;
    int primeniGolovi;

    public Team(String name){
        this.name = name;
        this.numGames = 0;
        this.numDraws = 0;
        this.numLosses = 0;
        this.numWins = 0;
        this.primeniGolovi = 0;
        this.dadeniGolovi = 0;
    }

    public int getDadeniGolovi() {
        return dadeniGolovi;
    }

    public int getPrimeniGolovi() {
        return primeniGolovi;
    }

    public void addWin(){
        this.numWins++;
        this.numGames++;
    }

    public void addGolovi(int dadeni,int primeni){
        this.dadeniGolovi += dadeni;
        this.primeniGolovi += primeni;
    }

    public void addLoss(){
        this.numLosses++;
        this.numGames++;
    }

    public void addDraw(){
        this.numDraws++;
        this.numGames++;
    }

    public int getPoints(){
        return numWins * 3 + numDraws;
    }

    @Override
    public String toString() {
        return String.format("%-15s%5d%5d%5d%5d%5d",name,numGames,numWins,numDraws,numLosses,getPoints());
    }
}

class FootballTable{

    private TreeMap<String,Team> mapa;

    public FootballTable(){
        mapa = new TreeMap<>();
    }

    public void addGame(String homeTeam,String awayTeam,int homeGoals,int awayGoals){
        if (!mapa.containsKey(homeTeam))
            mapa.put(homeTeam,new Team(homeTeam));
        if (!mapa.containsKey(awayTeam))
            mapa.put(awayTeam,new Team(awayTeam));

        if (homeGoals > awayGoals){
            mapa.get(homeTeam).addWin();
            mapa.get(homeTeam).addGolovi(homeGoals,awayGoals);
            mapa.get(awayTeam).addLoss();
            mapa.get(awayTeam).addGolovi(awayGoals,homeGoals);
        }else if (homeGoals < awayGoals){
            mapa.get(awayTeam).addWin();
            mapa.get(awayTeam).addGolovi(awayGoals,homeGoals);
            mapa.get(homeTeam).addLoss();
            mapa.get(homeTeam).addGolovi(homeGoals,awayGoals);
        }else {
            mapa.get(homeTeam).addDraw();
            mapa.get(homeTeam).addGolovi(homeGoals,awayGoals);
            mapa.get(awayTeam).addDraw();
            mapa.get(awayTeam).addGolovi(awayGoals,homeGoals);
        }
    }

    public void printTable(){
        List<Team> lista = new ArrayList<>();
        for (String temp : mapa.keySet()){
            lista.add(mapa.get(temp));
            //System.out.println(mapa.get(temp));
        }
        lista.sort(new Comparator<Team>() {
            @Override
            public int compare(Team team, Team t1) {
                if (team.getPoints() > t1.getPoints())
                    return -1;
                else if(team.getPoints() < t1.getPoints())
                    return 1;
                else{
                    int razlika1 = team.dadeniGolovi - team.primeniGolovi;
                    int razlika2 = t1.dadeniGolovi - t1.primeniGolovi;
                    if (razlika1 > razlika2)
                        return -1;
                    else if (razlika1 < razlika2)
                        return 1;
                    else
                        return team.name.compareTo(t1.name);
                }
            }
        });
        int count = 1;
        for (Team temp : lista){
            System.out.printf("%2d. %s\n",count , temp);
            count++;
        }
    }

}


public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

// Your code here


