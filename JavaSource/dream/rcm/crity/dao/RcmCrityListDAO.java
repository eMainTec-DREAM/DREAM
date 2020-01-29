package dream.rcm.crity.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Page - List DAO
 * @author kim21017
 * @version $Id: RcmCrityListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface RcmCrityListDAO
{
	/**
	 * FIND LIST
	 * @param rcmCrityCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, User user) throws Exception;
    
    /**
     * DELETE HDR LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteCrityList(String id, User user) throws Exception;
    /**
     * DELETE COL LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteCrityColList(String id, User user) throws Exception;
    /**
     * DELETE ROW LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteCrityRowList(String id, User user) throws Exception;
    /**
     * DELETE VALUE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteCrityValueList(String id, User user) throws Exception;

	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, User user);
    
}