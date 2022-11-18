/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sptech.school.guardian.angel.project;

import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.JdbcTemplate;

public class cli {

    public static void main(String[] args) {
        InformacoesLooca il = new InformacoesLooca();
        ProcessoGrupo processoGrupo = il.looca.getGrupoDeProcessos();
        List<Processo> processos = processoGrupo.getProcessos();
        Timer timer = new Timer();
        Integer delay = 0;
        Integer intervalo = 1;
        ConexaoAzure conexao = new ConexaoAzure();
        ConexaoMySql conexaoMy = new ConexaoMySql();
        JdbcTemplate con = conexao.getConexao();
        JdbcTemplate conMy = conexaoMy.getConexao();
        String insertionRam = "INSERT INTO registro(fkMaquina, componente, registroComponente, horaRegistro, dataRegistro) values ( 1, 1, ?, ?, ?)";
        String insertionCPU = "INSERT INTO registro(fkMaquina, componente, registroComponente, horaRegistro, dataRegistro) values (1, 2, ?, ?, ?)";
        String insertionDisco = "INSERT INTO registro(fkMaquina, componente, registroComponente, horaRegistro, dataRegistro) values (1, 3, ?, ?, ?)";
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                while (true) {
                    Date dataHoraAtual = new Date();
                    String data = new SimpleDateFormat("yyyy/MM/dd").format(dataHoraAtual);
                    String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

                    System.out.println("Java cli Guardian angel");
                    System.out.println("Exibir RAM");
                    System.out.println(String.format("A memoria ram está em: %.2f%% - %s", il.porcentagemRam(), il.validacaoRam()));
                    System.out.println("Exibir CPU");
                    System.out.println(String.format("%.2f%% - %s ", il.processador.getUso(), il.validacaoCPU()));
                    System.out.println("Exibir Disco");
                    System.out.println(String.format("%d%% - %s", il.exibirMemoriaDisco(), il.validacaoVolume()));

                    con.update(insertionRam, il.porcentagemRam(), hora, data);
                    con.update(insertionCPU, il.processador.getUso(), hora, data);
                    con.update(insertionDisco, il.porcentagemRam(), hora, data);

                    conMy.update(insertionRam, il.porcentagemRam(), hora, data);
                    conMy.update(insertionCPU, il.processador.getUso(), hora, data);
                    conMy.update(insertionDisco, il.porcentagemRam(), hora, data);
                    il.timer(7000);

                }
            }
        }, delay, intervalo
        );
    }
}
