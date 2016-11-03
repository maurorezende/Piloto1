package br.com.viavarejo.ss.test.cadastro.sucesso;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import br.com.viavarejo.ss.util.EnumAtributos;
import br.com.viavarejo.ss.util.TestAutomationBase;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;

@Config(browser = Browser.FIREFOX, url = CadastroCondPgNC10.URL_PORTAL_VIAVAREJO)
public class Cadastro_Cond_Pg_NC1 extends TestAutomationBase {

    public static final String URL_PORTAL_VIAVAREJO = "https://10.0.10.24/bahia/gateway?hptAppId=W1A1&hptExec=Y";
    private static final String MENSAGEM_OK_CONDICAO_PAGAMENTO = "CONDICAO DE PAGAMENTO CADASTRADA COM SUCESSO";
    private static final String CONDICAO_PAGAMENTO_CONSULTA = "http://operacaoh/V1-Comercializacao/faces/comercializacao/condicaoPagamento/condicaoPagamentoConsulta.jsf";
    private static final String ID_MASSA = "M-01";

    @Test
    public void teste() throws InterruptedException, IOException {

        // Inicio do script
        login(URL_PORTAL_VIAVAREJO, EMP_USR_NV_COMERCIALIZ, MAT_USR_NV_COMERCIALIZ, PWD_USR_NV_COMERCIALIZ);

        click(By.xpath(OP_NV_COMERCIALIZ));
        click(By.name("NM_BOT_PRC"));

        acceptAlert();
        navigateTo(CONDICAO_PAGAMENTO_CONSULTA);

        // Inicio do Cadastro de Condição de Pagamento
        click(By.id("form1:btnNovo"));

        selecionarCombo(ID_MASSA, EnumAtributos.EMPRESA);
        selecionarCombo(ID_MASSA, EnumAtributos.TIPO_VENDA);
        selecionarCombo(ID_MASSA, EnumAtributos.BANDEIRA);
        selecionarCombo(ID_MASSA, EnumAtributos.PARCELA_INICIAL);
        selecionarCombo(ID_MASSA, EnumAtributos.PARCELA_FINAL);
        selecionarCombo(ID_MASSA, EnumAtributos.TIPO_CARTAO);
        selecionarCombo(ID_MASSA, EnumAtributos.PARCELAMENTO);

        // Com Réplica de Bandeiras
        // click(By.id("id-correspondente"));

        // Confirmação do Cadastro
        click(By.id("form1:j_id99226738_4307b46b"));
        click(By.id("form1:botaoSalvar"));
        click(By.id("formDialogos:j_id267640243_593e6857"));

        // Aguardando mensagem de confirmação
        waitForElement(By.xpath("//div[@id='formDialogos:modalDialog']/div[2]/div"));

        String alerta = getTextModalByXPath("//div[@id='formDialogos:modalDialog']/div[2]/div");
        assertTrue(MENSAGEM_OK_CONDICAO_PAGAMENTO.equalsIgnoreCase(alerta));

        // Geração de Evidência da Tela
        gerarEvidencia(getClass().getSimpleName());

    }

    @After
    public void afterTest() {
        driver.quit();
    }

}
