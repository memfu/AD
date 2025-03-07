CREATE TABLE Aula (
    id_aula INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    capacidad INT NOT NULL
);

CREATE TABLE Profesor (
    id_profesor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);


CREATE TABLE Curso (
    id_curso INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    id_profesor INT,
    id_aula INT,
    CONSTRAINT fk_curso_profesor FOREIGN KEY (id_profesor) REFERENCES Profesor(id_profesor) 
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_curso_aula FOREIGN KEY (id_aula) REFERENCES Aula(id_aula) 
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Alumno (
    id_alumno INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email VARCHAR(100) UNIQUE,
    id_curso INT, 
    CONSTRAINT fk_alumno_curso FOREIGN KEY (id_curso) REFERENCES Curso(id_curso) 
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Profesor_Curso (
    id_profesor INT,
    id_curso INT,
    PRIMARY KEY (id_profesor, id_curso),
    CONSTRAINT fk_profesor FOREIGN KEY (id_profesor) REFERENCES Profesor(id_profesor) ON DELETE CASCADE,
    CONSTRAINT fk_curso FOREIGN KEY (id_curso) REFERENCES Curso(id_curso) ON DELETE CASCADE
);


INSERT INTO Aula (id_aula, nombre, capacidad) VALUES 
(1, 'Aula 101', 30),
(2, 'Aula 102', 25),
(3, 'Aula 201', 40),
(4, 'Laboratorio 1', 20);


INSERT INTO Profesor (id_profesor, nombre, apellido, email) VALUES 
(1, 'Carlos', 'Pérez', 'carlos.perez@centro.com'),
(2, 'Ana', 'Gómez', 'ana.gomez@centro.com'),
(3, 'David', 'Fernández', 'david.fernandez@centro.com'),
(4, 'María', 'López', 'maria.lopez@centro.com');


INSERT INTO Curso (id_curso, nombre, id_profesor, id_aula) VALUES 
(1, 'Matemáticas', 1, 1),
(2, 'Historia', 2, 2),
(3, 'Ciencias', 3, 3),
(4, 'Programación', 4, 4);


INSERT INTO Alumno (id_alumno, nombre, apellido, fecha_nacimiento, email, id_curso) VALUES 
(1, 'Juan', 'Martínez', '2005-06-15', 'juan.martinez@centro.com', 1),
(2, 'Laura', 'Sánchez', '2006-04-10', 'laura.sanchez@centro.com', 2),
(3, 'Pedro', 'Ramírez', '2005-12-20', 'pedro.ramirez@centro.com', 3),
(4, 'Elena', 'Torres', '2007-01-05', 'elena.torres@centro.com', 4);


INSERT INTO Profesor_Curso (id_profesor, id_curso) VALUES 
(1, 1),  -- Carlos Pérez enseña Matemáticas
(2, 2),  -- Ana Gómez enseña Historia
(3, 3),  -- David Fernández enseña Ciencias
(4, 4),  -- María López enseña Programación
(1, 3),  -- Carlos Pérez también enseña Ciencias
(2, 4),  -- Ana Gómez también enseña Programación
(3, 1);  -- David Fernández también enseña Matemáticas