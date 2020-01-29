package dream.work.let.categ.dao;

import java.util.List;

import common.bean.User;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;

/**
 * 안전작업유형 보완사항 list page - List DAO
 * @author euna0207
 * @version $Id: WorkLetCategEtcListDAO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public interface WorkLetCategEtcListDAO
{
	/**
	 * FIND LIST
	 * @param workLetCategEtcListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user) throws Exception;
    
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
	 * @param workLetCategEtcListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user) throws Exception;
    
}