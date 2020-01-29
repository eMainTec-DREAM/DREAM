package dream.tool.rpt.renthist.service;

import java.util.List;

import common.bean.User;
import dream.tool.rpt.renthist.dto.MaPttRentCommonDTO;
import dream.tool.rpt.renthist.form.MaPttRentListForm;

/**
 * ���ⱸ�뿩���� - ��� service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttRentListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param maPttRentCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaPttRentCommonDTO maPttRentCommonDTO);
    /**
     * req
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRentListForm
     * @param loginUser
     * @return
     * @throws Exception
     */
	public int reqPtReturn(MaPttRentListForm maPttRentListForm, User user);
    
}
