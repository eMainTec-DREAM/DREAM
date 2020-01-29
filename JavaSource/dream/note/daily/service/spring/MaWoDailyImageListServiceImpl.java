package dream.note.daily.service.spring;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import common.util.FileUtil;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyImageListDTO;
import dream.note.daily.service.MaWoDailyImageListService;

/**
 * 일일작업승인 사진 목록
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="maWoDailyImageListServiceTarget"
 * @spring.txbn id="maWoDailyImageListService"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */

public class MaWoDailyImageListServiceImpl implements MaWoDailyImageListService
{
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
	public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	@Override
	public List findSlideImage(MaWoDailyCommonDTO maWoDailyCommonDTO, MaWoDailyImageListDTO maWoDailyImageListDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException {
		return FileUtil.makeSlideImg(maWoDailyCommonDTO.getWoDayListId() , "WODAY",maWoDailyImageListDTO.getSubImgType());
	}

	@Override
	public MaWoDailyImageListDTO findImage(MaWoDailyCommonDTO maWoDailyCommonDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException {
		List afterImageList = FileUtil.makeSlideImg(maWoDailyCommonDTO.getWoDayListId() , "WODAY", "APPRSIGN");
		
		MaWoDailyImageListDTO maWoDailyImageListDTO = new MaWoDailyImageListDTO();
		maWoDailyImageListDTO.setSlideAfterFileList(afterImageList);
		
		return maWoDailyImageListDTO;
	}

	@Override
	public String[][] getImageCount(MaWoDailyCommonDTO maWoDailyCommonDTO, User user) {
		
		MaDocImgCommonDTO maDocImgCommonDTO = new MaDocImgCommonDTO();
		maDocImgCommonDTO.setObjectType("WODAY");
		maDocImgCommonDTO.setObjectId(maWoDailyCommonDTO.getWoDayListId());
		
		String totalCount = maDocImgDetailDAO.getImageCount(maDocImgCommonDTO, user);
		
		String[][] resultList = new String[1][1];
		resultList[0][0] = totalCount;
		
		return resultList;
	}
}

