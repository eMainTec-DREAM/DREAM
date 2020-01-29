package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.WorkListBmRplPartSerialListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.service.WorkListBmRplPartSerialListService;

/**
 * 작업결과 부품Serial 목록
 * @author kim21017
 * @version $Id: WorkListBmRplPartSerialListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListBmRplPartSerialListServiceTarget"
 * @spring.txbn id="workListBmRplPartSerialListService"
 * @spring.property name="workListBmRplPartSerialListDAO" ref="workListBmRplPartSerialListDAO"
 */
public class WorkListBmRplPartSerialListServiceImpl implements WorkListBmRplPartSerialListService
{
    private WorkListBmRplPartSerialListDAO workListBmRplPartSerialListDAO = null;


	public List findList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser)
    {      
        return workListBmRplPartSerialListDAO.findList(maWoResultMstrCommonDTO, workListBmRplPartSerialListDTO, maWoResultPartDetailDTO,  loginUser);
    }

	public WorkListBmRplPartSerialListDAO getWorkListBmRplPartSerialListDAO() {
		return workListBmRplPartSerialListDAO;
	}

	public void setWorkListBmRplPartSerialListDAO(WorkListBmRplPartSerialListDAO workListBmRplPartSerialListDAO) {
		this.workListBmRplPartSerialListDAO = workListBmRplPartSerialListDAO;
	}
	
	public int deleteList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workListBmRplPartSerialListDAO.deleteList(id, compNo);
            }
        
        return result;
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user) throws Exception 
	{
		return workListBmRplPartSerialListDAO.findTotalCount(maWoResultMstrCommonDTO, workListBmRplPartSerialListDTO, maWoResultPartDetailDTO, user);
	}
}

