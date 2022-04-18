package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.dto.CatalogWS;
import com.dh.catalogservice.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService {

	private MovieRepository movieRepository;



	@Autowired
	public CatalogServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;

	}

	@Override
	public CatalogWS getCatalogByGenre(String genre) {
		return new CatalogWS(genre, movieRepository.getMoviesByGenre(genre));
	}
}