package dream.pers.appstb.ready.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import common.util.MailUtil;
import dream.comm.revision.service.CommRevService;
import dream.invt.list.service.InvtDetailService;
import dream.invt.list.service.InvtPrcDetailService;
import dream.note.daily.service.MaWoDailyDetailService;
import dream.part.adj.stktake.service.PartAdjStkTakeDetailService;
import dream.part.iss.emg.req.service.PartIssEmgReqDetailService;
import dream.part.pur.buy.service.MaPtBuyReqHdrDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.message.mail.service.PersAppstbMessageMailService;
import dream.pers.appstb.ready.dao.AppReadyListDAO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;
import dream.pers.appstb.ready.service.AppReadyListService;
import dream.req.work.service.ReqWorkDetailService;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprDetailService;
import dream.work.cal.pmyear.service.MaWoYearSchedListService;
import dream.work.fmea.list.service.WorkFmeaDetailService;
import dream.work.list.service.MaWoResultMstrDetailService;
import dream.work.list.service.WoPlanDetailService;
import dream.work.planappr.service.WorkPlanApprDetailService;
import dream.work.pm.list.service.MaPmMstrDetailService;
import dream.work.pmi.list.service.WorkPmiDetailService;

/**
 * ���
 * @author javaworker
 * @version $Id: AppReadyListServiceImpl.java,v 1.1 2013/08/30 09:12:10 javaworker Exp $
 * @since 1.0
 * 
 * @spring.bean id="appReadyListServiceTarget"
 * @spring.txbn id="appReadyListService"
 * @spring.property name="appReadyListDAO" ref="appReadyListDAO"
 * @spring.property name="maWoDailyDetailService" ref="maWoDailyDetailService"
 * @spring.property name="invtPrcDetailService" ref="invtPrcDetailService"
 * @spring.property name="invtDetailService" ref="invtDetailService"
 * @spring.property name="reqWorkDetailService" ref="reqWorkDetailService"
 * @spring.property name="maPtBuyReqHdrDetailService" ref="maPtBuyReqHdrDetailService"
 * @spring.property name="partAdjStkTakeDetailService" ref="partAdjStkTakeDetailService"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="woPlanDetailService" ref="woPlanDetailService"
 * @spring.property name="workFmeaDetailService" ref="workFmeaDetailService"
 * @spring.property name="persAppstbMessageMailService" ref="persAppstbMessageMailService"
 * @spring.property name="partIssEmgReqDetailService" ref="partIssEmgReqDetailService"
 * @spring.property name="workCalPmInsApprDetailService" ref="workCalPmInsApprDetailService"
 * @spring.property name="workPlanApprDetailService" ref="workPlanApprDetailService"
 * @spring.property name="commRevService" ref="commRevService"
 * @spring.property name="workPmiDetailService" ref="workPmiDetailService"
 * @spring.property name="maPmMstrDetailService" ref="maPmMstrDetailService"
 */
public class AppReadyListServiceImpl implements AppReadyListService
{
    /** ���DAO */
    private AppReadyListDAO appReadyListDAO = null;
    
    /** �����۾�����Ȯ�� */
    private MaWoDailyDetailService maWoDailyDetailService = null;
    /** ��������Ȯ�� */
    private InvtPrcDetailService invtPrcDetailService = null;
    /** ���ڸ��Ȯ�� */
    private InvtDetailService invtDetailService = null;
    /** �۾���ûȮ�� */
    private ReqWorkDetailService reqWorkDetailService = null;
    /** ���Ž�ûȮ�� */
    private MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = null;
    /** ��ǰ�ǻ�Ȯ�� */
    private PartAdjStkTakeDetailService partAdjStkTakeDetailService = null;
    /** �۾�����Ȯ�� */
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    /** �۾���ȹȮ�� */
    private WoPlanDetailService woPlanDetailService = null;
    /** ���念�⼺��Ȯ�� */
    private WorkFmeaDetailService workFmeaDetailService = null;
    /** ����ÿ� ���ϸ� ����*/
    private PersAppstbMessageMailService persAppstbMessageMailService = null;
    /** ����ûȮ�� */
    private PartIssEmgReqDetailService partIssEmgReqDetailService = null;
    /** �������˰�ȹ���� */
    private WorkCalPmInsApprDetailService workCalPmInsApprDetailService = null;
    /** �۾���ȹ���� */
    private WorkPlanApprDetailService workPlanApprDetailService = null;
    /** �簳�� */
    private CommRevService commRevService = null;
    /** ���˽ǽ� */
    private WorkPmiDetailService workPmiDetailService = null;
    /** �����ֱ⼳�� */
    private MaPmMstrDetailService maPmMstrDetailService = null;
    
