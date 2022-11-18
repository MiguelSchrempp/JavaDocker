
package sptech.school.guardian.angel.project;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoMySql {

    private JdbcTemplate conexao;

    public ConexaoMySql() {
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost : 3306/GuardianAngel?useTimezone=true&serverTimezone=UTC");
        datasource.setUsername("urubu100");
        datasource.setPassword("urubu100");
        conexao = new JdbcTemplate(datasource);
}
    public JdbcTemplate getConexao(){
        return conexao;
    }

}
