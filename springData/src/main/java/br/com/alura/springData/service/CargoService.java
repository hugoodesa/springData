package br.com.alura.springData.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springData.interfaces.Crud;
import br.com.alura.springData.orm.Cargo;
import br.com.alura.springData.repository.CargoRepository;

@Service
public class CargoService implements Crud<Cargo> {

	CargoRepository repository;

	public CargoService(CargoRepository repository) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
	}
	
	public String menu() {
		
		StringBuilder str = new StringBuilder();
		
		str.append("========= \n");
		str.append(" 1 - Cadastrar \n");
		str.append(" 2 - Selecionar \n");
		str.append(" 3 - Deletar \n");
		str.append(" 4 - Atualizar \n");
		str.append(" 5 - Listar todos \n");
		str.append("========= \n");
		
		return str.toString();
	}
	
	public void exibirMenu() {
		System.out.println(menu());
	}
	
	public int getInput(Scanner sc) {
		exibirMenu();
		int opcaoSelecionada = sc.nextInt();
		return opcaoSelecionada;
	}
	
	public Cargo criarCargo(Scanner sc) {
		System.out.println("Informe a descricao do cargo : ");
		String descricao  = sc.next(); 
		
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		
		return cargo;
	}
	
	public Long solicitarId(Scanner sc){
		System.out.println("Informe o id do cargo");
		long idCargo = sc.nextLong();
		return idCargo;
	}
	
	public void exibirCargo(Cargo cargo) {
		System.out.println(cargo);
	}
	
	public void alterarDescricao(Scanner sc,Cargo cargo) {
		System.out.println("Nova descricao do cargo");
		String novaDescricao = sc.nextLine();
		
		cargo.setDescricao(novaDescricao);
		
		this.alterar(cargo.getId(), cargo);
	}
	
	public void iniciar(Scanner sc) {
		
		boolean continuar = true;
		Long idCargo = 0L;
		Cargo cargo;
		
		while (continuar) {
			int opcaoSelecionada = getInput(sc);
			
			switch (opcaoSelecionada) {
			case 1:
				Cargo criarCargo = criarCargo(sc);
				this.inserir(criarCargo);
				break;
			case 2:
				idCargo = solicitarId(sc);
				cargo = this.selecionar(idCargo);
				exibirCargo(cargo);
				break;
			case 3:
				idCargo = solicitarId(sc);
				this.deletar(idCargo);
				System.out.println("Deletado com sucesso");
				break;	
			case 4:
				idCargo = solicitarId(sc);
				cargo = this.selecionar(idCargo);
				alterarDescricao(sc, cargo);
				this.alterar(idCargo, cargo);
				System.out.println("Cargo atualizado com sucesso");
				break;	
			case 5:
				this.listar();
				break;	
			default:
				continuar=false;
				break;
			}
		}
	}

	@Override
	public void inserir(Cargo entidade) {
		this.repository.save(entidade);
	}

	@Override
	public Cargo selecionar(Long id) {
		Optional<Cargo> cargoEncontrado = this.repository.findById(id);
		Cargo cargo = cargoEncontrado.get();
		return cargo;
	}

	@Override
	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public void alterar(Long id,Cargo novoEstadoCargo) {
		Cargo cargo = this.selecionar(id);
		cargo.setDescricao(novoEstadoCargo.getDescricao());
		this.inserir(cargo);
	}

	@Override
	public void listar() {
	}
	
}
