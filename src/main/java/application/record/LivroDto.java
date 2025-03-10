package application.record;

import application.model.Livro;

public record LivroDto(long id, String titulo, String autores, String generos) {
    public LivroDto(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutores(), livro.getGeneros());
    }
}
