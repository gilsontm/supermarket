package View;

import javax.swing.JOptionPane;

import Model.Clerk;
import Model.Client;
import Model.Department;
import Model.Manager;
import Model.Supplier;

public class View {
	
	private static String[] optionsMenu = {"Cadastrar", "Consultar", "Alterar", "Excluir", "Sair"};
	private static String[] optionsLowerMenu1 = {"Cliente", "Fornecedor", "Atendente", "Gerente", "Departamento", "Cancelar"};
	private static String[] optionsLowerMenu2 = {"Cliente", "Fornecedor", "Atendente", "Gerente", "Cancelar"};
	
	public View(){
		
	}
	
	public Integer getSizeArray(String object){
		String number = JOptionPane.showInputDialog("Digite o limite de " + object + " (int): ");
		Integer size = null;
		while (size == null){
			try {
				size = Integer.parseInt(number);
				if (size <= 0){
					size = null;
					throw new Exception();
				}
			} catch (Exception e){
				number = JOptionPane.showInputDialog("Valor inválido. Digite o limite de " + object + " (int):");
			}
		}
		return size;
	}
	
	public Integer getOption(){
		return JOptionPane.showOptionDialog(null, "Escolha uma opção", "Menu", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsMenu, View.optionsMenu[0]);
	}
	
	public Integer getRegisterOption(){
		return JOptionPane.showOptionDialog(null, "O que deseja cadastrar?", "Cadastro", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsLowerMenu1, View.optionsLowerMenu1[0]);
	}
	
	public Integer getSearchOption(){
		return JOptionPane.showOptionDialog(null, "O que deseja consultar?", "Consulta", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsLowerMenu1, View.optionsLowerMenu1[0]);
	}
	
	public Integer getAlterOption(){
		return JOptionPane.showOptionDialog(null, "O que deseja alterar?", "Alteração", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsLowerMenu1, View.optionsLowerMenu1[0]);
	}
	
	public Integer getRemoveOption(){
		return JOptionPane.showOptionDialog(null, "O que deseja remover?", "Remoção", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, View.optionsLowerMenu2, View.optionsLowerMenu2[0]);
	}
	
	public String getString(String object, String attribute){
		String string = JOptionPane.showInputDialog("Digite o " + attribute + " do " + object);
		if (string == null) {
			return null;
		}
		while (string == null || string.isEmpty()){
			string = JOptionPane.showInputDialog("Valor inválido. Digite o " + attribute + " do " + object);
		}
		return string;
	}
	
	public Integer getInteger(String object, String attribute){
		String string = JOptionPane.showInputDialog("Digite o " + attribute + " do " + object + " (int >= 0)");
		if (string == null) {
			return null;
		}
		Integer number = null;
		while (number == null){
			try {
				number = Integer.parseInt(string);
				if (number < 0){
					throw new Exception();
				}
			} catch (Exception e) {
				string = JOptionPane.showInputDialog("Valor inválido. Digite o " + attribute + " do " + object + " (int > 0)");
			}
		}
		return number;
	}
	
	public Double getDouble(String object, String attribute){
		String string = JOptionPane.showInputDialog("Digite o " + attribute + " do " + object + " (double >= 0)");
		if (string == null) {
			return null;
		}
		Double number = null;
		while (number == null){
			try {
				number = Double.parseDouble(string);
				if (number < 0){
					throw new Exception();
				}
			} catch (Exception e) {
				string = JOptionPane.showInputDialog("Valor inválido. Digite o " + attribute + " do " + object + " (double >= 0)");
			}
		}
		return number;
	}
	
	public void printInformation(Client c){
		String information = "CLIENTE\nNome: " + c.getName() + "\nTelefone: " + c.getPhone() + "\nValor gasto: R$" + c.getValueSpent() +
				"\nPontos: " + c.calculatePoints() + "pontos.";
		JOptionPane.showMessageDialog(null, information);
	}
	
	public void printInformation(Supplier s){
		String information = "FORNECEDOR\nNome: " + s.getName() + "\nTelefone: " + s.getPhone() + "\nTipo do produto: " + 
				s.getProductType() + "\nNome da empresa: " + s.getCompany().getName() + "\nCNPJ da empresa: " + s.getCompany().getCnpj();
		JOptionPane.showMessageDialog(null, information);
	}
	
	public void printInformation(Clerk c){
		String information = "ATENDENTE\nNome: " + c.getName() + "\nTelefone: " + c.getPhone() + "\nSalário base: R$" + 
				c.getBaseSalary() + "\nSalário atual: " + c.calculateSalary() + "\nTempo de serviço: " + c.getTimeWorked() +
				"\nCarga horária: " + c.getWorkingHours() + "\nNúmero do caixa: " + c.getCounterNumber();
		JOptionPane.showMessageDialog(null, information);
	}
	
	public void printInformation(Manager m){
		String information = "GERENTE\nNome: " + m.getName() + "\nTelefone: " + m.getPhone() + "\nSalário base: R$" + 
				m.getBaseSalary() + "\nSalário atual: R$" + m.calculateSalary() + "\nTempo de serviço: " + m.getTimeWorked() +
				"\nCarga horária: " + m.getWorkingHours() + "\nDepartamento: " + m.getDepartment().getName() + 
				"(" + m.getDepartment().getId() + ").";
		JOptionPane.showMessageDialog(null, information);
	}
	
	public void printInformation(Department d){
		String information = "DEPARTAMENTO\nNúmero: " + d.getId() + "\nNome: " + d.getName();
		JOptionPane.showMessageDialog(null, information);
	}
	
	public void printMessage(String message){
		JOptionPane.showMessageDialog(null, message);
	}
}
