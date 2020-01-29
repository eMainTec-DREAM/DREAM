package dream.ass.asset.service;

import java.util.List;

import common.bean.User;
import dream.ass.asset.dto.AssAssetScoreCopyLovDTO;
import dream.ass.asset.form.AssAssetScoreCopyLovForm;
import dream.consult.comp.user.form.LovUsrGrpAcListForm;

/**
 * ���� ��� �˾� Service
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 */
public interface AssAssetScoreCopyLovService
{

    /**
     * �򰡰�� ��� �˻�
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assAssetScoreCopyLovForm
     * @param user
     * @return
     */
    List findAssList(AssAssetScoreCopyLovForm assAssetScoreCopyLovForm, User user) throws Exception;
    
    /**
     * ������ ��� COUNT
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assAssetScoreCopyLovForm
     * @param user
     * @return
     * @throws Exception
     */
	public String findTotalCount(AssAssetScoreCopyLovForm assAssetScoreCopyLovForm, User user) throws Exception;

    
    /**
     * ������ ��� �˻�
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param AssAssetScoreCopyLovDTO
     * @param user
     * @return
     */
    List findAssScoreList(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user);

}