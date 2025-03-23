create table instructors(
                            instructor_id serial primary key  ,
                            instructor_name varchar(100) not null ,
                            email varchar(50)

);

create table courses(
                        course_id serial primary key,
                        course_name varchar(50) not null ,
                        description text ,
                        instructor_id  int,
                        FOREIGN KEY (instructor_id)  References  instructors(instructor_id) ON DELETE CASCADE
);

create table  student_course(
                                id serial primary  key  ,
                                student_id  int ,
                                FOREIGN KEY (student_id) references students(student_id)  ON DELETE CASCADE ,
                                course_id  int ,
                                foreign key  (course_id) references courses(course_id) ON DELETE CASCADE
);


create table students(
                         student_id serial primary key ,
                         student_name  varchar(255)  not null ,
                         email   varchar(255) unique ,
                         phone_number varchar(20)

);