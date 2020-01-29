package dream.part.rpt.ptusehist.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistDetailDTO;

/**
 * ��ǰ���м� - Detail DAO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public interface PartRptPtUseHistDetailDAO
{
    /**
     * ��ǰ���м�(��ǰ) FIND DETAIL List
     * @param partRptOrgPtUseHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findPartDetailList(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception;
    /**
	 * FIND ��ǰ ���(��ǰ)  LIST COUNT
	 * @param partRptPtUseHistDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findPartTotalCount(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception;

	/**
	 * ��ǰ���м�(����) FIND DETAIL List
	 * @param partRptOrgPtUseHistDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findEqDetailList(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception;
	/**
	 * FIND ��ǰ ���(����)  LIST COUNT
	 * @param partRptPtUseHistDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findEqTotalCount(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO, User user) throws Exception;
    
}