package dream.rcm.crity.service;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowDetailDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;
/**
 * Criticality Matrix Row Page - Detail Service
 * @author kim21017
 * @version $Id: RcmCrityRowDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmCrityRowDetailService
{    
	/**
	 * FIND DETAIL
	 * @param rcmCrityCommonDTO
	 * @param rcmCrityRowListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public RcmCrityRowDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowListDTO rcmCrityRowListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param rcmCrityCommonDTO
	 * @param rcmCrityRowDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param rcmCrityCommonDTO
     * @param rcmCrityRowDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user) throws Exception;
}
