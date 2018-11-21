package View;

import javax.swing.JOptionPane;

import Model.Clerk;
import Model.Client;
import Model.Department;
import Model.Manager;
import Model.Supplier;

public class View {
	
	// op��es para os menus do sistema
	// a keyword 'final' descreve que esses valores s�o constantes durante a execu��o do programa
	private final static String[] optionsMenu = {"Cadastrar", "Consultar", "Alterar", "Excluir", "Sair"};
	private final static String[] optionsLowerMenu1 = {"Cliente", "Fornecedor", "Atendente", "Gerente", "Departamento", "Cancelar"};
	private final static String[] optionsLowerMenu2 = {"Cliente", "Fornecedor", "Atendente", "Gerente", "Cancelar"};
	
	public View(){
		
	}
	
	// requisita que o usu�rio digite o limite m�ximo de determinados objetos no sistema (ex: clientes, gerentes...)
	public Integer getSizeArray(String object){
		String number = JOptionPane.showInputDialog("Digite o limite de " + object + " (int): ");
		Integer size = null;
		while (size == null){
			try {
				if (number == null) {
					break;
				}
				size = Integer.parseInt(number);
				if (size <= 0) {
					size = null;
					throw new Exception();
				}
			} catch (Exception e) {
				number = JOptionPane.showInputDialog("Valor inv�lido. Digite o limite de " + object + " (int):");
			}
		}
		return size;
	}
	
	// exibe o menu principal
	public Integer getOption(){
		return JOptionPane.showOptionDialog(null, "Escolha uma op��o", "Menu", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsMenu, View.optionsMenu[0]);
	}
	
	// exibe o menu de cadastro
	public Integer getRegisterOption(){
		return JOptionPane.showOptionDialog(null, "O que deseja cadastrar?", "Cadastro", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsLowerMenu1, View.optionsLowerMenu1[0]);
	}
	
	// exibe o menu de consulta
	public Integer getSearchOption(){
		return JOptionPane.showOptionDialog(null, "O que deseja consultar?", "Consulta", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsLowerMenu1, View.optionsLowerMenu1[0]);
	}
	
	public Integer getAlterOption(){
		return JOptionPane.showOptionDialog(null, "O que deseja alterar?", "Altera��o", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsLowerMenu1, View.optionsLowerMenu1[0]);
	}
	
	// exibe o menu de remo��o (excluir um objeto dos arrays)
	public Integer getRemoveOption(){
		return JOptionPane.showOptionDialog(null, "O que deseja remover?", "Remo��o", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsLowerMenu2, View.optionsLowerMenu2[0]);
	}
	
	// requisita que o usu�rio digite uma string
	public String getString(String object, String attribute){
		String string = JOptionPane.showInputDialog("Digite o " + attribute + " do " + object);
		if (string == null) {
			return null;
		}
		while (string.isEmpty()) {
			string = JOptionPane.showInputDialog("Valor inv�lido. Digite o " + attribute + " do " + object);
			if (string == null) {
				return null;
			}
		}
		return string;
	}

	// requisita que o usu�rio digite um inteiro n�o negativo
	public Integer getInteger(String object, String attribute){
		String string = JOptionPane.showInputDialog("Digite o " + attribute + " do " + object + " (int >= 0)");
		if (string == null) {
			return null;
		}
		Integer number = null;
		while (number == null){
			try {
				if (string == null) {
					break;
				}
				number = Integer.parseInt(string);
				if (number < 0) {
					throw new Exception();
				}
			} catch (Exception e) {
				string = JOptionPane.showInputDialog("Valor inv�lido. Digite o " + attribute + " do " + object + " (int > 0)");
			}
		}
		return number;
	}
	
	// requisita que o usu�rio digite um double n�o negativo
	public Double getDouble(String object, String attribute){
		String string = JOptionPane.showInputDialog("Digite o " + attribute + " do " + object + " (double >= 0)");
		if (string == null) {
			return null;
		}
		Double number = null;
		while (number == null){
			try {
				if (string == null) {
					break;
				}
				number = Double.parseDouble(string);
				if (number < 0){
					throw new Exception();
				}
			} catch (Exception e) {
				string = JOptionPane.showInputDialog("Valor inv�lido. Digite o " + attribute + " do " + object + " (double >= 0)");
			}
		}
		return number;
	}
	
	// exibe as informa��es de um cliente
	public void printInformation(Client c){
		String information = "CLIENTE\nNome: " + c.getName() + "\nTelefone: " + c.getPhone() + "\nValor gasto: R$" + c.getValueSpent() +
				"\nPontos: " + c.calculatePoints() + " pontos.";
		JOptionPane.showMessageDialog(null, information);
	}
	
	// exibe as informa��es de um fornecedor
	public void printInformation(Supplier s){
		String information = "FORNECEDOR\nNome: " + s.getName() + "\nTelefone: " + s.getPhone() + "\nTipo do produto: " + 
				s.getProductType() + "\nNome da empresa: " + s.getCompany().getName() + "\nCNPJ da empresa: " + s.getCompany().getCnpj();
		JOptionPane.showMessageDialog(null, information);
	}
	
	// exibe as informa��es de um atendente
	public void printInformation(Clerk c){
		String information = "ATENDENTE\nNome: " + c.getName() + "\nTelefone: " + c.getPhone() + "\nSal�rio base: R$" + 
				c.getBaseSalary() + "\nSal�rio atual: R$" + c.calculateSalary() + "\nTempo de servi�o: " + c.getTimeWorked() +
				"\nCarga hor�ria: " + c.getWorkingHours() + "\nN�mero do caixa: " + c.getCounterNumber();
		JOptionPane.showMessageDialog(null, information);
	}
	
	// exibe as informa��es de um gerente
	public void printInformation(Manager m){
		String information = "GERENTE\nNome: " + m.getName() + "\nTelefone: " + m.getPhone() + "\nSal�rio base: R$" + 
				m.getBaseSalary() + "\nSal�rio atual: R$" + m.calculateSalary() + "\nTempo de servi�o: " + m.getTimeWorked() +
				"\nCarga hor�ria: " + m.getWorkingHours() + "\nDepartamento: " + m.getDepartment().getName() + 
				"(" + m.getDepartment().getId() + ").";
		JOptionPane.showMessageDialog(null, information);
	}
	
	public Integer showList(Object[] array) {
		String choice = (String) JOptionPane.showInputDialog(null, "Selecione uma op��o", "Escolha", JOptionPane.PLAIN_MESSAGE,  null, array, array[0]);
		if (choice == null) {
			return null;
		}
		return Integer.parseInt(choice.substring(0, 1));
	}
	
	public Integer showList(Object[] array, String message) {
		String choice = (String) JOptionPane.showInputDialog(null, message, "Escolha", JOptionPane.PLAIN_MESSAGE,  null, array, array[0]);
		if (choice == null) {
			return null;
		}
		return Integer.parseInt(choice.substring(0, 1));
	}
	
	// exibe as informa��es de um departamento
	public void printInformation(Department d){
		String information = "DEPARTAMENTO\nN�mero: " + d.getId() + "\nNome: " + d.getName();
		JOptionPane.showMessageDialog(null, information);
	}
	
	// exibe uma mensagem ao usu�rio
	public void printMessage(String message){
		JOptionPane.showMessageDialog(null, message);
	}
}
