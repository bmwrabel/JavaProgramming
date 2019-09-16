
public class Main {
	
	public static void main(String[] args) {
		// create MVC objects
		Model model = new Model();
        GraphComponent graph = new GraphComponent(model);
        View view = new View(model, graph);
        Controller controller = new Controller(model, view);
	}	
}