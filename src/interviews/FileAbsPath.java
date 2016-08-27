package interviews;
import java.util.Stack;

/**
 * Go Daddy interview question
 * Program to create absolute path for a given file path.
 * 
 * @author sumitkumar
 *
 */
class FileAbsPath {

	public static void main(String[] args) throws Exception {

		FileAbsPath f = new FileAbsPath();

		System.out.println(f.getAbsFilePath("/x/y/../z/../test"));
		System.out.println(f.getAbsFilePath("/x/y/../z/../c/././test"));
		System.out.println(f.getAbsFilePath("/x/y/../z/../c/../././test"));
	}

	private boolean isValidPathElement(String element) {

		// TODO A REGEX pattern to match for ., .., UNDERSCORE, ALPHANUMERIC

		return true;
	}

	public String getAbsFilePath(String dirPath) throws Exception {

		if (dirPath == null) {
			throw new IllegalArgumentException("File Path is null ");
		}

		// TODO Clean up the dirPAth to replace all \ with /

		String[] fPathArr = dirPath.split("/");
		Stack<String> resultStack = new Stack<String>();
		String element;

		for (int i = fPathArr.length - 1; i > 0; i--) {
			element = fPathArr[i];
			if (!isValidPathElement(element)) {
				throw new IllegalArgumentException("Invalid Path");
			}

			if (resultStack.isEmpty()) {
				resultStack.push(element);
				continue;
			}

			if ("..".equalsIgnoreCase(resultStack.peek())) {
				resultStack.pop();
				continue;
			}
			if (".".equalsIgnoreCase(resultStack.peek())) {
				resultStack.pop();
				resultStack.push(element);
			} else {
				resultStack.push(element);
			}
		}

		StringBuilder builder = new StringBuilder();
		while (!resultStack.isEmpty()) {
			builder.append("/").append(resultStack.pop());
		}

		return builder.toString();
	}

}
