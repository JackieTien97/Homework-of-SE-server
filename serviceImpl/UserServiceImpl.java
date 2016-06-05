package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String username, String password) throws RemoteException {
		File temp = new File("temp");
		int length = temp.getAbsolutePath().length();
		File file = new File(temp.getAbsolutePath().substring(0, length - 5));
		File [] listOfFiles = file.listFiles();
		for(int i = 0; i < listOfFiles.length; i++){
			if(listOfFiles[i].isFile()){
				if(listOfFiles[i].getName().equals(username + "_")){
					try {		
						FileReader fileReader = new FileReader(listOfFiles[i]);
						BufferedReader reader = new BufferedReader(fileReader);
						try {
							if(reader.readLine().equals(password)){
								reader.close();
								return true;
							}
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

}
