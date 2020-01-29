package dream.consult.program.table.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * ������ ���̺� - ��� service
 * @author  kim21017
 * @version $Id: MaTableListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaTableListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaTableListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maTableCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findListTypeList(MaTableCommonDTO maTableCommonDTO);    

    /**
     * delete
     * @author kim21017
     * @version $Id: MaTableListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDTOList
     * @return
     * @throws Exception
     */
    public int deleteListType(String[] deleteRows) throws Exception;

    public String findTotalCount(MaTableCommonDTO maTableCommonDTO, User user) throws Exception;

}
