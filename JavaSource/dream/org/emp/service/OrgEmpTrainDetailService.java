package dream.org.emp.service;

import dream.org.emp.dto.OrgEmpTrainDetailDTO;
import common.bean.User;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;

/**
 * 구매신청item - detail
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface OrgEmpTrainDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainListDTO
     * @param maEmpCommonDTO
     * @return
     * @throws Exception
     */
    public OrgEmpTrainDetailDTO findDetail(OrgEmpTrainListDTO orgEmpTrainListDTO, MaEmpCommonDTO maEmpCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainDetailDTO
     * @param maEmpCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO, MaEmpCommonDTO maEmpCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainDetailDTO
     * @param maEmpCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO, MaEmpCommonDTO maEmpCommonDTO) throws Exception;
}
