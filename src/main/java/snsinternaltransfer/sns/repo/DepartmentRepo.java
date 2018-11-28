package snsinternaltransfer.sns.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Department;

@Repository
public class DepartmentRepo {

    @Autowired
    JdbcTemplate template;

    public Department getDepartment(int departmentId) {
        String sql = "Select department FROM snsto.department WHERE deartmentID="+departmentId;

        RowMapper<Department> rm = new BeanPropertyRowMapper<>(Department.class);
        Department department = template.queryForObject(sql, rm);

        return department;

    }


}
