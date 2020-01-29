package dream.req.work.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.MailUtil;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.req.work.dao.MaWoReqDetailDAO;
import dream.req.work.dao.MaWoReqResDetailDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.req.work.service.MaWoReqDetailService;
import dream.work.alarm.req.dto.WorkAlarmReqDTO;
import dream.work.alarm.req.service.WorkAlarmReqService;

/**
 * 작업요청서 - 상세 serviceimpl  
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoReqDetailServiceTarget"
 * @spring.txbn id="maWoReqDetailService"
 * @spring.property name="maWoReqDetailDAO" ref="maWoReqDetailDAO"
 * @spring.property name="maWoReqResDetailDAO" ref="maWoReqResDetailDAO"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class MaWoReqDetailServiceImpl implements MaWoReqDetailService
{
    private MaWoReqDetailDAO maWoReqDetailDAO = null;
    private MaWoReqResDetailDAO maWoReqResDetailDAO = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
    
    public MaWoReqResDetailDAO getMaWoReqResDetailDAO()
    {
        return maWoReqResDetailDAO;
    }

    public void setMaWoReqResDetailDAO(MaWoReqResDetailDAO maWoReqResDetailDAO)
    {
        this.maWoReqResDetailDAO = maWoReqResDetailDAO;
    }

    public MaWoReqDetailDAO getMaWoReqDetailDAO() 
    {
        return maWoReqDetailDAO;
    }

    public void setMaWoReqDetailDAO(MaWoReqDetailDAO maWoReqDetailDAO) 
    {
        this.maWoReqDetailDAO = maWoReqDetailDAO;
    }

    public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public MaWoReqDetailDTO findDetail(MaWoReqCommonDTO maWoReqCommonDTO, User loginUser)throws Exception
    {

        return maWoReqDetailDAO.findDetail(maWoReqCommonDTO, loginUser);
    }
    
    public List findWoRecReport(MaWoReqDetailDTO maWoReqDetailDTO, User user) {
        Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        
        List detailList = maWoReqDetailDAO.findWoRecReport(maWoReqDetailDTO,user);
        reportMap = (Map)detailList.get(0);
        reportList.add((Map)reportMap);
         return reportList;
    }
    
    public int updateDetail(final MaWoReqDetailDTO maWoReqDetailDTO, final User user) throws Exception
    {   
        int resultCnt = 0;
        String oldRecBy = maWoReqDetailDAO.getRecBy(maWoReqDetailDTO, user);
        String newRecBy = maWoReqDetailDTO.getRecEmpId();
        boolean isChangedRecBy = false;
        if(!"".equals(newRecBy)&&!oldRecBy.equals(newRecBy)){
            isChangedRecBy = true;
        }
        
        resultCnt = maWoReqDetailDAO.updateDetail(maWoReqDetailDTO,user);
        
        if ("Y".equals(MwareConfig.getIsUseMailService())&&isChangedRecBy){
            //변경된 작업처리자에게 메일 보내기.
            MailUtil.sendMail("RQC20", maWoReqDetailDTO.getWoReqId(), user);
        }
        
        return resultCnt;
    }
    public int insertDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception
    {   
        int resultCnt = 0;
        resultCnt = maWoReqDetailDAO.insertDetail(maWoReqDetailDTO, user);
        
        // 설비Alarm 에서 작업요청 신규 생성 할 경우(TAALARAMREQ에 INSERT)
        if(!"".equals(maWoReqDetailDTO.getAlarmListId()) || maWoReqDetailDTO.getAlarmListId() != null) {
        	WorkAlarmReqService workAlarmReqService = (WorkAlarmReqService)CommonUtil.getBean("workAlarmReqService", user);
        	WorkAlarmReqDTO workAlarmReqDTO = new WorkAlarmReqDTO();
        	
        	workAlarmReqDTO.setAlarmListId(maWoReqDetailDTO.getAlarmListId());
        	workAlarmReqDTO.setWoReqId(maWoReqDetailDTO.getWoReqId());
        	
        	workAlarmReqService.insertDetail(workAlarmReqDTO, user);
        }
        
        return resultCnt;
    }

    @Override
    public int updateStatus(final MaWoReqDetailDTO maWoReqDetailDTO, final User user) throws Exception
    {
        int resultCnt = 0;
        resultCnt = maWoReqDetailDAO.updateStatus(maWoReqDetailDTO,user);
        
        MailUtil.sendMail("RQC10", maWoReqDetailDTO.getWoReqId(), user);
        
        return resultCnt;
    }
    @Override
    public int updateIncStatus(final MaWoReqDetailDTO maWoReqDetailDTO, final User user) throws Exception
    {
        int resultCnt = 0;
        resultCnt = maWoReqDetailDAO.updateIncStatus(maWoReqDetailDTO,user);
        
        MailUtil.sendMail("RQC10", maWoReqDetailDTO.getWoReqId(), user);
        
        return resultCnt;
    }
    
    @Override
    public int updateReqStatus(final MaWoReqDetailDTO maWoReqDetailDTO, final User user) throws Exception
    {
        int resultCnt = 0;
        resultCnt = maWoReqDetailDAO.updateReqStatus(maWoReqDetailDTO, user);

        return resultCnt;
    }
    
    @Override
    public String checkValidRecDept(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception
    {
        return maWoReqDetailDAO.checkValidRecDept(maWoReqDetailDTO, user);
    }

    @Override
    public String checkStatus(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception
    {
        String status = "";
        int chkCount = chkExistCnt(maWoReqCommonDTO, user);
        
        if(chkCount != 0) {
            status = "WRK";
        } else {
            status = "COM";
        }

        maWoReqDetailDAO.changeHdrStatus(maWoReqCommonDTO.getWoReqId(), status, user);
        
        if(!"".equals(maWoReqCommonDTO.getWoReqResId())) {
            maWoReqResDetailDAO.setWoResStatus(maWoReqCommonDTO, status, user);
        }
        
        return status;
    }
    
    @Override
    public int chkExistCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception
    {
        int chkCount = 0;
        String woReqId = maWoReqCommonDTO.getWoReqId();
        String chkWoExistCnt = maWoReqDetailDAO.chkWoExistCnt(woReqId, user, "WO");
        String chkInvtExistCnt = maWoReqDetailDAO.chkInvtExistCnt(woReqId, user, "INVT");
        
        if(!"0".equals(chkWoExistCnt) || !"0".equals(chkInvtExistCnt)) {
            int chkInvtStCnt = Integer.parseInt(maWoReqDetailDAO.chkInvtStCnt(woReqId, user, "INVT"));
            int chkWoStCnt = Integer.parseInt(maWoReqDetailDAO.chkWoStCnt(woReqId, user, "WO"));
            
            chkCount = chkInvtStCnt + chkWoStCnt;
        } else {
            chkCount = 1; 
        }
        
        return chkCount;
    }
}
