package parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Error: No web adress given");
			System.exit(-1);
		}
		
		String move = "";
		for (int i = 0; i < args.length; i++) {
			if (i > 0) {
				move = move + "_";
			}
			move = move + args[i];
		}
		
		String url = "https://www.smogon.com/dex/sm/moves/" + move + "/";
		Parser parser = new Parser(url);
		
		parser.fetch();
		parser.parseContent();
		
		File dir = new File("output/");
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		printToFile("output/" + move + ".txt", parser.getContent());
		printToFile("output/" + move + "_forms.txt", parser.getContent2());
		System.out.println("Wrote to file");
		System.out.println("Learned by " + parser.getContent().size() + " Pokemon");
		System.out.println("as well as " + parser.getContent2().size() + " special forms");
		
	}
	
	public static void printToFile(String filepath, ArrayList<String> strings) {
		
		File file = new File(filepath);
		
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF8"))) {
			for (int i = 0; i < strings.size(); i++) {
				if (i > 0) {
					out.append(",");
				}
				out.append(strings.get(i));
			}
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
