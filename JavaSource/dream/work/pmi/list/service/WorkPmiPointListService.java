package dream.work.pmi.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;

/**
 * 점검작업 점검  목록
 * @author  kim21017
 * @version $Id: WorkPmiPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmiPointListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkPmiPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiCommonDTO
     * @param workPmiPointListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findPointList(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser) throws Exception;
    /**
     *  delete
     * @author  kim21017
     * @version $Id: WorkPmiPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deletePointList(String[] deleteRows, User user) throws Exception;
    
	/**
	 * Save List
	 * @author  jung7126
	 * @version $Id:$
	 * @since   1.0
	 * 
	 * @param gridList
	 * @param user
	 * @throws Exception 
	 */
	public void savePointList(List<Map> gridList, User user) throws Exception;

	public String findTotalCount(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser)throws Exception;
}
