package dream.rcm.crity.service;

import java.util.List;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;
/**
 * Criticality Matrix Val Page - List Service
 * @author kim21017
 * @version $Id: RcmCrityValListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmCrityValListService {
	/**
	 * FIND LIST
	 * @param rcmCrityCommonDTO
	 * @param rcmCrityValListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityValListDTO rcmCrityValListDTO, User user) throws Exception;

	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityValListDTO rcmCrityValListDTO, User user);
}
