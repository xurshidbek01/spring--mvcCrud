package uz.pdp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Student;

import javax.swing.tree.RowMapper;
import java.util.List;

//@Repository
public class StudentRepositoryImpl implements StudentRepository {

    //bazaga ulanish xosil qilib beradigan
    JdbcTemplate jdbcTemplate;

    public StudentRepositoryImpl(DriverManagerDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int create(Student student) {
        try {
            return jdbcTemplate.update("insert into student(name,email,course,class_id,group_id) values (?,?,?,?,?)",
                    student.getName(), student.getEmail(), student.getCourse(),student.getClass_id(),student.getGroup_id());

        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Student> read() {
        return jdbcTemplate.query("select * from student", BeanPropertyRowMapper.newInstance(Student.class));
    }

    @Override
    public Student findStudentById(int studentId) {
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE id=?", BeanPropertyRowMapper.newInstance(Student.class), studentId);
    }

    @Override
    public Student findStudentByCourse(String course) {
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE course=?", BeanPropertyRowMapper.newInstance(Student.class), course);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE email=?", BeanPropertyRowMapper.newInstance(Student.class), email);
    }

    @Override
    public int update(Student student) {
        return jdbcTemplate.update("UPDATE student SET name=?, email=?, course=?,class_id=?,group_id=? WHERE id=?",
                student.getName(), student.getEmail(), student.getCourse(),student.getClass_id(),student.getGroup_id(), student.getId());
    }

    @Override
    public int delete(int studentId) {
        return jdbcTemplate.update("DELETE FROM student WHERE id=?", studentId);
    }
}
