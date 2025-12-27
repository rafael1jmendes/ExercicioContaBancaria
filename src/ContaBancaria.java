

public class ContaBancaria {

    private double saldo;
    private double limiteChequeEspecial;
    private double chequeEspecialUsado;
    private boolean taxaCobrada;

    // Construtor
    public ContaBancaria(double depositoInicial) {
        this.saldo = depositoInicial;
        this.chequeEspecialUsado = 0;
        this.taxaCobrada = false;

        if (depositoInicial <= 500) {
            this.limiteChequeEspecial = 50;
        } else {
            this.limiteChequeEspecial = depositoInicial * 0.5;  
            }
        }

        //Consultar saldo
        public double consultarSaldo() {
            return this.saldo;
        }

        //Consultar limite do cheque especial
        public double consultarLimiteChequeEspecial() {
            return this.limiteChequeEspecial - this.chequeEspecialUsado;
        }

        //Depositar dinheiro
        public void depositar(double valor) {
            if (valor <= 0) {
                System.out.println("Valor inválido para depósito.");
            return;
            }

            saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");

             cobrarTaxaChequeEspecial();
        }

        //Sacar dinheiro
        public void sacar(double valor) {
            if (valor<= 0){
                System.out.println("Valor inválido para saque.");
                return;
            }

            double saldoTotalDisponivel = saldo + (limiteChequeEspecial - chequeEspecialUsado);
            if (valor > saldoTotalDisponivel){
                System.out.println("Saldo insuficiente para saque de R$ " + valor + ".");
                return;
        }

        if (valor <= saldo){
            saldo -= valor;
        } else {
            double restante = valor - saldo;
            saldo = 0;
            chequeEspecialUsado += restante;
        }

        System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
    }
        

        //Verificar se esta usando cheque especial
        public boolean estaUsandoChequeEspecial() {
            return chequeEspecialUsado > 0;
        }

        //Cobrar taxa de 20% quando possivel
        private void cobrarTaxaChequeEspecial(){
            if  (chequeEspecialUsado > 0 && saldo > 0 &&  !taxaCobrada){
                double taxa = chequeEspecialUsado * 0.2;

            if (saldo >= taxa){
                saldo -= taxa;
                taxaCobrada = true;
                System.out.println("Taxa de R$ " + taxa + " cobrada pelo uso do cheque especial.");
            }
        }
    }

    // Exibir resumo da conta
    public void exibirResumow() {
        System.out.println("Saldo atual: R$ " + saldo);
        System.out.println("Limite do cheque especial disponível: R$ " + (limiteChequeEspecial - chequeEspecialUsado));
        System.out.println("Cheque especial usado: R$ " + chequeEspecialUsado);
    }

    //Pagar boleto
    public void pagarBoleto (double valor){
     System.out.println("Pagar boleto de R$ " + valor + " não implementado ainda.");
     sacar(valor);
    }

    public static void main(String[] args) {
    ContaBancaria conta = new ContaBancaria(400);

    conta.exibirResumow();

    conta.sacar(430);
    conta.exibirResumow();

    conta.depositar(100);
    conta.exibirResumow();

    conta.pagarBoleto(50);
    conta.exibirResumow();  
    }



}