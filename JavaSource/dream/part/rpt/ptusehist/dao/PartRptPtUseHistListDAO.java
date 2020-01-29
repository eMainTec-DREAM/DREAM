package dream.part.rpt.ptusehist.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistCommonDTO;

/**
 * 부품 사용 분석 - List DAO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public interface PartRptPtUseHistListDAO
{
	/**
	 * FIND 부품 사용 LIST
	 * @param partRptPtUseHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findPtUseList(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO, User user) throws Exception;
    
    /**
	 * FIND 부품 사용 LIST COUNT
	 * @param partRptPtUseHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO, User user) throws Exception;
    
}