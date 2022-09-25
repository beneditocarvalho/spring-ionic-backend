package com.logos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.logos.cursomc.domain.Categoria;
import com.logos.cursomc.domain.Cidade;
import com.logos.cursomc.domain.Cliente;
import com.logos.cursomc.domain.Endereco;
import com.logos.cursomc.domain.Estado;
import com.logos.cursomc.domain.Produto;
import com.logos.cursomc.domain.enums.TipoCliente;
import com.logos.cursomc.repositories.CategoriaRepository;
import com.logos.cursomc.repositories.CidadeRepository;
import com.logos.cursomc.repositories.ClienteRepository;
import com.logos.cursomc.repositories.EnderecoRepository;
import com.logos.cursomc.repositories.EstadoRepository;
import com.logos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
				
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "33333333333", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("2355523","991033344"));
		
		Endereco e1 = new Endereco(null, "Rua Seabstiao", "300", "Apto 303", "Centro", "860800260", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sasla 800", "Jardim", "984158899", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
				
		
	}

}	