import domain.Custom;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/27 19:53
 * @Version: 1.0
 */
public class JDBCTemplate {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "select * from custom_info";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> stringObjectMap : mapList) {
            System.out.println(stringObjectMap);
        }
    }

    //        1. 修改1号数据的 I_Level 为 L2
    @Test
    public void Test1() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "update custom_info set I_Level = 'L2' where id = ?";
        int i = jdbcTemplate.update(sql, 1);
        System.out.println(i);
    }

    //        2. 添加一条记录
    @Test
    public void Test2() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "insert into custom_info(id, name, gender, address, I_Level) values(?, ?, ?, ?, ?)";
        int i = jdbcTemplate.update(sql, 12, "Xiao", "M", "GuangZhou", "L3");
        System.out.println(i);
    }

    //        3. 删除刚才添加的记录
    @Test
    public void Test3() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "delete from custom_info where id = ?";
        int i = jdbcTemplate.update(sql, 12);
        System.out.println(i);
    }

    //        4. 查询id为1的记录，将其封装为Map集合
    @Test
    public void Test4() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "select * from custom_info where id = ?";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, 1);
        System.out.println(stringObjectMap);
    }

    //        5. 查询所有记录，将其封装为List
    @Test
    public void Test5() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "select * from custom_info";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> stringObjectMap : mapList) {
            System.out.println(stringObjectMap);
        }
    }

    //        6. 查询所有记录，将其封装为custom对象的List集合
    @Test
    public void Test6() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "select * from custom_info";
        // 封装成Custom对象的List数组
        List<Custom> list = jdbcTemplate.query(sql, new RowMapper<Custom>() {
            @Override
            public Custom mapRow(ResultSet resultSet, int i) throws SQLException {
                Custom custom = new Custom(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("gender"),
                        resultSet.getString("address"),
                        resultSet.getString("I_Level")
                );
                return custom;
            }
        });
        for (Custom custom : list) {
            System.out.println(custom);
        }
    }

    //        6. 查询所有记录，将其封装为custom对象的List集合
    @Test
    public void Test6_1() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "select * from custom_info";
        // 封装成Custom对象的List数组
        List<Custom> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Custom.class));
        for (Custom custom : list) {
            System.out.println(custom);
        }
    }

    //        7. 查询总记录数
    @Test
    public void Test7() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "select count(*) from custom_info";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(count);
    }
}
