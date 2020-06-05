import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;

class FileManager {
	
	public FileManager (){
		
		
	}
	
	
	static synchronized void fileDelete (String fileName) {
		
		File file = new File(fileName);
		
		try {	
			if(!file.exists()) throw new Exception();
			else file.delete();	
		} catch (Exception e) {
			System.out.println("c");
		}
	}
	
	static Vector<String> fileSearch (String filePath, String searchKeyword) {
		
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		Vector<String> searchedFiles = new Vector<String>();
		
		// to catch extension 
		Pattern pattern = Pattern.compile( ".+[.]" + searchKeyword ,Pattern.CASE_INSENSITIVE );
	
		for (File file : files) {
			if (file.isFile()) {
					// the file name matches extensions or has keyWord in String
					if(pattern.matcher(file.getName()).matches()
					||file.getName().contains(searchKeyword)){
						String f = file.getName();
						searchedFiles.add(f);
					}
			}
			else if(file.isDirectory()) {
				try {
					String d = file.getCanonicalPath().toString();
					new FileSearchPanel(searchKeyword, d);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return searchedFiles;
		
	}
	
	

}
