package common.mafinder.mamstr.service;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovPtIssListDTO;
import common.mafinder.mamstr.form.LovPtIssListForm;

/**
 * 출고부품 팝업 Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovPtIssListService
{
	List findPtIssAcList(LovPtIssListDTO lovPtIssListDTO, User user, LovPtIssListForm lovPtIssListForm);
	
	public String findPtIssListTotalCount(LovPtIssListDTO lovPtIssListDTO, User user, LovPtIssListForm lovPtIssListForm);

}