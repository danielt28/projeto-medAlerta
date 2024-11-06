package endereco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.Endereco;

public class EnderecoTest {

    Endereco endereco;

    @BeforeEach
    public void inicializa() {
        endereco = new Endereco("Avenida Paulista", "1000", "Apto 101", "Bela Vista", "São Paulo", "SP", "Brasil", "01311-200");
    }

    @Test
    public void testCriaEnderecoComNomeRuaENumero() {
        Endereco enderecoSimples = new Endereco("Rua da Consolação", "200");
        assertEquals("Rua da Consolação", enderecoSimples.getNomeDaRua());
        assertEquals("200", enderecoSimples.getNumero());
    }

    @Test
    public void testCriaEnderecoComNomeRuaNumeroEComplemento() {
        Endereco enderecoComComplemento = new Endereco("Rua Augusta", "300", "Apto 202");
        Assertions.assertAll("enderecoComComplemento",
                () -> assertEquals("Rua Augusta", enderecoComComplemento.getNomeDaRua()),
                () -> assertEquals("300", enderecoComComplemento.getNumero()),
                () -> assertEquals("Apto 202", enderecoComComplemento.getComplemento())
        );
    }

    @Test
    public void testCriaEnderecoCompleto() {
        Endereco enderecoCompleto = new Endereco("Rua Haddock Lobo", "400", "Apto 303", "Cerqueira César", "São Paulo", "SP", "Brasil", "01414-001");
        Assertions.assertAll("enderecoCompleto",
                () -> assertEquals("Rua Haddock Lobo", enderecoCompleto.getNomeDaRua()),
                () -> assertEquals("400", enderecoCompleto.getNumero()),
                () -> assertEquals("Apto 303", enderecoCompleto.getComplemento()),
                () -> assertEquals("Cerqueira César", enderecoCompleto.getNomeDoBairro()),
                () -> assertEquals("São Paulo", enderecoCompleto.getNomeDaCidade()),
                () -> assertEquals("SP", enderecoCompleto.getNomeDoEstado()),
                () -> assertEquals("Brasil", enderecoCompleto.getNomeDoPais()),
                () -> assertEquals("01414-001", enderecoCompleto.getCep())
        );
    }

    @Test
    public void testToString() {
        String expected = "Avenida Paulista/1000/Apto 101/Bela Vista/São Paulo/SP/Brasil/01311-200";
        assertEquals(expected, endereco.toString());
    }

    @Test
    public void testToStringFront() {
        String expected = "Avenida Paulista,1000,Apto 101,Bela Vista,São Paulo,SP,Brasil,01311-200";
        assertEquals(expected, endereco.toStringFront());
    }

    @Test
    public void testStringToEndereco() {
        String enderecoStr = "Rua das Flores/123/Bloco A/Centro/Rio de Janeiro/RJ/Brasil/22290-000";
        Endereco enderecoConvertido = Endereco.stringToEndereco(enderecoStr);

        Assertions.assertAll("enderecoConvertido",
                () -> assertEquals("Rua das Flores", enderecoConvertido.getNomeDaRua()),
                () -> assertEquals("123", enderecoConvertido.getNumero()),
                () -> assertEquals("Bloco A", enderecoConvertido.getComplemento()),
                () -> assertEquals("Centro", enderecoConvertido.getNomeDoBairro()),
                () -> assertEquals("Rio de Janeiro", enderecoConvertido.getNomeDaCidade()),
                () -> assertEquals("RJ", enderecoConvertido.getNomeDoEstado()),
                () -> assertEquals("Brasil", enderecoConvertido.getNomeDoPais()),
                () -> assertEquals("22290-000", enderecoConvertido.getCep())
        );
    }

    @Test
    public void assertionComHamcrestMatcher() {
        assertThat(endereco.getNomeDaRua(), containsString("Avenida Paulista"));
        assertThat(endereco.getNumero(), notNullValue());
        assertThat(endereco.getCep(), equalTo("01311-200"));
        assertThat(endereco, instanceOf(Endereco.class));
    }
}

