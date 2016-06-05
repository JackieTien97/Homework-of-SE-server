//璇蜂笉瑕佷慨鏀规湰鏂囦欢鍚�
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 璇峰疄鐜拌鏂规硶
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		String result = "";
		byte[] array = new byte[2048];
		char[] cmd = this.abtainCmd(code);
		char[] input = param.toCharArray();
		int pointerOfCmd = 0;
		int pointerOfInput = 0;
		int pointerOfArray = 0;
		while(pointerOfCmd < cmd.length){
			
			if(cmd[pointerOfCmd] == '>'){
				pointerOfArray++;
			}
			else if(cmd[pointerOfCmd] == '<'){
				pointerOfArray--;
			}
			else if(cmd[pointerOfCmd] == '+'){
				array[pointerOfArray]++;
			}
			else if(cmd[pointerOfCmd] == '-'){
				array[pointerOfArray]--;
			}
			else if(cmd[pointerOfCmd] == '.'){
				char temp = (char)array[pointerOfArray];
				result = result + temp;
			}
			else if(cmd[pointerOfCmd] == ','){
				array[pointerOfArray] = (byte)input[pointerOfInput];
				pointerOfInput++;
			}
			else if(cmd[pointerOfCmd] == '['){
				if(array[pointerOfArray] == 0){
					int num = 0;
					pointerOfCmd++;
					while(!(cmd[pointerOfCmd] == ']' && num == 0)){
						if(cmd[pointerOfCmd] == '['){
							num++;
						}
						else if(cmd[pointerOfCmd] == ']'){
							num--;
						}
						pointerOfCmd++;
					}
				}
			}
			else if(cmd[pointerOfCmd] == ']'){
				if(array[pointerOfArray] != 0){
					int num = 0;
					pointerOfCmd--;
					while(!(cmd[pointerOfCmd] == '[' && num == 0)){
						if(cmd[pointerOfCmd] == ']'){
							num++;
						}
						else if(cmd[pointerOfCmd] == '['){
							num--;
						}
						pointerOfCmd--;
					}
				}
			}
			pointerOfCmd++;
		}
		
		return result;
	}
	
	public char[] abtainCmd(String code){
		char[] cmdOfTemp = code.toCharArray();
		StringBuffer s = new StringBuffer("");
		for(char i : cmdOfTemp){
			if(i == '>' || i == '<' || i == '+' || i == '-' || i == '.' || i == ',' || i == '[' || i == ']'){
				s.append(i);
			}
		}
		char[] cmd = s.toString().toCharArray();
		return cmd;
	}


}
