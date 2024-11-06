package medicamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.Medicamento;

public class MedicamentoTest {

    Medicamento medicamento;

    @BeforeEach
    public void inicializa() {
        medicamento = new Medicamento("Paracetamol", 10.50f, "500mg", "comprimido", "após as refeições", true);
    }

    @Test
    public void testCriaMedicamentoComNome() {
        Medicamento medicamentoComNome = new Medicamento("Ibuprofeno");
        assertEquals("Ibuprofeno", medicamentoComNome.getNome());
    }

    @Test
    public void testCriaMedicamentoComNomePrecoEspecificacoes() {
        Medicamento medicamentoEspecificado = new Medicamento("Dipirona", 5.75f, "1g");
        Assertions.assertAll("medicamentoEspecificado",
                () -> assertEquals("Dipirona", medicamentoEspecificado.getNome()),
                () -> assertEquals(5.75f, medicamentoEspecificado.getPreco()),
                () -> assertEquals("1g", medicamentoEspecificado.getEspecificacoes())
        );
    }

    @Test
    public void testCriaMedicamentoComNomeTipoCondicoesRestricao() {
        Medicamento medicamentoComRestricao = new Medicamento("Amoxicilina", "xarope", "a cada 8 horas", true);
        Assertions.assertAll("medicamentoComRestricao",
                () -> assertEquals("Amoxicilina", medicamentoComRestricao.getNome()),
                () -> assertEquals("xarope", medicamentoComRestricao.getTipoDoRemedio()),
                () -> assertEquals("a cada 8 horas", medicamentoComRestricao.getCondicoesDeUso()),
                () -> assertTrue(medicamentoComRestricao.isRestricao())
        );
    }

    @Test
    public void testToString() {
        String expected = "Paracetamol,10.5,500mg,comprimido,após as refeições,true";
        assertEquals(expected, medicamento.toString());
    }

    @Test
    public void testCompareTo() {
        Medicamento outroMedicamento = new Medicamento("Ibuprofeno");
        assertTrue(medicamento.compareTo(outroMedicamento) > 0);
        outroMedicamento.setNome("Paracetamol");
        assertEquals(0, medicamento.compareTo(outroMedicamento));
    }

    @Test
    public void assertionComHamcrestMatcher() {
        assertThat(medicamento.getPreco(), equalTo(10.50f));
        assertThat(medicamento.getNome(), notNullValue());
        assertThat(medicamento.getNome(), containsString("Paracetamol"));
        assertThat(medicamento, instanceOf(Medicamento.class));
    }
}

