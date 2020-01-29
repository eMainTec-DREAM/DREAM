package dream.work.pmi.list.service.spring;

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
import dream.work.pmi.list.dao.WorkPmiPointDetailDAO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;
import dream.work.pmi.list.service.WorkPmiPointDetailService;

/**
 * 점검작업 점검
 * @author kim2107
 * @version $Id: WorkPmiPointDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmiPointDetailServiceTarget"
 * @spring.txbn id="workPmiPointDetailService"
 * @spring.property name="workPmiPointDetailDAO" ref="workPmiPointDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */
public class WorkPmiPointDetailServiceImpl implements WorkPmiPointDetailService
{
    private WorkPmiPointDetailDAO workPmiPointDetailDAO = null;
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
    public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	public WorkPmiPointDetailDAO getWorkPmiPointDetailDAO() {
		return workPmiPointDetailDAO;
	}

	public void setWorkPmiPointDetailDAO(WorkPmiPointDetailDAO workPmiPointDetailDAO) {
		this.workPmiPointDetailDAO = workPmiPointDetailDAO;
	}

	public WorkPmiPointDetailDTO findDetail(String pminslistId, String pmInsPointId, String pmPointId, User user)throws Exception
    {
		WorkPmiPointDetailDTO workPmiPointDetailDTO = workPmiPointDetailDAO.findDetail(pminslistId, pmInsPointId, pmPointId, user);
		
		List resultList = FileUtil.makeSlideImg(workPmiPointDetailDTO.getPmPointId(), "PM_POINT");
		
		workPmiPointDetailDTO.setSlideFileList(resultList);
		
        return workPmiPointDetailDTO;
    }
    
	public int updateDetail(WorkPmiPointDetailDTO workPmiPointDetailDTO, WorkPmiCommonDTO workPmiCommonDTO, User user) throws Exception
    {        
		int rtnValue = 0;
		boolean inputFlag = false;
		
		if("".equals(workPmiPointDetailDTO.getPmInsPointId()) || workPmiPointDetailDTO.getPmInsPointId() == null )
		{
			workPmiPointDetailDTO.setPmInsPointId(workPmiPointDetailDAO.getNextSequence("SQAPMINSPOINT_ID"));
			inputFlag = true;
		}
		
		rtnValue += workPmiPointDetailDAO.updateDetail(workPmiPointDetailDTO, workPmiCommonDTO, inputFlag, user);
		
		return rtnValue;
    }

	@Override
	public List findSlideImage(WorkPmiPointDetailDTO workPmiPointDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException
	{
		return FileUtil.makeSlideImg(workPmiPointDetailDTO.getPmPointId(), "PM_POINT");
	}
}
