package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String loadData(Model model){
        Actor actor_obj1 = new Actor();
        actor_obj1.setName("Harrison");
        actor_obj1.setRealname("Harrison Ford");

        Movie movie_obj1 = new Movie("Star War", 1977, "a series of movies");
        Movie movie_obj2 = new Movie("Out of Africa", 2000, "a series of movies");
        //actor_obj1.getMovies().add(movie_obj1);  // NOT CORRECT
        //actor_obj1.getMovies().add(movie_obj2);

        Set<Movie> movies = new HashSet<>();
        movies.add(movie_obj1);
        movies.add(movie_obj2);
        actor_obj1.setMovies(movies);

        actorRepository.save(actor_obj1);
        model.addAttribute("actors", actorRepository.findAll());

        // work on the movie object

        Set<Actor> cast = new HashSet<Actor>();
        cast.add(actor_obj1);

        movie_obj1.setCast(cast);
        movie_obj2.setCast(cast);

        movieRepository.save(movie_obj1);
        movieRepository.save(movie_obj2);
        model.addAttribute("movies", movieRepository.findAll());

        return "index";

    }
}
