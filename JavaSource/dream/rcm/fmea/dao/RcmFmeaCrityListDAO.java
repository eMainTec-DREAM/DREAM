package dream.rcm.fmea.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;


/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: RcmFmeaCrityListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFmeaCrityListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFmeaCrityListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @param rcmFmeaCrityListDTO
     * @return List
     */
    public List findList(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFmeaCrityListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String deleteRow);

	/**
	 * Insert Crity FMEA
	 * @param rcmFmeaCommonDTO
	 * @param rcmFmeaCrityListDTO
	 */
	public void insertCrity(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO);

	public void deleteCrity(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO);

	public String findTotalCount(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO, User user);
}