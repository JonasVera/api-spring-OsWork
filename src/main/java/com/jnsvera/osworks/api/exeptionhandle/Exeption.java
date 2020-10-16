package com.jnsvera.osworks.api.exeptionhandle;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Exeption {
	private Integer status;
	private LocalDate dataHora;
	private String titulo;
	private List<Campo> campos;
	
	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	public static class Campo {
		public Campo(String nome, String mensagem) {
			 this.nome = nome;
			this.mensagem = mensagem;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		private String nome;
		private String mensagem;
	}
	public Exeption() {
		 
	}
	
	public Exeption(Integer status, LocalDate dataHora, String titulo) {
		super();
		this.status = status;
		this.dataHora = dataHora;
		this.titulo = titulo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDate getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
