package dream.consult.rpt.mases.service;

/**
 * 실시간사용자 - 목록 service
 * @author  kim21017
 * @version $Id: MaSesMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaSesMngListService
{     
    /**
     * delete
     * @author kim21017
     * @version $Id: MaSesMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteSes(String[] deleteRows) throws Exception;
}
