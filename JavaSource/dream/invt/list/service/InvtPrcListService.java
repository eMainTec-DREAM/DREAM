package dream.invt.list.service;

import java.util.List;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;

/**
 * 목록 service
 * @author  kim21017
 * @version $Id: InvtListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface InvtPrcListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: InvtListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param invtCommonDTO
     * @param user 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user);

	/**
	 * Delete List
	 * @param deleteRows
	 * @param user
	 * @return 
	 */
	public int deleteList(String[] deleteRows, User user);    

    public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception;
}
