package dream.work.pm.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;

/**
 * WorkPmiDInsPoint Page - List Service
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmiDInsPointListService
{
    /**
     * FIND LIST
     * @param workPmiDInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception;
    /**
     * DELETE LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmiDInsCommonDTO
     * @return
     */
    public String findTotalCount(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception;

	/**
	 * Save List
	 * @author  syyang
	 * @version $Id:$
	 * @since   1.0
	 * 
	 * @param gridList
	 * @param user
	 * @throws Exception 
	 */
	public void saveList(List<Map> gridList, User user) throws Exception;

}
