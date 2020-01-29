package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WorkListCavalDetailDAO;
import dream.work.list.dto.WorkListCavalDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.WorkListCavalDetailService;

/**
 * 작업상세  - 검교정 - 측정값
 * @author kim2107
 * @version $Id: WorkListCavalDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListCavalDetailServiceTarget"
 * @spring.txbn id="workListCavalDetailService"
 * @spring.property name="workListCavalDetailDAO" ref="workListCavalDetailDAO"
 */
public class WorkListCavalDetailServiceImpl implements WorkListCavalDetailService
{
    private WorkListCavalDetailDAO workListCavalDetailDAO = null;
    
    public WorkListCavalDetailDAO getWorkListCavalDetailDAO() {
		return workListCavalDetailDAO;
	}

	public void setWorkListCavalDetailDAO(WorkListCavalDetailDAO workListCavalDetailDAO) {
		this.workListCavalDetailDAO = workListCavalDetailDAO;
	}

	public WorkListCavalDetailDTO findDetail(String wkOrId, String woCalibValueId, String compNo)throws Exception
    {
        return workListCavalDetailDAO.findDetail(wkOrId, woCalibValueId, compNo);
    }
    
	public int updateDetail(WorkListCavalDetailDTO workListCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return workListCavalDetailDAO.updateDetail(workListCavalDetailDTO, maWoResultMstrCommonDTO);
    }
	public int insertDetail(WorkListCavalDetailDTO workListCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return workListCavalDetailDAO.insertDetail( workListCavalDetailDTO, maWoResultMstrCommonDTO);
    }
}
