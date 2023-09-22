ALTER TABLE medicos ADD inativo TINYINT not null;
ALTER TABLE pacientes ADD inativo TINYINT not null;
UPDATE medicos SET inativo = 0;
UPDATE pacientes SET inativo = 0;

