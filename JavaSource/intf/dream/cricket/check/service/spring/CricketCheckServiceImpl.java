package intf.dream.cricket.check.service.spring;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import intf.dream.cricket.check.dao.CricketCheckDAO;
import intf.dream.cricket.check.service.CricketCheckService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketCheckServiceTarget"
 * @spring.txbn id="cricketCheckService"
 * @spring.property name="cricketCheckDAO" ref="cricketCheckDAO"
 */
public class CricketCheckServiceImpl implements CricketCheckService
{
    private CricketCheckDAO cricketCheckDAO = null;

	public CricketCheckDAO getCricketCheckDAO() {
		return cricketCheckDAO;
	}
	public void setCricketCheckDAO(CricketCheckDAO cricketCheckDAO) {
		this.cricketCheckDAO = cricketCheckDAO;
	}
	
	public List deviceCheck(Map map)
	{      
		return cricketCheckDAO.deviceCheck(map);
	}
	public List skinCheck(Map map)
	{      
		return cricketCheckDAO.skinCheck(map);
	}
	public List cricketVersionCheck(Map map)
	{      
		return cricketCheckDAO.cricketVersionCheck(map);
	}
	@Override
	public List logoCheck(Map map) throws InvalidKeyException, URISyntaxException, StorageException {
		List list = cricketCheckDAO.logoCheck(map);
		
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

