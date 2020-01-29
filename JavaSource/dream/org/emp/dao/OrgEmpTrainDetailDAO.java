package dream.org.emp.dao;

import dream.org.emp.dto.OrgEmpTrainDetailDTO;
import common.bean.User;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;

/**
 * 구매신청 item 상세 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface OrgEmpTrainDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainListDTO
     * @param maEmpCommonDTO
     * @return
     */
    public OrgEmpTrainDetailDTO findDetail(OrgEmpTrainListDTO orgEmpTrainListDTO, MaEmpCommonDTO maEmpCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainDetailDTO
     * @param maEmpCommonDTO
     * @return
     */
    public int updateDetail(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO, MaEmpCommonDTO maEmpCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainDetailDTO
     * @param maEmpCommonDTO
     * @return
     */
    public int insertDetail(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO, MaEmpCommonDTO maEmpCommonDTO);

}