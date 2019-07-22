import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

public class Main {
	
	public Main() {
		
		/*
		Path p = Paths.get("C:\\");
		String path = p.toString();
		
		File file = new File(path);
		File[] files = file.listFiles();
		
		System.out.println("List of files in " +path);
		for(File f : files) {
			System.out.println(f.toString());
		}
		*/
		
		//Path p = Paths.get("C:\\");
		//String path = p.toString();
		
		//File file = new File(path);
		
		JFileChooser chooser = new JFileChooser(); 
		
	    //chooser.setCurrentDirectory(file);
	    chooser.setDialogTitle("My Window");
	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    int returnVal = chooser.showOpenDialog(null);
	    
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		
		    File newFile = chooser.getSelectedFile();
		    String newPath = newFile.getPath();
		    
		    if(newFile.isDirectory()) {
		    	
		    	File[] files = newFile.listFiles();

				System.out.println("List of files an folders in " +newPath);
				System.out.println("-------------------------------------");
				
				for(File f : files) {
					System.out.println(f.toString());
				}
		    }
		    
		    else if(newFile.isFile()) {
		    	System.out.println("Stats for file " +newPath);
				System.out.println("-------------------------------------");
				System.out.println("Length: " + (newFile.length()/1024) + " kilobytes");
		    }
		    
			
		    
		    

		}
		
		else if(returnVal == JFileChooser.CANCEL_OPTION) {
			
			System.out.println("Cancel");
	    
		}

		
		
		//JFileChooser fc = new JFileChooser();
		//int returnVal = fc.showOpenDialog(path);
//		int returnVal = JFileChooser(path);
//		
//		if(returnVal == JFileChooser.APPROVE_OPTION ) {
//			File f = fc.getSelectedFile();
//			System.out.println(f.getAbsolutePath());
//		}
		
		
		
		
	}
	
	public void printFolder(File[] files) {
		

	}


	public static void main(String[] args) {
		new Main();
	}

}
