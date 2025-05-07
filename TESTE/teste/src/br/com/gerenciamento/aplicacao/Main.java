package br.com.gerenciamento.aplicacao;

import java.util.Date;
import java.util.Scanner;

import br.com.gerenciamento.dao.VendaDao;

import br.com.gerenciamento.dao.ClienteDao;
import br.com.gerenciamento.dao.FuncionarioDao;
import br.com.gerenciamento.dao.ProdutoDao;
import br.com.gerenciamento.models.Cliente;
import br.com.gerenciamento.models.Funcionario;
import br.com.gerenciamento.models.Produto;
import br.com.gerenciamento.models.ResultadoVenda;
import br.com.gerenciamento.utils.EntraDados;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        ProdutoDao produtoDao = new ProdutoDao();
        EntraDados entrada = new EntraDados();

        String selectId;
        String nome;
        String email;
        String cpf;
        String senha;
        String valor;
        int opcao = 0;
        String estoque;
        String escolha;
        int select;

        do {
            do {
                System.out.println("------------------------------------------------------------");
                System.out.println("ESCOLHA A OPÇÃO DESEJADA DO MENU");
                System.out.println("1 - CADASTRO (CLIENTE, FUNCIONARIO OU PRODUTO) NO SISTEMA");
                System.out.println("2 - DELETAR (CLIENTE, FUNCIONARIO OU PRODUTO) DO SISTEMA");
                System.out.println("3 - ATUALIZAR CADASTRO (CLIENTE, FUNCIONARIO OU PRODUTO) DO SISTEMA");
                System.out.println("4 - MOSTRAR A LISTA DE (CLIENTES, FUNCIONARIOS OU PRODUTOS) DO SISTEMA");
                System.out.println("5 - REALIZAR UMA VENDA");
                System.out.println("0 - ENCERRAR");
                System.out.printf("RESPOSTA: ");
                escolha = sc.next();

                if(!entrada.validarMenu(escolha)){
                    System.out.println("\n==================");
                    System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                    System.out.println("==================\n");
                }
            } while (!entrada.validarMenu(escolha));

                select = Integer.parseInt(escolha);
            switch (escolha) {
                case "1": do {
                    System.out.println("\nO QUE DESEJA CADASTRAR?");
                    System.out.println("1 - CLIENTE");
                    System.out.println("2 - FUNCIONARIO");
                    System.out.println("3 - PRODUTO");
                    System.out.printf("RESPOSTA: ");
                    escolha = sc.next();    
                    
                    if(!entrada.validar3Opcao(escolha)){
                        System.out.println("\n==================");
                        System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                        System.out.println("==================\n");
                    }
                } while (!entrada.validar3Opcao(escolha));
                opcao = Integer.parseInt(escolha);
    
                    if(opcao == 1){
                        
                        do {
                            System.out.println("\nDIGITE O NOME DO CLIENTE (MINIMO 3 CARACTERES): ");
                            System.out.printf("RESPOSTA: ");
                            nome = sc.next();
                            if(!entrada.validarNome(nome)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarNome(nome));

                        do {
                            System.out.println("DIGITE O EMAIL DO CLIENTE: ");
                            System.out.printf("RESPOSTA: ");
                            email = sc.next();
                            if(!entrada.validarEmail(email)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarEmail(email));

                        do {
                            System.out.println("DIGITE O CPF DO CLIENTE: ");
                            System.out.printf("RESPOSTA: ");
                            cpf = sc.next();
                            if(!entrada.validarCpf(cpf)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarCpf(cpf));

                        do {
                            System.out.println("DIGITE A SENHA DO CLIENTE (MINIMO 6 DIGITOS/CARACTERES): ");
                            System.out.printf("RESPOSTA: ");
                            senha = sc.next();
                            if(!entrada.validarSenha(senha)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarSenha(senha));

                        Cliente cliente = new Cliente(nome, email, cpf, senha);
                        clienteDao.save(cliente);
                        System.out.println("\n=================");
                        System.out.println("CLIENTE REGISTRADO COM SUCESSO!");
                        System.out.println("==================\n");
                    }else if(opcao==2){
                        
                        do {
                            
                            System.out.println("\nDIGITE O NOME DO FUNCIONARIO: ");
                            System.out.printf("RESPOSTA: ");
                            nome = sc.next();
                            
                            if(!entrada.validarNome(nome)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarNome(nome));

                        do {
                            System.out.println("DIGITE O EMAIL DO FUNCIONARIO: ");
                            System.out.printf("RESPOSTA: ");
                            email = sc.next();
                            if(!entrada.validarEmail(email)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarEmail(email));

                        do {
                            System.out.println("DIGITE A SENHA DO FUNCIONARIO: ");
                            System.out.printf("RESPOSTA: ");
                            senha = sc.next();
                            if(!entrada.validarSenha(senha)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarSenha(senha));

                        Funcionario funcionario = new Funcionario(nome, email, senha);
                        funcionario.setDataCadastro(new Date());
                        funcionarioDao.save(funcionario);
                        System.out.println("\n=================");
                        System.out.println("FUNCIONARIO REGISTRADO COM SUCESSO!");
                        System.out.println("==================\n");
                    }else{
                        sc.nextLine();
                        do {
                            System.out.println("\nDIGITE O NOME DO PRODUTO: ");
                            System.out.printf("RESPOSTA: ");
                            nome = sc.nextLine();
                            if(!entrada.validarNome(nome)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarNome(nome));

                        do {
                            System.out.println("DIGITE O ESTOQUE DO PRODUTO: ");
                            System.out.printf("RESPOSTA: ");
                            estoque = sc.nextLine();
                            if(!entrada.validarEstoque(estoque)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarEstoque(estoque));

                        do {
                            System.out.println("DIGITE O VALOR DO PRODUTO: ");
                            System.out.printf("RESPOSTA: ");
                            valor = sc.nextLine();
                            if(!entrada.validarValor(valor)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarValor(valor));

                        Produto produto = new Produto(nome, estoque, valor);
                        produto.setDataCadastro(new Date());
                        produtoDao.save(produto);
                        System.out.println("\n=================");
                        System.out.println("PRODUTO REGISTRADO COM SUCESSO!");
                        System.out.println("==================\n");
                    }
                    
                    break;
                case "2":
                    
                    do {
                    System.out.println("\nO QUE DESEJA DELETAR?");
                    System.out.println("1 - CLIENTE");
                    System.out.println("2 - FUNCIONARIO");
                    System.out.println("3 - PRODUTO");
                    System.out.printf("RESPOSTA: ");
                    escolha = sc.next();   
                    sc.nextLine();
                    if(!entrada.validar3Opcao(escolha)){
                        System.out.println("\n==================");
                        System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                        System.out.println("==================\n");
                    }
                } while (!entrada.validar3Opcao(escolha));
                opcao = Integer.parseInt(escolha);
    
                        if(opcao==1){
                            do {
                                System.out.println("\nQUAL O ID DO CLIENTE QUE DESEJA DELETAR?");
                                System.out.printf("RESPOSTA: ");
                                selectId = sc.nextLine();
    
                                if(!entrada.validarId(selectId)){
                                    System.out.println("\n==================");
                                    System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                    System.out.println("==================\n");
                                }
                            } while (!entrada.validarId(selectId));
                            int id = Integer.parseInt(selectId);
                            
                            clienteDao.deleteById(id);
                            
                        }else if(opcao==2){
                            do {
                                System.out.println("\nQUAL O ID DO FUNCIONARIO QUE DESEJA DELETAR?");
                                System.out.printf("RESPOSTA: ");
                                selectId = sc.nextLine();
                                if(!entrada.validarId(selectId)){
                                    System.out.println("\n==================");
                                    System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                    System.out.println("==================\n");
                                }
                            } while (!entrada.validarId(selectId));
                            int id = Integer.parseInt(selectId);
                            
                            funcionarioDao.deleteById(id);

                        }else{
                            do {
                                System.out.println("\nQUAL O ID DO PRODUTO QUE DESEJA DELETAR?");
                                System.out.printf("RESPOSTA: ");
                                selectId = sc.nextLine();
                                
                             if(!entrada.validarId(selectId)){
                                System.out.println("\n==================");
                                System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                System.out.println("==================\n");
                            }
                        } while (!entrada.validarId(selectId));
                        int id = Integer.parseInt(selectId);
                            produtoDao.deleteById(id);
                        }

                    break;
                case "3":
                    
                    do {
                    System.out.println("\nO QUE QUER ATUALIZAR?");
                    System.out.println("1 - CLIENTES");
                    System.out.println("2 - FUNCIONARIOS");
                    System.out.println("3 - PRODUTOS");
                    System.out.printf("RESPOSTA: ");
                    escolha = sc.next();  

                     if(!entrada.validar3Opcao(escolha)){
                        System.out.println("\n==================");
                        System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                        System.out.println("==================\n");
                    }
                } while (!entrada.validar3Opcao(escolha));
                opcao = Integer.parseInt(escolha);
                
                        if (opcao == 1) {
                            String idBusca;
                            boolean idValido = false;

                             while (!idValido) {
                                sc.nextLine();
                                do {
                                    System.out.println("\nDIGITE O ID DO CLIENTE QUE DESEJA ATUALIZAR: ");
                                    System.out.printf("RESPOSTA: ");
                                    idBusca = sc.nextLine();
                                    
                                    if(!entrada.validarId(idBusca)){
                                        System.out.println("\n==================");
                                        System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                        System.out.println("==================\n");
                                    }
                                } while (!entrada.validarId(idBusca));
                                int id = Integer.parseInt(idBusca);
                                Cliente c1 = clienteDao.getClienteById(id);

                                if (c1 != null) {
                                    do {
                                        System.out.println("DIGITE O NOME: ");
                                        System.out.printf("RESPOSTA: ");
                                        nome = sc.next();
                                        if(!entrada.validarNome(nome)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarNome(nome));

                                    do {
                                        System.out.println("DIGITE O EMAIL: ");
                                        System.out.printf("RESPOSTA: ");
                                        email = sc.next();
                                        if(!entrada.validarEmail(email)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarEmail(email));

                                    do {
                                        System.out.println("DIGITE O CPF: ");
                                        System.out.printf("RESPOSTA: ");
                                        cpf = sc.next();
                                        if(!entrada.validarCpf(cpf)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarCpf(cpf));

                                    do {
                                        System.out.println("DIGITE A SENHA: ");
                                        System.out.printf("RESPOSTA: ");
                                        senha = sc.next();
                                        if(!entrada.validarSenha(senha)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarSenha(senha));

                                    
                                    c1.setNome(nome);
                                    c1.setEmail(email);
                                    c1.setSenha(senha);
                                    c1.setCpf(cpf);
                                    
                                    clienteDao.update(c1);
                                    idValido = true;
                                    System.out.println("\n=================");
                                    System.out.println("CLIENTE ATUALIZADO COM SUCESSO!");
                                    System.out.println("==================\n");

                                } else {
                                    System.out.println("\nID DO CLIENTE NAO ENCONTRADO\n");
                                    idValido = true;
                                }
                            
                        }

                        }else if(opcao==2){
                            String idBusca;
                            boolean idValido = false;
                            sc.nextLine();
                             while (!idValido) {
                                do {
                                    System.out.println("\nDIGITE O ID DO FUNCIONARIO QUE DESEJA ATUALIZAR: ");
                                    System.out.printf("RESPOSTA: ");
                                    idBusca = sc.nextLine();
                                    if(!entrada.validarId(idBusca)){
                                        System.out.println("\n==================");
                                        System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                        System.out.println("==================\n");
                                    }
                                } while (!entrada.validarId(idBusca));
                                int id = Integer.parseInt(idBusca);
                                Funcionario f1 = funcionarioDao.getFuncionarioById(id);

                                if (f1 != null) {
                                    do {
                                        System.out.println("DIGITE O NOME: ");
                                        System.out.printf("RESPOSTA: ");
                                        nome = sc.next();
                                        if(!entrada.validarNome(nome)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarNome(nome));

                                    do {
                                        System.out.println("DIGITE O EMAIL: ");
                                        System.out.printf("RESPOSTA: ");
                                        email = sc.next();
                                        if(!entrada.validarEmail(email)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarEmail(email));

                                    do {
                                        System.out.println("DIGITE A SENHA: ");
                                        System.out.printf("RESPOSTA: ");
                                        senha = sc.next();
                                        if(!entrada.validarSenha(senha)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarSenha(senha));
                                    
                                    f1.setNome(nome);
                                    f1.setEmail(email);
                                    f1.setSenha(senha);
                                    funcionarioDao.update(f1);
                                    System.out.println("\n=================");
                                    System.out.println("FUNCIONARIO ATUALIZADO COM SUCESSO!");
                                    System.out.println("==================\n");
                                    idValido = true;
                                } else {
                                    System.out.println("\nID DO FUNCIONARIO NAO ENCONTRADO\n");
                                    idValido = true;
                                }
                            
                        }

                        }else{
                            String idBusca;
                            boolean idValido = false;
                            sc.nextLine();
                             while (!idValido) {
                                do {
                                    System.out.println("\nDIGITE O ID DO PRODUTO QUE DESEJA ATUALIZAR: ");
                                    System.out.printf("RESPOSTA: ");
                                    idBusca = sc.nextLine();
                                    if(!entrada.validarId(idBusca)){
                                        System.out.println("\n==================");
                                        System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                        System.out.println("==================\n");
                                    }
                                } while (!entrada.validarId(idBusca));
                                int id = Integer.parseInt(idBusca);
                                Produto p1 = produtoDao.getProdutoById(id);

                                if (p1 != null) {
                                    do {
                                        System.out.println("DIGITE O NOME: ");
                                        System.out.printf("RESPOSTA: ");
                                        nome = sc.next();
                                        if(!entrada.validarNome(nome)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarNome(nome));
                                    sc.nextLine();
                                    do {
                                        System.out.println("DIGITE O ESTOQUE: ");
                                        System.out.printf("RESPOSTA: ");
                                        estoque = sc.nextLine();
                                        if(!entrada.validarEstoque(estoque)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarEstoque(estoque));

                                    do {
                                        System.out.println("DIGITE O VALOR: ");
                                        System.out.printf("RESPOSTA: ");
                                        valor = sc.nextLine();
                                        if(!entrada.validarValor(valor)){
                                            System.out.println("\n==================");
                                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                                            System.out.println("==================\n");
                                        }
                                    } while (!entrada.validarValor(valor));

                                    
                                    p1.setNome(nome);
                                    p1.setEstoque(estoque);
                                    p1.setValor(valor);

                                    produtoDao.update(p1);
                                    idValido = true;
                                    System.out.println("\n=================");
                                    System.out.println("PRODUTO ATUALIZADO COM SUCESSO!");
                                    System.out.println("==================\n");
                                } else {
                                    System.out.println("\nID DO PRODUTO NAO ENCONTRADO\n");
                                    idValido = true;
                                }

                         }

                        }

                    break;
                case "4":
                    do {
                    System.out.println("\nO QUE QUER VISUALIZAR?");
                    System.out.println("1 - LISTA DE CLIENTES");
                    System.out.println("2 - LISTA DE FUNCIONARIOS");
                    System.out.println("3 - LISTA DE PRODUTOS");
                    System.out.printf("RESPOSTA: ");
                    escolha = sc.next();  

                     if(!entrada.validar3Opcao(escolha)){
                        System.out.println("\n==================");
                        System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                        System.out.println("==================\n");
                    }
                } while (!entrada.validar3Opcao(escolha));
                opcao = Integer.parseInt(escolha);
                    
                    if(opcao==1){
                        System.out.println("\nLISTA DE CLIENTES DO BANCO DE DADOS: ");
                        for (Cliente c : clienteDao.getClientes()) {
                            System.out.println("===================================");
                            System.out.println("ID: " + c.getId());
                            System.out.println("NOME: " + c.getNome());
                            System.out.println("EMAIL: " + c.getEmail());
                            System.out.println("CPF: " + c.getCpf());
                            System.out.println("SENHA: " + c.getSenha());
                            System.out.println("===================================\n");
                            }
                    }else if(opcao==2){
                        System.out.println("\nLISTA DE FUNCIONARIOS DO BANCO DE DADOS: ");
                        for (Funcionario f : funcionarioDao.getFuncionarios()) {
                            System.out.println("===================================");
                            System.out.println("ID: " + f.getId());
                            System.out.println("NOME: " + f.getNome());
                            System.out.println("EMAIL: " + f.getEmail());
                            System.out.println("SENHA: " + f.getSenha());
                            System.out.println("DATA DE CRIACAO: " + f.getDataCadastro());
                            System.out.println("===================================\n");
                            }
                    }else{
                        System.out.println("\nLISTA DE PRODUTOS DO BANCO DE DADOS: ");
                        for (Produto p : produtoDao.getProdutos()) {
                            System.out.println("===================================");
                            System.out.println("ID: " + p.getId());
                            System.out.println("NOME: " + p.getNome());
                            System.out.println("DATA DE CRIACAO: " + p.getDataCadastro());
                            System.out.println("ESTOQUE: " + p.getEstoque());
                            System.out.println("PREÇO: R$" + p.getValor());
                            System.out.println("===================================\n");
                    }
                }
                    break;
                case "5":
                    String produtoId;
                    String qtd;
                    sc.nextLine();
                    do {
                        System.out.println("\nDIGITE O ID DO PRODUTO: ");
                        produtoId = sc.nextLine();
                        if(!entrada.validarId(produtoId)){
                            System.out.println("\n==================");
                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                            System.out.println("==================\n");
                        }
                    } while (!entrada.validarId(produtoId));
                    int pId = Integer.parseInt(produtoId);
                
                    do {
                        System.out.println("\nDIGITE A QUANTIDADE VENDIDA: ");
                        qtd = sc.nextLine();
                        if(!entrada.validarEstoque(qtd)){
                            System.out.println("\n==================");
                            System.out.println("ALGO FOI DIGITADO ERRADO, TENTE NOVAMENTE!");
                            System.out.println("==================\n");
                        }
                    } while (!entrada.validarEstoque(qtd));
                    int quantidade = Integer.parseInt(qtd);


                    VendaDao vendaDAO = new VendaDao();
                    ResultadoVenda resultado = vendaDAO.registrarVenda(pId, quantidade);

                    if (resultado.isSucesso()) {
                        System.out.println("\n" + resultado.getMensagem());
                    } else {
                        System.out.println("\nErro: " + resultado.getMensagem());
                    }
                    break;
                default:
                System.out.println("\n=================");
                System.out.println("ENCERRANDO...");
                System.out.println("==================\n");
                    break;
            }

        } while (select!=0);
        
        sc.close();
        
        



        
}
}

