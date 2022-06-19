public class BlockImpl implements Block {

    private String color;
    private String material;

    public BlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }

    @Override
    public String toString() {
        return "BlockImpl{" +
                "color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
