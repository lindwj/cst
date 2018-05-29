import com.alibaba.fastjson.JSON;
import com.cst.service.model.Kdn;
import com.cst.service.util.Common;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Kdn k=new  Kdn();
System.out.println(Common.objectToJsonStr(k));

System.out.println(JSON.toJSON(Common.objectToJsonStr(k)));
	}

}
