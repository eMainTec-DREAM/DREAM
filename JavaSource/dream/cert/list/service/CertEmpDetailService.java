package dream.cert.list.service;

import common.bean.User;

import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpDetailDTO;

/**
 * 자격증 사원상세
 * @author  kim210117
 * @version $Id: CertEmpDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface CertEmpDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: CertEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param wkOrId
     * @param certEmpId
     * @param compNo
     * @return
     * @throws Exception
     */
    public CertEmpDetailDTO findDetail(String certListId, User loginUser)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: CertEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param certEmpDetailDTO
     * @param certCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: CertEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param certEmpDetailDTO
     * @param certCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser) throws Exception;

    /**
     * 사원중복검사
     */
    public String validEmp(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO,User loginUser);
}
