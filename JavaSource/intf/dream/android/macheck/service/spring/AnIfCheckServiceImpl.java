package intf.dream.android.macheck.service.spring;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import intf.dream.android.macheck.dao.AnIfCheckDAO;
import intf.dream.android.macheck.service.AnIfCheckService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfCheckServiceTarget"
 * @spring.txbn id="anIfCheckService"
 * @spring.property name="anIfCheckDAO" ref="anIfCheckDAO"
 */
public class AnIfCheckServiceImpl implements AnIfCheckService
{
    private AnIfCheckDAO anIfCheckDAO = null;

	public AnIfCheckDAO getAnIfCheckDAO() {
		return anIfCheckDAO;
	}
	public void setAnIfCheckDAO(AnIfCheckDAO anIfCheckDAO) {
		this.anIfCheckDAO = anIfCheckDAO;
	}
	
	public List deviceCheck(Map map)
	{      
		return anIfCheckDAO.deviceCheck(map);
	}
	public List skinCheck(Map map)
	{      
		return anIfCheckDAO.skinCheck(map);
	}
	public List versionCheck(Map map)
	{      
		return anIfCheckDAO.versionCheck(map);
	}
	public List antVersionCheck(Map map)
	{      
		return anIfCheckDAO.antVersionCheck(map);
	}
	public List beeVersionCheck(Map map)
	{      
		return anIfCheckDAO.beeVersionCheck(map);
	}
	public List cricketVersionCheck(Map map)
	{      
		return anIfCheckDAO.cricketVersionCheck(map);
	}
	@Override
	public List logoCheck(Map map) throws InvalidKeyException, URISyntaxException, StorageException {
		List list = anIfCheckDAO.logoCheck(map);
		
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

