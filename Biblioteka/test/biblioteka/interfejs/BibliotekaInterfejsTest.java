package biblioteka.interfejs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import biblioteka.Knjiga;

public abstract class BibliotekaInterfejsTest {

	protected BibliotekaInterfejs biblioteka;
	

	@Test
	void testDodajKnjiguNull() {
		assertThrows(NullPointerException.class, () -> biblioteka.dodajKnjigu(null));
	}
	
	@Test
	void testDodajKnjiguDuplikat() {
		Knjiga k = new Knjiga();
		
		k.setIsbn(12345);
		
		biblioteka.dodajKnjigu(k);
		
		assertThrows(IllegalArgumentException.class, () -> biblioteka.dodajKnjigu(k));
	}
	
	@Test
	void testDodajKnjiguSveOk() {
		Knjiga k = new Knjiga();
		
		k.setIsbn(12345);
		
		biblioteka.dodajKnjigu(k);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		
		assertEquals(1, knjige.size());
		assertTrue(knjige.contains(k));
	}
	
	
	@Test
	void testDodajKnjiguSveOk2() {
		Knjiga k = new Knjiga();
		
		k.setIsbn(12345);
		
		biblioteka.dodajKnjigu(k);
		
		Knjiga k2 = new Knjiga();
		
		k2.setIsbn(54321);
		
		biblioteka.dodajKnjigu(k2);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		
		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k));
		assertTrue(knjige.contains(k2));
	}

	@Test
	void testObrisiKnjigu() {
		Knjiga k = new Knjiga();
		
		k.setIsbn(12345);
		
		biblioteka.dodajKnjigu(k);
		
		Knjiga k2 = new Knjiga();
		
		k2.setIsbn(54321);
		
		biblioteka.dodajKnjigu(k2);
		
		biblioteka.obrisiKnjigu(k);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		
		assertEquals(1, knjige.size());
		assertTrue(knjige.contains(k2));
	}
	
	@Test
	void testObrisiKnjiguNePostoji() {
		Knjiga k = new Knjiga();
		
		k.setIsbn(12345);
		
		biblioteka.dodajKnjigu(k);
		
		Knjiga k2 = new Knjiga();
		
		k2.setIsbn(54321);
		
		biblioteka.dodajKnjigu(k2);
		
		Knjiga k3 = new Knjiga();
		
		k3.setIsbn(999);
		
		biblioteka.obrisiKnjigu(k3);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		
		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k2));
		assertTrue(knjige.contains(k));
	}


	@Test
	void testPronadjiKnjiguSveNull() {
		assertThrows(IllegalArgumentException.class, () -> biblioteka.pronadjiKnjigu(null, -1, null, null));	
	}
	
	@Test
	void testPronadjiKnjiguNaslovNull() {
		List<Knjiga> rezultat = biblioteka.pronadjiKnjigu(null, 0, null, "Laguna");
		
		assertTrue(rezultat.isEmpty());
	}
	
	@Test
	void testPronadjiKnjiguNaslov() {
		Knjiga k = new Knjiga();
		
		k.setIsbn(12345);
		k.setNaslov("Prohujalo sa vihorom");
		
		biblioteka.dodajKnjigu(k);
		
		Knjiga k2 = new Knjiga();
		
		k2.setIsbn(54321);
		k2.setNaslov("Gospodar prstenova");
		
		biblioteka.dodajKnjigu(k2);
	
		List<Knjiga> rezultat = biblioteka.pronadjiKnjigu(null, -1, "PRST", null);
		
		assertEquals(1, rezultat.size());
		assertTrue(rezultat.contains(k2));
	}
	
	@Test
	void testPronadjiViseKnjigaNaslov() {
		Knjiga k = new Knjiga();
		
		k.setIsbn(12345);
		k.setNaslov("Prohujalo sa vihorom");
		
		biblioteka.dodajKnjigu(k);
		
		Knjiga k2 = new Knjiga();
		
		k2.setIsbn(54321);
		k2.setNaslov("Gospodar prstenova");
		
		biblioteka.dodajKnjigu(k2);
	
		List<Knjiga> rezultat = biblioteka.pronadjiKnjigu(null, -1, "PR", null);
		
		assertEquals(2, rezultat.size());
		assertTrue(rezultat.contains(k));
		assertTrue(rezultat.contains(k2));
	}



}
