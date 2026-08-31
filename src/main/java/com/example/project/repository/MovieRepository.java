package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}