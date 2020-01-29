package dream.rcm.crity.dao;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityMatrixDTO;

/**
 * Criticality Matrix Page - Matrix DAO
 * @author kim21017
 * @version $Id: RcmCrityMatrixDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface RcmCrityMatrixDAO
{
	/**
	 * FIND COL
	 * @param rcmCrityCommonDTO
	 * @param rcmCrityMatrixDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String[][] findCol(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityMatrixDTO rcmCrityMatrixDTO, User user) throws Exception;
    /**
	 * FIND VAL
	 * @param rcmCrityCommonDTO
	 * @param rcmCrityMatrixDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String[][] findVal(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityMatrixDTO rcmCrityMatrixDTO, User user) throws Exception;
    
}