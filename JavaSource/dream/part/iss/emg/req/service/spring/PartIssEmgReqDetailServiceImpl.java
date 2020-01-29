package dream.part.iss.emg.req.service.spring;

import common.bean.User;
import common.util.MailUtil;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.list.service.MaPtIssEmgDetailService;
import dream.part.iss.emg.req.dao.PartIssEmgReqDetailDAO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqDetailDTO;
import dream.part.iss.emg.req.service.PartIssEmgReqDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 부품출고 - Detail Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partIssEmgReqDetailServiceTarget"
 * @spring.txbn id="partIssEmgReqDetailService"
 * @spring.property name="partIssEmgReqDetailDAO" ref="partIssEmgReqDetailDAO"
 * @spring.property name="maPtIssEmgDetailService" ref="maPtIssEmgDetailService"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class PartIssEmgReqDetailServiceImpl implements PartIssEmgReqDetailService
{
    private PartIssEmgReqDetailDAO partIssEmgReqDetailDAO = null;
    private MaPtIssEmgDetailService maPtIssEmgDetailService = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
    
    public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public MaPtIssEmgDetailService getMaPtIssEmgDetailService()
    {
        return maPtIssEmgDetailService;
    }

    public void setMaPtIssEmgDetailService(
            MaPtIssEmgDetailService maPtIssEmgDetailService)
    {
        this.maPtIssEmgDetailService = maPtIssEmgDetailService;
    }

    public PartIssEmgReqDetailDTO findIssReqDetail(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception
    {
    	return partIssEmgReqDetailDAO.findIssReqDetail(partIssEmgReqCommonDTO, user);
    }
    
    public int insertIssReqDetail(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception
    {
    	partIssEmgReqDetailDAO.insertIssReqDetail(partIssEmgReqDetailDTO, user);
    	return partIssEmgReqDetailDAO.updateReqInfo(partIssEmgReqDetailDTO, user);
    }
    
    public int updateIssReqDetail(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception
    {
    	 partIssEmgReqDetailDAO.updateIssReqDetail(partIssEmgReqDetailDTO, user);
    	 return partIssEmgReqDetailDAO.updateReqInfo(partIssEmgReqDetailDTO, user);
    }

	public PartIssEmgReqDetailDAO getPartIssEmgReqDetailDAO() {
		return partIssEmgReqDetailDAO;
	}

	public void setPartIssEmgReqDetailDAO(PartIssEmgReqDetailDAO partIssEmgReqDetailDAO) {
		this.partIssEmgReqDetailDAO = partIssEmgReqDetailDAO;
	}
	
    public int updateStatus(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception
    {
        int resultCnt = 0;
        
        resultCnt += partIssEmgReqDetailDAO.updateStatus(partIssEmgReqDetailDTO, user);
        resultCnt += partIssEmgReqDetailDAO.updateIssListStatus(partIssEmgReqDetailDTO, user);
        
        return resultCnt;
    }

    @Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        if("SI".equals(appReqDetailDTO.getParentStatus()))
        {
            PartIssEmgReqDetailDTO partIssEmgReqDetailDTO = new PartIssEmgReqDetailDTO();
            partIssEmgReqDetailDTO.setPtEmgIssReqId(appReqDetailDTO.getObjectId());
            partIssEmgReqDetailDTO.setPtEmgIssReqStatus(appReqDetailDTO.getParentStatus());
            this.updateStatus(partIssEmgReqDetailDTO, user);
        }
        else
        {
            partIssEmgReqDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }

    @Override
    public String[] issueParts(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception
    {
        String[] rtnValue = new String[5]; // 0:결과코드, 1:결과메세지, 2:생성년, 3:문서번호, 4:전기일
        rtnValue[0] = "S";
        String[] ptemgisslistIds = partIssEmgReqDetailDAO.getPtemgisslistIds(partIssEmgReqDetailDTO, user);
        MaPtIssEmgCommonDTO maPtIssEmgCommonDTO = new MaPtIssEmgCommonDTO();
        for(String ptemgisslistId:ptemgisslistIds){
            maPtIssEmgCommonDTO.setPtemgisslistId(ptemgisslistId);
            MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailService.findDetail(maPtIssEmgCommonDTO, user);
            maPtIssEmgDetailDTO.setIssueDate(maPtIssEmgDetailDTO.getIssueDate());//convertDate
            if("W".equals(maPtIssEmgDetailDTO.getPtemgissStatus()) || "X".equals(maPtIssEmgDetailDTO.getPtemgissStatus())){
                String[] tempValue = maPtIssEmgDetailService.issueComp(maPtIssEmgDetailDTO, user);
                if("E".equals(tempValue[0])) {
                    rtnValue[0] = tempValue[0];
                    rtnValue[1] += tempValue[1]+",\n";
                    rtnValue[2] += tempValue[2]+",\n";
                    rtnValue[3] += tempValue[3]+",\n";
                    rtnValue[4] += tempValue[4]+",\n";
                }
            }
        }
        partIssEmgReqDetailDTO.setPtEmgIssReqStatus("CI");//출고완료
        partIssEmgReqDetailDAO.updateStatus(partIssEmgReqDetailDTO, user);
        
        MailUtil.sendMail("Z200_ISS10", partIssEmgReqDetailDTO.getPtEmgIssReqId(), user);
        
        return rtnValue;
    }

    @Override
    public String[] issueCancelParts(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception
    {
        String[] rtnValue = new String[5]; // 0:결과코드, 1:결과메세지, 2:생성년, 3:문서번호, 4:전기일
        rtnValue[0] = "S";
        String[] ptemgisslistIds = partIssEmgReqDetailDAO.getPtemgisslistIds(partIssEmgReqDetailDTO, user);
        MaPtIssEmgCommonDTO maPtIssEmgCommonDTO = new MaPtIssEmgCommonDTO();
        for(String ptemgisslistId:ptemgisslistIds){
            maPtIssEmgCommonDTO.setPtemgisslistId(ptemgisslistId);
            MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailService.findDetail(maPtIssEmgCommonDTO, user);
            maPtIssEmgDetailDTO.setIssueDate(maPtIssEmgDetailDTO.getIssueDate());//convertDate
            if("C".equals(maPtIssEmgDetailDTO.getPtemgissStatus())){
                String[] tempValue = maPtIssEmgDetailService.issueCancel(maPtIssEmgDetailDTO, user);
                if("E".equals(tempValue[0])) {
                    rtnValue[0] = tempValue[0];
                    rtnValue[1] += tempValue[1]+",\n";
                    rtnValue[2] += tempValue[2]+",\n";
                    rtnValue[3] += tempValue[3]+",\n";
                    rtnValue[4] += tempValue[4]+",\n";
                }
            }
        }
        partIssEmgReqDetailDTO.setPtEmgIssReqStatus("X");//출고취소
        partIssEmgReqDetailDAO.updateStatus(partIssEmgReqDetailDTO, user);
        return rtnValue;
    }
}
