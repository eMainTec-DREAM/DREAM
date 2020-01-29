package dream.part.rpt.ptusehist.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistCommonDTO;
/**
 * ��ǰ ��� �м� - List Service
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 */
public interface PartRptPtUseHistListService {
	/**
	 * FIND ��ǰ ��� LIST
	 * @param partRptPtUseHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPtUseList(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO, User user) throws Exception;

	/**
	 * FIND ��ǰ ��� LIST COUNT
	 * @param partRptPtUseHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO, User user) throws Exception;
}
