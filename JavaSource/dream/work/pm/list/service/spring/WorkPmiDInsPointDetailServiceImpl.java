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
import dream.work.pm.list.dao.WorkPmiDInsPointDetailDAO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsPointDetailDTO;
import dream.work.pm.list.service.WorkPmiDInsPointDetailService;

/**
 * WorkPmiDInsPoint Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiDInsPointDetailServiceTarget"
 * @spring.txbn id="workPmiDInsPointDetailService"
 * @spring.property name="workPmiDInsPointDetailDAO" ref="workPmiDInsPointDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */
public class WorkPmiDInsPointDetailServiceImpl implements WorkPmiDInsPointDetailService
{
    private WorkPmiDInsPointDetailDAO workPmiDInsPointDetailDAO = null;
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
    public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	public WorkPmiDInsPointDetailDTO findDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception
    {
		WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO = workPmiDInsPointDetailDAO.findDetail(workPmiDInsCommonDTO, user);
		
		List resultList = FileUtil.makeSlideImg(workPmiDInsPointDetailDTO.getPmPointId(), "PM_POINT");
		
		workPmiDInsPointDetailDTO.setSlideFileList(resultList);
		
        return workPmiDInsPointDetailDTO;
    }
    
    public int insertDetail(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception
    {
         return workPmiDInsPointDetailDAO.insertDetail(workPmiDInsPointDetailDTO, user);
    }
    
    public int updateDetail(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception
    {
    	int rtnValue = 0;
    	
        String id = "";
        
        id = workPmiDInsPointDetailDAO.getId(workPmiDInsPointDetailDTO, user);
        
        workPmiDInsPointDetailDTO.setEqAsmbId(id);
        
        rtnValue+=workPmiDInsPointDetailDAO.updateDetail(workPmiDInsPointDetailDTO, user);
        
        return rtnValue;
    }

    public WorkPmiDInsPointDetailDAO getWorkPmiDInsPointDetailDAO() {
        return workPmiDInsPointDetailDAO;
    }

    public void setWorkPmiDInsPointDetailDAO(WorkPmiDInsPointDetailDAO workPmiDInsPointDetailDAO) {
        this.workPmiDInsPointDetailDAO = workPmiDInsPointDetailDAO;
    }
    
    public String getId(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception
    {
        return workPmiDInsPointDetailDAO.getId(workPmiDInsPointDetailDTO, user);
    }

	@Override
	public List findSlideImage(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException {
		return FileUtil.makeSlideImg(workPmiDInsPointDetailDTO.getPmPointId(), "PM_POINT");
	}
	
}
