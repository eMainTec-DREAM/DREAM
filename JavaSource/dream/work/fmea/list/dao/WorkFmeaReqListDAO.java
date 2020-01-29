package dream.work.fmea.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.fmea.list.dto.WorkFmeaReqCommonDTO;

/**
 * 고장영향성평가 - List DAO
 * @author kim21017
 * @version $Id: WorkFmeaReqListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkFmeaReqListDAO
{
	/**
	 * FIND LIST
	 * @param workFmeaReqCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkFmeaReqCommonDTO workFmeaReqCommonDTO, User user) throws Exception;
    
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
	 * @param workFmeaReqCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkFmeaReqCommonDTO workFmeaReqCommonDTO, User user) throws Exception;
}