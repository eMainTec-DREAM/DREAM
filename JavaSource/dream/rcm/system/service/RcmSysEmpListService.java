package dream.rcm.system.service;

import java.util.List;

import common.bean.User;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEmpListDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;

/**
 * 분석자 목록
 * @author  kim21017
 * @version $Id: RcmSysEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEmpListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmSysEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmSysEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
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
     * @version $Id: RcmSysEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysEmpListDTO 
     * @throws Exception
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO) throws Exception;
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User user);
}
