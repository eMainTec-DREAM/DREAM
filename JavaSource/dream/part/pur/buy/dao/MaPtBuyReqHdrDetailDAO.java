package dream.part.pur.buy.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 구매신청 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaPtBuyReqHdrDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @return
     */
    public MaPtBuyReqHdrDetailDTO findDetail(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO,User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     */
    public int insertDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User loginUser);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     */
    public int updateDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User user);

    /**
     * detail check
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     */
    public String checkDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO);
    
    public List findReportPtDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO);
    
    public List findReportPartList(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO);

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user); 
}