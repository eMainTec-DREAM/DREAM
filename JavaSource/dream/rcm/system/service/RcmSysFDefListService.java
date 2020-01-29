package dream.rcm.system.service;

import java.util.List;

import common.bean.User;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFDefListDTO;

/**
 * 기능정의 목록
 * @author  kim21017
 * @version $Id: RcmSysFDefListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysFDefListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmSysFDefListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmSysFDefListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, String compNo) throws Exception;
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser);

}
