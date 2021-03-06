package com.SS.Administrator.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author acorb
 *
 */
@Entity
@Table(name = "tbl_book")
@AccessType(value = Type.FIELD)
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6338124170695094872L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	
	@NotBlank
	@Column(name = "title")
    private String title;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "tbl_book_authors",
	inverseJoinColumns = @JoinColumn(name = "authorId"),
	joinColumns = @JoinColumn(name = "bookId"))
	@NotEmpty
	private List<Author> authors;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@Lazy(value = false)
	@JoinTable(name = "tbl_book_genres",
	joinColumns = @JoinColumn (name = "bookId"),
	inverseJoinColumns =  @JoinColumn (name= "genre_id"))
	@NotEmpty
	private List<Genre> genres;
	
	@ManyToOne(cascade = CascadeType.PERSIST)//(fetch = FetchType.EAGER)
	@Lazy(value = false)
	@JoinColumn(name = "pubId", referencedColumnName = "publisherId")
	@JsonManagedReference
	@NotNull
	private Publisher publisher;


    public Integer getBookId() {
        return bookId;
    }

	public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", authors=" + authors + ", genres=" + genres
				+ ", publisher=" + publisher + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
}
