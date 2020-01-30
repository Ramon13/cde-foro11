package br.com.javamon.util;

public enum Status {

	FINALIZED ('F'),
	CANCELED_BY_ADMIN ('C'),
	CANCELED_BY_USER ('U'),
	PENDING('P'),
	RELEASED('R');
	
	private char value;
	
	Status( char value ) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}
}
