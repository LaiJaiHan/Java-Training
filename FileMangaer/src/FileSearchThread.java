
class FileSearchThread extends Thread {
	
	String searchLocation, searchKeyword;
	FileSearchPanel fsPanel;

	public FileSearchThread(String searchKeyword, String searchLocation) {
		this.searchKeyword = searchKeyword;
		this.searchLocation = searchLocation;
	}
	
	public void run() {

		try{
			fsPanel = new FileSearchPanel(searchKeyword, searchLocation);
		} catch (Exception E){
			fsPanel.dispose();
		}
	
	}
	
}
