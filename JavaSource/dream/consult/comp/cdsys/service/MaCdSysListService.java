package dream.consult.comp.cdsys.service;

import java.util.List;

import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * �ý����ڵ� - ��� service
 * @author  kim21017
 * @version $Id: MaCdSysListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaCdSysListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaCdSysListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maCdSysCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findListTypeList(MaCdSysCommonDTO maCdSysCommonDTO);    

    /**
     * delete
     * @author kim21017
     * @version $Id: MaCdSysListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDTOList
     * @return
     * @throws Exception
     */
    public int deleteListType(String[] deleteRows) throws Exception;

}
