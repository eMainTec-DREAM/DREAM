package dream.invt.list.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dao.InvtPhaseDetailDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPhaseDetailDTO;
import dream.invt.list.service.InvtDetailService;
import dream.invt.list.service.InvtPhaseDetailService;
import dream.req.work.dao.MaWoReqResDetailDAO;

/**
 * 
 * @author kim2107
 * @version $Id: InvtPhaseDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtPhaseDetailServiceTarget"
 * @spring.txbn id="invtPhaseDetailService"
 * @spring.property name="invtPhaseDetailDAO" ref="invtPhaseDetailDAO"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 * @spring.property name="maWoReqResDetailDAO" ref="maWoReqResDetailDAO"
 */
public class InvtPhaseDetailServiceImpl implements InvtPhaseDetailService
{
    private InvtPhaseDetailDAO invtPhaseDetailDAO = null;
    private InvtDetailDAO invtDetailDAO = null;
    private MaWoReqResDetailDAO maWoReqResDetailDAO = null;
    
    public MaWoReqResDetailDAO getMaWoReqResDetailDAO() {
        return maWoReqResDetailDAO;
    }

    public void setMaWoReqResDetailDAO(MaWoReqResDetailDAO maWoReqResDetailDAO) {
        this.maWoReqResDetailDAO = maWoReqResDetailDAO;
    }

    public InvtDetailDAO getInvtDetailDAO()
    {
        return invtDetailDAO;
    }

    public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO)
    {
        this.invtDetailDAO = invtDetailDAO;
    }

    public InvtPhaseDetailDAO getInvtPhaseDetailDAO() {
        return invtPhaseDetailDAO;
    }

    public void setInvtPhaseDetailDAO(InvtPhaseDetailDAO invtPhaseDetailDAO) {
        this.invtPhaseDetailDAO = invtPhaseDetailDAO;
    }

    public InvtPhaseDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception
    {
        return invtPhaseDetailDAO.findDetail(invtCommonDTO, user);
    }
    
    public void updateDetail(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception
    {        
        invtPhaseDetailDAO.updateDetail(invtPhaseDetailDTO, invtCommonDTO, user);
        
        InvtDetailService invtDetailService = (InvtDetailService)CommonUtil.getBean("invtDetailService", user);
        invtDetailService.changeStatus(invtCommonDTO, user);
    }
    
    public int insertDetail(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception
    {        
//      int cnt = invtPhaseDetailDAO.insertDetail( invtPhaseDetailDTO, invtCommonDTO);
//        //투자목록 상태 업데이트
//        AppReqDetailDTO appReqDetailDTO = new AppReqDetailDTO();
//        appReqDetailDTO.setObjectId(invtPhaseDetailDTO.getInvtlistId());
//        invtDetailDAO.updateInvtListStatus(appReqDetailDTO, user);
        return 0;
    }

    public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception 
    {
        return invtPhaseDetailDAO.copyDetail(oldInvtId, newInvtId, oldKeyId, newKeyId, user);
    }
}
