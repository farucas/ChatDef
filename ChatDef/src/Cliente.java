
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	private String host;
	private int porta;
        private String nome;

	public Cliente() {
            Scanner construtor = new Scanner(System.in);
            System.out.println("Insira seu ip: ");
            this.host = construtor.next();
            System.out.println("Host = " + this.host);
            System.out.println("Insira a porta: ");
            this.porta = construtor.nextInt();
            System.out.println("Port = " + this.porta);
            System.out.println("Insira seu nome:");
            this.nome = construtor.next();
            System.out.println(this.nome);
            construtor.close();
                
                
	}
        

	public void executa() throws UnknownHostException, IOException {
		try(Socket cliente = new Socket(this.host, this.porta); 
				Scanner teclado = new Scanner(System.in); 
				PrintStream saida = new PrintStream(cliente.getOutputStream())) {
			System.out.println("O cliente se conectou ao servidor!");
	
			RecebedorDeMensagemDoServidor r = new RecebedorDeMensagemDoServidor(cliente.getInputStream());
			new Thread(r).start();
	
			while (teclado.hasNextLine()) {
				saida.println(teclado.nextLine());
			}
		}
	}
}
