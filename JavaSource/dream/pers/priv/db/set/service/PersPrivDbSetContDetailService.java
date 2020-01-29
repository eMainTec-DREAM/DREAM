package dream.pers.priv.db.set.service;

import common.bean.User;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;
/**
 * 대시보드(Contents) - Detail Service
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContDetailService.java,v 1.0 2018/08/03 11:22:40 nhkim8548 Exp $
 * @since 1.0
 */
public interface PersPrivDbSetContDetailService
{    
	/**
	 * FIND DETAIL
	 * @param persPrivDbSetCommonDTO
	 * @param assAssetScoreListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public PersPrivDbSetContDetailDTO findDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param persPrivDbSetCommonDTO
	 * @param persPrivDbSetContCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param persPrivDbSetCommonDTO
     * @param persPrivDbSetContCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user) throws Exception;
}
