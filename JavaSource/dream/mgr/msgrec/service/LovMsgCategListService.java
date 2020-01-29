package dream.mgr.msgrec.service;

import java.util.List;

import common.bean.User;
import dream.mgr.msgrec.dto.LovMsgCategListDTO;
import dream.mgr.msgrec.form.LovMsgCategListForm;

/**
 * 메시지타입 Lov Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovMsgCategListService
{

	/**
	 * AC Find List
	 * @param lovMsgCategListDTO
	 * @param user
	 * @param lovMsgCategListForm
	 * @return
	 */
	public List findMsgCategAcList(LovMsgCategListDTO lovMsgCategListDTO, User user, LovMsgCategListForm lovMsgCategListForm);

	/**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovMsgCategListDTO
	 * @param user
	 * @param lovMsgCategListForm
     * @return
     */
	public String findTotalCount(LovMsgCategListDTO lovMsgCategListDTO, User user, LovMsgCategListForm lovMsgCategListForm) throws Exception;
	
}