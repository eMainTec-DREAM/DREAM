package dream.work.fmea.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;

/**
 * 고장영향성평가 - List DAO
 * @author kim21017
 * @version $Id: WorkFmeaListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkFmeaListDAO
{
	/**
	 * FIND LIST
	 * @param workFmeaCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkFmeaCommonDTO workFmeaCommonDTO, User user) throws Exception;
    
    /**
     * DELETE HDR LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;

	/**
	 * FIND LIST COUNT
	 * @param workFmeaCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkFmeaCommonDTO workFmeaCommonDTO, User user) throws Exception;
}