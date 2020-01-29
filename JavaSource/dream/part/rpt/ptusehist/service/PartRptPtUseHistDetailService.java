package dream.part.rpt.ptusehist.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistDetailDTO;
/**
 * 부품사용분석 - Detail Service
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 */
public interface PartRptPtUseHistDetailService
{   
    /**
     * 부품사용분석(부품) FIND DETAIL List
     * @param partRptPtUseHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findPartDetailList(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception;
	/**
	 * FIND 부품 사용(부품) LIST COUNT
	 * @param partRptPtUseHistDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findPartTotalCount(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception;
    
    /**
     * 부품사용분석(설비) FIND DETAIL List
     * @param partRptPtUseHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findEqDetailList(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception;
    /**
     * FIND 부품 사용(설비) LIST COUNT
     * @param partRptPtUseHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findEqTotalCount(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception;
}
