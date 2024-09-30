import com.example.ResultadoValidacao;
import com.example.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class UsuarioTest {

    @Test
    public void DadosValidos() {
        Usuario usuario = new Usuario("Rafael", "teste@teste.com", "teste123", "teste123", "123.456.789-00", "12345-678");

        ResultadoValidacao resultado = usuario.salvarUsuario();
        assertTrue(resultado.isSucesso());
        assertEquals("Usuário salvo com sucesso!", resultado.getMensagemErro());
    }

    @Test
    public void senhaInvalida() {

        Usuario usuario = new Usuario(
                "João Silva",
                "joao.silva@example.com",
                "senhaSegura123",
                "senhaDiferente123",
                "123.456.789-09",
                "12345-678"
        );

        ResultadoValidacao resultado = usuario.salvarUsuario();

        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: A senha e a confirmação de senha não correspondem.", resultado.getMensagemErro());
    }

    @Test
    public void FaltaCampo(){
        Usuario usuario = new Usuario(
            "", 
            "joao.silva@example.com",
            "senhaSegura123",
            "senhaSegura123",
            "123.456.789-09",
            "12345-678");

            ResultadoValidacao resultado = usuario.salvarUsuario();

            assertFalse(resultado.isSucesso());
            assertEquals("Erro ao salvar o usuário: O nome é obrigatório.", resultado.getMensagemErro());
    }
    

    @Test
    public void FaltaEmail(){
        Usuario usuario = new Usuario(
            "João Silva",
                "",
                "senhaSegura123",
                "senhaSegura123",
                "123.456.789-09",
                "12345-678"
        );

        ResultadoValidacao resultado = usuario.salvarUsuario();

        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: O e-mail é obrigatório.", resultado.getMensagemErro());
    }

    @Test
    public void faltaSenha(){
        Usuario usuario = new Usuario(
                "João Silva",
                "joao.silva@example.com",
                "",
                "",
                "123.456.789-09",
                "12345-678" );

        ResultadoValidacao resultado = usuario.salvarUsuario();

        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: A senha e a confirmação de senha são obrigatórias.", resultado.getMensagemErro());
    }

    @Test
    public void faltaConfirmacaoSenha(){
        Usuario usuario = new Usuario(
                "João Silva",
                "joao.silva@example.com",
                "aaaaaaaaa",
                "",
                "123.456.789-09",
                "12345-678" );

        ResultadoValidacao resultado = usuario.salvarUsuario();

        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: A senha e a confirmação de senha são obrigatórias.", resultado.getMensagemErro());
    }

    @Test
    public void SemCpf(){
        Usuario usuario = new Usuario(
                "João Silva",   
                "joao.silva@example.com",
                "senhaSegura123", 
                "senhaSegura123",
                "",
                "12345-678");

        ResultadoValidacao resultado = usuario.salvarUsuario();
        
        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: O CPF ou CNPJ é obrigatório.", resultado.getMensagemErro());
    }

    @Test
    public void SemCep(){
        Usuario usuario = new Usuario(
                "João Silva",
                "joao.silva@example.com",
                "senhaSegura123",
                "senhaSegura123",
                "123.456.789-09",
                ""
        );

        ResultadoValidacao resultado = usuario.salvarUsuario();

        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: O CEP é obrigatório.", resultado.getMensagemErro());
    }

    @Test
    public void emailInvalido(){
        Usuario usuario = new Usuario(
                "João Silva",                     // nome
                "joao.silva$com",                 // email (inválido)
                "senhaSegura123",                 // senha
                "senhaSegura123",                 // confirmarSenha
                "123.456.789-09",                 // CPF
                "12345-678" 
        );

        ResultadoValidacao resultado = usuario.salvarUsuario();

        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: O e-mail fornecido é inválido.", resultado.getMensagemErro());
    }

    @Test
    public void senhaPequena(){
        Usuario usuario = new Usuario(
            "João Silva",
                "joao.silva@example.com",
                "senha", 
                "senha",
                "123.456.789-09",
                "12345-678" 
        );

        ResultadoValidacao resultado = usuario.salvarUsuario();

        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: A senha deve ter no mínimo 8 caracteres.", resultado.getMensagemErro());
    }

    @Test
    public void CpfInvalido(){
        Usuario usuario = new Usuario(
                "João Silva",
                "joao.silva@example.com",
                "senhaSegura123",
                "senhaSegura123",
                "123.45",
                "12345-678"
        );

        ResultadoValidacao resultado = usuario.salvarUsuario();

        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: O CPF ou CNPJ fornecido é inválido.", resultado.getMensagemErro());
    }

    @Test
    public void CnpjInvalido() {
        // Dado que eu passe todas as informações obrigatórias, mas com um CNPJ inválido
        Usuario usuario = new Usuario(
                "Maria Oliveira",
                "maria.oliveira@example.com",
                "senhaSegura123",
                "senhaSegura123",
                "12.345.678/000",
                "12345-678"
        );

        // Quando eu chamo o método para cadastrar o usuário
        ResultadoValidacao resultado = usuario.salvarUsuario();

        // Então devo receber a mensagem "Erro ao salvar o usuário: O CPF ou CNPJ fornecido é inválido."
        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: O CPF ou CNPJ fornecido é inválido.", resultado.getMensagemErro());
    }

    @Test
    public void CepInvalido() {
        // Dado que eu passe todas as informações obrigatórias, mas com um CEP inválido
        Usuario usuario = new Usuario(
                "Lucas Santos",
                "lucas.santos@example.com",
                "senhaSegura123",
                "senhaSegura123",
                "12345678900",
                "1234-567"
        );

        // Quando eu chamo o método para cadastrar o usuário
        ResultadoValidacao resultado = usuario.salvarUsuario();

        // Então devo receber a mensagem "Erro ao salvar o usuário: O CEP fornecido é inválido."
        assertFalse(resultado.isSucesso());
        assertEquals("Erro ao salvar o usuário: O CEP fornecido é inválido.", resultado.getMensagemErro());
    }

} 
