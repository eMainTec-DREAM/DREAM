package dream.rcm.crity.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Col Page - List DAO
 * @author kim21017
 * @version $Id: RcmCrityColListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface RcmCrityColListDAO
{
	/**
	 * FIND LIST
	 * @param rcmCrityCommonDTO
	 * @param rcmCrityColListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColListDTO rcmCrityColListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * DELETE VALUELIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteValList(String id, User user) throws Exception;

	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityColListDTO rcmCrityColListDTO, User user);
    
}