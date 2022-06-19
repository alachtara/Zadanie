import java.util.LinkedList;
import java.util.List;

public class CompositeBlockImpl extends BlockImpl implements CompositeBlock  {

    private List<Block> blocks;

    public CompositeBlockImpl( String color, String material) {
        super(color, material);
        this.blocks = new LinkedList<>();
        }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }

    @Override
    public String toString() {
        return "CompositeBlockImpl{" +
                "blocks=" + blocks +
                '}';
    }
}
