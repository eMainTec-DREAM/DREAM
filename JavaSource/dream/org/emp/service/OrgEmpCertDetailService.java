package dream.org.emp.service;

import dream.org.emp.dto.OrgEmpCertDetailDTO;
import common.bean.User;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;

/**
 * 구매신청item - detail
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface OrgEmpCertDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpCertListDTO
     * @param maEmpCommonDTO
     * @return
     * @throws Exception
     */
    public OrgEmpCertDetailDTO findDetail(OrgEmpCertListDTO orgEmpCertListDTO, MaEmpCommonDTO maEmpCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpCertDetailDTO
     * @param maEmpCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(OrgEmpCertDetailDTO orgEmpCertDetailDTO, MaEmpCommonDTO maEmpCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpCertDetailDTO
     * @param maEmpCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(OrgEmpCertDetailDTO orgEmpCertDetailDTO, MaEmpCommonDTO maEmpCommonDTO) throws Exception;
}
