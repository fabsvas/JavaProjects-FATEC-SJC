import java.util.ArrayList;

import br.com.cash.*;
import br.com.user.*;
import br.com.utilities.*;

public class App {

	public static void main(String[] args) {
		ArrayList<User> users = new ArrayList<User>();

		AsciiArt.printAsciiArt("FINTECH");
		Menu menu = new Menu();
		int op = 1000;

		while (op != 0) {
			menu.printMainMenu();
			Control ctl = new Control();
			op = ctl.option();

			switch (op) {
			case 1:
				User newUser = registerUser();
				users.add(newUser);
				break;

			case 2:
				Authentication login = loginUser();
				for (User user : users) {
					boolean isAuthenticated = user.getAuth().compareTo(login);
					if (isAuthenticated) {
						openAuthenticatedMenu(user);
					}
				}

				break;

			default:
				menu.printMainMenu();
				break;
			}
		}

	}

	public static User registerUser() {
		Control ctl = new Control();

		System.out.println("Entre com as seguintes informa��es");

		ctl = new Control();
		System.out.println("Nome: ");
		String name = ctl.text();

		ctl = new Control();
		System.out.println("Email: ");
		String email = ctl.text();

		System.out.println("Por favor, insira seu endere�o completo");

		ctl = new Control();
		System.out.println("Cidade: ");
		String city = ctl.text();

		ctl = new Control();
		System.out.println("Bairro: ");
		String neighborhood = ctl.text();

		ctl = new Control();
		System.out.println("Rua: ");
		String street = ctl.text();

		ctl = new Control();
		System.out.println("N�mero da casa: ");
		int number = ctl.number();

		System.out.println("Insira suas credenciais para logar em nosso sistema!");

		ctl = new Control();
		System.out.println("Nome de usu�rio: ");
		String username = ctl.text();

		ctl = new Control();
		System.out.println("Senha: ");
		String password = ctl.text();

		Authentication auth = new Authentication(username, password);
		Address address = new Address(city, neighborhood, street, number);
		User user = new User(auth, name, email, address);

		return user;
	}

	public static Authentication loginUser() {
		Control ctl = new Control();

		System.out.println("Bem-vindo a FINTECH!");

		ctl = new Control();
		System.out.println("Digite seu usu�rio: ");
		String registeredUsername = ctl.text();

		ctl = new Control();
		System.out.println("Digite sua senha: ");
		String registeredPassword = ctl.text();

		Authentication authLogin = new Authentication(registeredUsername, registeredPassword);

		return authLogin;
	}

	public static void openAuthenticatedMenu(User user) {
		Menu menu = new Menu();
		int op = 1000;
		User storedUser = user;

		ArrayList<CashFlow> cashFlows = new ArrayList<CashFlow>();

		while (op != 0) {
			menu.printAuthenticatedMenu();
			Control ctl = new Control();
			op = ctl.option();

			switch (op) {
			case 1:
				Control ctl1 = new Control();
				CashFlow newCashFlow = new CashFlow(storedUser);
				ctl1 = new Control();
				System.out.println("Cadastrar entrada (1) ou cadastrar sa�da (2): ");
				int option = ctl1.number();

				if (option == 1) {
					Input newInput = createInput();
					newCashFlow.setInput(newInput);
					newCashFlow.setOutput(new Output(0, 0, 0, 0, 0));
				} else {
					Output newOutput = createOutput();
					newCashFlow.setOutput(newOutput);
					newCashFlow.setInput(new Input(0));
				}

				cashFlows.add(newCashFlow);
				break;

			case 2:
				int index = 0;
				System.out.println("                       Fluxo de Caixa                         \n");
				for (CashFlow cashFlow : cashFlows) {
					index++;
					
					String createdByInput = cashFlow.getCreatedBy().getName();
					String createdByOutput = cashFlow.getCreatedBy().getName();
					
					int totalInput = cashFlow.getInput().sumParams();
					int totalOutput = cashFlow.getOutput().sumParams();
					
					System.out.printf("                          %d - Fluxo                           \n", index);
					System.out.println("--------------------------------------------------------------\n");
					if (totalInput != 0) {
						System.out.printf("Criador por: %s | Valor total entrada: R$ %d\n", createdByInput, totalInput);
					}
					if (totalOutput != 0) {
						System.out.printf("Criador por: %s | Valor total sa�da: R$ %d\n", createdByOutput, totalOutput);
					}
					System.out.println("--------------------------------------------------------------\n");
				}
				break;

			default:
				menu.printAuthenticatedMenu();
				break;
			}
		}
	}

	public static Input createInput() {
		Control ctl = new Control();

		ctl = new Control();
		System.out.println("Pessoa f�sica (1) ou jur�dica (2): ");
		int value = ctl.number();

		System.out.println("Entre com as seguintes informa��es");

		int salary = 0;
		int salesRevenue = 0;

		if (value == 1) {
			ctl = new Control();
			System.out.println("Sal�rio (somente valor): ");
			salary = ctl.number();

		} else {
			ctl = new Control();
			System.out.println("Receita de vendas (somente valor): ");
			salesRevenue = ctl.number();
		}

		ctl = new Control();
		System.out.println("Investimentos (somente valor): ");
		int investiment = ctl.number();

		Input input = new Input(investiment);
		if (salary > 0) {
			input.setSalary(salary);
		}

		if (salesRevenue > 0) {
			input.setSalesRevenue(salesRevenue);
		}

		return input;
	}

	public static Output createOutput() {
		Control ctl = new Control();

		System.out.println("Entre com as seguintes informa��es");

		ctl = new Control();
		System.out.println("Energia el�trica (somente valor): ");
		int eletricity = ctl.number();

		ctl = new Control();
		System.out.println("G�s (somente valor): ");
		int gas = ctl.number();

		ctl = new Control();
		System.out.println("Comida (somente valor): ");
		int food = ctl.number();

		ctl = new Control();
		System.out.println("Combust�vel (somente valor): ");
		int fuel = ctl.number();

		ctl = new Control();
		System.out.println("Outros gastos (somente valor): ");
		int others = ctl.number();

		Output output = new Output(eletricity, gas, food, fuel, others);

		return output;
	}
}
