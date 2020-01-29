package intf.dream.android.online.doc.service.spring;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import intf.dream.android.online.doc.dao.AnOnDocListDAO;
import intf.dream.android.online.doc.service.AnOnDocListService;

/**
 * serviceimpl
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnDocListServiceTarget"
 * @spring.txbn id="anOnDocListService"
 * @spring.property name="anOnDocListDAO" ref="anOnDocListDAO"
 */
public class AnOnDocListServiceImpl implements AnOnDocListService {
	private AnOnDocListDAO anOnDocListDAO = null;

	public AnOnDocListDAO getAnOnDocListDAO() {
		return anOnDocListDAO;
	}

	public void setAnOnDocListDAO(AnOnDocListDAO anOnDocListDAO) {
		this.anOnDocListDAO = anOnDocListDAO;
	}

	public List findDocList(Map map) throws Exception {
		return anOnDocListDAO.findDocList(map);
	}
	public List findFileUrl(Map map) throws Exception {
		
		map.put("docDataId", String.valueOf(map.get("docDataId")));
		
		List list = anOnDocListDAO.findDocList(map);
		
		for (int i = 0; i < list.size(); i++) {
			Map listMap = (Map)list.get(i);
			
			String fileId = String.valueOf(listMap.get("DOCDATA_ID"));
			String filePath = String.valueOf(listMap.get("NF_FILE_PATH"));
			
			String originFile = MwareConfig.getFileDir() + filePath + File.separator + fileId;
			String targetFile = MwareConfig.getWebAppPath() + "dream"+File.separator+"android"+File.separator+"" + fileId;
			
			try{
				File f = new File(targetFile);
				if (!f.isFile()) {
					FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream"+File.separator+"android"+File.separator);
				}
			}catch (IOException e){}
		}
		
		return list;
	}

}
