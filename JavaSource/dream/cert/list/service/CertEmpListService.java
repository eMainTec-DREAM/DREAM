package dream.cert.list.service;

import java.util.List;

import common.bean.User;

import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpListDTO;

/**
 * 자격증 사원  목록
 * @author  kim21017
 * @version $Id: CertEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface CertEmpListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: CertEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param certCommonDTO
     * @param certEmpListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findEmpList(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: CertEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteEmpList(String[] deleteRows, User loginUser) throws Exception;
    
    public String findTotalCount(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User user) throws Exception;

}
