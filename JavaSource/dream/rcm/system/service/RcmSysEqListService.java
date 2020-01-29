package dream.rcm.system.service;

import java.util.List;

import common.bean.User;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqListDTO;

/**
 * 설비설정 목록
 * @author  kim21017
 * @version $Id: RcmSysEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEqListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmSysEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmSysEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, String compNo) throws Exception;
    /**
     *  input
     * @author  kim21017
     * @version $Id: RcmSysEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysEqListDTO 
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO);
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User user);
}
