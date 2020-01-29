package dream.part.iss.emg.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;

/**
 * 긴급출고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtIssEmgDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 */
public interface MaPtIssEmgDetailDAO extends BaseJdbcDaoSupportIntf
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
    public MaPtIssEmgDetailDTO findDetail(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO,User user);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailDTO
     * @return
     */
    public int insertPtIssEmgList(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser);
    public int updatePtIssEmgList(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO);
    
    public int insertPtIssHist(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, String histId, String issMode, String issType, String wcodeId, User user);
    public int execPtIss(String histId,User user);
    
    public int insertPtRecHist(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, String histId, String recMode, String recType, String wcodeId, User user);
    public int execPtRec(String histId,User user);


	public int issueComp(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user);
	public int issueCancel(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user);

    public int insertPtIssEmgReq(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser);
    public int completePtIssEmgReqStatus(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser);
    public int cancelPtIssEmgReqStatus(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser);
    
    public String findStockQty(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user);

}