package Validaciones;

import java.text.Normalizer;

public class Validador {
    public static String quitarTildes(String texto) {
        if (texto == null) return null; // Maneja posibles null
        // NFD (Normalization Form Decomposed) separa los caracteres y sus marcas diacríticas (tildes, diéresis, etc.).
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                //Busca todos los caracteres diacríticos (tildes, diéresis, etc.) y los reemplaza por "" (los elimina).
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}
