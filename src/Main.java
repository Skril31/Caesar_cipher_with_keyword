import java.util.Scanner;

public class Main {
    public static String makeAlph(String ALPHABET, int shift, String keyword){
        keyword = keyword.toUpperCase();
        StringBuilder UniqKeyword = new StringBuilder();
        // удаление повторяющихся символов в ключевом слове
        keyword.chars().distinct().forEach(c -> UniqKeyword.append((char) c));
        final String FinalUniqKeyword = UniqKeyword.toString();
        // удаление символов ключевого слова из алфавита
        String AlpahabetWithoutKeyword = ALPHABET.chars()
                .filter(ch -> FinalUniqKeyword.indexOf(ch) < 0)
                .collect(StringBuilder::new,
                        (buf, ch) -> buf.append((char) ch),
                        StringBuilder::append)
                .toString();
        // теперь делаем нужный алфавит
        StringBuilder ResultAlphabet = new StringBuilder();
        for (int i = AlpahabetWithoutKeyword.length() - shift; i<AlpahabetWithoutKeyword.length(); i++){
            ResultAlphabet.append(AlpahabetWithoutKeyword.charAt(i));
        }
        ResultAlphabet.append(UniqKeyword);
        for (int i = 0; i<AlpahabetWithoutKeyword.length()-shift; i++){
            ResultAlphabet.append(AlpahabetWithoutKeyword.charAt(i));
        }
        return ResultAlphabet.toString();
    }
    public static String encrypt(String text, String ALPHABET, int shift, String keyword) {
        StringBuilder encryptedText = new StringBuilder();
        String ResultAlphabet = makeAlph(ALPHABET, shift, keyword);
        //
        for (char ch: text.toCharArray()){
            if (ch == ' '){
                encryptedText.append(' ');
            }
            else {
                encryptedText.append(ResultAlphabet.toString().toCharArray()[ALPHABET.indexOf(ch)]);
            }
        }

        return encryptedText.toString();
    }


    public static String decrypt(String encryptedText, String ALPHABET, int shift, String keyword) {
        String ResAlph = makeAlph(ALPHABET, shift, keyword);
        StringBuilder decText = new StringBuilder();
        for (char ch: encryptedText.toCharArray()){
            if (ch == ' '){
                decText.append(' ');
            }
            else {
                decText.append(ALPHABET.charAt(ResAlph.indexOf(ch)));
            }
        }
        return decText.toString();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String EngAlph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String RuAlph = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String RuText = "КОТ";
        String RuKeyword = "ТЕЛЕВИЗОР";
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String keyword = "DADDY";
        String encText = encrypt(text, EngAlph, 5, keyword);
        System.out.println(encText);
        System.out.println(decrypt(encText, EngAlph, 5, keyword));
    }
}