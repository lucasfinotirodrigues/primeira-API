package application.model;

import application.record.LivroDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo; 
    private String generos;
    private String autores;

    public Livro(LivroDto dto) {
        this.id = dto.id();
        this.titulo = dto.titulo();
        this.autores = dto.autores();
        this.generos = dto.generos();
    }

    public Livro(String titulo, String autores, String generos) {
        this.titulo = titulo;
        this.autores = autores;
        this.generos = generos;
    }
}
