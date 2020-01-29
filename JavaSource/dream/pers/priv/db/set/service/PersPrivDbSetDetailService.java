package dream.pers.priv.db.set.service;

import common.bean.User;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;
/**
 * Guide Page - Detail Service
 * @author nhkim8548
 * @version $Id: PersPrivDbSetDetailService.java,v 1.0 2018/08/06 09:12:40 nhkim8548 Exp $
 * @since 1.0
 */
public interface PersPrivDbSetDetailService
{    
	/**
	 * FIND PROGRAM GUIDE DETAIL
	 * @param persPrivDbSetCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public PersPrivDbSetDetailDTO findDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception;
	/**
	 * INSERT PROGRAM GUIDE
	 * @param persPrivDbSetDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception;
    /**
     * UPDATE PROGRAM GUIDE
     * @param persPrivDbSetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception;
    /**
     * FIND DASHBOARD MENU
     * @param persPrivDbSetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public void fineDbMenu(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception;
}
