package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.asset.categ.list.form.LovEqCtgPartAcListForm;
import dream.part.list.dto.LovPartListBinListDTO;
import dream.part.list.form.LovPartListBinListForm;

/**
 * 점검항목 Service
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovPartListBinListService
{

    /**
     * 설비종류별부품
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovPartListBinListDTO
     * @return
     */

	List findEqCtgPartAcList(LovPartListBinListDTO lovPartListBinListDTO, User user, LovPartListBinListForm lovPartListBinListForm) throws Exception;

	public String findTotalCount(LovPartListBinListDTO lovPartListBinListDTO, User loginUser, LovPartListBinListForm lovPartListBinListForm) throws Exception;
}