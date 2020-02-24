/**
 * 
 */
package com.SS.Administrator.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.Administrator.DAO.GenreDAO;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;

/**
 * @author acorb
 *
 */
@Component
public class GenreService {
	@Autowired
	GenreDAO gDao;
	
	public List<Genre> readAllGenres(){
		return gDao.findAll();
	}
	
	public void addGenre(Genre genre) throws IllegalArgumentException, SQLException {
		if(genre.getGenreName()!=null) {
			if(genre.getGenreId()!=null)
				if(gDao.existsById(genre.getGenreId()))
					throw new SQLException();
				else {
					Genre returned = gDao.save(genre);
					gDao.flush();
					genre= returned;
				}
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public void updateGenre(Genre genre) throws IllegalArgumentException{
		if(genre.getGenreId()!=null&&genre.getGenreName()!=null) {
			Genre returned = gDao.save(genre);
			gDao.flush();
			genre= returned;
		}else
			throw new IllegalArgumentException();
	}
	
	public Genre deleteGenre(int genreId)throws NoSuchElementException {
		Genre deletedGenre = gDao.findById(genreId).get();
		gDao.deleteById(genreId);
		gDao.flush();
		return deletedGenre;
	}
	public Genre findGenreById(int genreId)throws NoSuchElementException{
		return gDao.findById(genreId).get();
	}
	public List<Book> readBooksInGenre(int genreId)throws NoSuchElementException{
		return gDao.findById(genreId).get().getBooks();
	}
	
}