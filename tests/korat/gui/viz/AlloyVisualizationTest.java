package korat.gui.viz;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import edu.mit.csail.sdg.alloy4viz.AlloyInstance;
import edu.mit.csail.sdg.alloy4viz.AlloyRelation;
import edu.mit.csail.sdg.alloy4viz.StaticInstanceReader;
import edu.mit.csail.sdg.alloy4viz.VizState;
import junit.framework.TestCase;

public class AlloyVisualizationTest extends TestCase {
    public static class Node {
        Node next;
        int value;
    }

    public void testInstanceAndThemeLoadInAlloy6() throws Exception {
        Node first = new Node();
        first.value = -3;
        first.next = new Node();
        first.next.value = 7;
        first.next.next = first;
        ByteArrayOutputStream xml = new ByteArrayOutputStream();
        ByteArrayOutputStream theme = new ByteArrayOutputStream();
        new ToXMLInstanceConverter().convert(first,
                new PrintStream(xml, true, StandardCharsets.UTF_8),
                new PrintStream(theme, true, StandardCharsets.UTF_8));
        AlloyInstance instance = StaticInstanceReader.parseInstance(
                new StringReader(xml.toString(StandardCharsets.UTF_8)), 0);
        int nextTuples = 0;
        int valueTuples = 0;
        for (AlloyRelation relation : instance.model.getRelations()) {
            if (relation.getName().equals("next"))
                nextTuples += instance.relation2tuples(relation).size();
            if (relation.getName().equals("value"))
                valueTuples += instance.relation2tuples(relation).size();
        }
        assertEquals(2, nextTuples);
        assertEquals(2, valueTuples);
        Path themeFile = Files.createTempFile("korat-theme-", ".thm");
        try {
            Files.write(themeFile, theme.toByteArray());
            VizState state = new VizState(instance);
            state.loadPaletteXML(themeFile.toString());
            assertTrue(state.useOriginalName());
        } finally {
            Files.deleteIfExists(themeFile);
        }
    }
}
