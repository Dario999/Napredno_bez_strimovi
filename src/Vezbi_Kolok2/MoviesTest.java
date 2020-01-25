package Vezbi_Kolok2;

import java.util.*;

class Movie implements Comparable<Movie>{

    private String title;
    private int[] ratings;

    public Movie(String title,int[] ratings){
        this.title = title;
        this.ratings = ratings;
    }

    public double avgRating(){
        double sum = 0;
        for (int temp : ratings)
            sum += temp;
        return sum / ratings.length;
    }

    public int numRatings(){
        return ratings.length;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Movie movie) {
        if (avgRating() == movie.avgRating())
            return this.title.compareTo(movie.title);
        else if (avgRating() < movie.avgRating())
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f) of %d ratings",title,avgRating(),ratings.length);
    }
}

class MoviesList{

    private List<Movie> movies;

    public MoviesList(){
        movies = new ArrayList<>();
    }

    public void addMovie(String title,int[] ratings){
        movies.add(new Movie(title,ratings));
    }

    public List<Movie> top10ByAvgRating(){
        List<Movie> temp = new ArrayList<>();
        temp = movies;
        temp.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie movie, Movie t1) {
                return movie.compareTo(t1);
            }
        });
        return temp.subList(0,10);
    }

    // просечен ретјтинг на филмот x вкупно
    // број на рејтинзи на филмот / максимален
    // број на рејтинзи (од сите филмови во листата)
    public List<Movie> top10ByRatingCoef(){
        List<Movie> tempList = new ArrayList<>();
        tempList = movies;

        int sum = 0;
        for (Movie temp : movies)
            sum += temp.numRatings();

        tempList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie movie, Movie t1) {
                double first = movie.avgRating()*movie.numRatings();
                double second = t1.avgRating()*t1.numRatings();
                if (first == second)
                    return movie.getTitle().compareTo(t1.getTitle());
                else if (first < second)
                    return 1;
                else
                    return -1;
            }
        });

        return tempList.subList(0,10);
    }

}

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}

// vashiot kod ovde