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
		
		Client[] arrayClients = null;
		Supplier[] arraySuppliers = null;
		Employee[] arrayEmployees = null;
		Department[] arrayDepartments = null;
		
		boolean running = false;
		
		Integer sizeClients = v.getSizeArray("clientes");
		if (!(sizeClients == null)) {
			arrayClients = new Client[sizeClients];
			Integer sizeSuppliers = v.getSizeArray("fornecedores");
			if (!(sizeSuppliers == null)) {
				arraySuppliers = new Supplier[sizeSuppliers];
				Integer sizeEmployees = v.getSizeArray("funcionários");
				if (!(sizeEmployees == null)) {
					arrayEmployees = new Employee[sizeEmployees];
					Integer sizeDepartments = v.getSizeArray("departamentos");
					if (!(sizeDepartments == null)) {
						arrayDepartments = new Department[sizeDepartments];
						running = true;
					}
				}
			}
		}
		
		Integer id, departmentId;
		Integer optionMenu, optionLowerMenu;
		
		Client client;
		Supplier supplier;
		Company company;
		Clerk clerk;
		Manager manager;
		Department department;

		String name, phone, companyName, companyCnpj, productType;
		Double valueSpent, baseSalary;
		Integer timeWorked, workingHours, counterNumber;
		
		while (running) {
			optionMenu = v.getOption(); //0 - Cadastrar, 1 - Consultar, 2 - Alterar, 3 - Excluir, 4 - Sair	
			switch(optionMenu) {
			case 0: // cadastrar
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
					counterNumber = v.getInteger("atendente", "número do caixa");
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
					if (departmentId == null) {
						break;
					}
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
					name = v.getString("departamento", "nome");
					if (name == null) {
						break;
					}
					department = new Department();
					department.setId(id);
					department.setName(name);
					arrayDepartments[id] = department;
					break;
				case 5: // cancelar
					break;
				}
				break;
			case 1: // consultar
				optionLowerMenu = v.getSearchOption(); //0 - Cliente, 1 - Fornecedor, 2 - Atendente, 3 - Gerente, 4 - Departamento, 5 - Cancelar
				switch (optionLowerMenu) {
				case 0:
					id = v.getInteger("cliente", "id");
					if (id == null) {
						break;
					}
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
					if (id == null) {
						break;
					}
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
					if (id == null) {
						break;
					}
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
					if (id == null) {
						break;
					}
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
					if (id == null) {
						break;
					}
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
			case 2: // alterar dados
				optionLowerMenu = v.getAlterOption(); //0 - Cliente, 1 - Fornecedor, 2 - Atendente, 3 - Gerente, 4 - Departamento, 5 - Cancelar
				switch (optionLowerMenu) {
				case 0:
					id = v.getInteger("cliente", "id");
					if (id == null) {
						break;
					}
					if (id < 0 || id >= arrayClients.length) {
						v.printMessage("Id inválido");
						break;
					}
					client = arrayClients[id];
					if (client == null) {
						v.printMessage("Nenhum cliente cadastrado sob esse id.");
						break;
					}
					name = v.getString("cliente", "novo nome");
					if (name == null) {
						break;
					}
					phone = v.getString("cliente", "novo telefone");
					if (phone == null) {
						break;
					}
					valueSpent = v.getDouble("cliente", "novo valor gasto");
					if (valueSpent == null) {
						break;
					}
					client.setName(name);
					client.setPhone(phone);
					client.setValueSpent(valueSpent);
					break;
				case 1:
					id = v.getInteger("fornecedor", "id");
					if (id == null) {
						break;
					}
					if (id < 0 || id >= arraySuppliers.length) {
						v.printMessage("Id inválido.");
						break;
					}
					supplier = arraySuppliers[id];
					if (supplier == null) {
						v.printMessage("Nenhum fornecedor cadastrado sob esse id.");
						break;
					}
					name = v.getString("fornecedor", "novo nome");
					if (name == null) {
						break;
					}
					phone = v.getString("fornecedor", "novo telefone");
					if (phone == null) {
						break;
					}
					productType = v.getString("fornecedor", "novo tipo do produto");
					if (productType == null) {
						break;
					}
					companyName = v.getString("fornecedor", "novo nome da empresa");
					if (companyName == null) {
						break;
					}
					companyCnpj = v.getString("fornecedor", "novo CNPJ da empresa");
					if (companyCnpj == null) {
						break;
					}
					supplier.setName(name);
					supplier.setPhone(phone);
					supplier.setProductType(productType);
				
					company = supplier.getCompany();
					company.setName(companyName);
					company.setCnpj(companyCnpj);
					break;
				case 2:
					id = v.getInteger("atendente", "id");
					if (id == null){
						break;
					}
					if (id < 0 || id >= arrayEmployees.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (!(arrayEmployees[id] instanceof Clerk)) {
						v.printMessage("Nenhum atendente cadastrado sob esse id.");
						break;
					}
					name = v.getString("atendente", "novo nome");
					if (name == null) {
						break;
					}
					phone = v.getString("atendente", "novo telefone");
					if (phone == null) {
						break;
					}
					baseSalary = v.getDouble("atendente", "novo salário base");
					if (baseSalary == null) {
						break;
					}
					timeWorked = v.getInteger("atendente", "novo tempo de serviço");
					if (timeWorked == null) {
						break;
					}
					workingHours = v.getInteger("atendente", "nova carga horária");
					if (workingHours == null) {
						break;
					}
					counterNumber = v.getInteger("atendente", "novo número do caixa");
					if (counterNumber == null) {
						break;
					}
					clerk = (Clerk) arrayEmployees[id];
					clerk.setName(name);
					clerk.setPhone(phone);
					clerk.setBaseSalary(baseSalary);
					clerk.setTimeWorked(timeWorked);
					clerk.setWorkingHours(workingHours);
					clerk.setCounterNumber(counterNumber);
					break;
				case 3:
					id = v.getInteger("gerente", "id");
					if (id == null){
						break;
					}
					if (id < 0 || id >= arrayEmployees.length) {
						v.printMessage("Id inválido.");
						break;
					}
					if (!(arrayEmployees[id] instanceof Manager)) {
						v.printMessage("Nenhum gerente cadastrado sob esse id.");
						break;
					}
					name = v.getString("gerente", "novo nome");
					if (name == null) {
						break;
					}
					phone = v.getString("gerente", "novo telefone");
					if (phone == null) {
						break;
					}
					baseSalary = v.getDouble("gerente", "novo salário base");
					if (baseSalary == null) {
						break;
					}
					timeWorked = v.getInteger("gerente", "novo tempo de serviço");
					if (timeWorked == null) {
						break;
					}
					workingHours = v.getInteger("gerente", "nova carga horária");
					if (workingHours == null) {
						break;
					}
					manager = (Manager) arrayEmployees[id];
					manager.setName(name);
					manager.setPhone(phone);
					manager.setBaseSalary(baseSalary);
					manager.setTimeWorked(timeWorked);
					manager.setWorkingHours(workingHours);					
					break;
				case 4:
					id = v.getInteger("departamento", "id");
					if (id == null) {
						break;
					}
					if (id < 0 || id >= arrayDepartments.length) {
						v.printMessage("Id inválido.");
						break;
					}
					department = arrayDepartments[id];
					if (department == null) {
						v.printMessage("Nenhum departamento cadastrado sob esse id.");
						break;
					}
					name = v.getString("departamento", "novo nome");
					if (name == null) {
						break;
					}
					department.setName(name);
					break;
				case 5: // cancelar
					break;
				}
				break;
			case 3: // excluir
				optionLowerMenu = v.getRemoveOption(); //0 - Cliente, 1 - Fornecedor, 2 - Atendente, 3 - Gerente, 4 - Cancelar
				switch (optionLowerMenu) {
				case 0:
					id = v.getInteger("cliente", "id");
					if (id == null) {
						break;
					}
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
					if (id == null) {
						break;
					}
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
					if (id == null) {
						break;
					}
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
					if (id == null) {
						break;
					}
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
			case 4: // sair
				running = false;
				break;
			}
		}
	}
	
	// retorna a próxima posição livre (null) no array
	public static Integer getNextPosition(Object[] array){ 
		for (int i = 0; i < array.length; i++){
			if (array[i] == null){
				return i;
			}
		}
		return null;
	}
	
	// retorna true se o departamento 'd' já possui um gerente cadastrado, caso contrário retorna false
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
    
    // retorna true se encontrar ao menos uma vaga de gerente disponível em algum departamento,
    // caso contrário retorna false
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