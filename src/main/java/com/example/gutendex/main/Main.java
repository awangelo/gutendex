package com.example.gutendex.main;

import com.example.gutendex.model.Author;
import com.example.gutendex.model.Book;
import com.example.gutendex.model.BookData;
import com.example.gutendex.model.GutendexData;
import com.example.gutendex.repository.AuthorRepository;
import com.example.gutendex.repository.BookRepository;
import com.example.gutendex.service.ConvertData;
import com.example.gutendex.service.GutendexService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final GutendexService gutendexService = new GutendexService();
    private final ConvertData convertData = new ConvertData();
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final String ADDRESS = "https://gutendex.com/books/?search=";

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar livro por título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    6 - Exibir estatísticas de livros por idioma
                    0 - Sair
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listAuthorsAliveInYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 6:
                    showBookStatisticsByLanguage();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void searchBookByTitle() {
        System.out.println("Digite o nome do livro que deseja buscar:");
        var bookName = scanner.nextLine();
        var json = gutendexService.getData(ADDRESS + bookName.replace(" ", "+"));
        var gutendexData = convertData.getData(json, GutendexData.class);
        if (gutendexData != null && !gutendexData.results().isEmpty()) {
            BookData bookData = gutendexData.results().get(0);
            Book book = new Book(bookData);
            System.out.println(book);
            bookRepository.save(book);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void listRegisteredBooks() {
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void listRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    private void listAuthorsAliveInYear() {
        System.out.println("Digite o ano que deseja pesquisar:");
        var year = scanner.nextInt();
        scanner.nextLine();
        List<Author> authors = authorRepository.findAuthorsAliveInYear(year);
        authors.forEach(System.out::println);
    }

    private void listBooksByLanguage() {
        System.out.println("Digite o idioma que deseja pesquisar (ex: en, pt, es, fr):");
        var language = scanner.nextLine();
        List<Book> books = bookRepository.findByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma: " + language);
        } else {
            books.forEach(System.out::println);
        }
    }

    private void showBookStatisticsByLanguage() {
        System.out.println("Estatísticas de livros por idioma:");
        long englishBooks = bookRepository.countByLanguage("en");
        System.out.println("- Livros em Inglês: " + englishBooks);
        long portugueseBooks = bookRepository.countByLanguage("pt");
        System.out.println("- Livros em Português: " + portugueseBooks);
    }
}
