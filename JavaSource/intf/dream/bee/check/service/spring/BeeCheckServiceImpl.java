package intf.dream.bee.check.service.spring;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import intf.dream.bee.check.dao.BeeCheckDAO;
import intf.dream.bee.check.service.BeeCheckService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeCheckServiceTarget"
 * @spring.txbn id="beeCheckService"
 * @spring.property name="beeCheckDAO" ref="beeCheckDAO"
 */
public class BeeCheckServiceImpl implements BeeCheckService
{
    private BeeCheckDAO beeCheckDAO = null;

	public BeeCheckDAO getBeeCheckDAO() {
		return beeCheckDAO;
	}
	public void setBeeCheckDAO(BeeCheckDAO beeCheckDAO) {
		this.beeCheckDAO = beeCheckDAO;
	}
	
	public List deviceCheck(Map map)
	{      
		return beeCheckDAO.deviceCheck(map);
	}
	public List skinCheck(Map map)
	{      
		return beeCheckDAO.skinCheck(map);
	}
	public List beeVersionCheck(Map map)
	{      
		return beeCheckDAO.beeVersionCheck(map);
	}
	@Override
	public List logoCheck(Map map) throws InvalidKeyException, URISyntaxException, StorageException {
		List list = beeCheckDAO.logoCheck(map);
		
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

