package br.com.alura.springData.interfaces;

public interface Crud<T> {

	public void inserir(T entidade);

	public T selecionar(Long id);
	
	public void deletar(Long id);
	
	public void alterar(Long id,T entidade);
	
	public void listar();
	
}
