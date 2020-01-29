package dream.work.pm.list.service.spring;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
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
import dream.work.pm.list.dao.MaPmMstrPointDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.service.MaPmMstrPointDetailService;

/**
 * 작업결과 검사항목
 * @author jung7126
 * @version $Id: MaPmMstrPointDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmMstrPointDetailServiceTarget"
 * @spring.txbn id="maPmMstrPointDetailService"
 * @spring.property name="maPmMstrPointDetailDAO" ref="maPmMstrPointDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */
public class MaPmMstrPointDetailServiceImpl implements MaPmMstrPointDetailService
{
    private MaPmMstrPointDetailDAO maPmMstrPointDetailDAO = null;
    
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
    public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	public MaPmMstrPointDetailDAO getMaPmMstrPointDetailDAO() {
		return maPmMstrPointDetailDAO;
	}

	public void setMaPmMstrPointDetailDAO(MaPmMstrPointDetailDAO maPmMstrPointDetailDAO) {
		this.maPmMstrPointDetailDAO = maPmMstrPointDetailDAO;
	}

	public MaPmMstrPointDetailDTO findDetail(String wkOrId, String pmPointId, User user)throws Exception
    {
		List resultList = FileUtil.makeSlideImg(pmPointId, "PM_POINT"); 

		MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointDetailDAO.findDetail(wkOrId, pmPointId, user);
		
		maPmMstrPointDetailDTO.setSlideFileList(resultList);
		
        return maPmMstrPointDetailDTO;
    }
    
	public int updateDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception
    {        
        return maPmMstrPointDetailDAO.updateDetail(maPmMstrPointDetailDTO, maPmMstrMstrCommonDTO, user);
    }
	public int insertDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception
    {        
        return maPmMstrPointDetailDAO.insertDetail( maPmMstrPointDetailDTO, maPmMstrMstrCommonDTO, user);
    }

	@Override
	public int insertLovDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception {
		 return maPmMstrPointDetailDAO.insertLovDetail(maPmMstrPointDetailDTO, maPmMstrMstrCommonDTO, user);
	}

	@Override
	public List findSlideImage(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException
	{
        return FileUtil.makeSlideImg(maPmMstrPointDetailDTO.getPmPointId(), "PM_POINT");
    }
}