    public MaPmMstrDetailService getMaPmMstrDetailService() {
		return maPmMstrDetailService;
	}

	public void setMaPmMstrDetailService(MaPmMstrDetailService maPmMstrDetailService) {
		this.maPmMstrDetailService = maPmMstrDetailService;
	}

	public WorkPmiDetailService getWorkPmiDetailService() {
		return workPmiDetailService;
	}

	public void setWorkPmiDetailService(WorkPmiDetailService workPmiDetailService) {
		this.workPmiDetailService = workPmiDetailService;
	}

	public CommRevService getCommRevService()
    {
        return commRevService;
    }

    public void setCommRevService(CommRevService commRevService)
    {
        this.commRevService = commRevService;
    }

    public WorkPlanApprDetailService getWorkPlanApprDetailService() {
		return workPlanApprDetailService;
	}

	public void setWorkPlanApprDetailService(WorkPlanApprDetailService workPlanApprDetailService) {
		this.workPlanApprDetailService = workPlanApprDetailService;
	}

	public WorkCalPmInsApprDetailService getWorkCalPmInsApprDetailService() {
		return workCalPmInsApprDetailService;
	}

	public void setWorkCalPmInsApprDetailService(WorkCalPmInsApprDetailService workCalPmInsApprDetailService) {
		this.workCalPmInsApprDetailService = workCalPmInsApprDetailService;
	}

	public WoPlanDetailService getWoPlanDetailService()
    {
        return woPlanDetailService;
    }

    public void setWoPlanDetailService(WoPlanDetailService woPlanDetailService)
    {
        this.woPlanDetailService = woPlanDetailService;
    }

    public PartIssEmgReqDetailService getPartIssEmgReqDetailService()
    {
        return partIssEmgReqDetailService;
    }

    public void setPartIssEmgReqDetailService(
            PartIssEmgReqDetailService partIssEmgReqDetailService)
    {
        this.partIssEmgReqDetailService = partIssEmgReqDetailService;
    }

    public PersAppstbMessageMailService getPersAppstbMessageMailService() {
		return persAppstbMessageMailService;
	}

	public void setPersAppstbMessageMailService(
			PersAppstbMessageMailService persAppstbMessageMailService) {
		this.persAppstbMessageMailService = persAppstbMessageMailService;
	}

	public WorkFmeaDetailService getWorkFmeaDetailService()
    {
        return workFmeaDetailService;
    }

    public void setWorkFmeaDetailService(
            WorkFmeaDetailService workFmeaDetailService)
    {
        this.workFmeaDetailService = workFmeaDetailService;
    }

    public MaWoResultMstrDetailService getMaWoResultMstrDetailService()
    {
        return maWoResultMstrDetailService;
    }

    public void setMaWoResultMstrDetailService(
            MaWoResultMstrDetailService maWoResultMstrDetailService)
    {
        this.maWoResultMstrDetailService = maWoResultMstrDetailService;
    }

    public PartAdjStkTakeDetailService getPartAdjStkTakeDetailService()
    {
        return partAdjStkTakeDetailService;
    }

    public void setPartAdjStkTakeDetailService(
            PartAdjStkTakeDetailService partAdjStkTakeDetailService)
    {
        this.partAdjStkTakeDetailService = partAdjStkTakeDetailService;
    }

    public MaPtBuyReqHdrDetailService getMaPtBuyReqHdrDetailService()
    {
        return maPtBuyReqHdrDetailService;
    }

    public void setMaPtBuyReqHdrDetailService(
            MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService)
    {
        this.maPtBuyReqHdrDetailService = maPtBuyReqHdrDetailService;
    }

