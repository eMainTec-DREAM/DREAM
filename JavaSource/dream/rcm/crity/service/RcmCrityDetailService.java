package dream.rcm.crity.service;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityDetailDTO;
/**
 * Criticality Matrix Page - Detail Service
 * @author kim21017
 * @version $Id: RcmCrityDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmCrityDetailService
{    
	/**
	 * FIND DETAIL
	 * @param rcmCrityCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public RcmCrityDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
	 * @param rcmCrityDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(RcmCrityDetailDTO rcmCrityDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param rcmCrityDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmCrityDetailDTO rcmCrityDetailDTO, User user) throws Exception;
}
