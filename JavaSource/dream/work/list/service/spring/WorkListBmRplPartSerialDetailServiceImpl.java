package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WorkListBmRplPartSerialDetailDAO;
import dream.work.list.dto.WorkListBmRplPartSerialDetailDTO;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.WorkListBmRplPartSerialDetailService;

/**
 * 작업결과 작업자
 * @author kim2107
 * @version $Id: WorkListBmRplPartSerialDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListBmRplPartSerialDetailServiceTarget"
 * @spring.txbn id="workListBmRplPartSerialDetailService"
 * @spring.property name="workListBmRplPartSerialDetailDAO" ref="workListBmRplPartSerialDetailDAO"
 */
public class WorkListBmRplPartSerialDetailServiceImpl implements WorkListBmRplPartSerialDetailService
{
    private WorkListBmRplPartSerialDetailDAO workListBmRplPartSerialDetailDAO = null;
    
    public WorkListBmRplPartSerialDetailDAO getWorkListBmRplPartSerialDetailDAO() {
		return workListBmRplPartSerialDetailDAO;
	}

	public void setWorkListBmRplPartSerialDetailDAO(WorkListBmRplPartSerialDetailDAO workListBmRplPartSerialDetailDAO) {
		this.workListBmRplPartSerialDetailDAO = workListBmRplPartSerialDetailDAO;
	}

    
	public int updateDetail(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {   
		workListBmRplPartSerialDetailDAO.deleteGarbageSerial(workListBmRplPartSerialDetailDTO, maWoResultMstrCommonDTO);
		
        return workListBmRplPartSerialDetailDAO.updateDetail(workListBmRplPartSerialDetailDTO, maWoResultMstrCommonDTO);
    }
	public int insertDetail(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
		workListBmRplPartSerialDetailDAO.deleteGarbageSerial(workListBmRplPartSerialDetailDTO, maWoResultMstrCommonDTO);
		
        return workListBmRplPartSerialDetailDAO.insertDetail( workListBmRplPartSerialDetailDTO, maWoResultMstrCommonDTO);
    }
	public String validSerial(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser){
		return workListBmRplPartSerialDetailDAO.validSerial(workListBmRplPartSerialDetailDTO, maWoResultMstrCommonDTO, loginUser);
	}

	public WorkListBmRplPartSerialDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,
			WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, String compNo) throws Exception {
		 return workListBmRplPartSerialDetailDAO.findDetail(maWoResultMstrCommonDTO, workListBmRplPartSerialListDTO, compNo);
	}
}
