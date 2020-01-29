package dream.mgr.audtrail.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.audtrail.dao.MgrAudTrailDtlListDAO;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDtlListDTO;
import dream.mgr.audtrail.service.MgrAudTrailDtlListService;

/**
 * MgrAudTrailDtl Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrAudTrailDtlListServiceTarget"
 * @spring.txbn id="mgrAudTrailDtlListService"
 * @spring.property name="mgrAudTrailDtlListDAO" ref="mgrAudTrailDtlListDAO"
 */
public class MgrAudTrailDtlListServiceImpl implements MgrAudTrailDtlListService
{
    private MgrAudTrailDtlListDAO mgrAudTrailDtlListDAO = null;

   public List findList(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user) throws Exception
    {      
      String prevKeyId = mgrAudTrailDtlListDAO.findPrevKeyId(mgrAudTrailCommonDTO, user);
      
      mgrAudTrailDtlListDTO.setPrevTracelogId(prevKeyId);
      
        return mgrAudTrailDtlListDAO.findList(mgrAudTrailCommonDTO,mgrAudTrailDtlListDTO,user);
    }

    public MgrAudTrailDtlListDAO getMgrAudTrailDtlListDAO() {
        return mgrAudTrailDtlListDAO;
    }

    public void setMgrAudTrailDtlListDAO(MgrAudTrailDtlListDAO mgrAudTrailDtlListDAO) {
        this.mgrAudTrailDtlListDAO = mgrAudTrailDtlListDAO;
    }    
    
    public String findTotalCount(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user)  throws Exception
    {
        return mgrAudTrailDtlListDAO.findTotalCount(mgrAudTrailCommonDTO, mgrAudTrailDtlListDTO, user);
    }
}