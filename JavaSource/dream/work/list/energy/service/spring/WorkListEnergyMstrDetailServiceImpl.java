package dream.work.list.energy.service.spring;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.energy.dao.WorkListEnergyMstrDetailDAO;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.service.WorkListEnergyMstrDetailService;

/**
 * ���������� - �������� ��� �� ServiceImpl
 * @author  sy.yang
 * @version $Id: WorkListEnergyMstrDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 sy.yang Exp $
 * @since   1.0
 * @spring.bean id="workListEnergyMstrDetailServiceTarget"
 * @spring.txbn id="workListEnergyMstrDetailService"
 * @spring.property name="workListEnergyMstrDetailDAO" ref="workListEnergyMstrDetailDAO"
 */
public class WorkListEnergyMstrDetailServiceImpl implements WorkListEnergyMstrDetailService 
{
    private WorkListEnergyMstrDetailDAO workListEnergyMstrDetailDAO = null;
    
	public WorkListEnergyMstrDetailDAO getWorkListEnergyMstrDetailDAO() {
		return workListEnergyMstrDetailDAO;
	}
	public void setWorkListEnergyMstrDetailDAO(WorkListEnergyMstrDetailDAO workListEnergyMstrDetailDAO) {
		this.workListEnergyMstrDetailDAO = workListEnergyMstrDetailDAO;
	}
	
	public WorkListEnergyMstrDetailDTO findDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user)throws Exception
    {
        return workListEnergyMstrDetailDAO.findDetail(workListEnergyMstrListCommonDTO, user);
    }

	public int insertDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception 
	{
		return workListEnergyMstrDetailDAO.insertDetail(workListEnergyMstrListCommonDTO, workListEnergyMstrDetailDTO, user);
	}
	
	public int updateDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception
    {        
		return workListEnergyMstrDetailDAO.updateDetail(workListEnergyMstrDetailDTO, user);
    }
	
	public int completeDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception
    { 
		// �۾��Ϸ� (���� -> C ����)
		workListEnergyMstrDetailDTO.setPmschedStatusId("C");
		
		// �������� ����(PMSCHED_STATUS)�� ����
		workListEnergyMstrDetailDAO.completeSched(workListEnergyMstrDetailDTO, user);
		
		// ���� �۾������ ����(PMSCHED_STATUS)�� ����
		workListEnergyMstrDetailDAO.completePoint(workListEnergyMstrDetailDTO, user);

		// ���� �۾���� ����(PMSCHED_STATUS)�� ����
		return workListEnergyMstrDetailDAO.completeDetail(workListEnergyMstrDetailDTO, user);
    }
	public int completeCancelDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception
	{ 
		// �۾��Ϸ� (���� -> P ����)
		workListEnergyMstrDetailDTO.setPmschedStatusId("P");
		
		// �������� ����(PMSCHED_STATUS)�� ����
		workListEnergyMstrDetailDAO.completeSched(workListEnergyMstrDetailDTO, user);
		
		// ���� �۾������ ����(PMSCHED_STATUS)�� ����
		workListEnergyMstrDetailDAO.completePoint(workListEnergyMstrDetailDTO, user);
		
		// ���� �۾���� ����(PMSCHED_STATUS)�� ����
		return workListEnergyMstrDetailDAO.completeDetail(workListEnergyMstrDetailDTO, user);
	}
	
	public String checkPoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user) throws Exception
	{
		return workListEnergyMstrDetailDAO.checkPoint(workListEnergyMstrDetailDTO,user );
	}
	
	public String isLastPoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user) throws Exception
	{
		return workListEnergyMstrDetailDAO.isLastPoint(workListEnergyMstrDetailDTO,user );
	}

    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        if("C".equals(appReqDetailDTO.getParentStatus()))
        {
            WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = new WorkListEnergyMstrListCommonDTO();
            workListEnergyMstrListCommonDTO.setPminslistId(appReqDetailDTO.getObjectId());
            workListEnergyMstrListCommonDTO.setCompNo(user.getCompNo());
            WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailDAO.findDetail(workListEnergyMstrListCommonDTO, user);
            
            this.completeDetail(workListEnergyMstrDetailDTO, user);
        }
        else
        {
            workListEnergyMstrDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }

    public int insertEnergyPmInsSched(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception
    {
    	return workListEnergyMstrDetailDAO.insertEnergyPmInsSched(workListEnergyMstrListCommonDTO, workListEnergyMstrDetailDTO, user);
    }
	public int insertWoEnergyMstrDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception
	{
		return workListEnergyMstrDetailDAO.insertWoEnergyMstrDetail(workListEnergyMstrListCommonDTO, workListEnergyMstrDetailDTO, user);
	}
	
	@Override
	public String checkConfirm(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception {
		return workListEnergyMstrDetailDAO.checkConfirm(workListEnergyMstrDetailDTO,user );
	}
}
