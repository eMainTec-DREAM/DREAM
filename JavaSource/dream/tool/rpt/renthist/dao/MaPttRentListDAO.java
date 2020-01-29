package dream.tool.rpt.renthist.dao;

import java.util.List;

import common.bean.User;
import dream.tool.rpt.renthist.dto.MaPttRentCommonDTO;
import dream.tool.rpt.renthist.form.MaPttRentListForm;

/**
 * ���ⱸ�뿩���� - ��� dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttRentListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRentCommonDTO
     * @return List
     */
    public List findList(MaPttRentCommonDTO maPttRentCommonDTO);
    
    /**
     * req hdr create
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqHdrPtRtn(MaPttRentListForm maPttRentListForm, String ptrentlistId, User user);

    /**
     * req dtl create
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqDtlPtRtn(MaPttRentListForm maPttRentListForm, String partId, User user);
}