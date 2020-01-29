package dream.work.fmea.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;

/**
 * 고장영향성평가 - List DAO
 * @author kim21017
 * @version $Id: WorkFmeaResListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkFmeaResListDAO
{
	/**
	 * FIND LIST
	 * @param workFmeaResCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception;
    
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
	 * @param workFmeaResCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception;
}