import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * tail - output the last part of files.
 * 
 */

/**
 * @author
 *
 */
public class SimpleTail {

	private static final String LINE_OPTION = "-n";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			printHelpMessage();
		} else {
			printTailList(args);
		}

	}

	private static void printTailList(String[] args) {
		List<String> list = tail(args);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}

	/**
	 * This will tail the passed file.
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static List<String> tail(String[] args) {
		String fileName = args[args.length - 1];
		int numberOfLines = checkArgument(args);
		List<String> tailList = new ArrayList<String>();

		// if all arguments are valid, it will print last "numberOfLines".
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					new ReverseLineInputStream(new File(fileName))));
			while (numberOfLines > 0) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				tailList.add(0,line);
				numberOfLines--;
			}
		} catch (Throwable e) {			
			e.printStackTrace();
		} finally {
			if ( in != null) {
				try {
					in.close();
				} catch (IOException ignore) {
					
				}
			}
		}

		return tailList;

	}

	/**
	 * 
	 * @param args
	 *            command line arguments
	 * @return number of lines from the end of file have to read.
	 */
	private static int checkArgument(String[] args) {
		// if -n option is not there.
		// By default it will read last 10 lines, if available.Ï
		int numberOfLines = 10;
		if (args.length > 1) {
			if (LINE_OPTION.equals(args[0]) && args.length > 2) {
				try {
					numberOfLines = Integer.parseInt(args[1]);
					if (numberOfLines < 0) {
						System.err.println("Invalid argument : line number "
								+ args[1] + " must be greater than 0.");
						printHelpMessage();
						
					}
				} catch (NumberFormatException nfe) {
					System.err.println("Invalid argument : line number "
							+ args[1] + " is not a valid number.");
					printHelpMessage();
					
				}
			} else {
				System.err.println("Invalid argument");
				printHelpMessage();
				
			}
		}
		return numberOfLines;
	}

	/**
	 * This will print help message how to use this program.
	 */
	private static void printHelpMessage() {
		System.out.println("usage: SimpleTail [-n lines] file");
	}

	
}
