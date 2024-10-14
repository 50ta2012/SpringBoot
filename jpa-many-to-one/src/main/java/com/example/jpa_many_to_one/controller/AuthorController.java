package com.example.jpa_many_to_one.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_many_to_one.entity.Author;
import com.example.jpa_many_to_one.entity.AuthorRepository;
import com.example.jpa_many_to_one.entity.Book;
import com.example.jpa_many_to_one.entity.BookRepository;

@RestController
@CrossOrigin
public class AuthorController {

  @Autowired
  AuthorRepository authorRepository;

  @Autowired
  BookRepository bookRepository;

  @PostMapping("/add")
  public Author addAuthor(@RequestBody Author author) {

    return new Author().setAuthorDTO(authorRepository.save(author));
  }

  @GetMapping("/author/{id}")
  public Author getAuthorById(@PathVariable("id") String id) {

    Optional<Author> authorOptional = authorRepository.findById(id);

    if (authorOptional.isEmpty()) {
      return null;
    }

    return new Author().setAuthorDTO(authorOptional.get());
  }

  @GetMapping("/author/all")
  public List<Author> getAllAuthors() {

    return authorRepository.findAll().stream()
        .map(author -> new Author().setAuthorDTO(author))
        .collect(Collectors.toList());
  }

  @GetMapping("/book/all")
  public List<Book> getAllBooks() {
    return bookRepository.findAll().stream()
        .map(book -> new Book().setBookDTO(book))
        .collect(Collectors.toList());
  }

  @PostMapping("/update/{id}")
  public Author updateAuthorByName(@PathVariable("id") String id, @RequestBody Author author) {
    Optional<Author> authorInDbOpt = authorRepository.findById(id);
    if (authorInDbOpt.isEmpty()) {
      return null;
    }

    Author authorInDb = authorInDbOpt.get();
    authorInDb.setName(author.getName());

    return authorRepository.save(authorInDb);
  }

  @GetMapping("/echo")
  public String echo() {
    return "echo";
  }

  @GetMapping("/delete/all")
  public void deleteAll() {
    authorRepository.deleteAll();
    // bookRepository.deleteAll();
  }

  @DeleteMapping("/author/{authorId}")
  public String deleteAuthorById(@PathVariable("authorId") String authorId) {
    Optional<Author> authorOptional = authorRepository.findById(authorId);
    if (authorOptional.isEmpty()) {
      return null;
    }

    authorRepository.delete(authorOptional.get());
    return "Success";
  }

  @DeleteMapping("/book/{bookId}")
  public String deleteBookById(@PathVariable("bookId") String bookId) {
    // 刪除書只能從 parant 物件裡刪除
    Optional<Book> bookOptional = bookRepository.findById(bookId);

    if (bookOptional.isEmpty()) {
      return null;
    }

    Book bookWantToDelete = bookOptional.get();
    Author authorInDb = bookWantToDelete.getAuthor();

    authorInDb.getBooks().removeIf(book -> book.equals(bookWantToDelete));

    authorRepository.save(authorInDb);
    return "Success";
  }

  @PostMapping("/book/update")
  public Book updateBookByBookId(@RequestBody Book book) {
    Optional<Book> optionalBook = bookRepository.findById(book.getId());
    if (optionalBook.isEmpty()) {
      return null;
    }

    Book bookInDb = optionalBook.get();
    bookInDb.setTitle(book.getTitle());

    return new Book().setBookDTO(bookRepository.save(bookInDb));
  }
}
