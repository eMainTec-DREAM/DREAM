package dream.part.iss.emg.req.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;

import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;

/**
 * 긴급출고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtIssEmgDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 */
public interface PartIssEmgReqPartsDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgCommonDTO
     * @return
     */
    public PartIssEmgReqPartsDetailDTO findDetail(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailDTO
     * @return
     */
    public int insertPtIssEmgList(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user);
    
    
//    public int updateReqInfo(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user);
    
    
    public int updatePtIssEmgList(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user);
    

}