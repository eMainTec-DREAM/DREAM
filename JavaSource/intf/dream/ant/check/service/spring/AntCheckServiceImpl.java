package intf.dream.ant.check.service.spring;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import intf.dream.ant.check.dao.AntCheckDAO;
import intf.dream.ant.check.service.AntCheckService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antCheckServiceTarget"
 * @spring.txbn id="antCheckService"
 * @spring.property name="antCheckDAO" ref="antCheckDAO"
 */
public class AntCheckServiceImpl implements AntCheckService
{
    private AntCheckDAO antCheckDAO = null;

	public AntCheckDAO getAntCheckDAO() {
		return antCheckDAO;
	}
	public void setAntCheckDAO(AntCheckDAO antCheckDAO) {
		this.antCheckDAO = antCheckDAO;
	}
	
	public List deviceCheck(Map map)
	{      
		return antCheckDAO.deviceCheck(map);
	}
	public List skinCheck(Map map)
	{      
		return antCheckDAO.skinCheck(map);
	}
	public List antVersionCheck(Map map)
	{      
		return antCheckDAO.antVersionCheck(map);
	}
	@Override
	public List logoCheck(Map map) throws InvalidKeyException, URISyntaxException, StorageException {
		List list = antCheckDAO.logoCheck(map);
		
		for (int i = 0; i < list.size(); i++) {
			Map listMap = (Map)list.get(i);
			
			String fileId = String.valueOf(listMap.get("IMGDATAID"));
			String filePath = String.valueOf(listMap.get("FILEPATH"));
			
			String originFile = MwareConfig.getFileDir() + filePath + File.separator + fileId;
			String targetFile = MwareConfig.getWebAppPath() + "dream"+File.separator+"android"+File.separator+"image"+File.separator+"" + fileId;
			
			try{
				File f = new File(targetFile);
				if (!f.isFile()) {
					FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream"+File.separator+"android"+File.separator+"image"+File.separator);
				}
			}catch (IOException e){}
		}
		
		return list;
	}
}

