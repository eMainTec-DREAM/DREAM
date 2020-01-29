package dream.rcm.crity.service;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityColDetailDTO;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
/**
 * Criticality Matrix Col Page - Detail Service
 * @author kim21017
 * @version $Id: RcmCrityColDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmCrityColDetailService
{    
	/**
	 * FIND DETAIL
	 * @param rcmCrityCommonDTO
	 * @param rcmCrityColListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public RcmCrityColDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColListDTO rcmCrityColListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param rcmCrityCommonDTO
	 * @param rcmCrityColDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityColDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user) throws Exception;
}
