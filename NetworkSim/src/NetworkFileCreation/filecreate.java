package NetworkFileCreation;

import java.io.*;
import java.util.Random;

public class filecreate

{
	public static void main(String arg[]) throws Exception {
		int j = 0, minutes = 0, seconds = 0;
		// Getting the output stream of the file for writing
		File f = new File("src/initFile.txt");
		FileOutputStream fos = new FileOutputStream(f);
		PrintWriter pw = new PrintWriter(fos);

		Random rand = new Random();
		for (int i = 0; i < 15; i++) {
			int x = rand.nextInt(10) + 1;
			int y = rand.nextInt(10) + 1;
			int z = i + 1;
			minutes = rand.nextInt(10 + j) + minutes;
			seconds = rand.nextInt(20 + j) + seconds;
			if (seconds > 60) {
				seconds = seconds % 60;
				minutes = minutes + seconds / 60;
			}

			pw.write(Integer.toString(z));
			pw.write('|');
			pw.write(Integer.toString(x));
			pw.write(',');
			pw.write(Integer.toString(y));
			pw.write('@');
			pw.write(Integer.toString(minutes));
			pw.write('.');
			pw.write(Integer.toString(seconds));
			pw.write("\n");

		}
		pw.flush();
		fos.close();
		pw.close();
	}
}