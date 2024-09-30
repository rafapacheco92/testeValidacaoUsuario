package com.example;

import java.util.regex.Pattern;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    private String cpfOuCnpj;
    private String cep;

    public Usuario(String nome, String email, String senha, String confirmarSenha, String cpfOuCnpj, String cep) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
        this.cpfOuCnpj = cpfOuCnpj;
        this.cep = cep;
    }

    public ResultadoValidacao validarCadastro() {
        ResultadoValidacao resultado;

        resultado = validarNome();
        if (!resultado.isSucesso()) return resultado;

        resultado = validarEmail();
        if (!resultado.isSucesso()) return resultado;

        resultado = validarSenha();
        if (!resultado.isSucesso()) return resultado;

        resultado = validarCpfOuCnpj();
        if (!resultado.isSucesso()) return resultado;

        resultado = validarCep();
        if (!resultado.isSucesso()) return resultado;

        return new ResultadoValidacao(true);  // Validação bem-sucedida
    }

    private ResultadoValidacao validarNome() {
        if (nome == null || nome.trim().isEmpty()) {
            return new ResultadoValidacao(false, "O nome é obrigatório.");
        }
        return new ResultadoValidacao(true);
    }

    private ResultadoValidacao validarEmail() {
        if (email == null || email.trim().isEmpty()) {
            return new ResultadoValidacao(false, "O e-mail é obrigatório.");
        }

        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!Pattern.matches(regexEmail, email)) {
            return new ResultadoValidacao(false, "O e-mail fornecido é inválido.");
        }
        return new ResultadoValidacao(true);
    }

    private ResultadoValidacao validarSenha() {
        if (senha == null || confirmarSenha == null || senha.trim().isEmpty() || confirmarSenha.trim().isEmpty()) {
            return new ResultadoValidacao(false, "A senha e a confirmação de senha são obrigatórias.");
        }

        if (senha.length() < 8) {
            return new ResultadoValidacao(false, "A senha deve ter no mínimo 8 caracteres.");
        }

        if (!senha.equals(confirmarSenha)) {
            return new ResultadoValidacao(false, "A senha e a confirmação de senha não correspondem.");
        }
        return new ResultadoValidacao(true);
    }

    private ResultadoValidacao validarCpfOuCnpj() {
        if (cpfOuCnpj == null || cpfOuCnpj.trim().isEmpty()) {
            return new ResultadoValidacao(false, "O CPF ou CNPJ é obrigatório.");
        }

        String regexCpf = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";
        String regexCnpj = "^(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14})$";

        if (!Pattern.matches(regexCpf, cpfOuCnpj) && !Pattern.matches(regexCnpj, cpfOuCnpj)) {
            return new ResultadoValidacao(false, "O CPF ou CNPJ fornecido é inválido.");
        }
        return new ResultadoValidacao(true);
    }

    private ResultadoValidacao validarCep() {
        if (cep == null || cep.trim().isEmpty()) {
            return new ResultadoValidacao(false, "O CEP é obrigatório.");
        }

        String regexCep = "^(\\d{5}-\\d{3}|\\d{8})$";
        if (!Pattern.matches(regexCep, cep)) {
            return new ResultadoValidacao(false, "O CEP fornecido é inválido.");
        }
        return new ResultadoValidacao(true);
    }

    public ResultadoValidacao salvarUsuario() {
        ResultadoValidacao resultado = validarCadastro();
        if (resultado.isSucesso()) {
            chamarServicoExternoParaSalvar();
            return new ResultadoValidacao(true, "Usuário salvo com sucesso!");
            // Simulação de envio ao serviço
        } else {
            return new ResultadoValidacao(false, "Erro ao salvar o usuário: " + resultado.getMensagemErro());
        }
    }

    private void chamarServicoExternoParaSalvar() {
        System.out.println("Chamando serviço externo para salvar os dados...");
    }
}
