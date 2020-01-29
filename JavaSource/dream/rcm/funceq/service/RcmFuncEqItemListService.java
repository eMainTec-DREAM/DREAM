package dream.rcm.funceq.service;

import java.util.List;

import common.bean.User;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * 답변 목록
 * @author  kim21017
 * @version $Id: RcmFuncEqItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFuncEqItemListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmFuncEqItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @param rcmFuncEqItemListDTO
     * @throws Exception
     */
    public List findItemList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmFuncEqItemListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] m_id, String[] d_id) throws Exception;
    /**
     *  input
     * @author  kim21017
     * @version $Id: RcmSysFEnvListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int inputList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO);
	public String findTotalCount(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO,
			User user);

}
