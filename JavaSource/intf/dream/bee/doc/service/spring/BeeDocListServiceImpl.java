package intf.dream.bee.doc.service.spring;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import intf.dream.bee.doc.dao.BeeDocListDAO;
import intf.dream.bee.doc.dto.BeeDocCommonDTO;
import intf.dream.bee.doc.service.BeeDocListService;

/**
 * serviceimpl
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeDocListServiceTarget"
 * @spring.txbn id="beeDocListService"
 * @spring.property name="beeDocListDAO" ref="beeDocListDAO"
 */
public class BeeDocListServiceImpl implements BeeDocListService {
	private BeeDocListDAO beeDocListDAO = null;

	public BeeDocListDAO getBeeDocListDAO() {
		return beeDocListDAO;
	}

	public void setBeeDocListDAO(BeeDocListDAO beeDocListDAO) {
		this.beeDocListDAO = beeDocListDAO;
	}

	public List findDocList(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception {
		return beeDocListDAO.findDocList(beeDocCommonDTO, map);
	}

	public List findDocCount(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception {
		return beeDocListDAO.findDocCount(beeDocCommonDTO, map);
	}
	public List findFileUrl(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception {
		
		map.put("docDataId", String.valueOf(map.get("docDataId")));
		
		List list = beeDocListDAO.findDocList(beeDocCommonDTO, map);
		
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
