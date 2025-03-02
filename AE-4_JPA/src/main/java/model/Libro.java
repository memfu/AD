package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "libros")
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private  String titulo;
    @Column
    private double precio;

    // un libro tiene una editorial, una editorial puede tener muchos libros
    // Cascada: si hago aquí una modificación, la replico en cascada a todos aquellos objetos con los que esté relacionado
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    // un libro tiene un autor, un autor puede tener muchos libros
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToMany(mappedBy = "coleccionLibros")
    private List<Libreria> listaLibrerias;

    public Libro(String titulo, double precio) {
        this.titulo = titulo;
        this.precio = precio;
    }
}
