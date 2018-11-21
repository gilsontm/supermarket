package Controller;

import Model.Clerk;
import Model.Client;
import Model.Company;
import Model.Department;
import Model.Employee;
import Model.Manager;
import Model.Person;
import Model.Supplier;
import View.View;

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
					v.printMessage("Cliente cadastrado com sucesso (id = " + id +").");
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
					v.printMessage("Fornecedor cadastrado com sucesso (id = " + id +").");
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
					v.printMessage("Atendente cadastrado com sucesso (id = " + id +").");
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
					departmentId = v.showList(Controller.departmentArrayToString(arrayDepartments), "Selecione um departamento");
					if (departmentId == null) {
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
					manager.setId(id);
					manager.setName(name);
					manager.setPhone(phone);
					manager.setBaseSalary(baseSalary);
					manager.setTimeWorked(timeWorked);
					manager.setWorkingHours(workingHours);
					manager.setDepartment(arrayDepartments[departmentId]);
					arrayEmployees[id] = manager;
					v.printMessage("Gerente cadastrado com sucesso (id = " + id +").");
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
					v.printMessage("Departamento cadastrado com sucesso (id = " + id +").");
					break;
				case 5: // cancelar
					break;
				}
				break;
			case 1: // consultar
				optionLowerMenu = v.getSearchOption(); //0 - Cliente, 1 - Fornecedor, 2 - Atendente, 3 - Gerente, 4 - Departamento, 5 - Cancelar
				switch (optionLowerMenu) {
				case 0:
					if (Controller.numberOfElements(arrayClients) == 0) {
						v.printMessage("Nenhum cliente cadastrado.");
						break;
					}
					id = v.showList(Controller.personArrayToString(arrayClients));
					if (id == null) {
						break;
					}
					v.printInformation(arrayClients[id]);
					break;
				case 1:
					if (Controller.numberOfElements(arraySuppliers) == 0) {
						v.printMessage("Nenhum fornecedor cadastrado.");
						break;
					}
					id = v.showList(Controller.personArrayToString(arraySuppliers));
					if (id == null) {
						break;
					}
					v.printInformation(arraySuppliers[id]);
					break;
				case 2:
					if (Controller.numberOfClerks(arrayEmployees) == 0) {
						v.printMessage("Nenhum atendente cadastrado.");
						break;
					}
					id = v.showList(Controller.clerkArrayToString(arrayEmployees));
					if (id == null) {
						break;
					}
					v.printInformation((Clerk) arrayEmployees[id]);
					break;
				case 3:
					if (Controller.numberOfManagers(arrayEmployees) == 0) {
						v.printMessage("Nenhum gerente cadastrado.");
						break;
					}
					id = v.showList(Controller.managerArrayToString(arrayEmployees));
					if (id == null) {
						break;
					}
					v.printInformation((Manager) arrayEmployees[id]);
					break;
				case 4:
					if (Controller.numberOfElements(arrayDepartments) == 0) {
						v.printMessage("Nenhum departamento cadastrado.");
						break;
					}
					id = v.showList(Controller.departmentArrayToString(arrayDepartments));
					if (id == null) {
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
					if (Controller.numberOfElements(arrayClients) == 0) {
						v.printMessage("Nenhum cliente cadastrado.");
						break;
					}
					id = v.showList(Controller.personArrayToString(arrayClients));
					if (id == null) {
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
					client = arrayClients[id];
					client.setName(name);
					client.setPhone(phone);
					client.setValueSpent(valueSpent);
					break;
				case 1:
					if (Controller.numberOfElements(arraySuppliers) == 0) {
						v.printMessage("Nenhum fornecedor cadastrado.");
						break;
					}
					id = v.showList(Controller.personArrayToString(arraySuppliers));
					if (id == null) {
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
					supplier = arraySuppliers[id];
					supplier.setName(name);
					supplier.setPhone(phone);
					supplier.setProductType(productType);
				
					company = supplier.getCompany();
					company.setName(companyName);
					company.setCnpj(companyCnpj);
					break;
				case 2:
					if (Controller.numberOfClerks(arrayEmployees) == 0) {
						v.printMessage("Nenhum atendente cadastrado.");
						break;
					}
					id = v.showList(Controller.clerkArrayToString(arrayEmployees));
					if (id == null) {
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
					if (Controller.numberOfManagers(arrayEmployees) == 0) {
						v.printMessage("Nenhum gerente cadastrado.");
						break;
					}
					id = v.showList(Controller.managerArrayToString(arrayEmployees));
					if (id == null) {
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
					if (Controller.numberOfElements(arrayDepartments) == 0) {
						v.printMessage("Nenhum departamento cadastrado.");
						break;
					}
					id = v.showList(Controller.departmentArrayToString(arrayDepartments));
					if (id == null) {
						break;
					}
					department = arrayDepartments[id];
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
					if (Controller.numberOfElements(arrayClients) == 0) {
						v.printMessage("Nenhum cliente cadastrado.");
						break;
					}
					id = v.showList(Controller.personArrayToString(arrayClients));
					if (id == null) {
						break;
					}
					arrayClients[id] = null;
					v.printMessage("Cliente removido.");
					break;
				case 1:
					if (Controller.numberOfElements(arraySuppliers) == 0) {
						v.printMessage("Nenhum fornecedor cadastrado.");
						break;
					}
					id = v.showList(Controller.personArrayToString(arraySuppliers));
					if (id == null) {
						break;
					}
					arraySuppliers[id] = null;
					v.printMessage("Fornecedor removido.");
					break;
				case 2:
					if (Controller.numberOfClerks(arrayEmployees) == 0) {
						v.printMessage("Nenhum atendente cadastrado.");
						break;
					}
					id = v.showList(Controller.clerkArrayToString(arrayEmployees));
					if (id == null) {
						break;
					}
					arrayEmployees[id] = null;
					v.printMessage("Atendente removido.");
					break;
				case 3:
					if (Controller.numberOfManagers(arrayEmployees) == 0) {
						v.printMessage("Nenhum atendente cadastrado.");
						break;
					}
					id = v.showList(Controller.managerArrayToString(arrayEmployees));
					if (id == null) {
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
    
    // retorna o número de elementos não nulos do array
    public static Integer numberOfElements(Object[] array) {
    	Integer counter = 0;
    	for (Object e : array) {
    		if (e != null) {
    			counter++;
    		}
    	}
    	return counter;
    }
    
    public static Integer numberOfClerks(Employee[] array) {
    	Integer counter = 0;
    	for (Employee e : array) {
    		if (e != null) {
    			if (e instanceof Clerk) {
    				counter++;
    			}
    		}
    	}
    	return counter;
    }
    
    public static Integer numberOfManagers(Employee[] array) {
    	Integer counter = 0;
    	for (Employee e : array) {
    		if (e != null) {
    			if (e instanceof Manager) {
    				counter++;
    			}
    		}
    	}
    	return counter;
    }
    
    public static String[] personArrayToString(Person[] array) {
    	String[] arrayString = new String[Controller.numberOfElements(array)];
    	for (int i = 0; i < array.length; i++) {
    		if (array[i] != null) {
    			arrayString[i] = array[i].getId() + " - Nome: " + array[i].getName();
    		}
    	}
    	return arrayString;
    }
    
    public static String[] clerkArrayToString(Employee[] array) {
    	String[] arrayString = new String[Controller.numberOfClerks(array)];
    	for (int i = 0; i < array.length; i++) {
    		if (array[i] != null) {
    			if (array[i] instanceof Clerk) {
    				arrayString[i] = array[i].getId() + " - Nome: " + array[i].getName();
    			}
    		}
    	}
    	return arrayString;
    }
    
    public static String[] managerArrayToString(Employee[] array) {
    	String[] arrayString = new String[Controller.numberOfManagers(array)];
    	for (int i = 0; i < array.length; i++) {
    		if (array[i] != null) {
    			if (array[i] instanceof Manager) {
    				arrayString[i] = array[i].getId() + " - Nome: " + array[i].getName();
    			}
    		}
    	}
    	return arrayString;
    }
    
    
    public static String[] departmentArrayToString(Department[] array) {
    	String[] arrayString = new String[Controller.numberOfElements(array)];
    	for (int i = 0; i < array.length; i++) {
    		if (array[i] != null) {
    			arrayString[i] = array[i].getId() + " - Nome: " + array[i].getName();
    		}
    	}
    	return arrayString;
    }
}






