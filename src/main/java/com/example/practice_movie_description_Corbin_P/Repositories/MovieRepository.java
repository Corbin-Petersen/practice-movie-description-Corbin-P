package com.example.practice_movie_description_Corbin_P.Repositories;

import com.example.practice_movie_description_Corbin_P.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
