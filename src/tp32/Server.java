package tp32;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[]args) {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = serverSocket.accept();
			
			System.out.println("Client connecté");
			
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			Operation op = (Operation) ois.readObject();
			
			
			int result1 = op.getOp1();
			switch (op.getOperation()) {
            case '+': 
            	op.setResult1(result1 + op.getOp2());
                break;
            case '-':
            	op.setResult1(result1 - op.getOp2());
                break;
            case '*':
            	op.setResult1(result1 * op.getOp2());
                break;
            case '/':
                if (op.getOp2() == 0) {
                    throw new ArithmeticException("Division par zéro");
                }
                op.setResult1(result1 / op.getOp2());
                break;
            default:
                throw new IllegalArgumentException("Opérateur non valide");
        }
			
		
		
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(op);
		
		serverSocket.close();
		socket.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	
} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	}}






