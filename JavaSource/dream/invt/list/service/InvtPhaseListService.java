package dream.invt.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;



/**
 * 답변 목록
 * @author  kim21017
 * @version $Id: InvtPhaseListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface InvtPhaseListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: InvtPhaseListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtPhaseListDTO
     * @throws Exception
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: InvtPhaseListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param user 
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteList(String[] m_id, User user) throws Exception;

	/**
	 * Save List
	 * @author  syyang
	 * @version $Id:$
	 * @since   1.0
	 * 
	 * @param gridList
	 * @param user
	 * @throws Exception 
	 */
	public void savePointList(List<Map> gridList, User user) throws Exception;

    public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception;

    public int insertPhase(InvtCommonDTO invtCommonDTO, User user) throws Exception;
    
    // 절차중복검사
    public String validPhase(InvtCommonDTO invtCommonDTO, User user) throws Exception;

}
