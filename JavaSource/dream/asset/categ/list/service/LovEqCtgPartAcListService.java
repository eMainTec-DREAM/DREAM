package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.asset.categ.list.form.LovEqCtgPartAcListForm;

/**
 * 설비종류별부품 Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEqCtgPartAcListService
{

    /**
     * 설비종류별부품
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovEqCtgPartAcListDTO
     * @return
     */
    List findEqCtgPartAcList(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovEqCtgPartAcListDTO
	 * @param user
	 * @param lovEqCtgPartAcListForm
	 * @return
	 * @throws Exception 
	 */
	List findEqCtgPartAcAcList(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User user, LovEqCtgPartAcListForm lovEqCtgPartAcListForm) throws Exception;
    public String findTotalCount(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User loginUser, LovEqCtgPartAcListForm lovEqCtgPartAcListForm) throws Exception;

}