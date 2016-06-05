package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean creatFile(String userId, String fileName) throws RemoteException {
		File f = new File(userId + "_" + fileName);
		boolean flag = true;
		try {
			flag = f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File(userId + "_" + fileName);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		StringBuffer fileContent = new StringBuffer("");
		File f = new File(userId + "_" + fileName);
		FileReader fileReader = null;
		BufferedReader reader = null;
		try{
			fileReader = new FileReader(f);
			reader = new BufferedReader(fileReader);
			String m = null;
			while((m = reader.readLine()) != null){
				fileContent.append(m);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent.toString();
	}

	@Override
	public String readFileList(String userId) {
		String s = "";
		File temp = new File("temp");
		int length = temp.getAbsolutePath().length();
		File f = new File(temp.getAbsolutePath().substring(0, length - 5));
		File [] listOfFiles = f.listFiles();
		for(int i = 0; i < listOfFiles.length; i++){
			if(listOfFiles[i].isFile()){
				if(listOfFiles[i].getName().startsWith(userId + "_") && (listOfFiles[i].getName().length() > (userId.length() + 1))){
					s = s + listOfFiles[i].getName() + ",";
				}
			}
		}
		return s;
	}
	
}
