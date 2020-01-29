package dream.invt.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dao.InvtItemsDetailDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.invt.list.service.InvtDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.service.MaWoReqDetailService;
import dream.req.work.service.MaWoReqResDetailService;

/**
 * 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: InvtDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="invtDetailServiceTarget"
 * @spring.txbn id="invtDetailService"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 * @spring.property name="invtItemsDetailDAO" ref="invtItemsDetailDAO"
 * @spring.property name="maWoReqResDetailService" ref="maWoReqResDetailService"
 */
public class InvtDetailServiceImpl implements InvtDetailService
{
    private InvtDetailDAO invtDetailDAO = null;
    private MaWoReqResDetailService maWoReqResDetailService = null;
    private InvtItemsDetailDAO invtItemsDetailDAO = null;
    
    public InvtItemsDetailDAO getInvtItemsDetailDAO() {
        return invtItemsDetailDAO;
    }

    public void setInvtItemsDetailDAO(InvtItemsDetailDAO invtItemsDetailDAO) {
        this.invtItemsDetailDAO = invtItemsDetailDAO;
    }

    public MaWoReqResDetailService getMaWoReqResDetailService() {
        return maWoReqResDetailService;
    }

    public void setMaWoReqResDetailService(MaWoReqResDetailService maWoReqResDetailService) {
        this.maWoReqResDetailService = maWoReqResDetailService;
    }

    public InvtDetailDAO getInvtDetailDAO() {
        return invtDetailDAO;
    }

    public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO) {
        this.invtDetailDAO = invtDetailDAO;
    }

    public InvtDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception
    {
        return invtDetailDAO.findDetail(invtCommonDTO, user);
    }
    
    public int updateDetail(InvtDetailDTO invtDetailDTO, User user) throws Exception
    {        
        this.insertPhase(invtDetailDTO, user);
        
        return invtDetailDAO.updateDetail(invtDetailDTO, user);
    }

    public int insertDetail(InvtDetailDTO invtDetailDTO, User user) throws Exception
    {        
        invtDetailDAO.insertDetail(invtDetailDTO, user);
        
        //요청번호가 있으면 TAWOREQRES 생성 
        if(!"".equals(invtDetailDTO.getWoReqId()))
        {
            MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
            maWoReqResDetailDTO.setWoReqResId(invtDetailDAO.getNextSequence("SQAWOREQRES_ID"));
            maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd"));
            // 투자가 만들어지면 작성중 상태 -> 작성중일때는 WORES_STATUS가 REV
            maWoReqResDetailDTO.setResStatusId("REV");
            maWoReqResDetailDTO.setDeptId(invtDetailDTO.getDeptId());
            maWoReqResDetailDTO.setEmpId(invtDetailDTO.getEmpId());
            maWoReqResDetailDTO.setResponse(invtDetailDTO.getDescription());
            maWoReqResDetailDTO.setWoreqresMethod("INVT");
            maWoReqResDetailDTO.setInvtlistId(invtDetailDTO.getInvtlistId());
            maWoReqResDetailDTO.setWoReqGenType("REQ");
            
            MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
            maWoReqCommonDTO.setWoReqId(invtDetailDTO.getWoReqId());
            maWoReqCommonDTO.setCompNo(invtDetailDTO.getCompNo());
            
            maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
            
            // 요청 상태 체크 후 상태 변경
            MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", user);
            maWoReqCommonDTO.setWoReqResId(maWoReqResDetailDTO.getWoReqResId());
            maWoReqDetailService.checkStatus(maWoReqCommonDTO, user);
        }
        //투자 절차
            
        return invtDetailDAO.insertPhase(invtDetailDTO, user);
    }
    
    public void insertPhase(InvtDetailDTO invtDetailDTO, User user)
    {
        InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
        invtCommonDTO.setInvtlistId(invtDetailDTO.getInvtlistId());
        
        InvtDetailDTO currentDTO = invtDetailDAO.findDetail(invtCommonDTO, user);
        
        if("W".equals(currentDTO.getInvtlistStatus()) && !(invtDetailDTO.getInvtprctpId()).equals(currentDTO.getInvtprctpId()))
        {
            invtDetailDAO.deletePhase(invtDetailDTO, user);
            
            invtDetailDAO.insertPhase(invtDetailDTO, user);
        }
    }
    
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user)
    {
        /**
         * apprType : INVTLIST
         * apprStatus : C(결재완료), D(결재반려)
         * objectId : KEY ID
         */
        
        invtDetailDAO.setStatus(appReqDetailDTO, user);
    }

    public String copyDetail(InvtCommonDTO invtCommonDTO, InvtDetailDTO invtDetailDTO, User user) throws Exception 
    {
        String oldInvtId = invtDetailDTO.getOldInvtlistId();
        String newInvtId = invtDetailDTO.getInvtlistId();
        
        invtDetailDTO.setInvtlistId(newInvtId);
        invtDetailDAO.insertPhase(invtDetailDTO, user);
        invtDetailDTO.setOldInvtlistId(oldInvtId);
        
        invtItemsDetailDAO.copyDetail(oldInvtId, newInvtId, "", "", user);
        
        return invtDetailDAO.copyDetail(invtCommonDTO, invtDetailDTO, user);
    }

    @Override
    public int cancelStatus(InvtDetailDTO invtDetailDTO, User user) throws Exception {
        // 투자진행 마지막절차 : 완료 -> 대기
        invtDetailDAO.cancelInvtPhaseStatus(invtDetailDTO, user);
        
        // 투자계획 상태 : 완료 -> 진행
        return invtDetailDAO.cancelInvtStatus(invtDetailDTO, user);
    }

    @Override
    public void confirmStatus(InvtDetailDTO invtDetailDTO, User user)
    {
        AppReqDetailDTO appReqDetailDTO = new AppReqDetailDTO();
        appReqDetailDTO.setParentStatus("P"); //완료
        appReqDetailDTO.setObjectId(invtDetailDTO.getInvtlistId());
        
        appProcess(appReqDetailDTO, user);
    }
    
    @Override
    public String changeStatus(InvtCommonDTO invtCommonDTO, User user) throws Exception
    {
        // 투자계획 상태 변경
        String status = invtDetailDAO.checkStatus(invtCommonDTO.getInvtlistId(), user);
        
        AppReqDetailDTO appReqDetailDTO = new AppReqDetailDTO();
        appReqDetailDTO.setParentStatus(status); // C : 완료, P : 진행
        appReqDetailDTO.setObjectId(invtCommonDTO.getInvtlistId());
        
        appProcess(appReqDetailDTO, user);
        
        // 작업요청접수 상태 변경
        if("".equals(invtCommonDTO.getWoReqId()) || "".equals(invtCommonDTO.getWoReqResId())) {
            List woReqList = invtDetailDAO.findReq(invtCommonDTO, user);
            for(int i=0; i<woReqList.size(); i++) {
                MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", user);
                
                MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
                Map woReq = (Map) woReqList.get(i);
                maWoReqCommonDTO.setWoReqId(woReq.get("woReqId").toString());
                maWoReqCommonDTO.setWoReqResId(woReq.get("woReqResId").toString());
                                    
                maWoReqDetailService.checkStatus(maWoReqCommonDTO, user);
            }
        }
        
        return status;
    }
}
