import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class CompiladorApplication {

	public static void main(String args[]) throws IOException {

		Compilador compilador = new Compilador();
		ArrayList<String> codigo = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
			//stream.forEach(s -> codigo.append(s).append("\n"));
			stream.forEach(s -> codigo.add(s));
		} catch (IOException e) {
			throw new IOException("Problema ao ler arquivo");
		}
		compilador.compilar(new Codigo(codigo));
	}

}
