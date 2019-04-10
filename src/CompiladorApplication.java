import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CompiladorApplication {

	public static void main(String args[]) throws IOException {

		Compilador compilador = new Compilador();
		StringBuilder codigo = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
			stream.forEach(s -> codigo.append(s).append("\n"));
		} catch (IOException e) {
			throw new IOException("Problema ao ler arquivo");
		}
		compilador.compilar(new Codigo(codigo.toString()));
	}

}
