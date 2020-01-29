package dream.work.list.energy.service.spring;

import common.bean.User;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.work.list.energy.dao.WorkListEnergyPointDetailDAO;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;
import dream.work.list.energy.service.WorkListEnergyPointDetailService;

/**
 * 에너지 값 측정항목 상세 ServiceImpl
 * @author sy.yang
 * @version $Id: WorkListEnergyPointDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 sy.yang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListEnergyPointDetailServiceTarget"
 * @spring.txbn id="workListEnergyPointDetailService"
 * @spring.property name="workListEnergyPointDetailDAO" ref="workListEnergyPointDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */
public class WorkListEnergyPointDetailServiceImpl implements WorkListEnergyPointDetailService
{
    private WorkListEnergyPointDetailDAO workListEnergyPointDetailDAO = null;
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
	public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}
	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}
	public WorkListEnergyPointDetailDAO getWorkListEnergyPointDetailDAO() {
		return workListEnergyPointDetailDAO;
	}
	public void setWorkListEnergyPointDetailDAO(WorkListEnergyPointDetailDAO workListEnergyPointDetailDAO) {
		this.workListEnergyPointDetailDAO = workListEnergyPointDetailDAO;
	}

	
	public WorkListEnergyPointDetailDTO findDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User user) throws Exception
	{
		return workListEnergyPointDetailDAO.findDetail(workListEnergyMstrListCommonDTO, workListEnergyPointListDTO, user);
	}
    
	public int updateDetail(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO, WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user) throws Exception
    {        
		int rtnValue = 0;
		
		rtnValue += workListEnergyPointDetailDAO.updateDetail(workListEnergyPointDetailDTO, workListEnergyMstrListCommonDTO, user);
		
		return rtnValue;
    }
	
	public int insertEnergyPmPoint(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception
	{
		return workListEnergyPointDetailDAO.insertEnergyPmPoint(workListEnergyPointDetailDTO, workListEnergyMstrDetailDTO, user);
	}

}
