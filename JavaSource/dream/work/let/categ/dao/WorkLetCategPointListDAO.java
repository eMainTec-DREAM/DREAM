package dream.work.let.categ.dao;

import java.util.List;

import common.bean.User;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;

/**
 * 안전작업유형 - 점검항목 List Page - List DAO
 * @author euna0207
 * @version $Id: WorkLetCategPointListDAO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public interface WorkLetCategPointListDAO
{
	/**
	 * FIND LIST
	 * @param workLetCategPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param workLetCategPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user) throws Exception;
    
}