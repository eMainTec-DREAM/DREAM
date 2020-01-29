package dream.rcm.crity.service;

import java.util.List;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;
/**
 * Criticality Matrix Row Page - List Service
 * @author kim21017
 * @version $Id: RcmCrityRowListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmCrityRowListService {
	/**
	 * FIND LIST
	 * @param rcmCrityCommonDTO
	 * @param rcmCrityRowListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowListDTO rcmCrityRowListDTO, User user) throws Exception;
	/**
	 * DELETE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityRowListDTO rcmCrityRowListDTO, User user);
}
