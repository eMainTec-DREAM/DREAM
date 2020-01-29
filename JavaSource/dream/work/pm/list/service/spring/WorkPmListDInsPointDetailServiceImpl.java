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
import dream.work.pm.list.dao.WorkPmListDInsPointDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;
import dream.work.pm.list.service.WorkPmListDInsPointDetailService;

/**
 * WorkPmListDInsPoint Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmListDInsPointDetailServiceTarget"
 * @spring.txbn id="workPmListDInsPointDetailService"
 * @spring.property name="workPmListDInsPointDetailDAO" ref="workPmListDInsPointDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */
public class WorkPmListDInsPointDetailServiceImpl implements WorkPmListDInsPointDetailService
{
    private WorkPmListDInsPointDetailDAO workPmListDInsPointDetailDAO = null;
    
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
    public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	public WorkPmListDInsPointDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {
		List resultList = FileUtil.makeSlideImg(maPmMstrCommonDTO.getPmPointId(), "PM_POINT");

		WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO = workPmListDInsPointDetailDAO.findDetail(maPmMstrCommonDTO, user);
		
		workPmListDInsPointDetailDTO.setSlideFileList(resultList);
		
        return workPmListDInsPointDetailDTO;
    }
    
    public int insertDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, User user) throws Exception
    {
         return workPmListDInsPointDetailDAO.insertDetail(workPmListDInsPointDetailDTO, user);
    }
    
    public int updateDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, User user) throws Exception
    {
         return workPmListDInsPointDetailDAO.updateDetail(workPmListDInsPointDetailDTO, user);
    }

    public WorkPmListDInsPointDetailDAO getWorkPmListDInsPointDetailDAO() {
        return workPmListDInsPointDetailDAO;
    }

    public void setWorkPmListDInsPointDetailDAO(WorkPmListDInsPointDetailDAO workPmListDInsPointDetailDAO) {
        this.workPmListDInsPointDetailDAO = workPmListDInsPointDetailDAO;
    }

	@Override
	public int insertLovDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception {
		
		return workPmListDInsPointDetailDAO.insertLovDetail(workPmListDInsPointDetailDTO, maPmMstrCommonDTO, user);
	}
    
	@Override
	public List findSlideImage(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException
	{
        return FileUtil.makeSlideImg(workPmListDInsPointDetailDTO.getPmPointId(), "PM_POINT");
    }
	
}
