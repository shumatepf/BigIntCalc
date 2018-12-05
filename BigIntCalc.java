public class BigIntCalc {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Please enter two integers");
			System.out.println("The first integer must be larger than the second, and the second must not be 0");
			return;
		}

		String a = args[0];
		String b = args[1];

		System.out.printf("ADD RESULT: %s\n", add(a, b));
		System.out.printf("SUBTRACT RESULT: %s\n", subtract(a, b));
		System.out.printf("MULTIPLY RESULT: %s\n", multiply(a, b));
		System.out.printf("DIVIDE RESULT: %s\n", divide(a, b));
	}

	/**
	 * Adds two integers (read as strings) together. a + b = c
	 * 
	 * @param str1 - a
	 * @param str2 - b
	 * @return result - c (as String)
	 */
	public static String add(String str1, String str2) {
		int[] bigAr;
		int[] smallAr;
		int[] sumAr;
		if (str1.length() >= str2.length()) {
			bigAr = strIntAr(str1);
			smallAr = strIntAr(str2);
		} else {
			bigAr = strIntAr(str2);
			smallAr = strIntAr(str1);
		}
		sumAr = arrPad(bigAr, 1);

		int rem = 0;

		for (int i = smallAr.length - 1, j = bigAr.length - 1; i >= 0; i--, j--) {
			int sum = smallAr[i] + bigAr[j] + rem;
			int digit = sum % 10;
			rem = sum / 10;
			sumAr[j + 1] = digit;
		}

		int remIndex = bigAr.length - smallAr.length;

		while (rem != 0 && remIndex >= 0) {
			int sum = bigAr[remIndex] + rem;
			int digit = sum % 10;
			rem = sum / 10;

			sumAr[remIndex] = digit;
			remIndex--;
		}
		sumAr[0] = rem;

		return removeZero(sumAr);
	}

	public static String subtract(String str1, String str2) {
		int[] intAr1 = strIntAr(str1);
		int[] intAr2 = strIntAr(str2);

		boolean borrow = false;

		for (int i = intAr1.length - 1, j = intAr2.length - 1; j >= 0; i--, j--) {
			int bI = i;
			intAr1[i] -= intAr2[j];
			while (intAr1[bI] < 0) {
				intAr1[bI] += 10;
				intAr1[--bI]--;
			}
		}
		return removeZero(intAr1);
	}

	public static String multiply(String str1, String str2) {
		int[] bigAr;
		int[] smallAr;
		int[] prodAr;

		if (str1.length() >= str2.length()) {
			bigAr = strIntAr(str1);
			smallAr = strIntAr(str2);
		} else {
			bigAr = strIntAr(str2);
			smallAr = strIntAr(str1);
		}
		prodAr = new int[smallAr.length + bigAr.length + 1];

		int n = prodAr.length - 1;

		for (int i = smallAr.length - 1; i >= 0; i--) {
			int rem = 0;
			int x = n;
			for (int j = bigAr.length - 1; j >= 0; j--) {
				int product = smallAr[i] * bigAr[j] + rem;
				int digit = product % 10;
				rem = product / 10;
				prodAr[x] += digit;
				if (prodAr[x] >= 10) {
					rem = prodAr[x] / 10;
					prodAr[x] %= 10;
				}
				x--;
			}
			if (rem != 0) {
				prodAr[x] += rem;
				rem = 0;
			}
			n--;
		}
		return removeZero(prodAr);
	}

	public static String divide(String str1, String str2) {
		int[] ar1 = strIntAr(str1);
		int[] ar2 = strIntAr(str2);

		int[] result = new int[ar1.length];
		int[] initDiv = new int[ar2.length];

		int divIndex = -1;

		for (int i = 0; i < ar2.length; i++) {
			initDiv[i] = ar1[i];
			divIndex++;
		}

		try {
			subtract(removeZero(initDiv), removeZero(ar1));
		} catch (ArrayIndexOutOfBoundsException e) {
			initDiv = new int[ar2.length + 1];

			for (int i = 0; i < ar2.length + 1; i++) {
				initDiv[i] = ar1[i];
			}
			divIndex++;
		}

		String initStr = removeZero(initDiv);

		for (int i = 0; i < ar1.length; i++) {
			int count = 0;

			String tempInit = new String(initStr);

			while (true) {
				try {
					tempInit = subtract(tempInit, removeZero(ar2));
					count++;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}

			result[divIndex++] = count;

			if (divIndex == result.length) {
				break;
			}
			String tempProd = multiply(count + "", str2);

			initStr = subtract(initStr, tempProd) + ar1[divIndex];
		}
		return removeZero(result);
	}

	/**
	 * Helper method for converting string to int array.
	 * 
	 * @param str - String to be converted
	 * @return int array of string param
	 */
	private static int[] strIntAr(String str) {
		char[] charAr = str.toCharArray();
		int[] intAr = new int[charAr.length];

		for (int i = 0; i < charAr.length; i++) {
			intAr[i] = Character.getNumericValue(charAr[i]);

		}
		return intAr;
	}

	/**
	 * Pads int array with set # of single zeros at beginning of array.
	 * 
	 * @param arr   - array to be padded
	 * @param zeros - # of zeros to be added to beginning of int array
	 * @return padded array
	 */
	private static int[] arrPad(int[] arr, int zeros) {
		int[] newArr = new int[arr.length + zeros];
		for (int i = 0; i < zeros; i++) {
			newArr[i] = 0;
		}
		for (int i = zeros, oldIndex = 0; oldIndex < arr.length; i++, oldIndex++) {
			newArr[i] = arr[oldIndex];
		}
		return newArr;
	}

	private static String removeZero(int[] arr) {
		String result = "";
		boolean zeros = true;
		for (int i = 0; i < arr.length; i++) {
			if (zeros && arr[i] == 0) {
				result += "";
			} else {
				result += arr[i];
				zeros = false;
			}
		}
		if (result.equals("")) {
			return "0";
		}
		return result;
	}
}

