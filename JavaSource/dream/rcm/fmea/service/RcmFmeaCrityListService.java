package dream.rcm.fmea.service;

import java.util.List;

import common.bean.User;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;


/**
 * 답변 목록
 * @author  kim21017
 * @version $Id: RcmFmeaCrityListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFmeaCrityListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmFmeaCrityListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @param rcmFmeaCrityListDTO
     * @throws Exception
     */
    public List findList(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmFmeaCrityListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteList(String[] m_id) throws Exception;

	/**
	 * @param rcmFmeaCrityListDTO
	 * @param rcmFmeaCommonDTO
	 */
	public void insertCrity(RcmFmeaCrityListDTO rcmFmeaCrityListDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO);
	/**
	 * @param rcmFmeaCrityListDTO
	 * @param rcmFmeaCommonDTO
	 */
	public void deleteCrity(RcmFmeaCrityListDTO rcmFmeaCrityListDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO);
	public String findTotalCount(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO, User user);

}
