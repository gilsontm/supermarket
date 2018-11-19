package Controller;

import View.View;

import Model.Clerk;
import Model.Client;
import Model.Company;
import Model.Department;
import Model.Manager;
import Model.Employee;
import Model.Supplier;

public class Controller {

	public static void main(String[] args) {
		View v = new View();
		Integer id, departmentId;
		Integer optionMenu, optionLowerMenu;
		
		Client client;
		Supplier supplier;
		Company company;
		Clerk clerk;
		Manager manager;
		Department department;
		
		Client[] arrayClients = new Client[v.getSizeArray("clientes")];
		Supplier[] arraySuppliers = new Supplier[v.getSizeArray("fornecedores")];
		Employee[] arrayEmployees = new Employee[v.getSizeArray("funcionários")];
		Department[] arrayDepartments = new Department[v.getSizeArray("departamentos")];
		
		String name, phone, companyName, companyCnpj, productType;
		Double valueSpent, baseSalary;
		Integer timeWorked, workingHours, counterNumber;
		boolean running = true;
		do {
			optionMenu = v.getOption(); //0 - Cadastrar, 1 - Consultar, 2 - Alterar, 3 - Excluir, 4 - Sair	
			switch(optionMenu) {
			case 0: 
				optionLowerMenu = v.getRegisterOption(); //0 - Cliente, 1 - Fornecedor, 2 - Atendente, 3 - Gerente, 4 - Departamento, 5 - Cancelar
				switch(optionLowerMenu) {
				case 0:
					id = Controller.getNextPosition(arrayClients);
					if (id == null){
						v.printMessage("Limite de clientes atingido. Impossível cadastrar.");
						break;
					}
					name = v.getString("cliente", "nome");
					if (name == null) {
						break;
					}
					phone = v.getString("cliente", "telefone");
					if (phone == null) {
						break;
					}
					valueSpent = v.getDouble("cliente", "valor gasto");
					if (valueSpent == null) {
						break;
					}
					client = new Client();
					client.setId(id);
					client.setName(name);
					client.setPhone(phone);
					client.setValueSpent(valueSpent);
					arrayClients[id] = client;
					break;
				case 1:
					id = Controller.getNextPosition(arraySuppliers);
					if (id == null){
						v.printMessage("Limite de fornecedores atingido. Impossível cadastrar.");
						break;
					}
					name = v.getString("fornecedor", "nome");
					if (name == null) {
						break;
					}
					phone = v.getString("fornecedor", "telefone");
					if (phone == null) {
						break;
					}
					productType = v.getString("fornecedor", "tipo do produto");
					if (productType == null) {
						break;
					}
					companyName = v.getString("fornecedor", "nome da empresa");
					if (companyName == null) {
						break;
					}
					companyCnpj = v.getString("fornecedor", "CNPJ da empresa");
					if (companyCnpj == null) {
						break;
					}
					
					supplier = new Supplier();
					supplier.setId(id);
					supplier.setName(name);
					supplier.setPhone(phone);
					supplier.setProductType(productType);
					
					company = new Company();
					company.setName(companyName);
					company.setCnpj(companyCnpj);
					
					supplier.setCompany(company);
					arraySuppliers[id] = supplier;
					break;
				case 2:
					id = Controller.getNextPosition(arrayEmployees);
					if (id == null){
						v.printMessage("Limite de atendentes atingido. Impossível cadastrar.");
						break;
					}
					name = v.getString("atendente", "nome");
					if (name == null) {
						break;
					}
					phone = v.getString("atendente", "telefone");
					if (phone == null) {
						break;
					}
					baseSalary = v.getDouble("atendente", "salário base");
					if (baseSalary == null) {
						break;
					}
					timeWorked = v.getInteger("atendente", "tempo de serviço");
					if (timeWorked == null) {
						break;
					}
					workingHours = v.getInteger("atendente", "carga horária");
					if (workingHours == null) {
						break;
					}
					counterNumber = v.getInteger("atendente", "caixa");
					if (counterNumber == null) {
						break;
					}
					
					clerk = new Clerk();
					clerk.setId(id);
					clerk.setName(name);
					clerk.setPhone(phone);
					clerk.setBaseSalary(baseSalary);
					clerk.setTimeWorked(timeWorked);
					clerk.setWorkingHours(workingHours);
					clerk.setCounterNumber(counterNumber);
					arrayEmployees[id] = clerk;
					break;
				case 3:
					id = Controller.getNextPosition(arrayEmployees);
					if (id == null) {
						v.printMessage("Limite de funcionários atingido. Impossível cadastrar.");
						break;
					}
					
					if (!Controller.canRegisterNewManager(arrayEmployees, arrayDepartments)) {
						v.printMessage("Não há departamentos suficientes para cadastrar um novo gerente."
								+ " \nPor favor, cadastre um departamento primeiro.");
						break;
					}
					
					departmentId = v.getInteger("gerente", "id do departamento");
					if (departmentId < 0 || departmentId >= arrayDepartments.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arrayDepartments[departmentId] == null) {
						v.printMessage("Esse departamento não está cadastrado.");
						break;
					}
					if (Controller.hasManager(arrayEmployees, arrayDepartments[departmentId])) {
						v.printMessage("Esse departamento já tem um gerente cadastrado.");
						break;
					}
					
					name = v.getString("gerente", "nome");
					if (name == null) {
						break;
					}
					phone = v.getString("gerente", "telefone");
					if (phone == null) {
						break;
					}
					baseSalary = v.getDouble("gerente", "salário base");
					if (baseSalary == null) {
						break;
					}
					timeWorked = v.getInteger("gerente", "tempo de serviço");
					if (timeWorked == null) {
						break;
					}
					workingHours = v.getInteger("gerente", "carga horária");
					if (workingHours == null) {
						break;
					}
					
					manager = new Manager();
					manager.setName(name);
					manager.setPhone(phone);
					manager.setBaseSalary(baseSalary);
					manager.setTimeWorked(timeWorked);
					manager.setWorkingHours(workingHours);
					manager.setDepartment(arrayDepartments[departmentId]);
					arrayEmployees[id] = manager;
					break;
				case 4:
					id = Controller.getNextPosition(arrayDepartments);
					if (id == null) {
						v.printMessage("Limite de departamentos atingido.");
						break;
					}
					department = new Department();
					department.setId(id);
					department.setName(v.getString("departamento", "nome"));
					arrayDepartments[id] = department;
					break;
				case 5: // cancelar
					break;
				}
				break;
			case 1:
				optionLowerMenu = v.getSearchOption(); //0 - Cliente, 1 - Fornecedor, 2 - Atendente, 3 - Gerente, 4 - Departamento, 5 - Cancelar
				switch (optionLowerMenu) {
				case 0:
					id = v.getInteger("cliente", "id");
					if (id < 0 || id >= arrayClients.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arrayClients[id] == null) {
						v.printMessage("Nenhum cliente cadastrado sob esse id.");
						break;
					}
					v.printInformation(arrayClients[id]);
					break;
				case 1:
					id = v.getInteger("fornecedor", "id");
					if (id < 0 || id >= arraySuppliers.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arraySuppliers[id] == null) {
						v.printMessage("Nenhum cliente cadastrado sob esse id.");
						break;
					}
					v.printInformation(arraySuppliers[id]);
					break;
				case 2:
					id = v.getInteger("atendente", "id");
					if (id < 0 || id >= arrayEmployees.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arrayEmployees[id] == null) {
						v.printMessage("Nenhum atendente cadastrado sob esse id.");
						break;
					}
					if (!(arrayEmployees[id] instanceof Clerk)) {
						v.printMessage("O funcionário cadastrado sob esse id não é um atendente.");
						break;
					}
					v.printInformation((Clerk) arrayEmployees[id]);
					break;
				case 3:
					id = v.getInteger("gerente", "id");
					if (id < 0 || id >= arrayEmployees.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arrayEmployees[id] == null) {
						v.printMessage("Nenhum gerente cadastrado sob esse id.");
						break;
					}
					if (!(arrayEmployees[id] instanceof Manager)) {
						v.printMessage("O funcionário cadastrado sob esse id não é um gerente.");
						break;
					}
					v.printInformation((Manager) arrayEmployees[id]);
					break;
				case 4:
					id = v.getInteger("departamento", "id");
					if (id < 0 || id >= arrayDepartments.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arrayDepartments[id] == null) {
						v.printMessage("Nenhum departamento cadastrado sob esse id.");
						break;
					}
					v.printInformation(arrayDepartments[id]);
					break;
				}
				break;
			case 2:
				optionLowerMenu = v.getAlterOption(); //0 - Cliente, 1 - Fornecedor, 2 - Atendente, 3 - Gerente, 4 - Departamento, 5 - Cancelar
				switch (optionLowerMenu) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5: // cancelar
					break;
				}
				break;
			case 3:
				optionLowerMenu = v.getRemoveOption(); //0 - Cliente, 1 - Fornecedor, 2 - Atendente, 3 - Gerente, 4 - Cancelar
				switch (optionLowerMenu) {
				case 0:
					id = v.getInteger("cliente", "id");
					if (id < 0 || id >= arrayClients.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arrayClients[id] == null) {
						v.printMessage("Nenhum cliente cadastrado sob esse id.");
						break;
					}
					arrayClients[id] = null;
					v.printMessage("Cliente removido.");
					break;
				case 1:
					id = v.getInteger("fornecedor", "id");
					if (id < 0 || id >= arraySuppliers.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arraySuppliers[id] == null) {
						v.printMessage("Nenhum fornecedor cadastrado sob esse id.");
						break;
					}
					arraySuppliers[id] = null;
					v.printMessage("Fornecedor removido.");
					break;
				case 2:
					id = v.getInteger("atendente", "id");
					if (id < 0 || id >= arrayEmployees.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arrayEmployees[id] == null) {
						v.printMessage("Nenhum atendente cadastrado sob esse id.");
						break;
					}
					if (!(arrayEmployees[id] instanceof Clerk)) {
						v.printMessage("O funcionário cadastrado sob esse id não é um atendente.");
						break;
					}
					arrayEmployees[id] = null;
					v.printMessage("Atendente removido.");
					break;
				case 3:
					id = v.getInteger("gerente", "id");
					if (id < 0 || id >= arrayEmployees.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (arrayEmployees[id] == null) {
						v.printMessage("Nenhum gerente cadastrado sob esse id.");
						break;
					}
					if (!(arrayEmployees[id] instanceof Manager)) {
						v.printMessage("O funcionário cadastrado sob esse id não é um gerente.");
						break;
					}
					arrayEmployees[id] = null;
					v.printMessage("Gerente removido.");
					break;
				case 4: // cancelar
					break; 
				}
				break;
			case 4:
				running = false;
				break;
			}
		} while(running);

	}
	
	public static Integer getNextPosition(Object[] array){
		for (int i = 0; i < array.length; i++){
			if (array[i] == null){
				return i;
			}
		}
		return null;
	}
	
    public static boolean hasManager(Employee[] array, Department d){
		for (Employee e : array){
			if (e instanceof Manager){
				if (((Manager) e).getDepartment().equals(d)){
					return true;
				}
			}
		}
		return false;
    }
    
    public static boolean canRegisterNewManager(Employee[] arrayEmployees, Department[] arrayDepartments) {
    	int numberDepartments = 0;
    	for (Department d : arrayDepartments) {
    		if (d != null) {
    			numberDepartments++;
    		}
    	}
    	int numberManagers = 0;
    	for (Employee e : arrayEmployees) {
    		if (e instanceof Manager) {
    			numberManagers++;
    		}
    	}
    	if (numberManagers < numberDepartments) {
    		return true;
    	}
    	return false;
    }
}

