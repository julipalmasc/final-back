package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.dto.CatalogWS;
import com.dh.catalogservice.domain.repository.MovieRepository;
import com.dh.catalogservice.domain.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService {

	private MovieRepository movieRepository;
	private SerieRepository serieRepository;




	@Autowired
	public CatalogServiceImpl(MovieRepository movieRepository, SerieRepository serieRepository) {
		this.movieRepository = movieRepository;
		this.serieRepository = serieRepository;

	}

	@Override
	public CatalogWS getCatalogByGenre(String genre) {
		return new CatalogWS(genre, movieRepository.getMoviesByGenre(genre), serieRepository.getSeriesByGenre(genre));
	}
}
