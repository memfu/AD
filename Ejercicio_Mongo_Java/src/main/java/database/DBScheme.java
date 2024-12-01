package database;

public interface DBScheme {
    String user = "mongo";
    String pw = "mongo";

    String dbCentroEstudios = "centro_estudios";
    String collAlumnos = "alumnos";
    String collProfesores = "profesores";

    // Keys comunes para alumnos y profesores
    String keyRating = "rating";
    String keyAge = "age";
    String keyName = "name";
    String keyGender = "gender";
    String keyMail = "email";
    String keyPhone = "phone";

    // Keys solo para alumnos
    String keyCalifAl = "calificaation";
    String keyGradeAl = "higher_grade";

    // Keys solo para profesores
    String keySubjectsPr = "subjects";
    String keyTitlePr = "title";


}
