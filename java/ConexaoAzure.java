package sptech.school.guardian.angel.project;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoAzure {
    
    private JdbcTemplate conexao;

    
    public ConexaoAzure(){
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    
        datasource.setUrl ("jdbc:sqlserver://guardian-angel.database.windows.net:1433;database=guardianAngel;encryp\n" +
                           "t=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");
    
        datasource.setUsername ("admGuardianAngel");
    
        datasource.setPassword ("guardian#angel#grupo7");
                
        conexao = new JdbcTemplate(datasource);
    }
    
    public JdbcTemplate getConexao(){
        return conexao;
    }

}
