package model.entities;

public class Path {

    private String pathSent;
    private String pathDestiny;

    public Path(String pathSent, String pathDestiny) {
        this.pathSent = pathSent;
        this.pathDestiny = pathDestiny;
    }

    public String getPathSent() {
        return pathSent;
    }
    public void setPathSent(String pathSent) {
        this.pathSent = pathSent;
    }
    public String getPathDestiny() {
        return pathDestiny;
    }
    public void setPathDestiny(String pathDestiny) {
        this.pathDestiny = pathDestiny;
    }
    
    @Override
    public String toString() {
        return pathSent+","+ pathDestiny;
    }

}
