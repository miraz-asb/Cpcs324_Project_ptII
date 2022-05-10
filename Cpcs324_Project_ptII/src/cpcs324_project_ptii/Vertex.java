package cpcs324_project_ptii;

public class Vertex {

    private char label;//char lable
    private int labelI;//int lable
    private final char labels[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();// araay of cahr labels to denote vertex position 
    private boolean isVisited = false;
    private int position;

    //if the lable is int , it will be the poistion as well
    public Vertex(int position) {
        this.position = position;
        this.labelI = position;
    }
    
    //if lable is char, invoce getVertPos to find the position
    public Vertex(char c){
        this.position = getVertPos(c);
        this.label = c;
    }

    public int getLabelI() {
        return labelI;
    }

    public void setLabelI(int labelI) {
        this.labelI = labelI;
    }

    
    public void setLable(char label) {
        this.label = label;
    }

    public boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * check if the char label of vertex is exist in labels array
     *
     * @param label_c
     * @return
     */
    private int getVertPos(char label_c) {
        //loop and if label are found retrun its index 
        //if not found retun -1
        for (int i = 0; i < labels.length; i++) {
            if (labels[i] == label_c) {
                return i;
            }
        }
        return -1;
    }

}
