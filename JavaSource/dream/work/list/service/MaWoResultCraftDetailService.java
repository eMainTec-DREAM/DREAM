package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �۾���� �۾��� ��
 * @author  kim210117
 * @version $Id: MaWoResultCraftDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoResultCraftDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoResultCraftDetailDTO findDetail(String wkOrId, String woCraftId,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;

    /**
     * ����ߺ��˻�
     */
    public String validEmp(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser);
    /**
     * �۾��ð� �ߺ� �˻�
     */
    public String validTime(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
}
