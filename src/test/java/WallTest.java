import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WallTest {

    private static final BlockImpl BLOCK1 = new BlockImpl("black", "mat1");
    private static final BlockImpl BLOCK2 = new BlockImpl("white", "mat2");
    private static final BlockImpl BLOCK3 = new BlockImpl("yellow", "mat3");
    private static final CompositeBlockImpl COMPOSITE_BLOCK1 = new CompositeBlockImpl("black", "mat1");
    private static final CompositeBlockImpl COMPOSITE_BLOCK2 = new CompositeBlockImpl("red", "mat2");
    private Wall wall1;
    private Wall wall2;
    private Wall wall3;

    @BeforeAll
    static void setUpObjects() {
        COMPOSITE_BLOCK1.addBlock(BLOCK1);
        COMPOSITE_BLOCK1.addBlock(BLOCK2);
        COMPOSITE_BLOCK1.addBlock(BLOCK3);
        COMPOSITE_BLOCK2.addBlock(BLOCK3);
        COMPOSITE_BLOCK1.addBlock(COMPOSITE_BLOCK2);


    }

    @BeforeEach
    void setUp() {
        wall1 = new Wall(BLOCK1, BLOCK2);
        wall2 = new Wall(BLOCK1, BLOCK2, COMPOSITE_BLOCK2);
        wall3 = new Wall(BLOCK1, BLOCK2, COMPOSITE_BLOCK1);
    }

    @Test
    public void findBlockByColorGivenNullColorShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { wall1.findBlockByColor(null);
        });
        assertEquals("Color is null", exception.getMessage());
    }

    @Test
    public void findBlockByColorShouldReturnEmptyOptional() {
        assertEquals(Optional.empty(), wall1.findBlockByColor("pink"));
    }

    @Test
    public void findBlockByColorGivenFlatStructureShouldReturnCorrectObject() {
        assertEquals(BLOCK1, wall1.findBlockByColor("black").get());
        assertEquals(BLOCK2, wall1.findBlockByColor("white").get());
    }

    @Test
    public void findBlockByColorGivenNestedStructureShouldReturnCorrectObject() {
        assertEquals(BLOCK3, wall2.findBlockByColor("yellow").get());
        assertEquals(BLOCK3, wall3.findBlockByColor("yellow").get());
    }

    @Test
    public void findBlocksByMaterialGivenNullMaterialShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { wall1.findBlocksByMaterial(null);
        });
        assertEquals("Material is null", exception.getMessage());
    }

    @Test
    public void findBlocksByMaterialShouldReturnEmptyList() {
        assertEquals(0, wall1.findBlocksByMaterial("mat4").size());
    }

    @Test
    public void findBlocksByMaterialGivenFlatListShouldReturnCorrectList() {
        List<Block> expectedList1 = new LinkedList<>();
        expectedList1.add(BLOCK2);

        assertEquals(expectedList1, wall1.findBlocksByMaterial("mat2"));
    }

    @Test
    public void findBlocksByMaterialGivenNestedListShouldReturnCorrectList() {
        List<Block> expectedList2 = new LinkedList<>();
        expectedList2.add(BLOCK3);
        expectedList2.add(BLOCK3);

        assertEquals(expectedList2, wall3.findBlocksByMaterial("mat3"));
    }

    @Test
    public void countGivenFlatListShouldReturnCorrectValue() {
        assertEquals(2, wall1.count());
    }

    @Test
    public void countGivenNestedListShouldReturnCorrectValue() {
        assertEquals(6, wall3.count());
    }

}
