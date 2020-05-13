package verschluesselung;

import java.io.File; 
import java.io.FileReader; 
import java.io.IOException;
import java.util.Arrays; 

public class entschluesseln {
	
	private static String verNachricht = "";
	private static String verStringZahl = "";
	private static char [] buchstaben = new char [10];
	private static int [] buchstabenZahlen = new int [10];
	
	private static void buchstabenBefuellen() {
		buchstaben[0] = 'a';
		buchstaben[1] = 'b';
		buchstaben[2] = 'c';
		buchstaben[3] = 'd';
		buchstaben[4] = 'e';
		buchstaben[5] = 'f';
		buchstaben[6] = 'g';
		buchstaben[7] = 'h';
		buchstaben[8] = 'i';
		buchstaben[9] = 'j';
	}
	private static void buchstabenZahlenBefuellen() {
		for(int i = 0; i < buchstabenZahlen.length; i++) {
			buchstabenZahlen[i] = i;
		}
	}
	
    private static void ladeDatei(String datName) { 

        File datei = new File(datName); 

        if (!datei.canRead() || !datei.isFile()) System.exit(0); 

        FileReader fr = null; 
        int c; 
        StringBuffer buff = new StringBuffer(); 
        try { 
            fr = new FileReader(datei); 
            while ((c = fr.read()) != -1) { 
                buff.append((char) c); 
            } 
            fr.close(); 

        } catch (IOException e) { 
            e.printStackTrace(); 
        } 

        verNachricht = buff.toString();
        nachrichtUmwandeln(verNachricht);
    } 
	
	private static void nachrichtUmwandeln(String verNachricht) {
		char [] nachricht = verNachricht.toCharArray();
		for(int i = 0; i<nachricht.length; i++) {
			for(int j = 0; j<buchstaben.length; j++) {
				if(nachricht[i] == buchstaben[j]) {
					String s = Integer.toString(j);
					verStringZahl = verStringZahl + s;
				}
			}
		}
		codePruefen(verStringZahl);
	}
	private static void codePruefen(String verStringZahl) {
		char[] verCharNachricht = verStringZahl.toCharArray();
		int [] verIntNachricht = new int [verStringZahl.length()];
		int [] pruefVerIntNachricht = new int [verStringZahl.length()];
		int [] code = new int [verStringZahl.length()];
		for(int i = 0; i<verStringZahl.length(); i++) {
			verIntNachricht[i] = verStringZahl.charAt(i) - '0';
		}
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				for(int x = 0; x < 10; x++) {
					for(int y = 0; y < 10; y++) {
						for(int l = 0; l < verIntNachricht.length-3; l = l+4) {
						code[l] = i;
						code[l+1] = j;
						code[l+2] = x;
						code[l+3] = y;
						}
						for(int k = 0; k < verIntNachricht.length; k++) {
							if(verIntNachricht[k] < code[k]) {
								pruefVerIntNachricht[k] = (verIntNachricht[k] +10) - code[k];
							}else {
								pruefVerIntNachricht[k] = verIntNachricht[k] - code[k];
							}
						}
						aaaPruefung(pruefVerIntNachricht);
					}
				}
			}
		}
	}
	
	private static void aaaPruefung(int [] pruefVerIntNachricht) {
		int z = 0;
		for (int i = 0; i < pruefVerIntNachricht.length -2; i++) {
			if(pruefVerIntNachricht[i] == 0 && pruefVerIntNachricht[i+1] == 0 && pruefVerIntNachricht[i+2] == 0) z++;
		}
		if(z > 0) zahlenUmwandeln(pruefVerIntNachricht);
	} 
	
	private static void zahlenUmwandeln(int [] pruefVerIntNachricht) {
		String entNachricht = "";
		for(int i = 0; i < pruefVerIntNachricht.length; i++) {
			for(int j = 0; j < buchstabenZahlen.length; j++) {
				if(pruefVerIntNachricht[i] == buchstabenZahlen[j]) entNachricht = entNachricht + buchstaben[j];
			}
		}
		System.out.println(entNachricht);
	}

	public static void main(String[] args) {
		buchstabenBefuellen();
		buchstabenZahlenBefuellen();
		//ladeDatei();
	}
}
