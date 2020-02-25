/**
 * 
 */
package com.SS.Administrator.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;
import com.SS.Administrator.Service.GenreService;


/**
 * @author acorb
 *
 */
@RestController
public class GenreController {
	@Autowired
	GenreService genreService;
	
	@RequestMapping(path = "/admin/genres", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Genre>> getgenres(){
			List <Genre> genres = genreService.readAllGenres();
			return new ResponseEntity<List<Genre>>(genres,HttpStatus.OK);
	}
		
	@RequestMapping(path = "/admin/genres/{genreId}", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> getgenre(@PathVariable int genreId){
		try {
			Genre genre = genreService.findGenreById(genreId);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);}
	}
	@RequestMapping(path = {"/admin/genres"} , method = RequestMethod.POST, consumes ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces ={
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> addgenre(@RequestBody Genre genre){
		try {
			genreService.addGenre(genre);
			return new ResponseEntity<Genre>(genre,HttpStatus.ACCEPTED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@RequestMapping(path = "/admin/genres/{genreId}", method = RequestMethod.PUT, consumes ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces ={
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> updategenre(@RequestBody Genre genre, @PathVariable int genreId){
		if(genreId != genre.getGenre_Id())
			return new ResponseEntity<Genre>(HttpStatus.BAD_REQUEST);
		try {
			genreService.updateGenre(genre);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path = "/admin/genres/{genreId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> deletegenre(@PathVariable int genreId){
		try {
			Genre genre =genreService.deleteGenre(genreId);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path="/admin/genres/{genreId}/books",produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Book>>readBooksBygenre(@PathVariable int genreId){
		try {
			List <Book> books = genreService.readBooksInGenre(genreId);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path="/admin/genres/{genreId}/books/{bookId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>deleteBookFromGenre(@PathVariable int genreId, @PathVariable int bookId){
		try {
			Book book = genreService.deleteGenreFromBook(bookId, genreId);
			return new ResponseEntity<Book>(book,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(path="/admin/genres/{genreId}/books/{bookId}", method = RequestMethod.PUT,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>putBookFromGenre(@PathVariable int genreId, @PathVariable int bookId){
		try {
			Book book = genreService.addGenreToBook(bookId, genreId);
			return new ResponseEntity<Book>(book,HttpStatus.ACCEPTED);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(path="/admin/genres/{genreId}/books/{bookId}", method = RequestMethod.POST,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>postBookFromGenre(@PathVariable int genreId, @PathVariable int bookId){
		try {
			Book book = genreService.postGenreToBook(bookId, genreId);
			if(book==null)
				return new ResponseEntity<Book>(HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Book>(book,HttpStatus.ACCEPTED);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}
}
