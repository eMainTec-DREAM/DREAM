package dream.req.work.service;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.LovWoReqAcListDTO;
import dream.req.work.form.LovWoReqAcListForm;

/**
 * �۾���û �˾� Service
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 *
 */
public interface LovWoReqAcListService
{

    /**
     * �۾� �˻�
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param LovWoReqAcListDTO
     * @param loginUser
     * @return
     */
	List findWoReqAcList(LovWoReqAcListDTO lovWoReqAcListDTO, User user, LovWoReqAcListForm lovWoReqAcListForm);

	/**
     * find Total Count
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWoReqAcListDTO
     * @return
     */
	public String findTotalCount(LovWoReqAcListDTO lovWoReqAcListDTO, User user, LovWoReqAcListForm lovWoReqAcListForm) throws Exception;
}