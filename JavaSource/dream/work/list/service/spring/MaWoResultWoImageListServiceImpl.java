package dream.work.list.service.spring;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

import common.bean.MwareConfig;
import common.bean.User;
import common.file.FileUploadUtil;
import common.util.DateUtil;
import common.util.FileUtil;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultWoImageListDTO;
import dream.work.list.service.MaWoResultWoImageListService;

/**
 * 작업결과 작업사진 목록
 * @author kim21017
 * @version $Id: MaWoResultWoImageListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultWoImageListServiceTarget"
 * @spring.txbn id="maWoResultWoImageListService"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */

public class MaWoResultWoImageListServiceImpl implements MaWoResultWoImageListService
{
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
	public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	@Override
	public List findSlideImage(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultWoImageListDTO maWoResultWoImageListDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException {
		return FileUtil.makeSlideImg(maWoResultMstrCommonDTO.getWkOrId(), "WORESULT",maWoResultWoImageListDTO.getSubImgType());
	}

	@Override
	public MaWoResultWoImageListDTO findImage(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException {
		List beforeImageList = FileUtil.makeSlideImg(maWoResultMstrCommonDTO.getWkOrId(), "WORESULT","BEFORE");
		List afterImageList = FileUtil.makeSlideImg(maWoResultMstrCommonDTO.getWkOrId(), "WORESULT","AFTER");
		
		MaWoResultWoImageListDTO maWoResultWoImageListDTO = new MaWoResultWoImageListDTO();
		maWoResultWoImageListDTO.setSlideBeforeFileList(beforeImageList);
		maWoResultWoImageListDTO.setSlideAfterFileList(afterImageList);
		
		return maWoResultWoImageListDTO;
	}

	@Override
	public String[][] getImageCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) {
		
		MaDocImgCommonDTO maDocImgCommonDTO = new MaDocImgCommonDTO();
		maDocImgCommonDTO.setObjectType("WORESULT");
		maDocImgCommonDTO.setObjectId(maWoResultMstrCommonDTO.getWkOrId());
		
		String totalCount = maDocImgDetailDAO.getImageCount(maDocImgCommonDTO, user);
		
		String[][] resultList = new String[1][1];
		resultList[0][0] = totalCount;
		
		return resultList;
	}
}

