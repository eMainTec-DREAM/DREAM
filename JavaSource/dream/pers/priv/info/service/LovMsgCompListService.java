package dream.pers.priv.info.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dto.LovMsgCompListDTO;
import dream.pers.priv.info.form.LovMsgCompListForm;

/**
 * 메시지타입 Lov Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovMsgCompListService
{

	/**
	 * AC Find List
	 * @param lovMsgCompListDTO
	 * @param user
	 * @param lovMsgCompListForm
	 * @return
	 */
	public List findMsgCompAcList(LovMsgCompListDTO lovMsgCompListDTO, User user, LovMsgCompListForm lovMsgCompListForm);

	/**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovMsgCompListDTO
	 * @param user
	 * @param lovMsgCompListForm
     * @return
     */
	public String findTotalCount(LovMsgCompListDTO lovMsgCompListDTO, User user, LovMsgCompListForm lovMsgCompListForm) throws Exception;
	
}