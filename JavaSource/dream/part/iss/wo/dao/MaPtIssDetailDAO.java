package dream.part.iss.wo.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;

import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;

/**
 * 자재출고확정 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtIssDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 */
public interface MaPtIssDetailDAO extends BaseJdbcDaoSupportIntf
{

	public MaPtIssDetailDTO findDetail(MaPtIssCommonDTO maPtIssCommonDTO,User user);
	
	public boolean checkWorkOrderStatus(MaPtIssDetailDTO maPtIssDetailDTO, User user);
	
	public int insertWoPart(MaPtIssDetailDTO maPtIssDetailDTO, User user);
	
	public int insertPtIssList(MaPtIssDetailDTO maPtIssDetailDTO, User user);
	
	public int updateWoPart(MaPtIssDetailDTO maPtIssDetailDTO, User user);
	
	public int updatePtIssList(MaPtIssDetailDTO maPtIssDetailDTO, User user);
	
	public int confirmIssuePart(MaPtIssDetailDTO maPtIssDetailDTO, User user);
	
	public int insertPtIssHist(MaPtIssDetailDTO maPtIssDetailDTO, String ptisshistId, String ptissMode, User user);
	
	public void execSP_PT_OUTSTOCK(String ptisshistId, User user);

    public String getUseQty(MaPtIssDetailDTO maPtIssDetailDTO, User user);

    public int updateEqPart(MaPtIssDetailDTO maPtIssDetailDTO, User user);

    public int updateCancelEqPart(MaPtIssDetailDTO maPtIssDetailDTO, User user);

    public String findStockQty(MaPtIssDetailDTO maPtIssDetailDTO, User user);
}