    public ReqWorkDetailService getReqWorkDetailService()
    {
        return reqWorkDetailService;
    }

    public void setReqWorkDetailService(ReqWorkDetailService reqWorkDetailService)
    {
        this.reqWorkDetailService = reqWorkDetailService;
    }

    public InvtDetailService getInvtDetailService()
    {
        return invtDetailService;
    }

    public void setInvtDetailService(InvtDetailService invtDetailService)
    {
        this.invtDetailService = invtDetailService;
    }

    public InvtPrcDetailService getInvtPrcDetailService()
    {
        return invtPrcDetailService;
    }

    public void setInvtPrcDetailService(InvtPrcDetailService invtPrcDetailService)
    {
        this.invtPrcDetailService = invtPrcDetailService;
    }

    public MaWoDailyDetailService getMaWoDailyDetailService()
    {
        return maWoDailyDetailService;
    }

    public void setMaWoDailyDetailService(
            MaWoDailyDetailService maWoDailyDetailService)
    {
        this.maWoDailyDetailService = maWoDailyDetailService;
    }

    public AppReadyListDAO getAppReadyListDAO()
    {
        return appReadyListDAO;
    }

    public void setAppReadyListDAO(AppReadyListDAO appReadyListDAO)
    {
        this.appReadyListDAO = appReadyListDAO;
    }
    
    public List findAppReadyList(AppReadyCommonDTO appReadyCommonDTO, User user)
    {
        return appReadyListDAO.findList(appReadyCommonDTO, user);
    }

