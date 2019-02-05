package Banco;

import Chain.Block;
import Chain.Blockchain;
import java.util.*;
import java.time.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sistema {
    public static void MenuOperacoes() {
        System.out.println("Entre com a operação desejada:");
        System.out.println("[1] Abrir conta");
        System.out.println("[2] Depósito");
        System.out.println("[3] Saque");
        System.out.println("[4] Transferência");
        System.out.println("[5] Saldo");
        System.out.println("[6] Extrato");
        System.out.println("[0] Sair");
    } 
   
    public Conta BuscarConta(Blockchain sistema, int agencia, int conta) {
        for (int i = sistema.size()-1; i > 0; i--) {
            Object dados = sistema.GetBlock(i).GetData();
            Conta conta3, conta4;
            if (dados instanceof Depositar) {
                conta3 = ((Depositar) dados).GetConta();
                conta4 = null;
            } else if (dados instanceof Sacar) {
                conta3 = ((Sacar) dados).GetConta();
                conta4 = null;
            } else if (dados instanceof Transferir) {
                conta3 = ((Transferir) dados).GetContaOrigem();
                conta4 = ((Transferir) dados).GetContaDestino();
            } else {
                conta3 = null;
                conta4 = null;
            }
            
            if (conta3 != null && conta3.GetAgencia() == agencia && conta3.GetConta() == conta) {
                return conta3;
            }
            
            if (conta4 != null && conta4.GetAgencia() == agencia && conta4.GetConta() == conta) {
                return conta4;
            }
        }
        return null;
    }
    
    public void Extrato(Blockchain sistema, Conta buscada, Date dataInicial, Date dataFinal) {
        for (int i = sistema.size()-1; i > 0; i--) {
            Object transacao = sistema.GetBlock(i).GetData();
    
            Date data;
            Conta conta3, conta4;
            if (transacao instanceof Depositar) {
                data = ((Depositar) transacao).GetData();
                conta3 = ((Depositar) transacao).GetConta();
                conta4 = null;
            } else if (transacao instanceof Sacar) {
                data = ((Sacar) transacao).GetData();
                conta3 = ((Sacar) transacao).GetConta();
                conta4 = null;
            } else /*if (transacao instanceof Transferir)*/ {
                data = ((Transferir) transacao).GetData();
                conta3 = ((Transferir) transacao).GetContaOrigem();
                conta4 = ((Transferir) transacao).GetContaDestino();
            }
            
            if (conta3 != null && conta3.GetAgencia() == buscada.GetAgencia() && conta3.GetConta() == buscada.GetConta() || 
                    conta4 != null && conta4.GetAgencia() == buscada.GetAgencia() && conta4.GetConta() == buscada.GetConta()) {
                if (data.after(dataInicial) && data.before(dataFinal)) {
                    sistema.GetBlock(i).Imprimir();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Sistema Bancário");
        Blockchain sistema = new Blockchain();
        Conta conta1, conta2;
        Block bloco;
        Depositar depositoInicial, deposito;
        Sacar saque;
        Transferir transferencia;
        Sistema tb = new Sistema();
        Scanner ler = new Scanner(System.in);
        int opcao;
        
        conta1 = new Conta(1234, 56789);            
        depositoInicial = new Depositar(conta1, 50.0);
        bloco = new Block (depositoInicial, sistema.GetLastBlock().GetHash(), sistema.size()+1);
        sistema.AddBlock(bloco);
        
        conta1 = new Conta(1235, 56790);            
        depositoInicial = new Depositar(conta1, 50.0);
        bloco = new Block (depositoInicial, sistema.GetLastBlock().GetHash(), sistema.size()+1);
        sistema.AddBlock(bloco);
        
        conta1 = new Conta(1236, 56791);            
        depositoInicial = new Depositar(conta1, 50.0);
        bloco = new Block (depositoInicial, sistema.GetLastBlock().GetHash(), sistema.size()+1);
        sistema.AddBlock(bloco);
        
        conta1 = new Conta(1237, 56792);            
        depositoInicial = new Depositar(conta1, 50.0);
        bloco = new Block (depositoInicial, sistema.GetLastBlock().GetHash(), sistema.size()+1);
        sistema.AddBlock(bloco);
        
        conta1 = new Conta(1238, 56793);            
        depositoInicial = new Depositar(conta1, 50.0);
        bloco = new Block (depositoInicial, sistema.GetLastBlock().GetHash(), sistema.size()+1);
        sistema.AddBlock(bloco);
        
        conta1 = new Conta(1239, 56794);            
        depositoInicial = new Depositar(conta1, 50.0);
        bloco = new Block (depositoInicial, sistema.GetLastBlock().GetHash(), sistema.size()+1);
        sistema.AddBlock(bloco);
        
        conta1 = tb.BuscarConta(sistema, 1236, 56791);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 93.0);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1236, 56791);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 55.3);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1234, 56789);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 234.3);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1238, 56793);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 12.4);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1235, 56790);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 43.7);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1237, 56792);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 35.6);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1239, 56794);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 23.5);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1238, 56793);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 123.6);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1237, 56792);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 123.5);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1236, 56791);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 13.4);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1237, 56792);
        if (conta1 != null) {
            deposito = new Depositar(conta1, 44.6);
            bloco = new Block (deposito, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1234, 56789);
        if (conta1 != null) {
            saque = new Sacar(conta1, 40.4);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1237, 56792);
        if (conta1 != null) {
            saque = new Sacar(conta1, 13.6);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1239, 56794);
        if (conta1 != null) {
            saque = new Sacar(conta1, 57.2);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1236, 56791);
        if (conta1 != null) {
            saque = new Sacar(conta1, 20.6);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1237, 56792);
        if (conta1 != null) {
            saque = new Sacar(conta1, 18.5);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1237, 56792);
        if (conta1 != null) {
            saque = new Sacar(conta1, 12.6);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1235, 56790);
        if (conta1 != null) {
            saque = new Sacar(conta1, 12.6);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1234, 56789);
        if (conta1 != null) {
            saque = new Sacar(conta1, 10.4);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1237, 56792);
        if (conta1 != null) {
            saque = new Sacar(conta1, 20.4);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1235, 56790);
        if (conta1 != null) {
            saque = new Sacar(conta1, 10.4);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1237, 56792);
        if (conta1 != null) {
            saque = new Sacar(conta1, 10.6);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1238, 56793);
        if (conta1 != null) {
            saque = new Sacar(conta1, 24.6);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1239, 56794);
        if (conta1 != null) {
            saque = new Sacar(conta1, 30.6);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1234, 56789);
        if (conta1 != null) {
            saque = new Sacar(conta1, 4.4);
            bloco = new Block (saque, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1234, 56789);
        conta2 = tb.BuscarConta(sistema, 1238, 56793);
        if (conta1 != null && conta2 != null) {
            transferencia = new Transferir(conta1, conta2, 15.4);
            bloco = new Block (transferencia, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1235, 56790);
        conta2 = tb.BuscarConta(sistema, 1236, 56791);
        if (conta1 != null && conta2 != null) {
            transferencia = new Transferir(conta1, conta2, 30.4);
            bloco = new Block (transferencia, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1238, 56793);
        conta2 = tb.BuscarConta(sistema, 1239, 56794);
        if (conta1 != null && conta2 != null) {
            transferencia = new Transferir(conta1, conta2, 30.4);
            bloco = new Block (transferencia, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        conta1 = tb.BuscarConta(sistema, 1234, 56789);
        conta2 = tb.BuscarConta(sistema, 1238, 56793);
        if (conta1 != null && conta2 != null) {
            transferencia = new Transferir(conta1, conta2, 15.4);
            bloco = new Block (transferencia, sistema.GetLastBlock().GetHash(), sistema.size()+1);
            sistema.AddBlock(bloco);
        }
        
        sistema.Print();
        
        System.out.println("Deseja entrar no sistema?");
        System.out.println("[0] Não");
        System.out.println("[1] Sim");
        
        opcao = ler.nextInt();
        
        if (opcao == 1) {
            MenuOperacoes();

            opcao = ler.nextInt();

            while (opcao != 0) {
                try {
                    switch(opcao) {
                        case 1:
                            System.out.println("# Abertura de conta #");
                            
                            int agenciaNova;
                            int contaNova;
                            double primeiroDeposito;
                            
                            System.out.println("Entre com a agência");
                            agenciaNova = ler.nextInt();
                            System.out.println("Entre com a conta");
                            contaNova = ler.nextInt();
                            System.out.println("Entre com o valor do primeiro depósito");
                            primeiroDeposito = ler.nextDouble();
                            
                            conta1 = new Conta(agenciaNova, contaNova);
                            
                            depositoInicial = new Depositar(conta1, primeiroDeposito);
                            bloco = new Block (depositoInicial, sistema.GetLastBlock().GetHash(), sistema.size()+1);
                            sistema.AddBlock(bloco);
                            break;
                        case 2:
                            System.out.println("# Depósito #");
                            
                            int contaDeposito;
                            int agenciaDeposito;
                            double valorDeposito;
                            
                            System.out.println("Entre com a agência");
                            agenciaDeposito = ler.nextInt();
                            System.out.println("Entre com a conta");
                            contaDeposito = ler.nextInt();
                            
                            conta1 = tb.BuscarConta(sistema, agenciaDeposito, contaDeposito);
                            
                            if(conta1 == null)
                            {
                                System.out.println("Conta não encontrada");
                                break;
                            }
                            
                            System.out.println("Entre com o valor a depositar");
                            valorDeposito = ler.nextDouble();
                            
                            Depositar dep = new Depositar(conta1, valorDeposito);
                            bloco = new Block (dep, sistema.GetLastBlock().GetHash(), sistema.size()+1);
                            sistema.AddBlock(bloco);
                            
                            break;
                        case 3:
                            System.out.println("# Saque #");
                            
                            int agenciaSaque;
                            int contaSaque;
                            double valorSaque;
                            
                            System.out.println("Entre com a agência");
                            agenciaSaque = ler.nextInt();
                            System.out.println("Entre com a conta");
                            contaSaque = ler.nextInt();
                            
                            conta1 = tb.BuscarConta(sistema, agenciaSaque, contaSaque);
                            
                            if(conta1 == null)
                            {
                                System.out.println("Conta não encontrada");
                                break;
                            }
                            
                            System.out.println("Entre com o valor para sacar");
                            valorSaque = ler.nextDouble();
                            
                            Sacar saq = new Sacar(conta1, valorSaque);
                            bloco = new Block (saq, sistema.GetLastBlock().GetHash(), sistema.size()+1);
                            sistema.AddBlock(bloco);
                            break;
                        case 4:
                            System.out.println("# Transferência #");
                            
                            break;
                        case 5:
                            System.out.println("# Saldo #");
                            
                            int contab;
                            int agenciab;
                            
                            System.out.println("Entre com a agência");
                            agenciab = ler.nextInt();
                            System.out.println("Entre com a conta");
                            contab = ler.nextInt();
                            
                            conta1 = tb.BuscarConta(sistema, agenciab, contab);
                            
                            if(conta1 == null)
                            {
                                System.out.println("Conta não encontrada");
                                break;
                            }
                            
                            System.out.println(conta1.toString());
                            break;
                        case 6:
                            System.out.println("# Extrato #");
                            
                            int contaBuscada;
                            int agenciaBuscada;
                            
                            System.out.println("Entre com a agência");
                            agenciaBuscada = ler.nextInt();
                            System.out.println("Entre com a conta");
                            contaBuscada = ler.nextInt();
                            
                            conta1 = tb.BuscarConta(sistema, agenciaBuscada, contaBuscada);
                            
                            if(conta1 == null)
                            {
                                System.out.println("Conta não encontrada");
                                break;
                            }
                            
                            int mes, ano;
                            
                            System.out.println("Entre com o ano");
                            ano = ler.nextInt();
                            System.out.println("Entre com o mês");
                            mes = ler.nextInt();
                            
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                           
                            Date dataInicial = formatter.parse(new String("01-"+mes+"-"+ano));
                                                        
                            YearMonth mesAno = YearMonth.of(ano, mes);
                            //Date dataFinal = formatter.parse(new String(mesAno.lengthOfMonth()+"-"+mes+"-"+ano));        
                            Date dataFinal = formatter.parse(new String("01-"+(mes+1)+"-"+ano));
                            System.out.println(dataInicial);
                            System.out.println(dataFinal);
                            //tb.Extrato(sistema, conta1, dataInicial, dataFinal);
                            break;
                        default:
                            System.out.println("Opção não cadastrada");
                            break;
                    }
                    
                    MenuOperacoes();
                    opcao = ler.nextInt();
                } catch (ParseException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
