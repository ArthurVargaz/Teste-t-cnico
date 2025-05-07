package br.com.gerenciamento.utils;

public class EntraDados {

    public  boolean validarMenu(String esc){

        try {
            int entrada = Integer.parseInt(esc);
            return entrada >= 0 && entrada <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public  boolean validar3Opcao(String esc){

        try {
            int entrada = Integer.parseInt(esc);
            return entrada > 0 && entrada < 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public  boolean validarNome(String nome){

        if(nome.isEmpty() || nome==null){
            return false;
        }else if( (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) || nome.trim().length() < 3){  
            return false;
            
        }

        return true;
    }

    public  boolean validarEmail(String email){

        if(email.isEmpty() || email==null){
            return false;
        } 

        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public  boolean validarCpf(String cpf){

        if(cpf.isEmpty() || cpf==null){
            return false;
        } 
        cpf = cpf.replaceAll("\\D", "");
        return cpf.matches("\\d{11}");
        
    }

    public  boolean validarSenha(String senha){

        if(senha.isEmpty() || senha==null || senha.length() < 6){
            return false;
        } 
        
        return true;
        
    }

    public boolean validarEstoque(String estoque){

        try {
            int qtd = Integer.parseInt(estoque);
            return qtd >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
        
    }

    public boolean validarValor(String valor){

        try {
            int val = Integer.parseInt(valor);
            return val >= 0;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public boolean validarId(String id){

        try {
            int num = Integer.parseInt(id);
            return num >= 0;
        } catch (NumberFormatException e) {
            return false;
        }


    }
}
