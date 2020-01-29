package dream.work.pm.list.service.spring;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import common.util.FileUtil;
import dream.work.pm.list.dao.MaPmMstrDetailDAO;
import dream.work.pm.list.dao.WorkPmListEquipDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.service.WorkPmListEquipDetailService;

/**
 * ���漳��
 * @author kim21017
 * @version $Id: WorkPmListEquipDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmListEquipDetailServiceTarget"
 * @spring.txbn id="workPmListEquipDetailService"
 * @spring.property name="workPmListEquipDetailDAO" ref="workPmListEquipDetailDAO"
 * @spring.property name="maPmMstrDetailDAO" ref="maPmMstrDetailDAO"
 */
public class WorkPmListEquipDetailServiceImpl implements WorkPmListEquipDetailService
{
    private WorkPmListEquipDetailDAO workPmListEquipDetailDAO = null;
    private MaPmMstrDetailDAO maPmMstrDetailDAO = null;
    
    
    public MaPmMstrDetailDAO getMaPmMstrDetailDAO() {
		return maPmMstrDetailDAO;
	}

	public void setMaPmMstrDetailDAO(MaPmMstrDetailDAO maPmMstrDetailDAO) {
		this.maPmMstrDetailDAO = maPmMstrDetailDAO;
	}

	public WorkPmListEquipDetailDAO getWorkPmListEquipDetailDAO() {
		return workPmListEquipDetailDAO;
	}

	public void setWorkPmListEquipDetailDAO(WorkPmListEquipDetailDAO workPmListEquipDetailDAO) {
		this.workPmListEquipDetailDAO = workPmListEquipDetailDAO;
	}

	public WorkPmListEquipDetailDTO findDetail(String pmId, String pmEquipId, User user)throws Exception
    {
		List resultList = FileUtil.makeSlideImg(pmEquipId, "PMWMSTR"); 
		
		WorkPmListEquipDetailDTO workPmListEquipDetailDTO = workPmListEquipDetailDAO.findDetail(pmId, pmEquipId, user);
		
		workPmListEquipDetailDTO.setSlideFileList(resultList);
		
        return workPmListEquipDetailDTO;
    }
    
	public int updateDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {        
        int rtnValue = 0;
        rtnValue = workPmListEquipDetailDAO.updateDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
        if("PMC".equals(maPmMstrCommonDTO.getWoType()))
        {
        	workPmListEquipDetailDAO.mergeEqPmCycle(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
        }
        
        if ("N".equals(workPmListEquipDetailDTO.getIsActive())){
        	//��� ������ ������.
        	workPmListEquipDetailDAO.deletePmSchedulePmEQuip(user.getCompNo(),workPmListEquipDetailDTO.getPmEquipId());
        	
        }else if("Y".equals(workPmListEquipDetailDTO.getIsActive())){
        	
        	if(!workPmListEquipDetailDTO.getOldInitWrkDate().replaceAll("-", "").equals(workPmListEquipDetailDTO.getInitWrkDate().replaceAll("-", ""))){
        		// �������ڸ� ���������Ƿ� ������ ��¥�� �����ؾ� ��.
        		rtnValue = workPmListEquipDetailDAO.updateLastSchedDate(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
        		//�������� �ٽ� �����ؾ� ��.
        		rtnValue = maPmMstrDetailDAO.createPmSchedule(user.getCompNo(), maPmMstrCommonDTO.getPmId(), user.getEmpId());
        		rtnValue =  maPmMstrDetailDAO.createWorkOrder(user.getCompNo(), maPmMstrCommonDTO.getPmId());
        	}
        }
        
        rtnValue = workPmListEquipDetailDAO.updateSchedEquipDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
        
		
		return rtnValue; 
    }
	public int insertDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {        
		int rtnValue = 0;
        if("PMC".equals(maPmMstrCommonDTO.getWoType()))
        {
        	workPmListEquipDetailDAO.mergeEqPmCycle(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
        }
        
        
        rtnValue = workPmListEquipDetailDAO.insertDetail( workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
        
        if ("N".equals(workPmListEquipDetailDTO.getIsActive())){
        	//��� ������ ������.
        	workPmListEquipDetailDAO.deletePmSchedulePmEQuip(user.getCompNo(),workPmListEquipDetailDTO.getPmEquipId());
        	
        }else if("Y".equals(workPmListEquipDetailDTO.getIsActive())){
        	
        	if(!workPmListEquipDetailDTO.getOldInitWrkDate().replaceAll("-", "").equals(workPmListEquipDetailDTO.getInitWrkDate().replaceAll("-", ""))){
        		// �������ڸ� ���������Ƿ� ������ ��¥�� �����ؾ� ��.
        		rtnValue = workPmListEquipDetailDAO.updateLastSchedDate(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
        		//�������� �ٽ� �����ؾ� ��.
        		rtnValue = maPmMstrDetailDAO.createPmSchedule(user.getCompNo(), maPmMstrCommonDTO.getPmId(), user.getEmpId());
        		rtnValue =  maPmMstrDetailDAO.createWorkOrder(user.getCompNo(), maPmMstrCommonDTO.getPmId());
        	}
        }
        return rtnValue;
    }

	public List findSlideImage(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, User user)
			throws InvalidKeyException, URISyntaxException, StorageException {
		return FileUtil.makeSlideImg(workPmListEquipDetailDTO.getPmEquipId(), "PMWMSTR");
	}
}
