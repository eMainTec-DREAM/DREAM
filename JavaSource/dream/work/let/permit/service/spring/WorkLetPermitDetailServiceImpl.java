package dream.work.let.permit.service.spring;

import common.bean.User;
import dream.work.let.dao.WorkLetDetailDAO;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.permit.dao.WorkLetPermitDetailDAO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.service.WorkLetPermitDetailService;

/**
 * �����۾� - �����۾��㰡�� ��
 * @author syyang
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workLetPermitDetailServiceTarget"
 * @spring.txbn id="workLetPermitDetailService"
 * @spring.property name="workLetPermitDetailDAO" ref="workLetPermitDetailDAO"
 * @spring.property name="workLetDetailDAO" ref="workLetDetailDAO"
 */
public class WorkLetPermitDetailServiceImpl implements WorkLetPermitDetailService
{
    private WorkLetPermitDetailDAO workLetPermitDetailDAO = null;
    private WorkLetDetailDAO workLetDetailDAO = null;
    
    public WorkLetPermitDetailDAO getWorkLetPermitDetailDAO() {
		return workLetPermitDetailDAO;
	}

	public void setWorkLetPermitDetailDAO(WorkLetPermitDetailDAO workLetPermitDetailDAO) {
		this.workLetPermitDetailDAO = workLetPermitDetailDAO;
	}
	
	public WorkLetDetailDAO getWorkLetDetailDAO() {
		return workLetDetailDAO;
	}

	public void setWorkLetDetailDAO(WorkLetDetailDAO workLetDetailDAO) {
		this.workLetDetailDAO = workLetDetailDAO;
	}

	
	public WorkLetPermitDetailDTO findDetail(String woLetId, String woLetListId, User user)throws Exception
    {
        return workLetPermitDetailDAO.findDetail(woLetId, woLetListId, user);
    }
    
	public int updateDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
    {        
        return workLetPermitDetailDAO.updateDetail(workLetCommonDTO, workLetPermitDetailDTO, user);
    }
	public int insertDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
    {        
        return workLetPermitDetailDAO.insertDetail(workLetCommonDTO, workLetPermitDetailDTO, user);
    }

	@Override
	public String completeDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
	{
		String woLetStatus = "";
		
		// �����۾��㰡������ ���� �Ϸ�
		workLetPermitDetailDTO.setWoLetListStatus("COM");
        // �����۾��㰡������ �Ϸ�
		workLetPermitDetailDAO.completeDetail(workLetDetailDTO, workLetPermitDetailDTO, user);
		
		// �����۾� ���� ����
		// �����޴� �����۾� ���°� �Ϸᰡ �ƴ϶�� ���� ���������� ����
		woLetStatus = workLetDetailDAO.getWoLetStatus(workLetDetailDTO, user);
		
		workLetDetailDTO.setWoLetStatus(woLetStatus);
		workLetDetailDAO.completeDetail(workLetDetailDTO, user);
		
	    return woLetStatus;
	}
	@Override
	public String completeCancelDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception
	{
		String woLetStatus = "";
		
		// �����۾��㰡������ ���� �Ϸ���� (�Ϸ�->������)
		workLetPermitDetailDTO.setWoLetListStatus("DNG");
        // �����۾��㰡������ �Ϸ���� (�Ϸ�->������)
		workLetPermitDetailDAO.completeDetail(workLetDetailDTO, workLetPermitDetailDTO, user);

		// �����۾� ���� ����
		// �����۾� - �����۾��㰡������ �� '�Ϸ�' ���°� �������� ������ ���� �ۼ������� ����
		woLetStatus = workLetDetailDAO.getWoLetStatus(workLetDetailDTO, user);
		
		workLetDetailDTO.setWoLetStatus(woLetStatus);
		workLetDetailDAO.completeDetail(workLetDetailDTO, user);
        
		return woLetStatus;
	}

}
