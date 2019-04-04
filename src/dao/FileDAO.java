package dao;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface FileDAO {
	public int insertAddressList(List<Map<String, String>> addrList);
}