    public int deleteLine(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + appReadyListDAO.deleteLine(id,compNo);
            }
        return result;
    }

    
    public int approveLine(String[] apprusrIds, String[] apprlistIds, User user, AppReadyCommonDTO appReadyCommonDTO) throws Exception
    {
        int result = 0;
        String apprUserType = "";
        if(!apprusrIds.equals(null))
        {
            for(String apprusrId : apprusrIds)
            {
            	//������ ����Ÿ�� �������� Ȯ���ϰ� ������ ��츸 ó��. ����Ȯ�ΰ� �ݷ��� ó�� ���� ����.[AP:������   RF:������  DF:�����]
            	apprUserType =  appReadyListDAO.checkApprUsrType(apprusrId, user);

            	if("AP".equals(apprUserType)){ 
            		//apprusr_action : [P:ó����� -> C:ó���Ϸ�] ����
                    //apprusr_status : [Z:���  -> C:���� ] ����
            		appReadyListDAO.approveLine(apprusrId,  user, "C", "C", appReadyCommonDTO);
            		
            		//0.�ڽŰ� ������ ������ ���� �������� �ʴ� ����ڰ� �������� �ʾƾ� ó���ϰ� ���İ����ڰ� �����Ѵٸ� skip�Ѵ�.
            		if("0".equals(appReadyListDAO.isParalApprUser(apprusrId, user))){
            			
            			//1. ���������������� �����ڰ� �ִٸ� �� �����ڴ� ��� ��� ���·� ����ó��.
            			appReadyListDAO.updateReferenceLine(apprusrId, user);
            			
            			//2.���������ڸ� ������ �� ����ó��.
            			int rstCnt = appReadyListDAO.updateApprovalLine(apprusrId, user);
            			
            			//3. ���������ڰ� ���ٸ� ��������Ϸ�[C] ������.  ���������ڰ� �ִٸ� ���� ���� ������[P]��.
            			if(rstCnt == 0){
            				appReadyListDAO.updateApproveList(apprusrId,  user, "C");
            			}else{
            				appReadyListDAO.updateApproveList(apprusrId,  user, "P");
            			}
            			
            		}
            		
            		//4. ������¿� ���� ���� ���µ� ����ó����.
            		AppReqDetailDTO appReqDetailDTO = appReadyListDAO.findListDetail(apprusrId, user);
                    apprProcess(appReqDetailDTO, user);
                    
                    
            	}
            }
        }
        
        return result;
        
        
    }
    
    
    public int referenceLine(String[] apprusrIds, String[] apprlistIds, User user, AppReadyCommonDTO appReadyCommonDTO) throws Exception
    {
    	
    	int result = 0;
        String apprUserType = "";
        if(!apprusrIds.equals(null))
        {
            for(String apprusrId : apprusrIds)
            {
            	//������ ����Ÿ�� �������� Ȯ���ϰ� ������ ��츸 ó��. ����,�ݷ��ÿ��� ó�� ���� ����.[AP:������   RF:������  DF:�����]
            	apprUserType =  appReadyListDAO.checkApprUsrType(apprusrId, user);

            	if("RF".equals(apprUserType)){ 
            		//apprusr_action : [P:ó����� -> C:ó���Ϸ�] ����
                    //apprusr_status : [Z:���  -> R:Ȯ�� ] ����
            		appReadyListDAO.approveLine(apprusrId,  user, "C", "R", appReadyCommonDTO);
            		
            	}
            }
        }
    	
    	return result;
    	
    	
    }

    public int rejectLine(String[] apprusrIds, String[] apprlistIds, User user, AppReadyCommonDTO appReadyCommonDTO) throws Exception
    {
        int result = 0;
        String apprUserType = "";
        if(!apprusrIds.equals(null))
        {
            for(String apprusrId : apprusrIds)
            {
                
            	//������ ����Ÿ�� �������� Ȯ���ϰ� ������ ��츸 ó��. ����,�ݷ��ÿ��� ó�� ���� ����.[AP:������   RF:������  DF:�����]
            	apprUserType =  appReadyListDAO.checkApprUsrType(apprusrId, user);
            	if("AP".equals(apprUserType)){ 
            		//apprusr_action : [P:ó����� -> C:ó���Ϸ�] ����
                    //apprusr_status : [Z:���  -> D:�ݷ� ] ����
            		appReadyListDAO.approveLine(apprusrId,  user, "C", "D", appReadyCommonDTO);
            		
            		//1. ���� �������̰ų� ���簡 �������� ��� ������ ���ؼ� �ݷ�ó���� ������. �ݷ��̸� ������������ ����Ÿ�� �Ϸ�Ǹ� ���� ���簡 ������� �ʴ� ������� ����� ��� ���� ó����.
            		appReadyListDAO.updateRejectList(apprusrId, user);
            		
            		appReadyListDAO.updateApproveList(apprusrId,  user, "D");
            		
            		//2. ������¿� ���� ���� ���µ� ����ó����.
            		AppReqDetailDTO appReqDetailDTO = appReadyListDAO.findListDetail(apprusrId, user);
                    apprProcess(appReqDetailDTO, user);
                    
            	}
            }
           
        }
        return result;
    }
    
    
    
    
    
    
    
    public void apprProcess(final AppReqDetailDTO appReqDetailDTO, final User user) throws Exception
    {
        String apprType = appReqDetailDTO.getApprType();
        String apprStatus = appReqDetailDTO.getApprStatus(); //W:�ۼ���, R:�����û, P:��������, C:����Ϸ�, D:����ݷ�
        String parentStatus = "";
        
        String jsonParam = appReqDetailDTO.getJsonParam();

        if("WODAY".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WODAYLIST_STATUS
            {
                case "W":
                    parentStatus = "";      //�ۼ��� -> �̷����� ������ ����.
                    break;
                case "R":
                    parentStatus = "WRA";      //�����û -> �̷����� ������ ����.
                    break;
                case "P":   
                    parentStatus = "WOA";      //��������
                    break;
                case "C":
                    parentStatus = "C";      //����Ϸ�
                    break;
                case "D":
                    parentStatus = "WDA";      //����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            maWoDailyDetailService.appProcess(appReqDetailDTO, user);
        }
        if("INVTPRC".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> INVTPHASE_STATUS
            {
                case "W":
                    parentStatus = "";      //���
                    break;
                case "R":
                    parentStatus = "RA";      //�����û
                    break;
                case "P":   
                    parentStatus = "OA";      //������
                    break;
                case "C":
                    parentStatus = "C";      //�Ϸ�
                    break;
                case "D":
                    parentStatus = "DA";      //����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            invtPrcDetailService.appProcess(appReqDetailDTO, user);
        }
        
        if("INVTLIST".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> INVTLIST_STATUS
            {
                case "W":
                    parentStatus = "";      //�ۼ�
                    break;
                case "R":
                    parentStatus = "RA";      //�����û
                    break;
                case "P":   
                    parentStatus = "OA";      //������
                    break;
                case "C":
                    parentStatus = "P";      //����
                    break;
                case "D":
                    parentStatus = "DA";      //����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            InvtDetailService  invtDservice = (InvtDetailService) CommonUtil.getBean("invtDetailService", user);
            invtDservice.appProcess(appReqDetailDTO, user);
        }
        if("REQWORK".equals(apprType)) //�۾���û..
        {
            switch(apprStatus)              //APPR_STATUS -> WOREQ_STATUS
            {
                case "W":
                    parentStatus = "";      //�ۼ�
                    break;
                case "R":
                    parentStatus = "WRTRA";      //��û�� �����û
                    break;
                case "P":   
                    parentStatus = "WRTOA";      //��û�� ������
                    break;
                case "C":
                    parentStatus = "REQ";      //��û
                    break;
                case "D":
                    parentStatus = "WRTDA";      //��û�� ����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            reqWorkDetailService.appProcess(appReqDetailDTO, user);
        }
        
        if("REQINVTWORK".equals(apprType)) //���ڿ�û..
        {
        	switch(apprStatus)              //APPR_STATUS -> WOREQ_STATUS
        	{
        	case "W":
        		parentStatus = "";      //�ۼ�
        		break;
        	case "R":
        		parentStatus = "WRTRA";      //��û�� �����û
        		break;
        	case "P":   
        		parentStatus = "WRTOA";      //��û�� ������
        		break;
        	case "C":
        		parentStatus = "REQ";      //��û
        		break;
        	case "D":
        		parentStatus = "WRTDA";      //��û�� ����ݷ�
        		break;
        	default:
        		parentStatus = apprStatus;
        		break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);
        	reqWorkDetailService.appProcess(appReqDetailDTO, user);
        }
        
        //���Ž�û 
        if("PTBUYREQ".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> PTPRLIST_STATUS
            {
                case "W":
                    parentStatus = "";      //�ۼ�
                    break;
                case "R":
                    parentStatus = "WRA";      //�����û
                    break;
                case "P":   
                    parentStatus = "WOA";      //������
                    break;
                case "C":
                    parentStatus = "C";      //��û�Ϸ�
                    break;
                case "D":
                    parentStatus = "WDA";      //����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            maPtBuyReqHdrDetailService.appProcess(appReqDetailDTO, user);
        }
        if("PTSTKTAKE".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> PTSTKTAKE_STATUS
            {
                case "W":
                    parentStatus = "";      //�ۼ�
                    break;
                case "R":
                    parentStatus = "WRA";      //�����û
                    break;
                case "P":   
                    parentStatus = "WOA";      //������
                    break;
                case "C":
                    parentStatus = "C";      //�ǻ�Ȯ��
                    break;
                case "D":
                    parentStatus = "WDA";      //����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            partAdjStkTakeDetailService.appProcess(appReqDetailDTO, user);
        }
        if("WORKORDER".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WO_STATUS
            {
                case "W":
                    parentStatus = "";      //�ۼ�
                    break;
                case "R":
                    parentStatus = "PRWRA";      //��� �����û
                    break;
                case "P":   
                    parentStatus = "PRWOA";      //��� ������
                    break;
                case "C":
                    parentStatus = "C";             //�۾��Ϸ�
                    break;
                case "D":
                    parentStatus = "PRWDA";      //��� ����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            MaWoResultMstrDetailService wrms = (MaWoResultMstrDetailService)CommonUtil.getBean("maWoResultMstrDetailService", user);
            MaWoYearSchedListService wyss = (MaWoYearSchedListService)CommonUtil.getBean("maWoYearSchedListService", user);
            wrms.appProcess(appReqDetailDTO, user);
            wyss.apprProcess(appReqDetailDTO, user);
        }
        
        if("WOPLAN".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WO_STATUS
            {
                case "W":
                    parentStatus = "";      	 //�ۼ�
                    break;
                case "R":
                    parentStatus = "PPWRA";      //�۾���ȹ �����û
                    break;
                case "P":   
                    parentStatus = "PPWOA";      //�۾���ȹ ������
                    break;
                case "C":
                    parentStatus = "PPC";        //�۾���ȹ�Ϸ�
                    break;
                case "D":
                    parentStatus = "PPWDA";      //�۾���ȹ ����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            woPlanDetailService.appProcess(appReqDetailDTO, user);
        }
        
        if("WORKFMEA".equals(apprType))
        {
            String fmeaStatus = workFmeaDetailService.getStatus(appReqDetailDTO, user);
            if(/**"WRT".equals(fmeaStatus) || */"WRTRA".equals(fmeaStatus) || "WRTOA".equals(fmeaStatus) || "WRTDA".equals(fmeaStatus)) {
                switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
                {
                    case "W":
                        parentStatus = "";      //�ۼ�
                        break;
                    case "R":
                        parentStatus = "WRTRA";      //�ۼ� �����û
                        break;
                    case "P":   
                        parentStatus = "WRTOA";      //�ۼ� ������
                        break;
                    case "C":
                        parentStatus = "CCA";       //������:RCA
                        break;
                    case "D":
                        parentStatus = "WRTDA";      //�ۼ� ����ݷ�
                        break;
                    default:
                        parentStatus = apprStatus;
                        break;
                }
            }
            else {
                switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
                {
                    case "W":
                        parentStatus = "";      //�ۼ�
                        break;
                    case "R":
                        parentStatus = "RCARA";      //���� �����û
                        break;
                    case "P":   
                        parentStatus = "RCAOA";      //���� ������
                        break;
                    case "C":
                        parentStatus = "CCA";             //����Ϸ�
                        break;
                    case "D":
                        parentStatus = "RCADA";      //���� ����ݷ�
                        break;
                    default:
                        parentStatus = apprStatus;
                        break;
                }
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            WorkFmeaDetailService wrms = (WorkFmeaDetailService)CommonUtil.getBean("workFmeaDetailService", user);
            wrms.appProcess(appReqDetailDTO, user);
        }
        
        if("PTISSREQ".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
            {
                case "W":
                    parentStatus = "";      //�ۼ���
                    break;
                case "R":
                    parentStatus = "RA";      //�����û
                    break;
                case "P":   
                    parentStatus = "OA";      //������
                    break;
                case "C":
                    parentStatus = "SI";       //�����
                    break;
                case "D":
                    parentStatus = "DA";      //����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            partIssEmgReqDetailService.appProcess(appReqDetailDTO, user);
        }
        //�������˰�ȹ����
        if("PMINSSCHED".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
        	{
        	case "W":
        		parentStatus = "";      //�ۼ���
        		break;
        	case "R":
        		parentStatus = "RA";      //�����û
        		break;
        	case "P":   
        		parentStatus = "OA";      //������
        		break;
        	case "D":
        		parentStatus = "DA";      //����ݷ�
        		break;
        	case "C":
        		parentStatus = "C";       //����Ϸ�
        		break;
        	default:
        		parentStatus = apprStatus;
        		break;
        	}
        	
        	WorkCalPmInsApprDetailService workCalPmInsApprDetailService2 = (WorkCalPmInsApprDetailService)CommonUtil.getBean("workCalPmInsApprDetailService", user);
        	appReqDetailDTO.setParentStatus(parentStatus);
        	workCalPmInsApprDetailService2.appProcess(appReqDetailDTO, user);
        }
        
        //�۾���ȹ����
        if("WOPLANAPPR".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> WOPLANAPPR_STATUS
        	{
        	case "W":
                parentStatus = "";      	 //�ۼ�
                break;
            case "R":
                parentStatus = "PPWRA";      //�۾���ȹ �����û
                break;
            case "P":   
                parentStatus = "PPWOA";      //�۾���ȹ ������
                break;
            case "C":
                parentStatus = "PPC";        //�۾���ȹ�Ϸ�
                break;
            case "D":
                parentStatus = "PPWDA";      //�۾���ȹ ����ݷ�
                break;
            default:
                parentStatus = apprStatus;
                break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);
        	workPlanApprDetailService.appProcess(appReqDetailDTO, user);
        }
        
        //���񸶽��� ������ �Ϸ�
        if("EQUIPREV".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> REVISION_STATUS
        	{
        	case "W":
        		parentStatus = "W";      //�ۼ���
        		break;
        	case "R":
        		parentStatus = "RA";      //�����û
        		break;
        	case "P":   
        		parentStatus = "PA";      //������
        		break;
        	case "C":
        		parentStatus = "C";       //����Ϸ�
        		break;
        	case "D":
        		parentStatus = "DA";      //����ݷ�
        		break;
        	default:
        		parentStatus = apprStatus;
        		break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);    
        	CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
        	crs.appProcess(appReqDetailDTO, user, parentStatus, "ASSET");
        }
        
        //�����⸶���� ������ �Ϸ�
        if("EQTLREV".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> REVISION_STATUS
        	{
        	case "W":
        		parentStatus = "W";      //�ۼ���
        		break;
        	case "R":
        		parentStatus = "RA";      //�����û
        		break;
        	case "P":   
        		parentStatus = "PA";      //������
        		break;
        	case "C":
        		parentStatus = "C";       //����Ϸ�
        		break;
        	case "D":
        		parentStatus = "DA";      //����ݷ�
        		break;
        	default:
        		parentStatus = apprStatus;
        		break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);    
        	CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
        	crs.appProcess(appReqDetailDTO, user, parentStatus, "ASSET");
        }
        
        //���񸶽��� ���� �Ϸ�
        if("REVEQ".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
            {
                case "W":
                    parentStatus = "W";      //�ۼ���
                    break;
                case "R":
                    parentStatus = "R";      //�����û
                    break;
                case "P":   
                    parentStatus = "PR";      //������
                    break;
                case "C":
                    parentStatus = "C";       //�����
                    break;
                case "D":
                    parentStatus = "D";      //����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
            crs.appProcess(appReqDetailDTO, user, "W", "ASSET");
        }
        
        //���񸶽��� ���� �Ϸ�
        if("REVREQ".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
            {
                case "W":
                    parentStatus = "W";      //�ۼ���
                    break;
                case "R":
                    parentStatus = "R";      //�����û
                    break;
                case "P":   
                    parentStatus = "PR";      //������
                    break;
                case "C":
                    parentStatus = "C";       //�����
                    break;
                case "D":
                    parentStatus = "D";      //����ݷ�
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
            crs.appProcess(appReqDetailDTO, user, "P", "ASSET");
        }

        // ���˽ǽ� �Ϸ�
        if("PMINSRSLT".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> PMSCHED_STATUS
            {
                case "W":
                    parentStatus = "";      //�ۼ�
                    break;
                case "R":
                    parentStatus = "IRWRA";      // �����û
                    break;
                case "P":   
                    parentStatus = "IRWOA";      // ������
                    break;
                case "D":
                    parentStatus = "IRWDA";      // ����ݷ�
                    break;
                case "C":
                	parentStatus = "C";      	// �۾��Ϸ�
                	break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            WorkPmiDetailService wps = (WorkPmiDetailService)CommonUtil.getBean("workPmiDetailService", user);
            wps.appProcess(appReqDetailDTO, user);
        }
        
        // �����ֱ⼳�� ������ �Ϸ�
        if("PMINSREV".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> REVISION_STATUS
        	{
        	case "W":
        		parentStatus = "W";      //�ۼ���
        		break;
        	case "R":
        		parentStatus = "RA";      //�����û
        		break;
        	case "P":   
        		parentStatus = "PA";      //������
        		break;
        	case "C":
        		parentStatus = "C";       //����Ϸ�
        		break;
        	case "D":
        		parentStatus = "DA";      //����ݷ�
        		break;
    		default:
        		parentStatus = apprStatus;
        		break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);
        	CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
        	crs.appProcess(appReqDetailDTO, user, parentStatus, "PMSTD");
        }
        
        MailUtil.sendMail("APP", appReqDetailDTO.getApprlistId(), user);
    }

	@Override
	public String findTotalCount(AppReadyCommonDTO appReadyCommonDTO, User user)
	{
		return appReadyListDAO.findTotalCount(appReadyCommonDTO, user);
	}
}