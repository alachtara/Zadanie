import java.util.*;
import java.util.stream.Collectors;


public class Wall implements Structure {

    private final List <Block> blocks = new LinkedList<>();

    public Wall(Block... blocks) {
        this.blocks.addAll(Arrays.asList(blocks));
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if(color == null) {
            throw new IllegalArgumentException("Color is null");
        }

        List<Block> flattenBlocks =  flattenCompositeBlock(this.getBlocks());
        return flattenBlocks.stream().filter(b -> color.equals(b.getColor())).findFirst();
    }


    private List<Block> flattenCompositeBlock(List<Block> blocks) {

        List<Block> flattenBlocks = blocks.stream().map(block -> {
            return (block instanceof CompositeBlock) ? flattenCompositeBlock(((CompositeBlock) block).getBlocks()) : List.of(block);
        }).flatMap(Collection::stream).collect(Collectors.toList());

        return flattenBlocks;
    }


    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if(material == null) {
            throw new IllegalArgumentException("Material is null");
        }

        List<Block> flattenBlocks =  flattenCompositeBlock(this.getBlocks());
        return flattenBlocks.stream().filter(b -> material.equals(b.getMaterial())).collect(Collectors.toList());
    }

    @Override
    public int count() {
        List<Block> flattenBlocks =  flattenCompositeBlock(this.getBlocks());
        return flattenBlocks.size();
    }

    @Override
    public String toString() {
        return "Wall{" +
                "blocks=" + blocks +
                '}';
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
