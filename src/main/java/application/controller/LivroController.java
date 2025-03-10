package application.controller;

import application.model.Livro;
import application.record.LivroDto;
import application.repository.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> listarTodos() {
        List<LivroDto> livros = livroRepository.findAll().stream()
                .map(LivroDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable long id) {
        return livroRepository.findById(id)
                .map(livro -> ResponseEntity.ok(new LivroDto(livro)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LivroDto> criar(@RequestBody LivroDto livroDto) {
        Livro livro = new Livro(livroDto.titulo(), livroDto.autores(), livroDto.generos());
        Livro novoLivro = livroRepository.save(livro);
        return ResponseEntity.ok(new LivroDto(novoLivro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizar(@PathVariable long id, @RequestBody LivroDto livroDto) {
        return livroRepository.findById(id)
                .map(livroExistente -> {
                    livroExistente.setTitulo(livroDto.titulo());
                    livroExistente.setAutores(livroDto.autores());
                    livroExistente.setGeneros(livroDto.generos());
                    Livro livroAtualizado = livroRepository.save(livroExistente);
                    return ResponseEntity.ok(new LivroDto(livroAtualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}